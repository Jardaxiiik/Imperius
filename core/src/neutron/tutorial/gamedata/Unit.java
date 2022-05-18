package neutron.tutorial.gamedata;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import static neutron.tutorial.gamedata.Game.gameScreen;

import java.util.ArrayList;

public class Unit {
    public Unit unitType = null;
    public ArrayList<Abilities> abilities = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>();
    public Effect[] ApplyEffectOnAttack;
    public ArrayList<Effect> deathEffects = new ArrayList<>();

    public Image icon = null;

    public Filters filters = new Filters();
    public boolean moving; //cannot be controlled

    public String typeName;
    public String unitDescrition = "Its a unit!";
    public Player owner;
    public Sector currentSector;
    public Path currentPath;
    public int count = 1;
    public int generateResource =0;

    public int movementSpeed = 0;
    public int cloak = 0;
    public int decloakStrenght = 0;
    public int sightRange = 0;
    public int attackRange = 0; // 0 -> melee

    public int damageUnscaled = 0;
    public int lifeUnscaled = 0; // Of One Unit.

    public boolean damageTechAdditive; //damage+tech = damageScaled
    public boolean damageTechMultiplicative; //damage*tech = damageScaled
    public boolean lifeTechMultiplicative; //life*tech = lifeScaled
    public boolean lifeTechAdditive; //life + tech = lifeScaled
    public double resistance = 0; // enemies deal x% less damage
    public Double damageMult = 1.00; //unit damage*tech is multiplied by x;

    public Integer[] damageToTypeFlat = new Integer[filters.unitFilters.length]; //deals additional x damage to type*technology

    public Integer[] resistanceToTypeFlat = new Integer[filters.unitFilters.length]; // enemies deal x damage less (cannot go under 1)*technology
    public Double[] resistanceToTypeMult = new Double[filters.unitFilters.length]; // enemies deal x% damage less (cannot go under 1)

    // not yet implemented:
    //public boolean damageCountAdditive;
    //public boolean damageCountScaleDamage;

    public Unit(Player player) {
        owner = player;
        owner.unitTypes.add(this);
    }

    public Unit(Unit typeUnit,Sector sector) {
        unitType = typeUnit;
        if(typeUnit.abilities != null) {
            for(Abilities a : typeUnit.abilities) {
                new Abilities(a,this);
            }
        }
        filters = typeUnit.filters;
        owner = typeUnit.owner;
        generateResource = typeUnit.generateResource;
        if(0 < generateResource) {
            typeUnit.owner.resourceIncome += generateResource;
            Effect e = new Effect();
            e.targetPlayer = owner;
            e.changeResourceIncome = -generateResource;
            deathEffects.add(e);
        }

                movementSpeed = typeUnit.movementSpeed;
        cloak = typeUnit.cloak;
        typeName = typeUnit.typeName;
        sightRange = typeUnit.sightRange;
        attackRange = typeUnit.attackRange;

        damageUnscaled = typeUnit.damageUnscaled;
        lifeUnscaled = typeUnit.lifeUnscaled;
        damageTechAdditive = typeUnit.damageTechAdditive;
        damageTechMultiplicative = typeUnit.damageTechMultiplicative;
        lifeTechMultiplicative = typeUnit.lifeTechMultiplicative;
        lifeTechAdditive = typeUnit.lifeTechAdditive;
        resistance = typeUnit.resistance;
        damageMult = typeUnit.damageMult;

        damageToTypeFlat = typeUnit.damageToTypeFlat;
        resistanceToTypeFlat = typeUnit.resistanceToTypeFlat;
        resistanceToTypeMult = typeUnit.resistanceToTypeMult;
        currentSector = sector;

        sector.units.add(this);
        owner.units.add(this);
        UnitArriveInSector(this);

        for(Abilities a : abilities) {
            if(a.autoCast) {
                Abilities.CallAbility(a);
            }
        }
    }

    public static void RemoveUnit(Unit u) {
        System.out.println("removed unit "+u.typeName);
        u.filters.dead = true;
        u.count = 0;
        for(Abilities a : u.abilities) {
            gameScreen.game.ongoingAbilities.remove(a);
        }
        for(Effect e : u.effects) {
            gameScreen.game.ongoingEffects.remove(e);
        }
        for(Effect e : u.deathEffects) {
            gameScreen.game.ongoingEffects.add(e);
        }
        RemoveUnitVisionInSector(u);

        u.owner.units.remove(u);
        if(u.currentSector != null) {
            u.currentSector.units.remove(u);
        } else if(u.currentPath != null) {
            Path.RemovePassingUnit(u, u.currentPath);
        }
    }

    public static void RemoveUnitMerged(Unit u) {
        System.out.println("merge unit "+u.typeName);
        u.filters.dead = true;
        u.count = 0;
        /*
        for(Abilities a : u.abilities) {
            gameScreen.game.ongoingAbilities.remove(a);
        }
        for(Effect e : u.effects) {
            gameScreen.game.ongoingEffects.remove(e);
        }
        for(Effect e : u.deathEffects) {
            gameScreen.game.ongoingEffects.add(e);
        }
         */
        //RemoveUnitVisionInSector(u);

        u.owner.units.remove(u);
        if(u.currentSector != null) {
            u.currentSector.units.remove(u);
        } else if(u.currentPath != null) {
            Path.RemovePassingUnit(u, u.currentPath);
        }
    }


    public static void UnitArriveInSector(Unit u) {
        ArriveUnitVisionInSector(u);
        MergeInSector(u);
    }

    public static void RemoveUnitVisionInSector(Unit u) {
        if(u.currentSector != null) {
            for(Unit w : u.currentSector.units) {
                if(!w.equals(u) && w.owner == u.owner) {
                    return;
                }
            }
            u.owner.revealedSectors.remove(u.currentSector);
        }
    }

    public static void ArriveUnitVisionInSector(Unit u) {
        if(u.currentSector != null && !u.owner.revealedSectors.contains(u.currentSector)) {

            u.owner.revealedSectors.add(u.currentSector);
        }
    }

    //need to show
    public static int DamageOutput(Unit u, Filters defender, int attacks) {
        double damageDone = u.damageUnscaled;
        for(int i = 0; i < u.damageToTypeFlat.length; i++) {
            if(defender.unitFilters[i]) {
                damageDone += u.damageToTypeFlat[i];
            }
        }

        if(u.damageTechAdditive) {
            damageDone += u.owner.technology;
        }
        if(u.damageTechMultiplicative) {
            damageDone = damageDone*u.owner.technology;
        }
        damageDone = Math.floor(damageDone*u.damageMult*attacks);
        return (int) damageDone;
    }

    //need to show on screen
    public static int GetFullLife(Unit u) {
        double life = u.lifeUnscaled;
        if(u.lifeTechAdditive) {
            life += u.owner.technology;
        }
        if(u.lifeTechMultiplicative) {
            life = life*u.owner.technology;
        }
        life = Math.floor(life*u.count);
        return (int) Math.floor(life);
    }

    //need to show
    public static int GetOneLife(Unit u) {
        double life = u.lifeUnscaled;
        if(u.lifeTechAdditive) {
            life += u.owner.technology;
        }
        if(u.lifeTechMultiplicative) {
            life = life*u.owner.technology;
        }
        return (int) Math.floor(life);
    }

    public static void CastAbility() {

    }

    //double = damage done
    public static double SufferDamage(Unit target, double dealtDamage, Filters attacker) {
        double damageLeft = 0;
        for(int i = 0; i < target.resistanceToTypeFlat.length; i++) {
            if(attacker.unitFilters[i]) {
                if(target.resistanceToTypeMult[i] != 0) {
                    dealtDamage =  dealtDamage * target.resistanceToTypeMult[i];
                }
                damageLeft -= target.resistanceToTypeFlat[i];
            }
        }
        damageLeft += dealtDamage;
        if(damageLeft <= 0) {
            return 0;
        }
        damageLeft = damageLeft - damageLeft*target.resistance;

        return damageLeft;
    }

    public static void MergeInSector(Unit u) {
        for(int t = 0; t<u.currentSector.units.size()-1;t++) {
            if(u.unitType == u.currentSector.units.get(t).unitType && u.effects.equals(u.currentSector.units.get(t).effects)) {
                u.count += u.currentSector.units.get(t).count;
                RemoveUnitMerged(u.currentSector.units.get(t));
            }
        }
    }

    public static String UnitStats(Unit u) {
        StringBuilder s = new StringBuilder();
        s.append(u.typeName).append(" of ").append(u.owner.playerName).append(" ").append(u.count).append(" ");
        if(u.lifeUnscaled != 0 || u.lifeTechAdditive) {
            int counter = u.lifeUnscaled;
            if(u.damageTechAdditive) {
                counter += u.owner.technology;
            }
            if(u.damageTechMultiplicative) {
                counter = counter*u.owner.technology;
            }
            s.append("HP: ").append(counter * u.count).append(" ");

            if(u.resistance != 0) {
                s.append("d resist: ").append(u.resistance).append(" ");
            }

            for(int i = 0; i<u.resistanceToTypeMult.length; i++) {
                if(u.resistanceToTypeMult[i] != 0) {
                    s.append("+ ").append(u.resistanceToTypeMult[i]).append(" to ").append(Filters.unitFilterNames[i]).append(" ");
                }
            }

            for(int i = 0; i<u.resistanceToTypeFlat.length; i++) {
                if(u.resistanceToTypeFlat[i] != 0) {
                    s.append("+ ").append(u.resistanceToTypeFlat[i]).append(" defense to ").append(Filters.unitFilterNames[i]).append(" ");
                }
            }
        }

        if(u.damageUnscaled != 0 || u.damageTechAdditive) {
            int counter = u.damageUnscaled;
            if(u.damageTechAdditive) {
                counter += u.owner.technology;
            }
            if(u.damageTechMultiplicative) {
                counter = counter*u.owner.technology;
            }
            s.append("d: ").append(counter * u.count * u.damageMult).append(" ");
        }

        for(int i = 0; i<u.damageToTypeFlat.length; i++) {
            if(u.damageToTypeFlat[i] != 0) {
                s.append("+ ").append(u.damageToTypeFlat[i] * u.owner.technology * u.count).append(" to ").append(Filters.unitFilterNames[i]).append(" ");
            }
        }

        if(u.attackRange != 0) {
            s.append("Attack Range ").append(u.attackRange).append(" ");
        }

        if(u.movementSpeed !=0) {
            s.append("Movement ").append(u.movementSpeed).append(" ");
        }

        if(u.sightRange !=0) {
            s.append("sight").append(u.sightRange).append(" ");
        }

        for(int i = 0; i<u.filters.unitFilters.length; i++) {
            if(u.filters.unitFilters[i]) {
                s.append(" ").append(Filters.unitFilterNames[i]).append(" ");
            }
        }

        return s.toString();
    }

    public static String UnitTooltip(Unit u) {
        StringBuilder s = new StringBuilder();
        s.append(u.typeName).append(" of ").append(u.owner.playerName).append(" ").append(u.count).append(" ");
        if(u.lifeUnscaled != 0 || u.lifeTechAdditive) {
            int counter = u.lifeUnscaled;
            if(u.damageTechAdditive) {
                counter += u.owner.technology;
            }
            if(u.damageTechMultiplicative) {
                counter = counter*u.owner.technology;
            }
            s.append("HP: ").append(counter * u.count).append(" ");

            if(u.resistance != 0) {
                s.append("d resist: ").append(u.resistance).append(" ");
            }

            for(int i = 0; i<u.resistanceToTypeMult.length; i++) {
                if(u.resistanceToTypeMult[i] != 0) {
                    s.append("+ ").append(u.resistanceToTypeMult[i]).append(" to ").append(Filters.unitFilterNames[i]).append(" ");
                }
            }

            for(int i = 0; i<u.resistanceToTypeFlat.length; i++) {
                if(u.resistanceToTypeFlat[i] != 0) {
                    s.append("+ ").append(u.resistanceToTypeFlat[i]).append(" defense to ").append(Filters.unitFilterNames[i]).append(" ");
                }
            }
        }

        if(u.damageUnscaled != 0 || u.damageTechAdditive) {
            int counter = u.damageUnscaled;
            if(u.damageTechAdditive) {
                counter += u.owner.technology;
            }
            if(u.damageTechMultiplicative) {
                counter = counter*u.owner.technology;
            }
            s.append("d: ").append(counter * u.count * u.damageMult).append(" ");
        }

        for(int i = 0; i<u.damageToTypeFlat.length; i++) {
            if(u.damageToTypeFlat[i] != 0) {
                s.append("+ ").append(u.damageToTypeFlat[i] * u.owner.technology * u.count).append(" to ").append(Filters.unitFilterNames[i]).append(" ");
            }
        }

        if(u.attackRange != 0) {
            s.append("Attack Range ").append(u.attackRange).append(" ");
        }

        if(u.movementSpeed !=0) {
            s.append("Movement ").append(u.movementSpeed).append(" ");
        }

        if(u.sightRange !=0) {
            s.append("sight").append(u.sightRange).append(" ");
        }

        for(int i = 0; i<u.filters.unitFilters.length; i++) {
            if(u.filters.unitFilters[i]) {
                s.append(" ").append(Filters.unitFilterNames[i]).append(" ");
            }
        }
        return s.toString();
    }
}
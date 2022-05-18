package neutron.tutorial.gamedata;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

import static neutron.tutorial.gamedata.Game.gameScreen;

public class Abilities {
    public Unit casterUnit;
    public Player casterPlayer;
    public Player targetPlayer;
    public Sector targetSector = null;
    public int cooldown = 1; //in turns
    public int currentCooldown;
    public String abilityDesc;
    public String abilityName;
    public boolean autoCast = false;
    public Image icon = null;

    public boolean selectPathSector = false;
    public boolean moveToTargetSector = false;

    public boolean remove = false;

    String spellName;

    public ArrayList<Effect> effects; //effects are executed at the end of cast

    public Abilities() {
    }

    public Abilities(Player typePlayer) {
        casterPlayer = typePlayer;
        typePlayer.abilityTypes.add(this);
    }

    public Abilities(Abilities a, Unit u) {
        u.abilities.add(this);

        casterUnit = u;
        casterPlayer = u.owner;
        targetPlayer = casterPlayer;

        cooldown = a.cooldown;
        currentCooldown = 0;
        spellName = a.spellName;
        effects = a.effects;

        selectPathSector = a.selectPathSector;
        moveToTargetSector = a.moveToTargetSector;
        abilityDesc = a.abilityDesc;
        abilityName = a.abilityName;
    }

    public static void ExecuteAbility(Abilities a) {
        if(a.remove) {
            RemoveAbility(a);
        }
        gameScreen.game.ongoingAbilities.add(a);

        if(a.moveToTargetSector) {
            for(Path p : a.casterUnit.currentSector.paths) {
                if(p.in == a.targetSector) {
                    Path.AddPassingUnit(a.casterUnit, p);
                    gameScreen.UpdateUnitsTab(gameScreen.casterSector);
                    break;
                }
            }
            if(a.casterUnit.currentPath == null) {
                System.out.println("No path leading to target sector found! Ability set ready");
                a.targetSector = null;
                AbilityReady(a);
            }

        }

        if(a.targetPlayer != null) {
            // TODO: need Effects fields update
            if(a.effects != null) {
                for (Effect e : a.effects) {
                    Effect t = new Effect(e, null, null, null);
                    a.targetPlayer.effects.add(t);
                    gameScreen.game.ongoingEffects.add(t);
                }
            }
        }
    }

    public static void IterateCooldown(Abilities e) {
        System.out.println("Iterating "+e.abilityName+"...");
        if(e.remove) {
            RemoveAbility(e);
        }

        if(e.moveToTargetSector) {
            CallAbility(e);
        } else {
            if(e.targetPlayer == null) {
                RemoveAbility(e);
            } else {
                e.currentCooldown--;
                if(e.currentCooldown<=0) {
                    AbilityReady(e);
                }
            }
        }
    }

    public static void CallAbility(Abilities a) { // Before execute
        if(a.remove) {
            RemoveAbility(a);
        }

        if(a.moveToTargetSector) {
            if(a.targetSector == null) {
                if(a.casterUnit.currentSector == null) {
                    System.out.println("Error - Unit is currently on a path. Can't move!");
                    return;
                }
                System.out.println("Effect moveToTargetSector called but there is no sector");
                //Effect f = new Effect();
                //f.targetDistance=100;
                //f.targetSector=e.casterPlayer.homeSector;
                Game.AbilitySelectSector(a, a.casterUnit.owner);
                //ExecuteAbility(e); // move means no execution before choose target

            } else {
                Path p = a.casterUnit.currentPath;
                Path.UpdateMovement(p, a.casterUnit);
                if(a.casterUnit.currentPath == null) {
                    AbilityReady(a);
                    ResetAbility(a);
                }
            }
        } else {
            ExecuteAbility(a);
        }
    }

    public static void PlayerUseAbility(Abilities a) {
        if(gameScreen.game.ongoingAbilities.contains(a)) {
            System.out.println("Ability "+a.abilityName+" is on cooldown! "+a.currentCooldown);
            return;
        }

        CallAbility(a);
    }

    public static void ResetAbility(Abilities a) {
        a.targetSector = null;
    }

    public static void AbilityReady(Abilities a) {
        gameScreen.game.ongoingAbilities.remove(a);
        if(a.autoCast) {
            Abilities.CallAbility(a);
        } else {
            a.currentCooldown = 0;
        }

    }

    public static void RemoveAbility(Abilities e) {
        System.out.println("Removing ability "+e.abilityName);
        gameScreen.game.ongoingAbilities.remove(e);
        e.casterUnit.abilities.remove(e);

		/*
		 if(this.targetUnit != null) {
			this.targetUnit.effects.remove(this);
		}
		if(this.targetSector != null) {
			this.targetSector.effects.remove(this);
		}
		 */


    }
}


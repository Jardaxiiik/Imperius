package neutron.tutorial.gamedata;

import java.util.ArrayList;

public class Combat {
public static void GroupCombat(ArrayList<Unit> groupA, ArrayList<Unit> groupB) {
    ArrayList<CombatEntity> combatGroupA = new ArrayList<>();
    ArrayList<CombatEntity> combatGroupB = new ArrayList<>();

    for(Unit a : groupA) {
        combatGroupA.add(new CombatEntity(a));
    }
    for(Unit b : groupB) {
        combatGroupB.add(new CombatEntity(b));
    }

    combatGroupA = SortGroupByAttackPriority(combatGroupA);
    combatGroupB = SortGroupByAttackPriority(combatGroupB);
    ArrayList<CombatEntity> dead = new ArrayList<>();

    for(int n = combatGroupA.size()-1; n >=0; n--) {
        boolean b = true;
        CombatEntity attacker = combatGroupA.get(n);
        while(attacker.attacks >0 && b) {
            CombatEntity defender = findBestEnemy(attacker, combatGroupB);
            if(defender != null) {
                double l = defender.life;
                double k = Unit.SufferDamage(defender.unit, Unit.DamageOutput(attacker.unit, defender.filters, combatGroupA.get(n).attacks), attacker.filters);
                l -= k;
                System.out.println("life "+attacker.unit.typeName+" "+k+ "  attacks "+defender.unit.typeName+ " "+l);
                if(l <=0) {
                    defender.life = 0;
                    dead.add(defender);
                } else {
                    defender.life = l;
                }
                int num = (int) Math.floor(-l/Unit.DamageOutput(attacker.unit, defender.filters,1));
                if(num != 0) {
                    attacker.attacks = num;
                }
            } else {
                b = false;
            }
        }

    }

    for(int n = combatGroupB.size()-1; n >=0; n--) {
        boolean b = true;
        CombatEntity attacker = combatGroupB.get(n);
        while(attacker.attacks >0 && b) {
            CombatEntity defender = findBestEnemy(attacker, combatGroupA);
            if(defender != null) {
                double l = defender.life;
                double k = Unit.SufferDamage(defender.unit, Unit.DamageOutput(attacker.unit, defender.filters, attacker.attacks), attacker.filters);
                l -= k;
                System.out.println("life "+attacker.unit.typeName+" "+k+ "  attacks "+defender.unit.typeName+ " "+l);
                if(l <=0) {
                    defender.life = 0;
                    dead.add(defender);

                } else {
                    defender.life = l;
                }
                int num = (int) Math.floor(-l/Unit.DamageOutput(attacker.unit, defender.filters,1));
                if(num != 0) {
                    attacker.attacks = num;
                }
            } else {
                b = false;
            }
        }
    }

    //EndPhase
    for(CombatEntity et : combatGroupA) {
        int a = (int) Math.floor(et.life/et.oneLife);
        if(a == 0) {
            dead.add(et);
        } else {
            et.unit.count = a;
        }
    }
    for(CombatEntity et : combatGroupB) {
        int a = (int) Math.floor(et.life/et.oneLife);
        if(a == 0) {
            dead.add(et);
        } else {
            et.unit.count = a;
        }
    }
    for(CombatEntity et : dead) {

        if(et.unit.currentSector != null) {
            et.unit.owner.turnSummary.add(et.unit.typeName+ "has died in Sector "+et.unit.currentSector);
        } else if(et.unit.currentPath != null) {
            et.unit.owner.turnSummary.add(et.unit.typeName+ "has died on Path "+et.unit.currentPath);
        }
        System.out.println("remove"+et.unit.typeName);
        Unit.RemoveUnit(et.unit);
    }
}

    // null if no target
    private static CombatEntity findBestEnemy(CombatEntity attacker, ArrayList<CombatEntity> groupE) {

        for(CombatEntity e : groupE) {
            if (e.life > 0) {
                if(e.unit.typeName == "Base" && groupE.indexOf(e)<(groupE.size()-1)) { // bases at the end

                } else {
                    return e;
                }
            }
        }
        return null;
    }

	/*
	private static void SetHasSpecial(CombatEntity e) {
		ArrayList<Integer> hasSpecial = new ArrayList<Integer>();
		for(int n = 0; n<e.filters.unitFilters.length; n++) {
			if(e.unit.damageToTypeFlat[n] != 0) {
				hasSpecial.add(n);
			}
		}
		e.attackFilterChanges = hasSpecial;
		 hasSpecial = new ArrayList<Integer>();
		for(int n = 0; n<e.filters.unitFilters.length; n++) {
			if(e.unit.resistanceToTypeFlat[n] != 0 || e.unit.resistanceToTypeMult[n] != 0) {
				hasSpecial.add(n);
			}
		}
		e.defenseFilterChanges = hasSpecial;
	}
	*/

    private static ArrayList<CombatEntity> SortGroupByAttackPriority(ArrayList<CombatEntity> c) { // who attacks first
        boolean b = true;

        while(b) {
            b = false;
            for(int n = 0; n< c.size(); n++) {
                if(n-1 >= 0) {
                    if((c.get(n).attackRange < c.get(n-1).attackRange) ||
                            ((c.get(n).movementSpeed < c.get(n-1).movementSpeed)&&(c.get(n).attackRange==c.get(n-1).attackRange))) {
                        CombatEntity e = c.get(n);
                        c.set(n, c.get(n-1));
                        c.set(n-1, e);
                        b = true;
                    }
                }
            }
        }

        return c;
    }
}

class CombatEntity {

    public Unit unit;
    public Filters filters;
    public int count;

    public int oneLife;
    public double life;
//	public ArrayList<Integer> attackFilterChanges;
//	public ArrayList<Integer> defenseFilterChanges;

    public int damagePerOne;
    public int attacks;
    //public Effect[] ApplyEffectOnAttack;

    public int attackRange;
    public int movementSpeed;
    public int cloak;

    public CombatEntity(Unit u) {
        unit = u;
        filters = u.filters;
        count = u.count;

        oneLife = u.lifeUnscaled;
        life = Unit.GetFullLife(u);
        damagePerOne = Unit.DamageOutput(u, new Filters(), 1);
        //attackFilterChanges = new ArrayList<Integer>();
        //defenseFilterChanges = new ArrayList<Integer>();

        attacks = count;
        attackRange = u.attackRange;
        movementSpeed = u.movementSpeed;
        cloak = u.cloak;


    }
}

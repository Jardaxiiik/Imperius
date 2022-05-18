package neutron.tutorial.gamedata;

import static neutron.tutorial.gamedata.Game.gameScreen;

public class Effect {
    public Player targetPlayer=null;
    public Unit casterUnit=null;
    public Sector targetSector=null;
    public Object ancestor = null;

    public int addToTech = 0;
    public int addToResources = 0;
    public int changeResourceIncome = 0;

    public int executionTime = 0;
    public int periodTime = 1;
    public int periodCount = 1;

    public Effect periodicEffect;
    public Effect finalEffect; //if unit not die

    public boolean remove = false;
    public Unit spawnedUnit=null; // created after the damageEffect

    //SPLASH
    public int targetDistance = 0;
    public boolean onlyRevealed=false;

	/*
	public int damage = 0;
	public int splashDamage = 0;
	public Filters filters = new Filters(); //filters for splash
	public int radius = 0; //for splash; if 0 splashDamage then applies effect to all in area with filters



	public Sector targetSector = null;
	public Unit targetUnit= null; //if deathEffect then this is killer unit
	public Player casterPlayer=null; //player of the effect

	public boolean targetAllPlayers=false;
	public boolean targetAllPlayersExceptCaster=false;
	public boolean targetUnitPlayerChangeTech = false;
	public boolean persistUntilDestroyed=false;
	public boolean targetIsCaster = false;
	public boolean targetSectorAtUnit = false; //target sector is the sector where is unit
	public boolean targetSectorAtUnitIsMovable = false; //The sector moves with the unit

	public boolean mustHaveTargetUnit = false;
	public boolean mustHaveTargetSector = false;
	public boolean mustHaveTargetPlayer = false;
	public boolean noTargetUnit = false;

	public double chance = 1; //chance for the Effect to occour
	*/

    public Effect() {
    }

    public Effect(Player player) {
        // TypeEffect

        targetPlayer = player;
        player.effectTypes.add(this);
    }

    public Effect(Effect typeEffect,Sector newtargetSector,Unit newtargetUnit, Unit caster) {
        addToTech = typeEffect.addToTech;
        addToResources = typeEffect.addToResources;
        changeResourceIncome = typeEffect.changeResourceIncome;

        executionTime = typeEffect.executionTime;
        periodTime = typeEffect.periodTime;
        periodCount = typeEffect.periodCount;

        periodicEffect = typeEffect.periodicEffect;
        finalEffect = typeEffect.finalEffect;

        spawnedUnit = typeEffect.spawnedUnit;

        targetPlayer = typeEffect.targetPlayer;
        casterUnit = caster;
        targetSector = newtargetSector;

		/*
		damage = typeEffect.damage;
		splashDamage = typeEffect.splashDamage;
		filters = typeEffect.filters;
		radius = typeEffect.radius;


		targetUnit = newtargetUnit;
		casterPlayer = typeEffect.casterPlayer;
		targetPlayer = typeEffect.targetPlayer;

		chance = typeEffect.chance;

		targetAllPlayers= typeEffect.targetAllPlayers;
		targetAllPlayersExceptCaster=typeEffect.targetAllPlayersExceptCaster;
		targetUnitPlayerChangeTech = typeEffect.targetUnitPlayerChangeTech;
		persistUntilDestroyed= typeEffect.persistUntilDestroyed;
		targetIsCaster = typeEffect.targetAllPlayersExceptCaster;
		targetSectorAtUnit = typeEffect.targetSectorAtUnit; //target sector is the sector where is unit
		targetSectorAtUnitIsMovable = typeEffect.targetSectorAtUnitIsMovable; //The sector moves with the unit

		mustHaveTargetUnit = typeEffect.mustHaveTargetUnit;
		mustHaveTargetSector = typeEffect.mustHaveTargetSector;
		mustHaveTargetPlayer = typeEffect.mustHaveTargetPlayer;
		noTargetUnit = typeEffect.noTargetUnit;

		if(targetUnitPlayerChangeTech) {
			targetPlayer = targetUnit.owner;
		}
		if(targetIsCaster) {
			targetPlayer = casterPlayer;
		}
		if(noTargetUnit) {
			targetUnit = null;
		}
		if(targetSectorAtUnit && targetUnit != null) {
			targetSector = targetUnit.currentSector;
		}

		boolean hasTargetUnit = (targetUnit != null);
		boolean hasTargetPlayer = (targetPlayer != null);
		boolean hasTargetSector = (targetSector != null);
		if(((mustHaveTargetUnit && hasTargetUnit)|| mustHaveTargetUnit == false) &&
			((mustHaveTargetPlayer && hasTargetPlayer) || mustHaveTargetPlayer == false) &&
			((mustHaveTargetSector && hasTargetSector)|| mustHaveTargetSector ==false)) {

			if(periodTime >1 || periodCount > 1 || executionTime > 1) {
				casterPlayer.game.ongoingEffects.add(this);
				if(targetPlayer !=null) {
					targetPlayer.effects.add(this);
				}
				if(targetUnit != null) {
					targetUnit.effects.add(this);
				}
				if(targetSector !=null) {
					targetSector.effects.add(this);
				}
			}
		} else {
			casterPlayer = null;
		}
		*/
    }

    public static void ExecuteEffect(Effect e, boolean finalLoop) {
        if(e.remove) {
            RemoveEffect(e);
        }

        if(e.targetPlayer != null) {
            e.targetPlayer.technology += e.addToTech;
            e.targetPlayer.resources += e.addToResources;
            e.targetPlayer.resourceIncome += e.changeResourceIncome;

            gameScreen.UpdateResources();
            gameScreen.UpdateTechnology();

            // hmm...
            if(e.spawnedUnit != null && e.targetSector !=null) {
                new Unit(e.spawnedUnit, e.targetSector);
                gameScreen.UpdateUnitsTab(gameScreen.casterSector);
            }
        }



		/*
		if(targetSector !=null) {
			if(targetSectorAtUnitIsMovable&&targetUnit != null) {
				targetSector = targetUnit.currentSector;
			}

		}

		if(targetUnit !=null) {
			targetUnit
		}

		if(finalLoop) {
			if(finalEffect != null) {
				Effect e = new Effect(finalEffect, targetSector,targetUnit);
				e.CallEffect();
			}
			RemoveEffect();
		} else {
			if(periodicEffect != null) {
				Effect e = new Effect(periodicEffect, targetSector,targetUnit);
				e.CallEffect();
			}
		}
		*/
    }

    public static void CallEffect(Effect e) {
        if(e.remove || e.targetPlayer == null) {
            RemoveEffect(e);
        }

        e.executionTime--;
        if(e.executionTime<=0) {
            e.periodCount--;
            if(e.periodCount <=0) {
                //Execute final effect
                ExecuteEffect(e, true);
                RemoveEffect(e);
            } else {
                //Execute normal period Effect
                ExecuteEffect(e, false);
                e.executionTime = e.periodTime;
            }
        }

		/*
		if(targetUnit == null && radius ==0 && targetSector == null && targetPlayer == null) {
			casterPlayer.game.ongoingEffects.remove(this);
		} else {
			executionTime--;
			if(executionTime<=0) {
				periodCount--;
				if(periodCount <=0) {
					//Execute final effect
					ExecuteEffect(true);
				} else {
					//Execute normal period Effect
					ExecuteEffect(false);
					executionTime = periodTime;
				}
			}
		}
		*/
    }

    public static void RemoveEffect(Effect e) {
        if(gameScreen.game.ongoingEffects.contains(e)) {
            gameScreen.game.ongoingEffects.remove(e);
        }

/*
        if(e.targetPlayer != null && e.targetPlayer.effects.contains(e)) {
            e.targetPlayer.effects.remove(e);
        }

		 if(this.targetUnit != null) {
			this.targetUnit.effects.remove(this);
		}
		if(this.targetSector != null) {
			this.targetSector.effects.remove(this);
		}
		 */


    }
}

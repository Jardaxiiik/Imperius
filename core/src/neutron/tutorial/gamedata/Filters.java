package neutron.tutorial.gamedata;

public class Filters {
    public boolean ground = false;
    public boolean air = false;
    public boolean water = false;
    public boolean swamp = false;
    public boolean mountain = false;
    public boolean underground = false;

    public boolean army = false; // can deal damage
    public boolean economy = false; // does add resource
    public boolean research = false; // add technology

    public boolean structure = false;
    public boolean dead = false;

    public boolean light = false;
    public boolean med = false;
    public boolean heavy = false;
    public boolean massive = false;
    public boolean biological = false;
    public boolean cloaked = false;
    public boolean invulnerable = false;

    public boolean[] movementFilters = {ground, air, water, swamp, mountain, underground};
    public boolean[] unitFilters = {ground, air, water, swamp, mountain, underground, army, economy, research, dead};
    public boolean[] sectorFilters = {ground, air, water, swamp, mountain, underground};
    static String[] unitFilterNames= {"ground","air", "water", "swamp", "mountain", "underground", "army", "economy", "research"};
}


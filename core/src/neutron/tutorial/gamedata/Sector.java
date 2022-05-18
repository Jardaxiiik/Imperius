package neutron.tutorial.gamedata;

import java.util.ArrayList;

public class Sector {
    public ArrayList<Unit> units = new ArrayList<>();
    public ArrayList<Path> paths = new ArrayList<>();
    public ArrayList<Path> pathsIn = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>();
    public String name;
    public Filters filters;

    public Sector(String newName) {
        name = newName;
    }

    //wrong. Use owner.SectorSightRange and owner.SectorDecloakRange and Power
    public static ArrayList<Unit> GetKnownUnitsInSector(Player player, Sector sector) {
        int detection = 0;
        ArrayList<Unit> unitsInSector = new ArrayList<>();
        for(Unit u : sector.units) {
            if(u.owner == player) {
                if(u.decloakStrenght > detection) {
                    detection = u.decloakStrenght;
                    unitsInSector.add(u);
                }
            }
        }

        for(Unit u : sector.units) {
            if(u.owner != player) {
                if(u.decloakStrenght <= detection) {
                    unitsInSector.add(u);
                }
            }
        }
        return unitsInSector;
    }
}


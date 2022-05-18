package neutron.tutorial.gamedata;

import java.util.ArrayList;

public class Path {
    ArrayList<Unit> passingUnits = new ArrayList<>();
    ArrayList<Integer> passingUnitsDistanceFromTargetLocation = new ArrayList<>();

    public Sector in;
    public Sector out;
    public int lenght;
    public Filters passFilter;

    public Path(Sector into, Sector outFrom, boolean both, int newlenght, Filters newFilters) {
        lenght = newlenght;
        passFilter = newFilters;
        in = into;
        out = outFrom;
        if(both) {
            new Path(outFrom, into, false, newlenght, newFilters);
        }
        outFrom.paths.add(this);
        into.pathsIn.add(this);
    }


    // For now just sectors are revealed
    public static ArrayList<Unit> GetKnownUnitsOnPath(Player player, Path path) {
        ArrayList<Unit> unitsOnPath = new ArrayList<>();
        for(Unit u : path.passingUnits) {
            if(u.owner == player) {
                unitsOnPath.add(u);
            } /*else {

				 int l = Player.GetHighestDecloakInSector(player, path.in, path.passingUnitsDistanceFromTargetLocation.get(path.passingUnits.indexOf(u)));
				 int p = Player.GetHighestDecloakInSector(player, path.out, path.lenght - path.passingUnitsDistanceFromTargetLocation.get(path.passingUnits.indexOf(u)));
				 if(l>=u.cloak) {
					 unitsOnPath.add(u);
				 } else {
					 if(p>= u.cloak) {
						 unitsOnPath.add(u);
					 }
				 }
			 }
			 */
        }
        return unitsOnPath;
    }


    public static void UpdateMovement(Path p, Unit u) {
        System.out.println("Updating movement for "+u.typeName+"!");
        int t = p.passingUnits.indexOf(u);
        int s = p.passingUnitsDistanceFromTargetLocation.get(t) - u.movementSpeed;
        if(s <= 0) {
            UnitArrive(u, p);
        } else {
            p.passingUnitsDistanceFromTargetLocation.set(t, s);
        }
    }

    public static void AddPassingUnit(Unit u, Path p) {
        u.currentSector.units.remove(u);
        u.currentSector = null;
        u.currentPath = p;
        u.moving = true;
        p.passingUnits.add(u);
        p.passingUnitsDistanceFromTargetLocation.add(p.lenght);
    }

    public static void UnitArrive(Unit u, Path p) {
        System.out.println("Unit "+u.typeName+" has arrived in "+p.in.name);
        if(p.passingUnits.contains(u)) {
            u.currentSector = p.in;
            u.moving = false;
            p.in.units.add(u);

            RemovePassingUnit(u,p);

            u.currentPath = null;
        }
    }

    public static void RemovePassingUnit(Unit u, Path p) {
        if(p.passingUnits.contains(u)) {
            int i = p.passingUnits.indexOf(u);
            p.passingUnits.remove(u);
            p.passingUnitsDistanceFromTargetLocation.remove(i);
        }
    }

    public static Integer GetUnitDistanceToTarget(Unit u, Path p) {
        int i = 0;
        if(p.passingUnits.contains(u)) {
            i = p.passingUnitsDistanceFromTargetLocation.get(p.passingUnits.indexOf(u));
        }
        return i;
    }
}


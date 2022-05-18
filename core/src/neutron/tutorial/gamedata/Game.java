package neutron.tutorial.gamedata;

import java.util.ArrayList;

import neutron.tutorial.views.MainScreen;

public class Game {
    public static MainScreen gameScreen;
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Sector> sectors = new ArrayList<Sector>();
    public ArrayList<Effect> ongoingEffects = new ArrayList<Effect>();
    public ArrayList<Abilities> ongoingAbilities = new ArrayList<Abilities>();

    public int turnCount = 0;
    public Player playerToShow; // for One Device play
    boolean gameIsStopped = false;

    public Game(MainScreen mainScreen) {
        gameScreen = mainScreen;
        mainScreen.game = this;
        GameInicialization();
        gameScreen.GenerateSectors();
        StartGame();
    }

    // Game Inicialization
        public void GameInicialization() {
            Player p1 = new Player("p1",1,this);
            Player p2 = new Player("p2",1,this);
            players.add(p1);
            players.add(p2);
            Sector s1 = new Sector("Alfa");
            sectors.add(s1);
            Sector s2 = new Sector("Beta");
            sectors.add(s2);
            new Path(s2,s1,true, 30, new Filters());

            p1.resources = 1;
            p2.resources = 1;
            p1.technology = 1;
            p2.technology = 1;

            // p1
            Unit ut1 = new Unit(p1);
            ut1.typeName = "Base";
            ut1.lifeUnscaled = 10;
            ut1.generateResource =1;

            Unit ut11 = new Unit(p1);
            ut11.typeName = "Army";
            ut11.damageUnscaled = 1;
            ut11.lifeUnscaled = 1;
            ut11.movementSpeed = 10;

            //p2
            Unit ut21 = new Unit(p2);
            ut21.typeName = "Army";
            ut21.damageUnscaled = 1;
            ut21.lifeUnscaled = 1;
            ut21.movementSpeed = 10;

            Unit ut2 = new Unit(p2);
            ut2.typeName = "Base";
            ut2.lifeUnscaled = 10;
            ut2.generateResource =1;

            Abilities a = new Abilities(p1);
            a.abilityName = "Move army";
            a.moveToTargetSector = true;
            ut11.abilities.add(a);

            Abilities b = new Abilities(p2);
            b.abilityName = "Move army";
            b.moveToTargetSector = true;
            ut21.abilities.add(b);

            //

            p1.homeSector = s1;
            p2.homeSector = s2;

            new Unit(ut1, s1);
            new Unit(ut2, s2);

            Effect e1 = new Effect(p1);
            e1.spawnedUnit = ut1;

            Effect e11 = new Effect(p1);
            e11.spawnedUnit = ut11;

            Effect e2 = new Effect(p2);
            e2.spawnedUnit = ut2;

            Effect e21 = new Effect(p2);
            e21.spawnedUnit = ut21;

            Card ct1 = new Card(p1);
            ct1.name = "Build Base";
            ct1.effects.add(e1);
            ct1.costScalesWithBases = true;
            ct1.resourceCost = 2;
            //ct1.sector = p1.homeSector;

            Card ct11 = new Card(p1);
            ct11.name = "Train Army";
            ct11.effects.add(e11);
            ct11.resourceCost = 1;

            Card ct12 = new Card(p1);
            ct12.addTechnology = 1;
            ct12.name = "Buy Technology";
            ct12.resourceCost = 2;

            Card ct2 = new Card(p2);
            ct2.name = "Build Base";
            ct2.costScalesWithBases = true;
            ct2.effects.add(e2);
            ct2.resourceCost = 2;

            Card ct21 = new Card(p2);
            ct21.name = "Train Army";
            ct21.effects.add(e21);
            ct21.resourceCost = 1;

            Card ct22 = new Card(p2);
            ct22.addTechnology = 1;
            ct22.name = "Buy Technology";
            ct22.resourceCost = 2;

            p2.turnSummary.add("Player 2: Let the battle begin!");
            p2.turnSummary.add("Player 2: Victory or death!");

            p1.turnSummary.add("Player 1: Let the battle begin!");
            p1.turnSummary.add("Player 2: There is no end to this war..");

            p1.visibleSectors.add(s1);
            p1.visibleSectors.add(s2);
            p2.visibleSectors.add(s1);
            p2.visibleSectors.add(s2);
        }

        public void StartGame() {
            playerToShow = players.get(0);
            ShowUpdatedScreen(playerToShow);

            /*
            ShowUpdatedScreen(playerToShow);
            while(VictoryCheck()) {
                BuyCards(playerToShow);
                UseAllBoughtCards(playerToShow);
                CommandPhase(playerToShow);
                EndTurn();
            }

             */
        }

    // Player Turn Start
        private void ShowTurnSummary(Player informedPlayer) {
            /*
            for(String s : informedPlayer.turnSummary) {
                BasicCommunication.InformPlayer(s);
            }

             */
        }

    // Player Turn End
        public void ClickNextTurn(Player p) {
            int t = players.indexOf(p);
            gameScreen.ResetAllTouches();
            if(t < players.size()-1) {
                playerToShow = players.get(t+1);
                ShowUpdatedScreen(playerToShow);
            } else {
                playerToShow = players.get(0);
                EndTurn();
            }
        }

    // New Turn
        private boolean VictoryCheck() {
            int b=0;
            for(Player a : players) {
                if(a.defeated == true) {
                    b = b++;
                }
            }
            if(b+1 >= players.size()) {
                return false;
            }
            return true;
        }

        private void EndTurn() {
            gameIsStopped = true;
            turnCount++;
            for(Player p : players) {
                if(p.turnSummary.isEmpty()) {
                    p.turnSummary.add(turnCount+". ");
                } else {
                    p.turnSummary.set(0, turnCount + ". " + p.turnSummary.get(0));
                }
            }
            UpdateAbilities();
            UpdateEffects();
            SearchForCombat();
            UpdateVision();
            UpdateIncome();

            ShowUpdatedScreen(playerToShow);
            ShowTurnSummary(playerToShow);
            gameIsStopped = false;
        }

        private void UpdateEffects() {
            if(ongoingEffects.isEmpty()) {
                return;
            }
            int i = 0;
            while(i < ongoingEffects.size()) {
                Effect e = ongoingEffects.get(i);
                Effect.CallEffect(e);
                if(i < ongoingEffects.size()) {
                    if(ongoingEffects.get(i) != e) {
                        i--;
                    }
                }
                i++;
            }
        }

        private void UpdateAbilities() {
            if(ongoingAbilities.isEmpty()) {
                return;
            }
            System.out.println("Start Updating Abilities!");
            int i = 0;
            while(i < ongoingAbilities.size()) {
                Abilities a = ongoingAbilities.get(i);
                Abilities.IterateCooldown(a);
                if(i < ongoingAbilities.size()) {
                    if(ongoingAbilities.get(i) != a) {
                        i--;
                    }
                }
                i++;
            }
        }

        private void UpdateVision() {
            for(Player p : players) {
                p.revealedSectors.clear();
                for(Unit u : p.units) {
                    if(!p.revealedSectors.contains(u.currentSector) && u.currentSector != null) {
                        p.revealedSectors.add(u.currentSector);
                    }
                }
            }
        }

        private void UpdateIncome() {
            for(Player p : players) {
                if(p.resourceIncome >= 0) {
                    p.resources += p.resourceIncome;
                }
            }
        }

        private void SearchForCombat() { //Now: CombatCreate Does not support more than two enemies. Yet
            for(Sector s : sectors) {
                ArrayList<Player> p = new ArrayList<>();
                ArrayList<ArrayList<Unit>> groups = new ArrayList<>();

                for(Unit u : s.units) {
                    if(!p.contains(u.owner)) {
                        Player pt= u.owner;
                        p.add(u.owner);
                        ArrayList<Unit> group = new ArrayList<>();
                        groups.add(group);
                        for(Unit w : s.units) {
                            if(pt == w.owner) {
                                group.add(w);
                            }
                        }
                    }
                }
                if(p.size() >= 2) {
                    System.out.println("combat reported in "+s.name);
                    CombatCreate(groups.get(0), groups.get(1));
                }
            }
        }

        public void CombatCreate(ArrayList<Unit> groupA, ArrayList<Unit> groupB) {
            Combat.GroupCombat(groupA, groupB);
        }

    // Player Commands = everything during Player´s turn
        public void CommandPhase(Player commander) {
            /*
            playerToShow.turnSummary.add("Command phase. Any time write g to end turn or sector number to show units in");
            screen.IterateToNextWrittenMessage();

            ShowUpdatedScreen(commander);
            int t = turnCount;
            boolean repeat = true;
            while(turnCount==t && repeat) {
                ShowUpdatedScreen(commander);
                String s = BasicCommunication.Scanner();
                if(s.equals("g")) {
                    repeat = false;
                } else if(BasicCommunication.isNumber(s)) {
                    if(Integer.valueOf(s)<sectors.size()) {
                        CommandUnitsInSector(sectors.get(Integer.valueOf(s)),commander);
                    }
                }

            }

             */
        }

        private void ShowUpdatedScreen(Player informedPlayer) {

            gameScreen.ShowCardsInShop(informedPlayer);
            gameScreen.UpdateUnitsTab(informedPlayer.homeSector);
            gameScreen.UpdateSectorTab();
            gameScreen.UpdateResources();
            gameScreen.UpdateTechnology();
            gameScreen.IterateToNextWrittenMessage();
            gameScreen.ApplyWaitingScreen("Player "+playerToShow.playerName+" turn.\n Tap here to start!");

            /*
            TXT version
            String s = "";
            int counter = 0;
            for(Sector a:sectors) {
                s += counter+". " + a.name+" - ";
                ArrayList<Unit> units = Sector.GetKnownUnitsInSector(informedPlayer, a);
                for(Unit b: units) {
                    s += Unit.UnitTooltip(b);
                }
                BasicCommunication.InformPlayer(s);
                s = "";
                for(Path b:a.paths) {
                    s += counter+". path to " + b.in.name+" - ";
                    ArrayList<Unit> pathUnits = Path.GetKnownUnitsOnPath(informedPlayer,b);
                    for(Unit c: pathUnits) {
                        s += Unit.UnitTooltip(c);
                    }
                    BasicCommunication.InformPlayer(s);
                    s = "";
                }
                counter ++;
            }
            s = "resource : "+informedPlayer.resources;
            BasicCommunication.InformPlayer(s);
             */
        }

        private void BuyCards(Player player) {
            /*
            int t = turnCount;
            boolean k = true;
            while(k) {
                ArrayList<Card> buyCards = Card.GenerateCardsOffer(player, turnCount);
                for(Card a : buyCards) {
                    String s = buyCards.indexOf(a)+". "+ a.name+" "+ "cost: "+a.resourceCost+" ";
                    if(a.addTechnology != 0) {
                        s += "add tech +" + a.addTechnology+ " ";
                    }
                    if(a.addResources !=0) {
                        s += "add resources +"+a.addResources+ " ";
                    }

                    for(Effect e : a.effects) {
                        if(e.executionTime >0) {
                            s += "effect executes after "+e.executionTime+" turns ";
                        }

             /*
                        /*
                        if(e.damage !=0) {
                            s += "deals " +e.damage + " damage ";
                        }
                        if(e.spawnedUnit !=null) {
                            s += "spawns " + e.spawnedUnit.count + " " + e.spawnedUnit.typeName + " ";
                        }
                        */
            /*
                        if(e.addToTech != 0) {
                            s += "add tech +" + a.addTechnology+ " ";
                        }

                        if(e.addToResources != 0) {
                            s += "add resources +"+a.addResources+ " ";
                        }
                    }
                    BasicCommunication.InformPlayer(s);
                }

                BasicCommunication.InformPlayer("Choose card or stop shopping phase (g)");
                String s = BasicCommunication.Scanner();
                if(s.equals("g")) {
                    return;
                } else {
                    boolean b = false;
                    while(b) {
                        while(BasicCommunication.isNumber(s) == false && turnCount == t ) {
                            BasicCommunication.InformPlayer("wrongInput");
                            s = BasicCommunication.Scanner();
                        }
                        if(buyCards.size()>= Integer.valueOf(s)) {
                            b = true;
                        }
                    }
                }

                int v = Integer.valueOf(s);
                Card a = buyCards.get(v);
                Card.BuyCard(player, a);
            }
            */
        }

        public void UseAllBoughtCards(Player player) {
            /*
            int t = turnCount;
            for(Card a : player.boughtCards) {

                String s = player.boughtCards.indexOf(a)+". "+ a.name+" "+ "cost: "+a.resourceCost+" ";
                if(a.addTechnology != 0) {
                    s += "add tech +" + a.addTechnology+ " ";
                }
                if(a.addResources !=0) {
                    s += "add resources +"+a.addResources+ " ";
                }

                for(Effect e : a.effects) {
                    if(e.executionTime >0) {
                        s += "effect executes after "+e.executionTime+" turns ";
                    }

             */
                    /*
                    if(e.damage !=0) {
                        s += "deals " +e.damage + " damage ";
                    }
                    if(e.spawnedUnit !=null) {
                        s += "spawns " + e.spawnedUnit.count + " " + e.spawnedUnit.typeName + " ";
                    }
                    */
            /*
                    if(e.addToTech != 0) {
                        s += "add tech +" + a.addTechnology+ " ";
                    }

                    if(e.addToResources != 0) {
                        s += "add resources +"+a.addResources+ " ";
                    }
                }
                BasicCommunication.InformPlayer(s);
                Card.ExecuteCard(a);
                if(t == turnCount) {
                    return; // wrong, should reload screen and activate message log and shopping Phase
                }
            }

             */
        }

        private void CommandUnitsInSector(Sector s, Player p) {
            /*
            boolean b = true;
            while(b) {
                BasicCommunication.InformPlayer("Choose unit to control or go back typing g");
                if(p.revealedSectors.contains(s)) {
                    ArrayList<Unit> allies = new ArrayList<Unit>();
                    for(Unit u : s.units) {
                        if(u.owner == p) {
                            allies.add(u);
                            BasicCommunication.InformPlayer((allies.size()-1)+". "+ u.typeName+" count: "+u.count);
                        } else {
                            BasicCommunication.InformPlayer(u.owner+" "+u.typeName+" count: "+u.count);
                        }
                    }
                    b = SelectUnitsToCommand(p, allies);
                } else {
                    return;
                }
            }

             */

        }

        private boolean SelectUnitsToCommand(Player player, ArrayList<Unit> allies) {
            /*
            boolean b = true;
            while(b) {
                String i = BasicCommunication.Scanner();
                if(BasicCommunication.isNumber(i)) {
                    if(Integer.valueOf(i)<allies.size()) {
                        b = ShowUnitAbilitiesAndDescription(allies.get(Integer.valueOf(i)));
                    }
                } else if(i.equals("g")) {
                    return false;
                }
            }

             */
            return true;
        }

        private boolean ShowUnitAbilitiesAndDescription(Unit unit) {
            /*
            boolean b = true;
            while(b) {
                BasicCommunication.InformPlayer(unit.typeName+": type g to return");
                BasicCommunication.InformPlayer(unit.unitDescrition);
                int counter = 0;
                for(Abilities a : unit.abilities) {
                    BasicCommunication.InformPlayer(counter+". "+a.abilityName);
                    counter++;
                }

                b = UseUnitAbilities(unit);
            }

             */
            return false;
        }

        private static boolean UseUnitAbilities(Unit u) {
            /*
            boolean b = true;
            while(b) {
                String i = BasicCommunication.Scanner();
                if(BasicCommunication.isNumber(i)) {
                    if(0<=Integer.valueOf(i) && Integer.valueOf(i)<u.abilities.size() ) {
                        Abilities.CallAbility(u.abilities.get(Integer.valueOf(i)));
                    }
                } else if(i.equals("g")) {
                    return false;
                }
            }

             */
            return true;
        }

        public static void AbilitySelectSector(Abilities a, Player p) {

            gameScreen.AbilityChooseSector(a);
        }

        public void ApplyAbilityForSector(Sector s) {
            gameScreen.casterAbility.targetSector = s;
            Abilities.ExecuteAbility(gameScreen.casterAbility);

            gameScreen.casterAbility = null;
            gameScreen.ShowUnitAbilities();
        }
}

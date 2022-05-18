package neutron.tutorial.gamedata;

import java.util.ArrayList;

import static neutron.tutorial.gamedata.Game.gameScreen;

public class Player {
    public String playerName;
    public int race; // 0 - federation, 1 - aliens, 2 - monks

    public int resources = 1;
    public int technology = 1;
    public int resourceIncome = 0;

    public boolean defeated;
    public boolean isComputer;
    //
    public Sector homeSector;
    //
    public ArrayList<Unit> units = new ArrayList<>();
    public ArrayList<Card> boughtCards = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>(); //used to track effects targeting this player
    public ArrayList<String> turnSummary = new ArrayList<>();
    public Game game;

    //Types
    public ArrayList<Unit> unitTypes = new ArrayList<>();
    public ArrayList<Card> cardTypes = new ArrayList<>();
    public ArrayList<Abilities> abilityTypes = new ArrayList<>();
    public ArrayList<Effect> effectTypes = new ArrayList<>();

    // revealed - actual player vision, visible - can be seen as a sector in screen tab
    public ArrayList<Sector> revealedSectors = new ArrayList<>();
    public ArrayList<Sector> visibleSectors = new ArrayList<>();


	/*

	public ArrayList<Integer> sightRangeOfSectors = new ArrayList<Integer>(); // per num of revealedSectors [decloakPower][decloakrange]
	public ArrayList<Integer> decloakRangeOfSectors = new ArrayList<Integer>(); // [decloakpower] decloakRange
	public ArrayList<Integer> decloakPowerOfSectors = new ArrayList<Integer>(); // [decloakpower] decloakRange
	*/

    public Player(String newPlayerName, int newrace, Game thisgame) {
        playerName = newPlayerName;
        race = newrace;
        game = thisgame;
    }

    public void AddResource(int r) {
        resources += r;
        if(resources <0) {
            resources = 0;
        }
        gameScreen.UpdateResources();
        for(Card c : cardTypes) {
            Card.CheckCardCost(c);
        }
    }

    public void AddTechnology(int t) {
        technology += t;
        if(technology <0) {
            technology = 0;
        }
        gameScreen.UpdateTechnology();
    }

    public void AddResourceIncome(int i) {
        resourceIncome += i;
        gameScreen.UpdateResources();
    }

	/*
	public static int GetHighestDecloakInSector(Player player, Sector sector, int range) {
		int b = 0;
		if(player.revealedSectors.contains(sector)) {
			int l = player.revealedSectors.indexOf(sector);
			if(player.decloakRangeOfSectors.get(l)>=range) {
				if(player.decloakPowerOfSectors.get(l)>b) {
					b = player.decloakPowerOfSectors.get(l);
				}
			}
		}
		return b;
	}
	*/
}


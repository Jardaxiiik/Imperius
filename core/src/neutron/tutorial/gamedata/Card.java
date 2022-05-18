package neutron.tutorial.gamedata;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

import static neutron.tutorial.gamedata.Game.gameScreen;

public class Card {
    public int resourceCost;
    public String name;
    public Player player;
    public Image icon = null;

    //requirments
    //public boolean[] isOffered;
    public int technologyRequired;
    public int resourcesRequired;

    public boolean costScalesWithBases = false;

    // Control
    public ArrayList<Effect> effects = new ArrayList<>();

    public int addTechnology; //if creates units using create effect then these units can have effect to decrease technology
    public int addResources;

    public Card(Player newPlayer) {
        //Type
        player = newPlayer;
        player.cardTypes.add(this);
    }

    public Card(Card typeCard, Player forplayer)  {
        resourceCost = typeCard.resourceCost;
        name = typeCard.name;
        player = forplayer;

        //requirments
        //isOffered = typeCard.isOffered;
        technologyRequired = typeCard.technologyRequired;
        resourcesRequired = typeCard.resourcesRequired;

        //
        effects.addAll(typeCard.effects);

        addTechnology = typeCard.addTechnology; //if creates units using create effect then these units can have effect to decrease technology
        addResources = typeCard.addResources;
    }

    public static ArrayList<Card> GenerateCardsOffer (Player player, int turn) {
        ArrayList<Card> cardsOffer = new ArrayList<>();
        boolean b = player.cardTypes.isEmpty();
        if(b) { return new ArrayList<>();
        }
        for(Card a : player.cardTypes) {
            if(a.resourcesRequired<=player.resources && a.technologyRequired<=player.technology) { //&& a.isOffered.length>turn
                Card c = new Card(a,player);
                cardsOffer.add(c);
				/*
				if(a.isOffered[turn] == true) {
					cardsOffer.add(a);
				}
				*/
            }
        }
        return cardsOffer;
    }

    public static boolean BuyCard(Player buyer, Card card, Sector boughtPlace) {
        int left = buyer.resources - card.resourceCost;
        if(left < 0) {
            return false;
        } else {
            card.player.AddResource(-card.resourceCost);
            System.out.println("card "+card.name + " successfully bought");
            gameScreen.UpdateResources();
        }

        ApplyCardEffect(card, boughtPlace);

        return true;
    }

    public static void ApplyCardEffect(Card card,Sector sector) {
        if(sector != null) {
            if(!card.effects.isEmpty()) {
                for(Effect a : card.effects) {
                    Effect b = new Effect(a,sector,null,null);
                    //gameScreen.ongoingEffects.add(b);
                    Effect.CallEffect(b);
                    //for now instant
                }
            }
        }

        card.player.AddResource(card.addResources);
        card.player.AddTechnology(card.addTechnology);
    }

    public static void CheckCardCost(Card c) {
        if(c.costScalesWithBases) {
            c.resourceCost = c.player.resourceIncome*2;
        }
        gameScreen.UpdateCardTab();
    }
}


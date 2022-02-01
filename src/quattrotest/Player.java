package quattrotest;

import java.util.ArrayList;

public class Player {
	
	//variable to store name of player
	String name;
	
	//list of cards present in player's hand
	ArrayList<StandardColorCard> CardsInHand = new ArrayList<StandardColorCard>();
	
	//constructor
	public Player(String name) {
		super();
		this.name = name;
	}

	//return all cards present in player's hand
	public ArrayList<StandardColorCard> getCardsInHand() {
		return this.CardsInHand;
	}

	//returns name of player
	public String getName() {
		return name;
	}

	//sets name of player
	public void setName(String name) {
		this.name = name;
	}
	
	//returns valid card if any present in player's hand, else null is returned
	//@param - StandardColorCard [top card of discard pile]
	public StandardColorCard getValidCardFromHand(StandardColorCard card) {
		//Logic - compare given card with all cards present in player's hand, if matched any, return that card
		for(int i=0;i<CardsInHand.size();i++) {
			StandardColorCard handCard = CardsInHand.get(i);
			if(handCard.color == card.color || handCard.value == card.value) {     //color and value of cards are compared
				CardsInHand.remove(i);											   //matched card is removed from hand of player
				return handCard;
			}
		}
		return null;
	}
	
	//the given card is added to player's hand
	//@param - StandardColorCard [top card of draw pile]
	public void addCardToHand(StandardColorCard card) {
		CardsInHand.add(card);
	}
	
	//returns total cards present in player's hand
	public int getCardCount() {
		return CardsInHand.size();
	}	
	
	//if present, card with given code is removed from player's hand and returned, else null is returned.
	//@param - int [Card Code]
	public StandardColorCard removeCard(int code) {
		StandardColorCard handCard;
		for(int i=0;i<CardsInHand.size();i++) {
			handCard = CardsInHand.get(i);
			if(handCard.getCardCode()==code) {
				CardsInHand.remove(i);
				return handCard;
			}
		}
		return null;		
	}
	
	//show all cards present in player's hand
	public void showCards() {
		System.out.println("Cards that " + name +" has are:");
		for(int i=0;i<CardsInHand.size();i++) {
			System.out.println(CardsInHand.get(i));
		}
	}
	
}

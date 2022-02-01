package quattrotest;

import java.util.ArrayList;
import java.util.Collections;

public class DiscardPile {
	
	//array-list that contains cards present in discard pile
	ArrayList<StandardColorCard> discardpile = new ArrayList<StandardColorCard>();     
	
	//returns all cards present in the discard pile
	public ArrayList<StandardColorCard> getDiscardpile() {
		Collections.shuffle(discardpile);						
		return discardpile;
	}
	
	//to check if discard pile is empty or not
	public boolean isEmpty() {
		return discardpile.size() == 0;
	}

	//to add given card(argument) to the top of discard pile
	public void addToPile(StandardColorCard card) {
		discardpile.add(card);
	}
	
	//returns top card of discard pile and returns null if pile is empty
	public StandardColorCard top() {
		if(isEmpty()) {
			System.out.println("Discard Pile is Empty"); //throws exception
			return null;
		}
		StandardColorCard card = discardpile.get(discardpile.size()-1);
		return card;		
	}
	
	//removes and return top card of discard pile and returns null if pile is empty
	public StandardColorCard fetchTop() {
		if(isEmpty()) {
			System.out.println("Discard Pile is Empty"); //throws exception
			return null;
		}
		StandardColorCard card = discardpile.get(discardpile.size()-1);
		discardpile.remove(discardpile.size()-1);
		return card;		
	}
	
	//to empty the discard pile
	public void clear() {
		discardpile.clear();
	}
		
}

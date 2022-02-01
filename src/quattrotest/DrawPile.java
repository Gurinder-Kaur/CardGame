package quattrotest;

import java.util.ArrayList;
import java.util.Collections;

public class DrawPile {
	
	//array-list that contains cards present in draw pile
	ArrayList<StandardColorCard> drawpile = new ArrayList<StandardColorCard>();
	
	//constructor to initialize draw pile with 40 color cards
	DrawPile(){
		initializePile();
		Collections.shuffle(drawpile);
	}
	
	//add 40 color cards to draw pile
	private void initializePile() {
		for(StandardColors item: StandardColors.values()) {         // add cards of standard colors - BLUE, RED, GREEN, YELLOW
			for(int i=0;i<10;i++) {									// add 10 cards (0-9) of each standard color
				StandardColorCard card = new StandardColorCard(i,item.name());
				drawpile.add(card);
			}
		}
	}
	
	//returns draw pile
	public ArrayList<StandardColorCard> getDrawpile() {
		return drawpile;
	}

	//insert given set of cards(arguments) to empty draw pile
	public void setDrawpile(ArrayList<StandardColorCard> drawpile) {
		if(isEmpty()) {
			System.out.println("Can't fill non-empty draw pile");
			return;
		}
		this.drawpile = drawpile;
		Collections.shuffle(drawpile);
	}
	
	//removes and return top card from draw pile and returns null if pile is empty
	public StandardColorCard pop() {
		if(isEmpty()) {
			System.out.println("Draw Pile is Empty"); //throw exception
			return null;
		}
		StandardColorCard card = drawpile.get(drawpile.size()-1);
		drawpile.remove(drawpile.size()-1);
		return card;
	}
	
	//to check if draw pile is empty or not
	public boolean isEmpty() {
		return drawpile.size() == 0;
	}
	
}

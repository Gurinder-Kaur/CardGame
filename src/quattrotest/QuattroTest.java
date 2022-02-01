package quattrotest;

import java.util.Scanner;

public class QuattroTest {
	
	static Scanner sc= new Scanner(System.in);
	
	//draw pile is created, from where user will fetch the card
	static DrawPile draw = new DrawPile();
	
	//discard pile is created, to where user will add the discarded card
	static DiscardPile discard = new DiscardPile();
	
	//variable to keep track of game, if any player wins, gamefinish changes to true
	static boolean gamefinish = false;
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------------------------------------- ");
		System.out.println("	    WELCOME TO THE CARD GAME ");
		System.out.println("------------------------------------------------- ");
		System.out.println();
		String name = getName();
		System.out.println("Player Name is: "+ name);
		
		//four players are created, 1 user and 3 bots
		Player human = new Player(name);
		Player bot1 = new Player("Bot-1");
		Player bot2 = new Player("Bot-2");
		Player bot3 = new Player("Bot-3");
		
		//game starts - one card is added to discard pile from draw pile
		discard.addToPile(draw.pop());
		
		//initially 3 cards are given to each player from draw pile
		allotCards(human);
		allotCards(bot1);
		allotCards(bot2);
		allotCards(bot3);
		
		//variable to keep track of turn between players
		int turn = 0;
		
		//game goes on in loop unless game finish changes to true
		while(!gamefinish) {
			Thread.sleep(1000);
			
			//check draw pile, if empty all cards from discard pile is added to draw pile except top card
			if(draw.isEmpty()) {
				StandardColorCard discardedcard = discard.fetchTop();
				draw.setDrawpile(discard.getDiscardpile());
				discard.clear();
				discard.addToPile(discardedcard);
				System.out.println("Cards shuffled [ dicard pile --> draw pile ]");
			}
			
			//turn switches between players
			switch(turn % 4) {
			case 0:
				HumanPlaying(human);
				turn++;
				break;
			case 1:
				BotPlaying(bot1);
				turn++;
				break;
			case 2:
				BotPlaying(bot2);
				turn++;
				break;
			case 3:
				BotPlaying(bot3);
				turn++;
				break;
			}
		}	
		
	}
	
	//method to get player's name
	//exception is thrown if no name is entered
	public static String getName() throws IllegalArgumentException{
		String name = "";
		boolean validInput = false;											
		while(!validInput){											//loop to ask user to enter name again unless valid name is entered
			try {
				System.out.print("Enter your name: ");
				name= sc.nextLine();
				if(name.isBlank()) 
					throw new IllegalArgumentException();			// if no input given by user, exception is thrown
				else
					validInput = true;			
					
			}
			catch (IllegalArgumentException e) {					//exception catch with message
				System.out.println("Enter a name with one or more non-space characters.");
			}
		}		
		return name;
		
	}

	//method to give three cards to each player from draw pile initially
	public static void allotCards(Player player) {
		for(int i=0;i<3;i++) {
			player.addCardToHand(draw.pop());
		}
	}
	
	//method to define rules for bot
	public static void BotPlaying(Player player) {
		System.out.println("------------------------------------------------- ");
		StandardColorCard discardedcard = discard.top();
		System.out.println("The top card is: " + discardedcard);
		
		//valid card is selected from bot's hand, if any
		//argument - top card of discard pile
		StandardColorCard botSelection = player.getValidCardFromHand(discardedcard);
		
		if(botSelection!=null) {						//if valid card is available in bot's hand, card is added to top of discard pile
			System.out.println(player.name + " has played card " + botSelection.getColor() + " " + botSelection.getValue());
			discard.addToPile(botSelection);
		}
		else {											//if no valid card available in bot's hand, card from draw pile is added to bot's hand
			player.addCardToHand(draw.pop());
			System.out.println(player.name + " has picked up a card ");
		}
		int cardcount = player.getCardCount();          //get count of cards in bot's hand
		System.out.println(player.name + " has following number of cards "+ cardcount);
		if(cardcount==0) {							    //if count is zero, game over and bot becomes the winner
			System.out.println(player.name + " is the winner.");
			gamefinish = true;
		}
		
	}

	//method to define rules for user
	public static void HumanPlaying(Player human) throws IllegalArgumentException{
		System.out.println("------------------------------------------------- ");
		System.out.println();
		StandardColorCard discardedcard = discard.top();
		System.out.println("The top card is: " + discardedcard.getColor() +" "+ discardedcard.getValue());	
		human.showCards();
		
		//user is asked to enter color code of card it want to play
		System.out.println("Enter the card code of the card you would like to play ");
		System.out.println("If you don't have a valid card enter 0 ");
		boolean validInput = false;
			
		while(!validInput) {
			try {
				int cardcode = sc.nextInt();
				 //choice 0 means no card to play and card from draw pile is added to user's hand
				if(cardcode == 0) {                      
					StandardColorCard topcard = draw.pop();
					human.addCardToHand(topcard);
					System.out.println("A card has been added to your hand");
					human.showCards();
					validInput = true;
				}
				else {
					
					//card with given code is removed from user's hand, if present, else exception is thrown
					StandardColorCard removecard = human.removeCard(cardcode);
					if(removecard==null) {
						throw new IllegalArgumentException();
					}
					//validation: removed card is compared with top card of discard pile,
					//if color or value of cards differs,removed card is again added to user's hand and exception is thrown
					else if(removecard.getColor()!=discardedcard.getColor() && removecard.getValue()!=discardedcard.getValue()) {
						human.addCardToHand(removecard);
						System.out.println("Rule: Either color or number should match with top card");
						throw new IllegalArgumentException();
					}
					//if removed card is valid, it is added to top of discard pile
					else {
						System.out.println(human.name + " has played " + removecard.getColor() + " " + removecard.getValue());
						discard.addToPile(removecard);
						System.out.println(human.name + " has following number of Cards: " + human.getCardCount());
						validInput = true;
					}
				}
				
			}
			//exception caught and user is asked to enter card code again
			catch(IllegalArgumentException e) {
				System.out.println("Please enter valid Card code.");
			}
			
		}	
		
		//check count of cards in user's hand. If zero, user won and game over
		if(human.getCardCount()==0) {
			System.out.println("CONGRATULATIONS, YOU WON!!!");
			gamefinish = true;
		}
	}
	
}

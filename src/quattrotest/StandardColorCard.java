package quattrotest;

public class StandardColorCard extends ColorCard {

    //unique code given to each card
	public int cardCode;
	
	//color of the card
	public StandardColors color;

	//constructor
	public StandardColorCard(Integer value, String color) throws IllegalArgumentException{
		if(value<0 && value>9) {
			throw new IllegalArgumentException("Number must be between 0-9");  //if value of card is out of range 0-9, exception is thrown
		}
		if(!StandardColors.isExist(color)) {
			throw new IllegalArgumentException("Add standard color only");		//if color of card is out of enum- StandardColors, exception is thrown
		}
		this.color = StandardColors.valueOf(color);
		this.value = value;
		this.cardCode = this.color.getValue()*10 + (value+1);		//logic to give unique code to each card		
	}

	//returns unique code of card
	public int getCardCode() {
		return cardCode;
	}

	//returns color of card
	public String getColor() {
		return color.name();
	}

	@Override
	public String toString() {
		return "Card Code: " + cardCode + " - " + color + " " + value;
	}

}

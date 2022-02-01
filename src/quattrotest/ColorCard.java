package quattrotest;

public class ColorCard extends Card {
	
	String cardType = "ColorCard";     
	String color;
	Integer value;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public ColorCard() {}
	public ColorCard(Integer value, String color) {
		this.value = value;
		this.color = color;
	}

	@Override
	public String cardType() {
		return this.cardType;
	}

}

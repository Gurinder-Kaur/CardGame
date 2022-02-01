package quattrotest;

public abstract class Card {
	private boolean available = true;
	protected String cardType;

	
	public abstract String cardType();
	
	public boolean isAvailable() {
		return available;
	}
	
	public void markUnavailable() {
		this.available = false;
	}
	
	public void markAvailable() {
		this.available = true;
	}

}

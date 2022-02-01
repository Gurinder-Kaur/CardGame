package quattrotest;

//enum to define fixed set of standard colors 

public enum StandardColors {
	
	Blue(0), Yellow(1), Red(2), Green(3);
	private int value;

	StandardColors(int i) {
		this.setValue(i);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	//to check if given color(argument) exists in standard colors or not
	public static Boolean isExist(String color) {
		for(StandardColors item: StandardColors.values()) {
			if(color.equals(item.name())) {
				return true;
			}
		}
		return false;
	}
	
}

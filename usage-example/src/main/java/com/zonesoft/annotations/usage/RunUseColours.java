package com.zonesoft.annotations.usage;

public class RunUseColours {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Colours colours = new Colours();
		System.out.println("BLUE=" + Colours.BLUE);
//		System.out.println("GREEN=" + colours.GREEN);
		System.out.println("ORANGE=" + Colours.ORANGE);
		System.out.println("YELLOW=" + Colours.YELLOW);
		System.out.println("PURPLE=" + Colours.PURPLE);
		System.out.println("RED=" + Colours.RED);
	}

}

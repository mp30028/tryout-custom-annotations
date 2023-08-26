package com.zonesoft.annotations.usage;

public class RunUseRivers {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Rivers rivers = new Rivers();
		System.out.println("CONGO=" + Rivers.CONGO);
		System.out.println("NIGER=" + Rivers.NIGER);
		System.out.println("NILE=" + Rivers.NILE);
		System.out.println("ZAMBEZI=" + Rivers.ZAMBEZI);

	}

}

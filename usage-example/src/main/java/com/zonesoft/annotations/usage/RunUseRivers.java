package com.zonesoft.annotations.usage;

import com.zonesoft.annotations.e2e_testing.PageModelFactory;

public class RunUseRivers {

	public static void main(String[] args) {
		Rivers rivers = PageModelFactory.createPageModel(Rivers.class);
		System.out.println("CONGO=" + rivers.CONGO());
		System.out.println("NIGER=" + Rivers.NIGER);
		System.out.println("NILE=" + rivers.NILE());
		System.out.println("ZAMBEZI=" + rivers.ZAMBEZI());
		System.out.println("Page-Path=" + rivers.getPagePath());
	}

}

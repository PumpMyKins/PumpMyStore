package fr.pumpmybstore.rank;

public enum Rank {

	TIER1(1,1,1),
	TIER2(3,3,3),
	TIER3(5,5,5);
	
	private int modification;
	private int kitchoice;
	private int kitrandom;
	
	private Rank(int modification, int kitchoice , int kitrandom) {
		this.modification = modification;
		this.kitchoice = kitchoice;
		this.kitrandom = kitrandom;
	}

	public int getModification() {
		return modification;
	}

	public int getKitPerServerChoice() {
		return kitchoice;
	}

	public int getKitGlobalRandom() {
		return kitrandom;
	}
	
}

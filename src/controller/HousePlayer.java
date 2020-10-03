package controller;

public class HousePlayer {
	private String name;
	
	public HousePlayer() {
		name = "House";
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

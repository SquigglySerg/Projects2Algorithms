package main;

public class TableCell {
	private int cost;
	private Direction parent_dir;
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Direction getParent_dir() {
		return parent_dir;
	}

	public void setParent_dir(Direction parent_dir) {
		this.parent_dir = parent_dir;
	}

	@Override
	public String toString() {
		return "[" + cost + "]";
	};
}

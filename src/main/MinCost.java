package main;

public class MinCost {
	static public int P;
	static public int C;
	private int[][] table;
	private int[] g;
	private int n; // must be size of g
	
	public MinCost(int P, int C, int[] g, int n) {
		super();
		this.P = P;
		this.C = C;
		this.g = g;
		this.n = n;
	}
	
	public TableCell[][] generate_table() {
		TableCell[][] out = new TableCell[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = i; j < n; j++) {
				if(i == 0 && j == 0) {
					out[i][j].setCost(P);	// first element
					break;
				}
				else if(i == 0) {	// first row
					out[i][j].setCost(out[i][j-1].getCost() + (j-1)*C*g[j]);
					break;
				}
				int[] option = new int[3];
				option[0] = out[i-1][j].getCost() + j*C*g[i]; // top
				option[1] = out[i-1][j-1].getCost() + j*C*g[i] + P; // top-left
				int min = option[0];
				if(min > option[1]) {	// top-left
					min = option[1];
					out[i][j].setParent_dir(Direction.UP_LEFT);
				}
				else {
					min = option[0]; //top
					out[i][j].setParent_dir(Direction.UP);
				}
				if(i == j) {	// if there is no left neighbor, do not attempt option 2
					out[i][j].setCost(min);
					break;
				}
				option[2] = out[i][j-1].getCost() + P; // left
				if(min > option[2]) {
					min = option[2];
					out[i][j].setParent_dir(Direction.LEFT);
				}
				out[i][j].setCost(min);
			}
		}
		return out;
	}
	
	//public get_path()
}

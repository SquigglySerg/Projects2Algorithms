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
	
	public int[][] generate_table() {
		int[][] out = new int[n][n];
		for(int i = 1; i <= n; i++) {
			for(int j = i; j <= n; j++) {
				if(i == 0 && j == 0) {
					out[i][j] = P;	// first element
				}
				else if(i == 0) {	// first row
					out[i][j] = out[i][j-1] + (j-1)*C*g[j];
				}
				int[] option = new int[3];
				option[0] = j*C*g[i]; // top
				option[1] = option[0] + P; // top-left
				option[2] = P; // left
				if(i == j) {	// ignore the left option
					int min = option[0];
					if(min > option[1]) {
						min = option[1];
					}
					out[i][j] = out[]
				}
			}
		}
		return out;
	}
	
}

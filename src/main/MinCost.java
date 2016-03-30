package main;

import java.util.ArrayList;

public class MinCost {
	static public int P;
	static public int C;
	private TableCell[][] table;
	private int[] g;
	private int n; // must be size of g
	private ArrayList<String> path;
	
	public ArrayList<String> getPath() {
		return path;
	}

	private TableCell[][] generate_table() {
		TableCell[][] out = new TableCell[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = i; j < n; j++) {
				out[i][j] = new TableCell();
				if(i == 0 && j == 0) {
					out[i][j].setCost(P);	// first element
					out[i][j].setParent_dir(Direction.START);
					continue;
				}
				else if(i == 0) {	// first row
					out[i][j].setCost(out[i][j-1].getCost() + (j-1)*C*g[j]);
					continue;
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
					continue;
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
	
	public MinCost(int P, int C, int[] g, int n) {
		super();
		this.P = P;
		this.C = C;
		this.g = g;
		this.n = n;
		this.table = generate_table();
		ArrayList<String> path = new ArrayList<String>();
		TableCell nextCell = this.table[n-1][n-1];
		int i = n-1, j = n-1;
		while(nextCell.getParent_dir() != Direction.START) {
			if(i == 0) {
				path.add(0, "store");
				path.add(0, "next day");
				j -= 1;
			}
			if(nextCell.getParent_dir() == Direction.UP_LEFT) {
				path.add(0, "order/store");
				path.add(0, "next day");
				i -= 1;
				j -= 1;
			}
			if(nextCell.getParent_dir() == Direction.UP) {
				path.add(0, "store");
				path.add(0, "next day");
				i -= 1;
			}
			if(nextCell.getParent_dir() == Direction.LEFT) {
				path.add(0, "order");
				j -= 1;
			}
			if(nextCell == this.table[i][j]) {
				System.out.println("Warning: No progress! Stopping...");
				System.out.println(path.toString());
				return;
			}
			nextCell = this.table[i][j];
		}
		path.add(0, "order");
		path.add(path.size(), "end");
		this.path = path;
	}
	
	public static void main(String[] args) {
		int n = 5;
		int[] g = new int[n];
		g[0] = 5;
		g[1] = 2;
		g[2] = 3;
		g[3] = 1;
		g[4] = 5;
		MinCost test = new MinCost(5, 2, g, n);
		System.out.println(test.getPath().toString());
	}
}

import java.util.ArrayList;
import java.util.Vector;

public class Projects2algorithms {
	
	public static void main(String[] args) {
		Projects2algorithms test = new Projects2algorithms();
		int n = 6;
		int[] gallons_req = new int[] {0, 10, 50, 20, 40, 30, 30};
		test.get_table(gallons_req, n);
	}
	
	// gallons_req must be n+1 size
	public int[][] get_table(int[] gallons_req, int n) {
		int[][] table = new int[n + 1][n + 1];
		table[0][0] = 0;
		//first row: table[0][i]
		for(int i = 1; i <= n; i++) {
			table[0][i] = table[0][i-1] + gallons_req[i];
		}
		//first column: table[i][0]
		for(int i = 1; i <= n; i++) {
			table[i][0] = -gallons_req[i];
		}
		for(int i = 1; i <= n; i++) {
			System.out.println();
			for(int j = 1; j <= n; j++) {
				// table[i][j], i refers to row #, j refers to column #
				if(i + j > n) {
					table[i][j] = Integer.MAX_VALUE;
					continue;
				}
				table[i][j] = table[i][j-1] + gallons_req[i + j];
			}
		}
		return table;
	}
	
	private boolean option_is_valid(int[][] table, int[] option, int L) {
		int total = table[0][option[0]];
		int n = table[0].length - 1;
		for(int i = 1; i <= n; i++) {
			if(total + table[i][option[i]] > L) {
				return false; // not a valid option, cannot hold more than L gallons
			}
			total += table[i][option[i]];
			if(i == n && total != 0) {
				return false; // not a valid option, must have 0 left over
			}
		}
		return false;
	}
	
	public ArrayList<int[]> get_valid_options(int[][] table, int[] option, int x, ArrayList<int[]> options, int L) {
		int n = table[0].length - 1;
		if(x == n) {
			if(option_is_valid(table, option, L)) {
				options.add(option);
			}
		}
		for(int i = 0; i <= n; i++) {
			int[] next_option = option; 
			next_option[x] = i;
			options = get_valid_options(table, next_option, x + 1, options);
		}
		return options;
	}
}


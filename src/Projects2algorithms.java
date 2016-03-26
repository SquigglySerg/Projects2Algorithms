import java.util.ArrayList;
import java.util.Vector;

public class Projects2algorithms {
	
	public static void main(String[] args) {
		
	}
	
	public double[] min_cost_path(double curr_gals, int curr_day, int n, double[] needed_gals, double[] curr_path) {
		// first check possibility that we do not have enough to cover cost
		if(needed_gals[curr_day + 1] > curr_gals) {
			// have to make an order
			// order size varies by how much would be required for the ____ days ahead
			// where we compare to find the best option recursively
			// ex: ordering to last 1 day vs. ordering to last the next 2 days
			double amount = needed_gals[curr_day + 1] - curr_gals;
			double[] possibilities = new double[n - curr_day];
			for(int i = 0; i < n - curr_day; i++) {
				possibilities[i] = amount;
				amount += needed_gals[i + curr_day + 1];
			}
			// now try all possibilities
			for(int i = 0; i < n; i++) {
				
			}
		}
		// otherwise, choose between making and not making an order, where the order size varies
		// by how much would be required for the ____ days ahead
		return null;
	}
	
}

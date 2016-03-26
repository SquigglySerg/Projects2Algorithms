import java.util.ArrayList;
import java.util.Vector;

public class Projects2algorithms {
	
	public static void main(String[] args) {
		
	}
	
	// correct to initially call min_cost_path(true, 0, 0, 0, n, gi, empty array)
	public double[] min_cost_path(double curr_gals, int curr_day, int n, double[] needed_gals, double[] curr_path) {
		// first check possibility that we do not have enough to cover cost
		boolean make_order = false;
		if(needed_gals[curr_day + 1] > curr_gals) {
			make_order = true;
		}
		if(make_order) {
			// have to make an order
			// order size varies by how much would be required for the ____ days ahead
			// where we compare to find the best option recursively
			// ex: ordering to last 1 day vs. ordering to last the next 2 days
			double min_amount = needed_gals[curr_day + 1] - curr_gals;
			double possible_amount = min_amount;
			// first possibility: we order enough for the next day only:
			double[] possibilities = new double[n];
			possibilities[0] = possible_amount;
			for(int i = 1; i <= n; i++) {
				possible_amount += needed_gals[i];
				possibilities[i] = possible_amount;
			}
		}
		// otherwise, choose between making and not making an order, where the order size varies
		// by how much would be required for the ____ days ahead
		return null;
	}
	
}

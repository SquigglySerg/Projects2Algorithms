import java.util.ArrayList;

public class GasStation {
	private int P;
	private int C;
	private int L;
	private int K;
	
	private ArrayList<Integer> gas;
	private TableCell[][] table;
	
	public GasStation(ArrayList<Integer> gas, int P, int C, int L, int K){
		this.gas = gas;
		this.P = P;
		this.C = C;
		this.K = K;
		this.L = L;
		
		//Initializing the Table
		table = new TableCell[gas.size()][gas.size()];
		for(int i = 0; i < gas.size(); i++){
			for(int j = 0; j <gas.size(); j++){
				table[i][j] = new TableCell();
				table[i][j].setCost(9999999);
			}
		}
		
		//Populating the initial values in the table
		table[0][0].setCost(P);
		table[0][0].setParent_dir(Direction.START);
		
		int gs = gas.get(0);
		for(int i = 1; i < gas.size(); i++){
			if( (gs += gas.get(i)) < L){
				table[0][i].setCost( table[0][i-1].getCost() + (i*C*gas.get(i)) );
				table[0][i].setParent_dir(Direction.START);
				
			}
		}
		
		//printTable();
	}
	
	public int minCost(){
		int lastOrder = 0;
		for(int i = 1; i < gas.size(); i++){
			for(int j = i; j < gas.size(); j++){
				int top = table[i-1][j].getCost();
				int diagonal = table[i-1][j-1].getCost() + P;
				int left = 9999999;
				
				if(i != j){
					left = table[i][j-1].getCost() + (j-lastOrder)*C*gas.get(j);
				}
				
				table[i][j].setCost(top);
				table[i][j].setParent_dir(Direction.UP);
				if( diagonal <= top){
					table[i][j].setCost(diagonal);
					table[i][j].setParent_dir(Direction.UP_LEFT);
				}
				if( left <= table[i][j].getCost()){
					table[i][j].setCost(left);
					table[i][j].setParent_dir(Direction.LEFT);
				}
				
				
				
				if(table[i][j].getCost() == diagonal)
					lastOrder = j;
				
				if(getStoredGas(lastOrder+1, j+1) > L)
					table[i][j].setCost(9999999);
				
			}
		}
		
		printTable();
		
		return table[gas.size()-1][gas.size()-1].getCost();
	}
	
	public int getStoredGas(int lastOrderDay, int currentDay){
		int gs = 0;
		
		for(int i = lastOrderDay-1; i < currentDay; i++)
			gs += gas.get(i);
		
		return gs;
	}
	
	public void traceback(){
		TableCell cell = table[gas.size()-1][gas.size()-1];
		
		int i = gas.size()-1, j = gas.size()-1;
		while(i>0 && j>0){
			//System.out.println(cell.getParent_dir());
			if( cell.getParent_dir() == Direction.UP_LEFT){
				System.out.println("Ordered Day " + (i+1));
				i--; j--;
				cell = table[i][j];
			}
			else if(cell.getParent_dir() == Direction.LEFT){
				//System.out.println("Ordered Day " + (i+1));
				j--;
				cell = table[i][j];
			}
			else{
				i--;
				cell = table[i][j];
			}
				
		}
		System.out.println("Ordered Day 1");
	}
	
	public void printTable(){
		for(int i = 0; i < gas.size(); i++){
			for(int j = 0; j < gas.size(); j++){
				System.out.print(table[i][j].getCost() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayList<Integer> gas = new ArrayList<>();
		gas.add(100); gas.add(10); gas.add(300); gas.add(50);
		
		GasStation gStore = new GasStation( gas, 20, 1, 300, 2);

		System.out.println("Cost = $" + gStore.minCost());
		gStore.traceback();
	}

}

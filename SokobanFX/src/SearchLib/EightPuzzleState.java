package SearchLib;

public class EightPuzzleState {
int[][] numbers;
	
	public EightPuzzleState(int[][] numbers) {
		this.numbers = numbers;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				str += numbers[i][j];
			}
			str += "\n";
		}
		return str;	
	}
	
	@Override
	public int hashCode() {		
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {	
		EightPuzzleState state = (EightPuzzleState)obj;		
		
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				if (numbers[i][j] != state.numbers[i][j])
					return false;
			}			
		}
		
		return true;
	}

}

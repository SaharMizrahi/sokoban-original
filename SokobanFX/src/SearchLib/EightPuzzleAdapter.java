package SearchLib;

import java.util.HashMap;

public class EightPuzzleAdapter implements Searchable<EightPuzzleState>
{

private EightPuzzle game;
	
	public EightPuzzleAdapter(EightPuzzle game) {
		this.game = game;
	}
	
	@Override
	public State<EightPuzzleState> getInitialState() {
		int[][] board = game.getBoard();
		EightPuzzleState s = new EightPuzzleState(board);
		State<EightPuzzleState> initialState = new State<EightPuzzleState>(s);
		return initialState;
	}

	@Override
	public State<EightPuzzleState> getGoalState() {
		int[][] board = new int[EightPuzzle.SIZE][EightPuzzle.SIZE];
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = count++; 
			}
		}
		
		EightPuzzleState s = new EightPuzzleState(board);
		State<EightPuzzleState> goalState = new State<EightPuzzleState>(s);
		return goalState;
	}
	
	private int[][] copyBoard(int[][] board) {
		int[][] newBoard = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}

	@Override
	public HashMap<Action, State<EightPuzzleState>> getAllPossibleMoves(State<EightPuzzleState> state) {
		int[][] board = state.getState().numbers;
		//List<State<EightPuzzleState>> list = new ArrayList<State<EightPuzzleState>>();
		HashMap<Action, State<EightPuzzleState>> map = new HashMap<>();
		
		Position pos = game.getEmptyCell(board);
		if (pos.getCol() > 0) { // move empty space left
			int[][] newBoard = copyBoard(board);
			newBoard[pos.getRow()][pos.getCol()] = board[pos.getRow()][pos.getCol() - 1];
			newBoard[pos.getRow()][pos.getCol() - 1] = board[pos.getRow()][pos.getCol()];
			
			EightPuzzleState s = new EightPuzzleState(newBoard);
			State<EightPuzzleState> newState = new State<EightPuzzleState>(s);
			map.put(new Action("move left"), newState);
		}
		if (pos.getCol() < board.length - 1) { // move empty space right
			int[][] newBoard = copyBoard(board);
			newBoard[pos.getRow()][pos.getCol()] = board[pos.getRow()][pos.getCol() + 1];
			newBoard[pos.getRow()][pos.getCol() + 1] = board[pos.getRow()][pos.getCol()];
			
			EightPuzzleState s = new EightPuzzleState(newBoard);
			State<EightPuzzleState> newState = new State<EightPuzzleState>(s);
			map.put(new Action("move right"), newState);
		}
		
		if (pos.getRow() < board.length - 1) { // move empty space down
			int[][] newBoard = copyBoard(board);
			newBoard[pos.getRow()][pos.getCol()] = board[pos.getRow() + 1][pos.getCol()];
			newBoard[pos.getRow() + 1][pos.getCol()] = board[pos.getRow()][pos.getCol()];
			
			EightPuzzleState s = new EightPuzzleState(newBoard);
			State<EightPuzzleState> newState = new State<EightPuzzleState>(s);
			map.put(new Action("move down"), newState);
		}
		if (pos.getRow() > 0) { // move empty space up
			int[][] newBoard = copyBoard(board);
			newBoard[pos.getRow()][pos.getCol()] = board[pos.getRow() - 1][pos.getCol()];
			newBoard[pos.getRow() - 1][pos.getCol()] = board[pos.getRow()][pos.getCol()];
			
			EightPuzzleState s = new EightPuzzleState(newBoard);
			State<EightPuzzleState> newState = new State<EightPuzzleState>(s);
			map.put(new Action("move up"), newState);
		}		
		
		return map;
	}


}


public class checkersRun {

	public static void main(String[] args) {
		Board board = new Board();
		Frame frame = new Frame(board);
		Moves move = new Moves(board, board.getP1Icon(), board.getP2Icon(), board.getP1DoubleIcon(), board.getP2DoubleIcon());
		Checkers checkers = new Checkers(frame, board, move);
	}

}

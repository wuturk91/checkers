import java.awt.*;
import javax.swing.*;

public class Moves {
	private Board board;
	private Cell[][] cells;
	
	private ImageIcon p1Icon;
	private ImageIcon p1DoubleIcon;
	private ImageIcon p2Icon;
	private ImageIcon p2DoubleIcon;
	
	private int yCo;
	private int xCo;
	
	private boolean impendingMovesFound;
	private boolean availableMovesFound;
	private boolean capturesFound;
	private JButton[][] impendingMoves;
	private JButton[][] availableMoves;
	private JButton[][] availableCaptures;
	
	
	private boolean capturePossible;
	private boolean movePossible;
	private boolean moreCaptures;
	
	public Moves(Board gameBoard, ImageIcon p1, ImageIcon p2, ImageIcon p1Double, ImageIcon p2Double) {
		board = gameBoard;
		cells = gameBoard.getCells();
		capturePossible = false;
		moreCaptures = false;
		p1Icon = p1;
		p2Icon = p2;
		p1DoubleIcon = p1Double;
		p2DoubleIcon = p2Double;
	}
	
	public JButton[][] impendingMoves(String currentPlayer) {
		impendingMoves = new JButton[8][3];
		impendingMovesFound = false;
		
		int i = 0;
		
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				JButton sourceCell = cells[y][x].getCell();
				boolean crowned = false;
				if(sourceCell.getIcon() == p1DoubleIcon || sourceCell.getIcon() ==  p2DoubleIcon) {
					crowned = true;
				}
				
				if(sourceCell.getIcon() == board.getCurrentPlayerIcon(currentPlayer) && currentPlayer.equals("Player 1") && !crowned) {
					//Check Top Left
					if(validCoOrdinates(y-1, x-1)) {
						yCo = y-1; xCo = x-1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP2Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP2DoubleIcon())) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y-2, x-2)) {
								yCo = y-2; xCo = x-2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
					//Check Top Right
					if(validCoOrdinates(y-1, x+1)) {
						yCo = y-1; xCo = x+1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP2Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP2DoubleIcon())) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y-2, x+2)) {
								yCo = y-2; xCo = x+2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
				} else if(sourceCell.getIcon() == board.getCurrentPlayerIcon(currentPlayer) && currentPlayer.equals("Player 2") && !crowned) {
					//Check Bottom Left
					if(validCoOrdinates(y+1, x-1)) {
						yCo = y+1; xCo = x-1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP1Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP1DoubleIcon())) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y+2, x-2)) {
								yCo = y+2; xCo = x-2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
					//Check Bottom Right
					if(validCoOrdinates(y+1, x+1)) {
						yCo = y+1; xCo = x+1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP1Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP1DoubleIcon())) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y+2, x+2)) {
								yCo = y+2; xCo = x+2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
				} else if(sourceCell.getIcon() == board.getCurrentPlayerDoubleIcon(currentPlayer) && crowned) {
					//Check Top Left
					if(validCoOrdinates(y-1, x-1)) {
						yCo = y-1; xCo = x-1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y-2, x-2)) {
								yCo = y-2; xCo = x-2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
					//Check Top Right
					if(validCoOrdinates(y-1, x+1)) {
						yCo = y-1; xCo = x+1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y-2, x+2)) {
								yCo = y-2; xCo = x+2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
					//Check Bottom Left
					if(validCoOrdinates(y+1, x-1)) {
						yCo = y+1; xCo = x-1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y+2, x-2)) {
								yCo = y+2; xCo = x-2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
					//Check Bottom Right
					if(validCoOrdinates(y+1, x+1)) {
						yCo = y+1; xCo = x+1;
						if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
							//Source
							impendingMoves[i][0] = cells[y][x].getCell();
							//Opponent Piece/Capture Piece
							impendingMoves[i][1] = cells[yCo][xCo].getCell();
							if(validCoOrdinates(y+2, x+2)) {
								yCo = y+2; xCo = x+2;
								if(cells[yCo][xCo].getCell().getIcon() == null) {
									impendingMoves[i][2] = cells[yCo][xCo].getCell();
									impendingMovesFound = true;
									i++;
								} else {
									impendingMoves[i][0] = null;
									impendingMoves[i][1] = null;
								}
							} else {
								impendingMoves[i][0] = null;
								impendingMoves[i][1] = null;
							}
						}
					}
				}
				
			}
		}
		
		
		return impendingMoves;
	}
	
	public JButton[][] impendingMoves(JButton sourceCell, String currentPlayer) {
		impendingMoves = new JButton[8][3];
		impendingMovesFound = false;
		
		int i = 0;

		boolean crowned = false;
		if(sourceCell.getIcon() == p1DoubleIcon || sourceCell.getIcon() ==  p2DoubleIcon) {
			crowned = true;
		}
		setSourceCoOrdinates(sourceCell);
		if(currentPlayer.equals("Player 1") && !crowned) {
			//Check Top Left
			if(validCoOrdinates(yCo-1, xCo-1)) {
				yCo -= 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP2Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP2DoubleIcon())) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo-1)) {
						yCo -= 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Top Right
			if(validCoOrdinates(yCo-1, xCo+1)) {
				yCo -= 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP2Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP2DoubleIcon())) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo+1)) {
						yCo -= 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
		} else if(currentPlayer.equals("Player 2") && !crowned) {
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Left
			if(validCoOrdinates(yCo+1, xCo-1)) {
				yCo += 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP1Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP1DoubleIcon())) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo-1)) {
						yCo += 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Right
			if(validCoOrdinates(yCo+1, xCo+1)) {
				yCo += 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP1Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP1DoubleIcon())) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo+1)) {
						yCo += 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
		} else if(crowned) {
			setSourceCoOrdinates(sourceCell);
			//Check Top Left
			if(validCoOrdinates(yCo-1, xCo-1)) {
				yCo -= 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo-1)) {
						yCo -= 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Top Right
			if(validCoOrdinates(yCo-1, xCo+1)) {
				yCo -= 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo+1)) {
						yCo -= 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Left
			if(validCoOrdinates(yCo+1, xCo-1)) {
				yCo += 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo-1)) {
						yCo += 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Right
			if(validCoOrdinates(yCo+1, xCo+1)) {
				yCo += 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer))) {
					//Source
					impendingMoves[i][0] = sourceCell;
					//Opponent Piece/Capture Piece
					impendingMoves[i][1] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo+1)) {
						yCo += 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							impendingMoves[i][2] = cells[yCo][xCo].getCell();
							impendingMovesFound = true;
							i++;
						} else {
							impendingMoves[i][0] = null;
							impendingMoves[i][1] = null;
						}
					} else {
						impendingMoves[i][0] = null;
						impendingMoves[i][1] = null;
					}
				}
			}
		}
		
		
		return impendingMoves;
	}
	
	public boolean impendingMovesFound(String currentPlayer) {
		impendingMoves(currentPlayer);
		return impendingMovesFound;
	}
	
	public boolean moreImpendingMovesFound(JButton sourceCell, String currentPlayer) {
		impendingMoves(sourceCell, currentPlayer);
		return impendingMovesFound;
	}
	
	public JButton[][] getImpendingMoves() {
		return impendingMoves;
	}
	
	public JButton[][] availableMoves(JButton sourceCell, String currentPlayer) {
		availableMoves = new JButton[4][1];
		availableCaptures = new JButton[4][2];
		availableMovesFound = false;
		boolean crowned = false;
		if(sourceCell.getIcon() == p1DoubleIcon || sourceCell.getIcon() ==  p2DoubleIcon) {
			crowned = true;
		}
		int c = 0;
		int m = 0;
		setSourceCoOrdinates(sourceCell);
		if(currentPlayer.equals("Player 1") && !crowned) {
			//Check Top Left
			if(validCoOrdinates(yCo-1, xCo-1)) {
				yCo -= 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == board.getP2Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP2DoubleIcon()) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo-1)) {
						yCo -= 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Top Right
			if(validCoOrdinates(yCo-1, xCo+1)) {
				yCo -= 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP2Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP2DoubleIcon())) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo+1)) {
						yCo -= 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
		} else if(currentPlayer.equals("Player 2") && !crowned) {
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Left
			if(validCoOrdinates(yCo+1, xCo-1)) {
				yCo += 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP1Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP1DoubleIcon())) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo-1)) {
						yCo += 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Right
			if(validCoOrdinates(yCo+1, xCo+1)) {
				yCo += 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getP1Icon() || cells[yCo][xCo].getCell().getIcon() == board.getP1DoubleIcon())) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo+1)) {
						yCo += 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
		} else if(crowned) {
			setSourceCoOrdinates(sourceCell);
			//Check Top Left
			if(validCoOrdinates(yCo-1, xCo-1)) {
				yCo -= 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer)) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo-1)) {
						yCo -= 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Top Right
			if(validCoOrdinates(yCo-1, xCo+1)) {
				yCo -= 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer))) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo-1, xCo+1)) {
						yCo -= 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Left
			if(validCoOrdinates(yCo+1, xCo-1)) {
				yCo += 1; xCo -= 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer))) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo-1)) {
						yCo += 1; xCo -= 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
			setSourceCoOrdinates(sourceCell);
			//Check Bottom Right
			if(validCoOrdinates(yCo+1, xCo+1)) {
				yCo += 1; xCo += 1;
				if(cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer))) {
					//Opponent piece
					availableCaptures[c][0] = cells[yCo][xCo].getCell();
					if(validCoOrdinates(yCo+1, xCo+1)) {
						yCo += 1; xCo += 1;
						if(cells[yCo][xCo].getCell().getIcon() == null) {
							availableCaptures[c][1] = cells[yCo][xCo].getCell();
							capturesFound = true;
							c++;
						} else {
							availableCaptures[c][0] = null;
						}
					} else {
						availableCaptures[c][0] = null;
					}
				} else if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
					availableMoves[m][0] = cells[yCo][xCo].getCell();
					availableMovesFound = true;
					m++;
				}
			}
		}
		
		if(c < m) {
			return availableCaptures;
		} else {
			return availableMoves;
		}
		
		
	}
	
	public boolean availableMovesFound(JButton sourceCell, String currentPlayer) {
		availableMoves(sourceCell, currentPlayer);
		return availableMovesFound;
	}
	
	public boolean availableCapturesFound(JButton sourceCell, String currentPlayer) {
		availableMoves(sourceCell, currentPlayer);
		return capturesFound;
	}
	
	public JButton[][] getAvailableMoves() {
		return availableMoves;
	}
	
	public JButton[][] getCaptures() {
		return availableCaptures;
	}
	
	public boolean movesFound(String currentPlayer) {
		boolean found = false;
		
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				JButton sourceCell = cells[y][x].getCell();
				
				boolean crowned = false;
				if(sourceCell.getIcon() == p1DoubleIcon || sourceCell.getIcon() ==  p2DoubleIcon) {
					crowned = true;
				}
				setSourceCoOrdinates(sourceCell);
				if(currentPlayer.equals("Player 1") && cells[y][x].getCell().getIcon() == board.getCurrentPlayerIcon(currentPlayer)  && !crowned) {
					//Check Top Left
					if(validCoOrdinates(yCo-1, xCo-1)) {
						yCo -= 1; xCo -= 1;
						 if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							found = true;
						}
					}
					setSourceCoOrdinates(sourceCell);
					//Check Top Right
					if(validCoOrdinates(yCo-1, xCo+1)) {
						yCo -= 1; xCo += 1;
						 if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							 found = true;
						}
					}
				} else if(currentPlayer.equals("Player 2") && cells[y][x].getCell().getIcon() == board.getCurrentPlayerIcon(currentPlayer)  && !crowned) {
					setSourceCoOrdinates(sourceCell);
					//Check Bottom Left
					if(validCoOrdinates(yCo+1, xCo-1)) {
						yCo += 1; xCo -= 1;
						if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							found = true;
						}
					}
					setSourceCoOrdinates(sourceCell);
					//Check Bottom Right
					if(validCoOrdinates(yCo+1, xCo+1)) {
						yCo += 1; xCo += 1;
						if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							found = true;
						}
					}
				} else if(crowned && cells[y][x].getCell().getIcon() == board.getCurrentPlayerDoubleIcon(currentPlayer)  ) {
					setSourceCoOrdinates(sourceCell);
					//Check Top Left
					if(validCoOrdinates(yCo-1, xCo-1)) {
						yCo -= 1; xCo -= 1;
						if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							found = true;
						}
					}
					setSourceCoOrdinates(sourceCell);
					//Check Top Right
					if(validCoOrdinates(yCo-1, xCo+1)) {
						yCo -= 1; xCo += 1;
						if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							found = true;
						}
					}
					setSourceCoOrdinates(sourceCell);
					//Check Bottom Left
					if(validCoOrdinates(yCo+1, xCo-1)) {
						yCo += 1; xCo -= 1;
						if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
							found = true;
						}
					}
					setSourceCoOrdinates(sourceCell);
					//Check Bottom Right
					if(validCoOrdinates(yCo+1, xCo+1)) {
						yCo += 1; xCo += 1;
						if(cells[y][x].getCell().getIcon() == sourceCell.getIcon() && cells[yCo][xCo].getCell() instanceof JButton && (cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerIcon(currentPlayer) || cells[yCo][xCo].getCell().getIcon() == board.getNextPlayerDoubleIcon(currentPlayer))) {
							if(cells[yCo][xCo].getCell() instanceof JButton && cells[yCo][xCo].getCell().getIcon() == null) {
								found = true;
							}
						}
					}
				}
			}
		}
		return found;			
	}
	
	public void setSourceCoOrdinates(JButton sourceCell) {
		yCo = -1;
        xCo = -1;
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells.length; x++) {
                if (cells[y][x].getCell() == sourceCell) {
                    yCo = y;
                    xCo = x;
                    break;
                }
            }
        }
	}
	
	public boolean validCoOrdinates() {
		boolean valid = false;
		if((yCo > -1 && yCo < 8) && (xCo > -1 && xCo < 8)) {
			valid = true;
		}
		
		return valid;
	}
	
	public boolean validCoOrdinates(int y, int x) {
		boolean valid = false;
		if((y > -1 && y < 8) && (x > -1 && x < 8)) {
			valid = true;
		}
		
		return valid;
	}
	
	public void checkForCrown(String currentPlayer) {
		if (currentPlayer.equals("Player 1")) {
			//Check if top row contains any singlep1icons
			for(int i = 0; i < 8; i++) {
				if(cells[0][i].getCell().getIcon() == p1Icon ) {
					cells[0][i].getCell().setIcon(p1DoubleIcon);
				}
			}
			
		} else if (currentPlayer.equals("Player 2")) {
			//Check if bottom row contains any singlep1icons
			for(int i = 0; i < 8; i++) {
				if(cells[7][i].getCell().getIcon() == p2Icon ) {
					cells[7][i].getCell().setIcon(p2DoubleIcon);
				}
			}
		}
	}
	
	public boolean isCapturePossible(JButton sourceCell, String currentPlayer) {
		availableMoves(sourceCell, currentPlayer);
		return capturesFound;
	}
	
	public boolean isMovePossible() {
		return movePossible;
	}
	
	public ImageIcon getCurrentPlayerIcon(String currentPlayer) {
		if (currentPlayer.equals("Player 1")) {
			return p1Icon;
		} else {
			return p2Icon;
		}
		
	}
	
	public ImageIcon getCurrentPlayerDoubleIcon(String currentPlayer) {
		if (currentPlayer.equals("Player 1")) {
			return p1DoubleIcon;
		} else {
			return p2DoubleIcon;
		}
		
	}
	
	public ImageIcon getNextPlayerIcon(String currentPlayer) {
		if (currentPlayer.equals("Player 1")) {
			return p2Icon;
		} else {
			return p1Icon;
		}
		
	}
	
	public ImageIcon getNextPlayerDoubleIcon(String currentPlayer) {
		if (currentPlayer.equals("Player 1")) {
			return p2DoubleIcon;
		} else {
			return p1DoubleIcon;
		}

	}
	
}

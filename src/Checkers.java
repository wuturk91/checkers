import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Checkers {
	private Frame frame;
	private Board board;
	private Moves moves;
	private Cell[][] cells;
	private String currentPlayer;
    private int player1Score;
    private int player2Score;
    //Game States
    private boolean impendingMoves;
    private boolean moveInProgress;
    private boolean capturing;
    //Game Storage
    private JButton[][] availableMoves;
    private JButton[][] availableCaptures;
    private JButton[][] impendingMovesFound;
    private JButton formerCell;
    private JButton capture;
    
	public Checkers(Frame frame, Board board, Moves moves) {
		this.frame = frame;
		this.board = board;
		this.moves = moves;
		cells = board.getCells();
		addActionListeners();
		impendingMoves = false;
	    moveInProgress = false;
	    capturing = false;
	    formerCell = null;
	    for(int y = 0; y < 8; y++) {
    		for(int x = 0; x < 8; x++) {
    			 cells[y][x].getCell().setEnabled(false);
    		}
    	}
	}
	
	public void addActionListeners() {
		//Add listeners to menu buttons
		frame.getStart().addActionListener(
				(ActionEvent e) -> { start(); });
		frame.getReset().addActionListener(
				(ActionEvent e) -> { reset(); });
		frame.getQuit().addActionListener(
				(ActionEvent e) -> { quit(); });
		for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
            	cells[y][x].getCell().addActionListener(
                    (ActionEvent e) -> { move(e); });
            }
        }
		
	}
	
	public void start() {
		currentPlayer = "Player 1";
		player1Score = 0;
		player2Score = 0;
		//Add listeners to cells
		frame.setPlayerTurn(currentPlayer);
		for(int y = 0; y < 8; y++) {
    		for(int x = 0; x < 8; x++) {
    			 cells[y][x].getCell().setEnabled(true);
    		}
    	}
	}
	
	public void reset() {
		currentPlayer = "Player 1";
		player1Score = 0;
		player2Score = 0;
		board.createBoardGrid();
		board.placeDefaultCounters();
		for(int y = 0; y < 8; y++) {
    		for(int x = 0; x < 8; x++) {
    			 cells[y][x].getCell().setEnabled(true);
    		}
    	}
		impendingMoves = false;
	    moveInProgress = false;
	    capturing = false;
	    formerCell = null;
	}

	public void quit() {
		frame.getFrame().setVisible(false); 
		frame.getFrame().dispose();
	}
	
	public void move(ActionEvent e) {
		//If cell clicked exists, cast to JButton
		JButton sourceCell = null;
    	Object source = e.getSource();
    	if(source instanceof JButton) {
    		sourceCell = (JButton) source;
    	} 
    	if(!moveInProgress) {
    		if(moves.impendingMovesFound(currentPlayer)) {
        		impendingMovesFound = moves.getImpendingMoves();
        		impendingMoves = true;
        		moveInProgress = true;
        	}
    	}

    	if(moveInProgress && impendingMoves && !capturing) {
    		if(isImpendingMove(sourceCell)) {
    			highlightImpendingCaptures(sourceCell);
    			capturing = true;
    		} else {
    			//Update Frame to inform player of a impending capture
    		}

    	} else if(moveInProgress && impendingMoves && capturing) {
    		if(isImpendingCaptureHighlighted(sourceCell)) {
    			sourceCell.setIcon(formerCell.getIcon());
    			formerCell.setIcon(null);
    			capture.setIcon(null);
    			unhighlightImpendingCaptures();
    			moveInProgress = false; 
    			impendingMoves = false; 
    			capturing = false;
				moves.checkForCrown(currentPlayer);
				setScore();
    		
    			if(moves.moreImpendingMovesFound(sourceCell, currentPlayer)) {
            		impendingMovesFound = moves.getImpendingMoves();
            		impendingMoves = true;
            		moveInProgress = true;
            	} else {
    				switchPlayer();
    				endGame();
    			}

    		} else {
    			unhighlightImpendingCaptures();
    			capturing = false;
    		}

    	} else if(!moveInProgress && !impendingMoves && !capturing) {
    		if(isLegal(sourceCell)) {
    			if(moves.availableMovesFound(sourceCell, currentPlayer)) {
    				availableMoves = moves.getAvailableMoves();
    				highlightMoves();
    				moveInProgress = true;
    				formerCell = sourceCell;
    			}
    		}
    		
    	} else if(moveInProgress && !impendingMoves && !capturing) {
    		if(isMoveHighlighted(sourceCell)) {
    			sourceCell.setIcon(formerCell.getIcon());
    			formerCell.setIcon(null);
    			unhighlightMoves();
    			moveInProgress = false;
				moves.checkForCrown(currentPlayer);
    			switchPlayer();
    			endGame();
    			
    		} else {
    			unhighlightMoves();
    			moveInProgress = false;
    		}

    	} else if(moveInProgress && !impendingMoves && capturing) {
    		if(isCaptureHighlighted(sourceCell)) {
    			sourceCell.setIcon(formerCell.getIcon());
    			formerCell.setIcon(null);
    			capture.setIcon(null);
    			unhighlightMoves();
    			moveInProgress = false;
    			capturing = false;
				moves.checkForCrown(currentPlayer);
				setScore();
    			
    			if(moves.moreImpendingMovesFound(sourceCell, currentPlayer)) {
            		impendingMovesFound = moves.getImpendingMoves();
            		impendingMoves = true;
            		moveInProgress = true;
            	} else {
    				switchPlayer();
    				endGame();
    			}
    			
    		} else {
    			unhighlightCaptures();
    			capturing = false;
    			moveInProgress = false;
    		}

    	} else {
    		System.out.println("No state in-game recognised.");
    	}
	}
	
	public boolean isLegal(JButton cell) {
    	boolean legal = false;
    	if(cell.getIcon() == moves.getCurrentPlayerIcon(currentPlayer) || cell.getIcon() == moves.getCurrentPlayerDoubleIcon(currentPlayer)) {
    		legal = true;
    	}
    	
    	return legal;
    }
	
	public void switchPlayer() {
		if(currentPlayer.equals("Player 1")) {
			currentPlayer = "Player 2";
		} else {
			currentPlayer = "Player 1";
		}
		frame.setPlayerTurn(currentPlayer);
	}
	
	public void endGame() {
    	if(player1Score == 12) {
    		frame.endGame(currentPlayer);
    	} else if(player2Score == 12) {
    		frame.endGame(currentPlayer);
    	} else {
			boolean movesLeft = false;
    		for(int y = 0; y < 8; y++) {
    			for(int x = 0; x < 8; x++) {
    				if(moves.movesFound(currentPlayer)) {
    					movesLeft = true;
    				}
    			}
    		}
    		if(!movesLeft) {
    			System.out.println("NO MOVES LEFT");
    			switchPlayer();
    			frame.endGame(currentPlayer);
    		}
    	}
    }
	
	public void setScore() {
		if(currentPlayer.equals("Player 1")) {
			player1Score++;
			frame.setPlayerScore(currentPlayer , player1Score);
		} else if(currentPlayer.equals("Player 2")) {
			player2Score++;
			frame.setPlayerScore(currentPlayer , player2Score);
		}
	}
	
	public boolean isImpendingMove(JButton sourceCell) {
		boolean impending = false;
		
		for(int i = 0; i < 8; i++) {
			if(impendingMovesFound[i][0] instanceof JButton && impendingMovesFound[i][0] == sourceCell) {
				impending = true;
				formerCell = sourceCell;
				capture = impendingMovesFound[i][1];
			}
		}
		
		return impending;
	}
	
	public void highlightImpendingCaptures(JButton sourceCell) {
		for(int i = 0; i < 8; i++) {
			if(impendingMovesFound[i][0] instanceof JButton && impendingMovesFound[i][0] == sourceCell) {
				impendingMovesFound[i][2].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
			}
		}
	}
	
	public void unhighlightImpendingCaptures() {
		for(int i = 0; i < 8; i++) {
			if(impendingMovesFound[i][0] instanceof JButton) {
				impendingMovesFound[i][2].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
	}
	
	public boolean isImpendingCaptureHighlighted(JButton sourceCell) {
		boolean highlight = false;
		
		for(int i = 0; i < 8; i++) {
			if(impendingMovesFound[i][0] instanceof JButton && impendingMovesFound[i][2] == sourceCell) {
				highlight = true;
				capture = impendingMovesFound[i][1];
				i = 4;
			}
		}
		
		return highlight;
	}
	
	public void highlightCaptures() {
		for(int i = 0; i < 4; i++) {
			if(availableCaptures[i][0] instanceof JButton) {
				availableCaptures[i][1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
			}
		}
	}
	
	public void unhighlightCaptures() {
		for(int i = 0; i < 4; i++) {
			if(availableCaptures[i][0] instanceof JButton) {
				availableCaptures[i][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
	}
	
	public boolean isCaptureHighlighted(JButton sourceCell) {
		boolean highlight = false;
		
		for(int i = 0; i < 4; i++) {
			if(availableCaptures[i][0] instanceof JButton && availableCaptures[i][1] == sourceCell) {
				capture = availableCaptures[i][1];
				i = 4;
			}
		}
		
		return highlight;
	}
	
	public void highlightMoves() {
		for(int i = 0; i < 4; i++) {
			if(availableMoves[i][0] instanceof JButton) {
				availableMoves[i][0].setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
			}
		}
	}
	
	public void unhighlightMoves() {
		for(int i = 0; i < 4; i++) {
			if(availableMoves[i][0] instanceof JButton) {
				availableMoves[i][0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
	}
	
	public boolean isMoveHighlighted(JButton sourceCell) {
		boolean highlight = false;
		
		for(int i = 0; i < 4; i++) {
			if(availableMoves[i][0] instanceof JButton && availableMoves[i][0] == sourceCell) {
				highlight = true;
				i = 4;
			}
		}
		
		return highlight;
	}
	
}

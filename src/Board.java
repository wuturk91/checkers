import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Board {
	private JPanel board;
	private Cell[][] cells;
	//Gameboard Tile
    private String tileResource;
    private Image boardTile;
    private Icon tile;
    //Player 1 Tile
    private String p1Resource;
    private Image p1Tile;
    private ImageIcon p1Icon;
    //Player 1 Double Tile
    private String p1DoubleResource;
    private Image p1DoubleTile;
    private ImageIcon p1DoubleIcon;
    //Player 2 Tile
    private String p2Resource;
    private Image p2Tile;
    private ImageIcon p2Icon;
    //Player 2 Double Tile
    private String p2DoubleResource;
    private Image p2DoubleTile;
    private ImageIcon p2DoubleIcon;

	public Board() {
		//Create main panel for the board
		board = new JPanel();
		board.setLayout(new GridLayout(8,8));
		board.setBackground(Color.BLACK);
		board.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.decode("#125262")));
		//Create array to hold buttons for the board
		cells = new Cell[8][8];
		//Generate all image resources 
		generaterImageResources();
		//Generate game board and set its default tiles and counters
		generateGameBoard();
		createBoardGrid();
		placeDefaultCounters();
		
	}
	
	public void generaterImageResources() {
		//Player 1
		p1Resource = "player1Single.jpg";
		try {
			p1Tile = ImageIO.read(this.getClass().getResourceAsStream(p1Resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p1Icon = new ImageIcon(p1Tile);
		//Player 1 Double
		p1DoubleResource = "player1Double.jpg";
		try {
			p1DoubleTile = ImageIO.read(this.getClass().getResourceAsStream(p1DoubleResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p1DoubleIcon = new ImageIcon(p1DoubleTile);
		//Player 2
		p2Resource = "player2Single.jpg";
		try {
			p2Tile = ImageIO.read(this.getClass().getResourceAsStream(p2Resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2Icon = new ImageIcon(p2Tile);
		//Player 2
		p2DoubleResource = "player2Double.jpg";
		try {
			p2DoubleTile = ImageIO.read(this.getClass().getResourceAsStream(p2DoubleResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2DoubleIcon = new ImageIcon(p2DoubleTile);
		//Empty Tile
		tileResource = "tile.png";
        try {
			boardTile = ImageIO.read(this.getClass().getResourceAsStream(tileResource));
		} catch (IOException e) {
			// Catch block
			e.printStackTrace();
		}
        tile = new ImageIcon(boardTile);
	}
	
	public void generateGameBoard() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				//Generate new cell
				Cell cell = new Cell();
				//Add button to array and board panel.
				cells[y][x] = cell;		
				board.add(cell.getCell());
			}
		}
	}
	
	public void createBoardGrid() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if (x % 2 == 0 && y % 2 == 0) {
		            cells[y][x].getCell().setIcon(tile);
		            cells[y][x].getCell().setBackground(Color.BLACK);
		        } else if (x % 2 == 0 && y % 2 != 0) {
		        	cells[y][x].getCell().setIcon(null);
		        	cells[y][x].getCell().setBackground(Color.ORANGE);
		        } else if (x % 2 != 0 && y % 2 == 0) {
		        	cells[y][x].getCell().setIcon(null);
		        	cells[y][x].getCell().setBackground(Color.ORANGE);
		        } else if (x % 2 != 0 && y % 2 != 0) {
		        	cells[y][x].getCell().setIcon(tile);
		        	cells[y][x].getCell().setBackground(Color.BLACK);
		        }
			}
		}
	}
	
	public void placeDefaultCounters() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
		    	//Top Counters (Player 2)
		        if (y == 0) {
		            if (x % 2 != 0) {
		                cells[y][x].getCell().setIcon(p2Icon);
		            }
		        } else if (y == 1) {
		            if (x % 2 == 0) {
		            	cells[y][x].getCell().setIcon(p2Icon);
		            }
		        } else if (y == 2) {
		            if (x % 2 != 0) {
		            	cells[y][x].getCell().setIcon(p2Icon);
		            }
		        }
		      //Bottom Counters (Player 1)
		        if (y == 5) {
		            if (x % 2 == 0) {
		            	cells[y][x].getCell().setIcon(p1Icon);
		            }
		        } else if (y == 6) {
		            if (x % 2 != 0) {
		            	cells[y][x].getCell().setIcon(p1Icon);
		            }
		        } else if (y == 7) {
		            if (x % 2 == 0) {
		            	cells[y][x].getCell().setIcon(p1Icon);
		            }
		        }
			}
		}
	}
	
    public JPanel getBoardPanel() {
    	return board;
    }
    
    public Cell[][] getCells() {
    	return cells;
    }
    
    public ImageIcon getP1Icon() {
    	return p1Icon;
    }
    
    public ImageIcon getP1DoubleIcon() {
    	return p1DoubleIcon;
    }
    
    public ImageIcon getP2Icon() {
    	return p2Icon;
    }
    
    public ImageIcon getP2DoubleIcon() {
    	return p2DoubleIcon;
    }
    
    public ImageIcon getCurrentPlayerIcon(String currentPlayer) {
    	if(currentPlayer.equals("Player 1")) {
    		return p1Icon;
    	} else {
    		return p2Icon;
    	}
    	
    }
    
    public ImageIcon getCurrentPlayerDoubleIcon(String currentPlayer) {
    	if(currentPlayer.equals("Player 1")) {
    		return p1DoubleIcon;
    	} else {
    		return p2DoubleIcon;
    	}
    	
    }
	
    public ImageIcon getNextPlayerIcon(String currentPlayer) {
    	if(currentPlayer.equals("Player 1")) {
    		return p2Icon;
    	} else {
    		return p1Icon;
    	}
    	
    }
    
    public ImageIcon getNextPlayerDoubleIcon(String currentPlayer) {
    	if(currentPlayer.equals("Player 1")) {
    		return p2DoubleIcon;
    	} else {
    		return p1DoubleIcon;
    	}
    }
    
}

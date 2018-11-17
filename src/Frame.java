import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Frame {
	
	private JFrame frame;
	private Container contentPane;
	private JPanel mainPanel;
	private Board board;
	private JPanel boardPanel;
	private JPanel infoPanel;
	private JLabel infoLabel;
	private JPanel menuPanel;
	private JLabel menuLabel;
	private JButton start;
	private JButton reset;
	private JButton quit;
	private JPanel scoreBoard;
	private JLabel p1Name;
	private int p1CapturesMade;
	private JLabel p1Captures;
	private int p1TotalWins;
	private JLabel p2Name;
	private int p2CapturesMade;
	private JLabel p2Captures;
	private int p2TotalWins;
	
	private ImageIcon titleIcon;
	private Image checkersTitle;
	private ImageIcon menuIcon;
	private Image menuTitle;
	private ImageIcon startGameIcon;
	private Image startGameTitle;
	private ImageIcon p1MoveIcon;
	private Image p1MoveTitle;
	private ImageIcon p2MoveIcon;
	private Image p2MoveTitle;
	private ImageIcon p1WinsIcon;
	private Image p1WinsTitle;
	private ImageIcon p2WinsIcon;
	private Image p2WinsTitle;
	private ImageIcon p1titleIcon;
	private Image p1Title;
	private ImageIcon p2titleIcon;
	private Image p2Title;
	private ImageIcon p1ProfileIcon;
	private Image p1ProfileImage;
	private ImageIcon p2ProfileIcon;
	private Image p2ProfileImage;
	private ImageIcon startIcon;
	private Image startImage;
	private ImageIcon resetIcon;
	private Image resetImage;
	private ImageIcon quitIcon;
	private Image quitImage;
	
	public Frame(Board board) {
		//Initiate frame and set default close operation
		frame = new JFrame("Checkers - By Cem Salih (2018)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Grab content pane and assign main panel
		contentPane = frame.getContentPane();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		contentPane.add(mainPanel);
		
		generateFrameResources();
		
		//Generate title panel
		generateTitlePanel();
		//Generate board panel
		this.board = board;
		generateBoardPanel();
		//Generate info panel
		generateInfoPanel();
		//Generate menu panel
		generateMenuPanel();
		//Generate players panel
		generatePlayersPanel();
		
		//Set Frame Icon
		frame.setIconImage(p1ProfileImage);
		
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.pack();
		frame.setVisible(true);
	}
	
	public void generateFrameResources() {
		//Title
		String titleResource = "checkersTitleNew.jpg";
		try {
			checkersTitle = ImageIO.read(this.getClass().getResourceAsStream(titleResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		titleIcon = new ImageIcon(checkersTitle);
		//Start Game Info
		String startGameResource = "start.jpg";
		try {
			startGameTitle = ImageIO.read(this.getClass().getResourceAsStream(startGameResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		startGameIcon = new ImageIcon(startGameTitle);
		//Player 1 Move
		String p1MoveResource = "player1move.jpg";
		try {
			p1MoveTitle = ImageIO.read(this.getClass().getResourceAsStream(p1MoveResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p1MoveIcon = new ImageIcon(p1MoveTitle);
		//Player 2 Move
		String p2MoveResource = "player2move.jpg";
		try {
			p2MoveTitle = ImageIO.read(this.getClass().getResourceAsStream(p2MoveResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2MoveIcon = new ImageIcon(p2MoveTitle);
		//Player 1 Wins
		String p1WinsResource = "player1wins.jpg";
		try {
			p1WinsTitle = ImageIO.read(this.getClass().getResourceAsStream(p1WinsResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p1WinsIcon = new ImageIcon(p1WinsTitle);
		//Player 2 Wins
		String p2WinsResource = "player2wins.jpg";
		try {
			p2WinsTitle = ImageIO.read(this.getClass().getResourceAsStream(p2WinsResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2WinsIcon = new ImageIcon(p2WinsTitle);
		//Menu Title
		String menuResource = "menuTitle.jpg";
		try {
			menuTitle = ImageIO.read(this.getClass().getResourceAsStream(menuResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		menuIcon = new ImageIcon(menuTitle);
		//p1Title
		String p1titleResource = "player1Title.jpg";
		try {
			p1Title = ImageIO.read(this.getClass().getResourceAsStream(p1titleResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p1titleIcon = new ImageIcon(p1Title);
		//p2Title
		String p2titleResource = "player2Title.jpg";
		try {
			p2Title = ImageIO.read(this.getClass().getResourceAsStream(p2titleResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2titleIcon = new ImageIcon(p2Title);
		//p1 Profile
		String p1ProfileResource = "player1profile.jpg";
		try {
			p1ProfileImage = ImageIO.read(this.getClass().getResourceAsStream(p1ProfileResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p1ProfileIcon = new ImageIcon(p1ProfileImage);
		//p2 Profile
		String p2ProfileResource = "player2profile.jpg";
		try {
			p2ProfileImage = ImageIO.read(this.getClass().getResourceAsStream(p2ProfileResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2ProfileIcon = new ImageIcon(p2ProfileImage);
		//Start Button
		String startResource = "startButton.jpg";
		try {
			startImage = ImageIO.read(this.getClass().getResourceAsStream(startResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		startIcon = new ImageIcon(startImage);
		//Reset Button
		String resetResource = "resetButton.jpg";
		try {
			resetImage = ImageIO.read(this.getClass().getResourceAsStream(resetResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		resetIcon = new ImageIcon(resetImage);
		//Quit Button
		String quitResource = "quitButton.jpg";
		try {
			quitImage = ImageIO.read(this.getClass().getResourceAsStream(quitResource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		quitIcon = new ImageIcon(quitImage);

	}
	
	public void generateTitlePanel() {
		//Create title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(Color.decode("#1b98b7"));
		//Create title
		JLabel title = new JLabel();
		title.setIcon(titleIcon);
		titlePanel.add(title);
		//Add title panel to main panel NORTH
		mainPanel.add(titlePanel, BorderLayout.NORTH);
	}
	
	public void generateMenuPanel() {
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		
		//Menu Title
		JPanel topMenuPanel = new JPanel();	
		topMenuPanel.setBackground(Color.decode("#1b98b7"));
		menuLabel = new JLabel("");
		menuLabel.setIcon(menuIcon);
		topMenuPanel.add(menuLabel);
		menuPanel.add(topMenuPanel, BorderLayout.NORTH);
		
		//Start and reset panel
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(Color.decode("#1b98b7"));
		optionsPanel.setLayout(new BorderLayout());
		menuPanel.add(optionsPanel, BorderLayout.CENTER);
		JPanel startReset = new JPanel();
		startReset.setLayout(new BorderLayout());
		optionsPanel.add(startReset, BorderLayout.NORTH);
		//Add start and reset buttons
		JPanel startPanel = new JPanel();
		startPanel.setBackground(Color.decode("#1b98b7"));
		start = new JButton();
		start.setIcon(startIcon);
		start.setPreferredSize(new Dimension(140, 50));
		startPanel.add(start);
		startReset.add(startPanel, BorderLayout.NORTH);
		JPanel resetPanel = new JPanel();
		resetPanel.setBackground(Color.decode("#1b98b7"));
		reset = new JButton();
		reset.setIcon(resetIcon);
		reset.setPreferredSize(new Dimension(140, 50));
		resetPanel.add(reset);
		startReset.add(resetPanel, BorderLayout.SOUTH);
				
		JPanel quitPanel = new JPanel();
		quitPanel.setBackground(Color.decode("#1b98b7"));
		quit = new JButton();
		quit.setIcon(quitIcon);
		quit.setPreferredSize(new Dimension(140, 50));
		quitPanel.add(quit);
		menuPanel.add(quitPanel, BorderLayout.SOUTH);
		
		//Add menu panel to main panel WEST
		mainPanel.add(menuPanel, BorderLayout.WEST);
	}
	
	public void generateBoardPanel() {
		//Initiate board panel and add game baord to it
		boardPanel = new JPanel();
		boardPanel.setLayout(new FlowLayout());
		boardPanel.setBackground(Color.decode("#1b98b7"));
		//boardPanel.setBackground(Color.decode("#1b98b7"));
		boardPanel.add(board.getBoardPanel());
		//Add board panel to main panel CENTER
		mainPanel.add(boardPanel, BorderLayout.CENTER);
	}
	
	public void generateInfoPanel() {
		//Initiate info panel
		infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout());
		infoPanel.setBackground(Color.decode("#1b98b7"));
		//Initiate info label and add info panel SOUTH
		infoLabel = new JLabel("");
		infoLabel.setIcon(startGameIcon);
		infoLabel.setFont(new Font("Serif", Font.BOLD, 35));
		infoPanel.add(infoLabel);
		mainPanel.add(infoPanel, BorderLayout.SOUTH);
	}
	
	public void generatePlayersPanel() {
		//Initiate players panel
		JPanel playersPanel = new JPanel();
		playersPanel.setLayout(new GridLayout(2,1));
		//Initiate and create player 1 panel
		JPanel player1Panel = new JPanel();
		player1Panel.setLayout(new BorderLayout());
		player1Panel.setBackground(Color.decode("#1b98b7"));
		JLabel p1Title = new JLabel("");
		p1Title.setIcon(p1titleIcon);
		player1Panel.add(p1Title, BorderLayout.NORTH);
		JPanel p1StatsLayout = new JPanel();
		p1StatsLayout.setLayout(new BorderLayout());
		p1StatsLayout.setBackground(Color.decode("#1b98b7"));
		JPanel p1Stats = new JPanel();
		p1Stats.setLayout(new GridLayout(2,1));
		p1Stats.setBackground(Color.decode("#1b98b7"));
		p1CapturesMade = 0;
		p1Captures = new JLabel("Captures: " + p1CapturesMade);
		p1Captures.setFont(new Font("Serif", Font.BOLD, 20));
		p1Captures.setForeground(Color.WHITE);
		p1Stats.add(p1Captures);
		p1TotalWins = 0;
		JLabel p1Wins = new JLabel("Wins: " + p1TotalWins);
		p1Wins.setFont(new Font("Serif", Font.BOLD, 20));
		p1Wins.setForeground(Color.WHITE);
		p1Stats.add(p1Wins);
		p1StatsLayout.add(p1Stats, BorderLayout.SOUTH);
		player1Panel.add(p1StatsLayout, BorderLayout.CENTER);
		JLabel p1Icon = new JLabel("");
		p1Icon.setIcon(p1ProfileIcon);
		p1Icon.setBorder(BorderFactory.createEmptyBorder(10, 50, 140, 20));
		player1Panel.add(p1Icon, BorderLayout.SOUTH);
		playersPanel.add(player1Panel);
		//Initiate and create player 2 panel
		JPanel player2Panel = new JPanel();
		player2Panel.setLayout(new BorderLayout());
		player2Panel.setBackground(Color.decode("#1b98b7"));
		JLabel p2Title = new JLabel("");
		p2Title.setIcon(p2titleIcon);
		player2Panel.add(p2Title, BorderLayout.NORTH);
		JPanel p2StatsLayout = new JPanel();
		p2StatsLayout.setLayout(new BorderLayout());
		p1StatsLayout.setBackground(Color.decode("#1b98b7"));
		JPanel p2Stats = new JPanel();
		p2Stats.setLayout(new GridLayout(2,1));
		p2Stats.setBackground(Color.decode("#1b98b7"));
		p2CapturesMade = 0;
		p2Captures = new JLabel("Captures: " + p2CapturesMade);
		p2Captures.setFont(new Font("Serif", Font.BOLD, 20));
		p2Captures.setForeground(Color.WHITE);
		p2Stats.add(p2Captures);
		p2TotalWins = 0;
		JLabel p2Wins = new JLabel("Wins: " + p2TotalWins);
		p2Wins.setFont(new Font("Serif", Font.BOLD, 20));
		p2Wins.setForeground(Color.WHITE);
		p2Stats.add(p2Wins);
		p2StatsLayout.add(p2Stats, BorderLayout.SOUTH);
		player2Panel.add(p2StatsLayout, BorderLayout.CENTER);
		JLabel p2Icon = new JLabel("");
		p2Icon.setIcon(p2ProfileIcon);
		p2Icon.setBorder(BorderFactory.createEmptyBorder(10, 50, 140, 20));
		player2Panel.add(p2Icon, BorderLayout.SOUTH);
		playersPanel.add(player2Panel);
		
		mainPanel.add(playersPanel, BorderLayout.EAST);
	}
	
	public void setPlayerTurn(String currentPlayer) {
    	if(currentPlayer.equals("Player 1")) {
    		infoLabel.setIcon(p1MoveIcon);
    	} else {
    		infoLabel.setIcon(p2MoveIcon);
    	}
    }
	
	public void setPlayerScore(String currentPlayer , int capturesMade) {
        if(currentPlayer.equals("Player 1")) {
        	p1Captures.setText("Captures: " + capturesMade);
        } else {
        	p2Captures.setText("Captures: " + capturesMade);
        }
    }
	
	public void disableBoard() {
    	Cell[][] cells = board.getCells();
    	for(int y = 0; y < 8; y++) {
    		for(int x = 0; x < 8; x++) {
    			 cells[y][x].getCell().setEnabled(false);
    		}
    	}
    }
	
	public void endGame(String currentPlayer) {
    	disableBoard();
    	if(currentPlayer.equals("Player 1")) {
    		infoLabel.setIcon(p2WinsIcon);
    	} else {
    		infoLabel.setIcon(p1WinsIcon);
    	}
    }
	
	public JButton getStart() {
		return start;
	}
	
	public JButton getReset() {
		return reset;
	}
	
	public JButton getQuit() {
		return quit;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}

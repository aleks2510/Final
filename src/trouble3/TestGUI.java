package trouble3;

import javax.swing.*;

import java.awt.*;

public class TestGUI {
	// These are the panels to set the GUI
	static SidePanel eastPanel;
	static SidePanel westPanel;
	static SouthPanel southPanel;
	static CenterPlayBoard centerPlayBoard;
	static Player[] players;

	public static void main(String[] args) {
		Board.blankBoard();
		players = Action.createPlayers();
		eastPanel = new SidePanel(players[0]);
		westPanel = new SidePanel(players[1]);
		southPanel = new SouthPanel();
		centerPlayBoard = new CenterPlayBoard();
		guicontrol ctrlFrame = new guicontrol();
		ctrlFrame.setSize(200, 200);
		ctrlFrame.setVisible(true);
		MainFrm frame = new MainFrm();
		frame.setSize(1500, 1500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout(2, 2));
		// add East/West/center/south panels
		frame.add(eastPanel, BorderLayout.EAST);// Adding panel to frame
		frame.add(westPanel, BorderLayout.WEST);// Adding panel to frame
		frame.add(southPanel, BorderLayout.SOUTH);// Adding panel to frame
		frame.add(centerPlayBoard, BorderLayout.CENTER);
		frame.setVisible(true);// Setting to visible.
		// Here we are creating the frame that will prompt the instructions for
		// the game
		JFrame rulesFrame = new JFrame();
		JPanel rulesPanel = new JPanel();
		JLabel myLabel = new JLabel();
		// The text is in html and we use the .setText method to read it and
		// display it.
		myLabel.setText("<html><p>Welcome to Star Trouble Game<br /> 1. Who goes first: Players roll the die, the player that rolls the first six will make the first move.<br />2. Movement: players must roll a 6 to move one of their 2 pieces on to the starting area.<br />3. Conditions:<br />Players cannot have more than one piece on the same location at a time.<br />Landing on a space occupied by another player's piece will bump the other player's piece home.<br />If a 6 is rolled the player gets to roll again.<br />Winner: The winner will be the player who gets both pieces into the finished area.</p></html>");
		rulesPanel.add(myLabel);
		rulesFrame.add(rulesPanel);
		rulesFrame.setSize(620, 200);
		rulesFrame.setLocationRelativeTo(null);
		rulesFrame.setVisible(true);
		Turn.nextTurn();
	}

}

class MainFrm extends JFrame {
	public MainFrm() {
		// add East/West/center/south panels
		// SidePanel eastPanel = new SidePanel();
		// add(eastPanel);
	}
}

// This class will create a South panel Object which will display
// "Welcome to Start Trouble"
class SouthPanel extends JPanel {
	JLabel message;

	public SouthPanel() {
		message = new JLabel("Welcome to Star Trouble!");
		message.setForeground(Color.YELLOW);
		add(message);
		setBackground(Color.BLACK);
	}
}

// This class will create a CenterPanel Object with a black color background.
class CenterPanel extends JPanel {
	public CenterPanel() {
		setBackground(Color.BLACK);
	}
}

// This class will create a SidePanel Object. This object will have a rollPanel
// and a Player panel on it and it will be placed south and north respectively.
class SidePanel extends JPanel {
	// Adding class variables.
	public RollPanel rollPanel;
	public PlayerPanel playerPanel;

	public SidePanel(Player player) {
		setLayout(new BorderLayout(0, 150));// Setting layout for the panel.
		playerPanel = new PlayerPanel(player);
		rollPanel = new RollPanel();
		rollPanel.setVisible(false);
		// Deciding which player will be number 1 and which will be number 2.
		if (player.playerID == 0)
			add(playerPanel, BorderLayout.SOUTH);
		else
			add(playerPanel, BorderLayout.NORTH);
		add(rollPanel, BorderLayout.CENTER);
		setBackground(Color.BLACK);
	}

	// This class will create a PlayerPanel Object which will have the name of
	// the player and it will contain the pieces of each player.
	class PlayerPanel extends JPanel {
		// Setting variables to the class.
		Player player;
		JLabel name;
		ButtonHolder peices;

		public PlayerPanel(Player player) {
			this.player = player;
			name = new JLabel(player.name);
			peices = new ButtonHolder(player);
			name.setHorizontalTextPosition(AbstractButton.CENTER);
			name.setHorizontalAlignment(AbstractButton.CENTER);
			setLayout(new BorderLayout());
			add(name, BorderLayout.NORTH);// Adding labels to the panel
			add(peices, BorderLayout.CENTER);// Adding buttons to the panel.
		}

		// This class will create ButtonHolder Object which will allocate the
		// pieces for the player and will place them in a button.
		class ButtonHolder extends JPanel {
			Player player;
			IconButton[] pieceButton;

			public ButtonHolder(Player player) {
				this.player = player;

				pieceButton = new IconButton[2];
				for (int i = 0; i < player.piece.length; i++) {
					pieceButton[i] = new IconButton(player.piece[i].buttonID);
					pieceButton[i].addActionListener(new PieceButtonListener(
							player.piece[i]));
					pieceButton[i].setPressedIcon(player.piece[i].clickedID);
					pieceButton[i].setRolloverIcon(player.piece[i].mouseOverID);
					add(pieceButton[i]);
				}
			}
		}

	}
}

// This class will create a RollPanel Object that will contain the icon for the
// Die and the label to display the "Click to Roll"
class RollPanel extends JPanel {
	public JLabel rollValueLabel = new JLabel("Click To Roll");
	public IconButton dieButton = new IconButton(new ImageIcon(
			"Assets/Archive/die.png"));

	public RollPanel() {

		setLayout(new GridLayout(2, 1, 0, 10));// Setting the layout
		dieButton.addActionListener(new DieButtonListener());
		dieButton.clickable = true;
		rollValueLabel.setForeground(Color.YELLOW);
		add(dieButton);
		add(rollValueLabel);

		rollValueLabel.setHorizontalAlignment(AbstractButton.CENTER);
		rollValueLabel.setVerticalAlignment(AbstractButton.TOP);
		setBackground(Color.BLACK);
	}
}

// This class will create a IconButton Object to place the icon buttons.
class IconButton extends JButton {
	public boolean clickable;

	public IconButton(ImageIcon icon) {
		super(icon);
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
	}
}

// This class will create a CenterPlayBoard Object which it will contain five
// different panels to the board.
class CenterPlayBoard extends JPanel {
	JLabel[] spaces = new JLabel[Board.Spaces.length];

	CenterPlayBoard() {
		loadSpaces();
		setLayout(new BorderLayout());
		add(new CenterNorthPanel(spaces), BorderLayout.NORTH);
		add(new CenterEastPanel(spaces), BorderLayout.EAST);
		add(new CenterSouthPanel(spaces), BorderLayout.SOUTH);
		add(new CenterWestPanel(spaces), BorderLayout.WEST);
		add(new CenterMiddlePanel(), BorderLayout.CENTER);
	}

	private void loadSpaces() {
		for (int i = 0; i < Board.Spaces.length; i++) {
			spaces[i] = new JLabel(Board.Spaces[i].boardID);
		}
	}

	public void updateSpaces() {
		for (int i = 0; i < Board.Spaces.length; i++) {
			spaces[i].setIcon(Board.Spaces[i].boardID);
		}
	}
}

class CenterNorthPanel extends JPanel {
	CenterNorthPanel(JLabel[] spaces) {
		setLayout(new GridLayout(1, 10, 5, 5));
		setBackground(Color.DARK_GRAY);

		for (int i = 0; i < 10; i++) {
			this.add(spaces[i]);
		}
	}
}

class CenterEastPanel extends JPanel {
	CenterEastPanel(JLabel[] spaces) {
		setLayout(new GridLayout(8, 1, 5, 5));
		setBackground(Color.DARK_GRAY);

		for (int i = 10; i < 18; i++) {
			this.add(spaces[i]);
		}
	}
}

class CenterSouthPanel extends JPanel {
	CenterSouthPanel(JLabel[] spaces) {
		setLayout(new GridLayout(1, 10, 5, 5));
		setBackground(Color.DARK_GRAY);

		for (int i = 27; i > 17; i--) {
			this.add(spaces[i]);
		}
	}
}

class CenterWestPanel extends JPanel {
	CenterWestPanel(JLabel[] spaces) {
		setLayout(new GridLayout(8, 1, 5, 5));
		setBackground(Color.DARK_GRAY);

		for (int i = 35; i > 27; i--) {
			this.add(spaces[i]);
		}
	}
}

class CenterMiddlePanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ImageIcon b = new ImageIcon("Assets/Floor/bg.jpg");
		Image background = b.getImage();
		g.drawImage(background, 0, 0, getWidth(), getHeight(), Color.BLACK,
				this);
	}
}
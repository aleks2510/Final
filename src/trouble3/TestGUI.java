package trouble3;
import javax.swing.*;

import java.awt.*;
public class TestGUI {

	static SidePanel eastPanel;
	static SidePanel westPanel;
	static SouthPanel southPanel;
	static CenterBoard centerPanel;
	static Player[] players;
	
	public static void main(String[] args) {
		
		players = Action.createPlayers();
		eastPanel = new SidePanel(players[0]);
		westPanel = new SidePanel(players[1]);
		southPanel = new SouthPanel();
		centerPanel = new CenterBoard();
		Board.blankBoard();
		MainFrm frame = new MainFrm();
		frame.setSize(1500,1500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout(2,2));
		//add East/West/center/south panels
		SidePanel eastPanel = new SidePanel(players[0]);
		SidePanel westPanel = new SidePanel(players[1]);
		SouthPanel southPanel = new SouthPanel();
		CenterPlayBoard centerPanel = new CenterPlayBoard();
		
		frame.add(eastPanel, BorderLayout.EAST);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.setVisible(true);
                
                Turn.nextTurn();
                int turnCounter = 0;
                

	}

}

class MainFrm extends JFrame{
	public MainFrm(){
		//add East/West/center/south panels
		//SidePanel eastPanel = new SidePanel();
		//add(eastPanel);
	}
}

class SouthPanel extends JPanel{
	JLabel message;
	public SouthPanel(){
		message = new JLabel("Welcome to Star Trouble!");
		add(message);
		setBackground(Color.BLACK);
	}
}

class CenterPanel extends JPanel{
	public CenterPanel(){
		setBackground(Color.BLACK);
	}
}

class SidePanel extends JPanel{
	public RollPanel rollPanel;
	public PlayerPanel playerPanel;
	public SidePanel(Player player){
	setLayout(new BorderLayout(0,150));
	
	playerPanel = new PlayerPanel(player);
	rollPanel = new RollPanel();
	rollPanel.setVisible(false);
	add(playerPanel, BorderLayout.NORTH);
	add(rollPanel, BorderLayout.CENTER);
	setBackground(Color.BLACK);
	}
	
	class PlayerPanel extends JPanel{
		Player player;
		JLabel name;
		ButtonHolder peices;
		
		public PlayerPanel(Player player){
		this.player = player;
		name = new JLabel(player.name);
		peices = new ButtonHolder(player);
		name.setHorizontalTextPosition(AbstractButton.CENTER);
		name.setHorizontalAlignment(AbstractButton.CENTER);
		setLayout(new BorderLayout());
		add(name, BorderLayout.NORTH);
		add(peices, BorderLayout.CENTER);
	}
		class ButtonHolder extends JPanel{
			Player player;
			IconButton[] pieceButton;
			public ButtonHolder(Player player){
				this.player = player;
				
				pieceButton = new IconButton[2];
				for(int i = 0; i < player.piece.length; i++){
					pieceButton[i] = new IconButton(player.piece[i].buttonID);
					pieceButton[i].addActionListener(new PieceButtonListener(player.piece[i]));
				pieceButton[i].setPressedIcon(player.piece[i].clickedID);
					pieceButton[i].setRolloverIcon(player.piece[i].mouseOverID);
					add(pieceButton[i]);
				}
			}
			}

		}
	}
	class RollPanel extends JPanel{
		public JLabel rollValueLabel = new JLabel("Click To Roll");
+		public IconButton dieButton = new IconButton(new ImageIcon("Assets/Archive/die.png"));
		public RollPanel(){
		
			setLayout(new GridLayout(2,1,0,10));
		add(dieButton);
			add(rollValueLabel);
			
			rollValueLabel.setHorizontalAlignment(AbstractButton.CENTER);
			rollValueLabel.setVerticalAlignment(AbstractButton.TOP);
			setBackground(Color.BLACK);
		}
	}

class IconButton extends JButton{
	public boolean clickable;
	public IconButton(ImageIcon icon){
		super(icon);
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
	}
}

class CenterBoard extends JPanel{
	CenterBoard(){
		setLayout(new GridLayout(10, 10, 5, 5));
		int index = 0;
		for(int i=0; i<10; i++){
			if(i == 0 || i == 9){
				for(int j=0; j<10; j++){
					JPanel box = new JPanel();
					index++;
					box.setSize(100, 100);
					box.setVisible(true);
					box.setBackground(Color.BLUE);
					add(box);
				}
			}
			else{
				for(int k=0; k<10; k++){
					JPanel box = new JPanel();
					box.setSize(100, 100);
					box.setBackground(Color.BLUE);
					
					if(k == 0 || k == 9){
						box.setVisible(true);
						
					}
					else{
						box.setOpaque(false);
					}
					add(box);
				}
			}
		}
	}
}


class CenterPlayBoard extends JPanel{
	CenterPlayBoard(){
		setLayout(new BorderLayout());
			add(new CenterNorthPanel(), BorderLayout.NORTH);
			add(new CenterEastPanel(), BorderLayout.EAST);
			add(new CenterSouthPanel(), BorderLayout.SOUTH);
			add(new CenterWestPanel(), BorderLayout.WEST);
			add(new CenterMiddlePanel(), BorderLayout.CENTER);
	}
}

class CenterNorthPanel extends JPanel {
	CenterNorthPanel() {
		setLayout(new GridLayout(1, 10, 5, 5));
		setBackground(Color.DARK_GRAY);
		
		for(int i=0; i<10; i++){
			JPanel box = new JPanel();
			String a = String.valueOf(i);
			ImageIcon tile = Board.Spaces[i].boardID;
			box.add(new JLabel(tile));
			box.setBackground(Color.DARK_GRAY);
			box.setVisible(true);
			add(box);
		}
	}
}

class CenterEastPanel extends JPanel {
	CenterEastPanel() {
		setLayout(new GridLayout(8, 1, 5, 5));
		setBackground(Color.DARK_GRAY);
		
		for(int i=10; i<18; i++){
			JPanel box = new JPanel();
			String a = String.valueOf(i);
			ImageIcon tile = Board.Spaces[i].boardID;
			box.add(new JLabel(tile));
			box.setBackground(Color.DARK_GRAY);
			box.setVisible(true);
			add(box);
		}
	}
}

class CenterSouthPanel extends JPanel {
	CenterSouthPanel() {
		setLayout(new GridLayout(1, 10, 5, 5));
		setBackground(Color.DARK_GRAY);
		
		for(int i=27; i>17; i--){
			JPanel box = new JPanel();
			String a = String.valueOf(i);
			ImageIcon tile = Board.Spaces[i].boardID;
			box.add(new JLabel(tile));
			box.setBackground(Color.DARK_GRAY);
			box.setVisible(true);
			add(box);
		}
	}
}

class CenterWestPanel extends JPanel {
	CenterWestPanel() {
		setLayout(new GridLayout(8, 1, 5, 5));
		setBackground(Color.DARK_GRAY);
		
		for(int i=35; i>27; i--){
			JPanel box = new JPanel();
			String a = String.valueOf(i);
			ImageIcon tile = Board.Spaces[i].boardID;
			box.add(new JLabel(tile));
			box.setBackground(Color.DARK_GRAY);
			box.setVisible(true);
			add(box);
		}
	}
}

class CenterMiddlePanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ImageIcon b = new ImageIcon("Assets/Floor/bg.jpg");
		Image background = b.getImage();
		g.drawImage(background, 0, 0, getWidth(), getHeight(), Color.BLACK, this);
	}
}

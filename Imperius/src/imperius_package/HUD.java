package imperius_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HUD implements ActionListener {
	
		//Var declaration
		VariablesClass Variables = new VariablesClass();
		int count = 0;
		
		//labels
		static JLabel StartInfoLabel;
		static JLabel ResourcesLabel;
		static JLabel ArmyCountLabel;
		static JLabel BaseCountLabel;
		static JLabel ArmyOnWay1Label;
		static JLabel ArmyOnWay2Label;
		static JLabel ArmyInEnemyBaseLabel;
		static JLabel TechCountLabel;
		static JLabel TechSpySentCount;
		static JLabel TechSpyComingCount;
		static JLabel BaseSpySentCount;
		static JLabel BaseSpyComingCount;
		static JLabel ArmySpySentCount;
		static JLabel ArmySpyComingCount;
		static JLabel GameLastInfoLabel;
		
		//Frames
		static JFrame ImperiusFrame;
		
		//Fields
		static JTextField AddArmyField = new JTextField(1);
		static JTextField AddTechField = new JTextField(1);
		static JTextField SettingsAIArmy = new JTextField(1);
		static JTextField SettingsAIBases = new JTextField(1);
		static JTextField SettingsAITech = new JTextField(1);
		static JTextField SettingsAIResources = new JTextField(1);
		
		//Buttons
		static JButton NextRoundButton;
		static JButton StartButton;
		static JButton AddTechButton;
		static JButton AddArmyButton;
		static JButton AddBaseButton;
		static JButton AddTechSpyButton;
		static JButton AddBaseSpyButton;
		static JButton AddArmySpyButton;
		static JButton SentArmyToEnemy;
		static JButton SentArmy1BackButton;
		static JButton SentArmy2BackButton;
		static JButton SentArmyInEnemyBaseBackButton;
		
		//Panels
		static JPanel StartPanel;
		static JPanel GamePanel;
		static JPanel InfoPanel;
		
		//Creation
		public void GUI() {
			Variables.StartHry();
			//StartPanel
			StartPanel = new JPanel();
			StartPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 400, 400));
			
			StartButton = new JButton(new ImageIcon("C:\\Users\\jarda\\eclipse-education\\OurFirstTest\\bin\\start.png"));
			StartButton.addActionListener(this);
			StartButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 100));
			
			StartInfoLabel = new JLabel("");
			StartInfoLabel.setPreferredSize(new Dimension(100, 100));
			StartInfoLabel.setBorder(BorderFactory.createLoweredBevelBorder());
			StartInfoLabel.setBounds(0, 0, 400, 400);
			StartPanel.add(StartButton);
			StartPanel.add(StartInfoLabel);
			StartPanel.setVisible(true);
			
			//set layout X, Y - how many rows/columns of objects
			//StartPanel.setLayout(new GridLayout(0, 1));
			
			//GamePanel
			GamePanel = new JPanel();
			GamePanel.setBorder(BorderFactory.createEmptyBorder(0, 400, 300, 300));
			
			NextRoundButton = new JButton(new AbstractAction("Konec Kola") { 
				private static final long serialVersionUID = 1L;
		        public void actionPerformed( ActionEvent e ) {
		        	//How long they are
//				      JTextField xField = new JTextField(10);
//				      JTextField yField = new JTextField(50);
//				      JPanel myPanel = new JPanel();
//				      myPanel.add(new JLabel("x:"));
//				      myPanel.add(xField);
//				      myPanel.add(Box.createHorizontalStrut(20));
//				      myPanel.add(new JLabel("y:"));
//				      myPanel.add(yField);
				      //Must be there
//				      int result = JOptionPane.showConfirmDialog(null, myPanel,
//				          "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
//				      if (result == JOptionPane.OK_OPTION) {
//				        System.out.println("x value: " + xField.getText());
//				        System.out.println("y value: " + yField.getText());
//				      }
		        		Variables.AIPlay();
				    }
		    });
			NextRoundButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));
			NextRoundButton.setBackground(Color.blue);
			
			GamePanel.add(NextRoundButton);
			GamePanel.setVisible(true);
			
			//Frame
			ImperiusFrame = new JFrame();
			ImperiusFrame.setBounds(0, 0, 600, 600);
			
			// Panel Load
			ImperiusFrame.add(GamePanel, BorderLayout.WEST);
			ImperiusFrame.add(StartPanel, BorderLayout.EAST);
			
			//Other
			ImperiusFrame.setResizable(false);
			ImperiusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ImperiusFrame.setTitle("Imperius");
			ImperiusFrame.pack();
			ImperiusFrame.setVisible(true);
		};

		public void actionPerformed(ActionEvent substract) {
			
			StartInfoLabel.setText("ahoj!");
		}

	}

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI {
  JFrame window;
  ImageIcon iconImg;
  Container con;
  Dimension roomImgSize, structureImgSize, encounterImgSize;
  JPanel mainTextPanel, titleNamePanel, startButtonPanel, updateButtonPanel, inputTextPanel;
  JLabel roomImgLabel, structureImgLabel, encounterImgLabel, titleNameLabel;
  JButton startButton, updateButton;
  JTextField inputTextField;
  JTextArea mainTextArea;
  JScrollPane scroll;
  Font titleFont = new Font("Times New Roman", Font.PLAIN, 128), normalFont = new Font("Times New Roman", Font.PLAIN, 25);

  public void createUI(Game.ChoiceHandler cHandler){
    //window
    window = new JFrame();
    window.setTitle("Game");
    window.setSize(900, 700);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.black);
    window.setLayout(null);
    window.setVisible(true);
    iconImg = new ImageIcon("img/icons/gameicon.png");
    window.setIconImage(iconImg.getImage());
    con = window.getContentPane();

    //titalscreen
    titleNamePanel = new JPanel();
    titleNamePanel.setBounds(100, 100, 700, 150);
    titleNamePanel.setBackground(Color.black);
    con.add(titleNamePanel);

    titleNameLabel = new JLabel("GAME");
    titleNameLabel.setForeground(Color.white);
    titleNameLabel.setFont(titleFont);
    titleNamePanel.add(titleNameLabel);

    startButtonPanel = new JPanel();
    startButtonPanel.setBounds(300, 400, 300, 100);
    startButtonPanel.setBackground(Color.black);
    con.add(startButtonPanel);

    startButton = new JButton("START");
    startButton.setBackground(Color.black);
    startButton.setForeground(Color.white);
    startButton.setFont(normalFont);
    startButton.setFocusPainted(false);
    startButton.addActionListener(cHandler);
    startButton.setActionCommand("start");
    startButtonPanel.add(startButton);

    updateButtonPanel = new JPanel();
    updateButtonPanel.setBounds(50, 550, 120, 50);
    updateButtonPanel.setBackground(Color.black);
    con.add(updateButtonPanel);

    updateButton = new JButton("Update");
    updateButton.setBackground(Color.black);
    updateButton.setForeground(Color.white);
    updateButton.setFont(normalFont);
    updateButton.setFocusPainted(false);
    updateButton.addActionListener(cHandler);
    updateButton.setActionCommand("update");
    updateButtonPanel.add(updateButton);

    //name input
    inputTextPanel = new JPanel();
    inputTextPanel.setBounds(375, 300, 150, 100);
    inputTextPanel.setBackground(Color.black);

    inputTextField = new JTextField("enter name");
    inputTextField.setBounds(375, 300, 200, 100);
    inputTextField.setBackground(Color.black);
    inputTextField.setForeground(Color.white);
    inputTextField.setBorder(null);
    inputTextField.setFont(normalFont);
    inputTextField.setEditable(true);
    inputTextField.addActionListener(cHandler);
    inputTextField.setActionCommand("makePlayer");
    inputTextPanel.add(inputTextField);

    con.add(inputTextPanel);

    encounterImgLabel = new JLabel();
    con.add(encounterImgLabel);

    structureImgLabel = new JLabel();
    con.add(structureImgLabel);

    roomImgLabel = new JLabel();
    con.add(roomImgLabel);

    mainTextPanel = new JPanel();
    mainTextPanel.setBounds(10, 420, 500, 220);
    mainTextPanel.setBackground(Color.black);

    mainTextArea = new JTextArea("Replace 'enter here' with your player name, if the name has a saved game on this computer, you will continue an old run. When you are ready press enter");
    mainTextArea.setBounds(10, 420, 500, 220);
    mainTextArea.setBackground(Color.black);
    mainTextArea.setForeground(Color.white);
    mainTextArea.setFont(normalFont);
    mainTextArea.setLineWrap(true);
    mainTextArea.setWrapStyleWord(true);
    mainTextArea.setEditable(false);
    mainTextPanel.add(mainTextArea);
    con.add(mainTextPanel);
    //setVisible
    con.setVisible(false);
    con.setVisible(true);
    
  }

    public void drawRoom(String file){
    roomImgLabel.setIcon(new ImageIcon("img/rooms/"+file+".png"));
    roomImgSize = roomImgLabel.getPreferredSize(); 
    roomImgLabel.setBounds(10, 10, roomImgSize.width, roomImgSize.height);
  }

  public void drawStructure(String file){
    structureImgLabel.setIcon(new ImageIcon("img/structure/"+file+".png"));
    structureImgSize = structureImgLabel.getPreferredSize(); 
    structureImgLabel.setBounds(10, 10, structureImgSize.width, structureImgSize.height);
  }

  public void drawEncounter(String category, String file){
    encounterImgLabel.setIcon(new ImageIcon("img/"+category+"/"+file+".png"));
    encounterImgSize = encounterImgLabel.getPreferredSize(); 
    encounterImgLabel.setBounds(10, 10, encounterImgSize.width, encounterImgSize.height);
  }
}

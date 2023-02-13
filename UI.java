import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI {
  Game game;
  Player player;
  GameInventory in;

  JFrame window;
  private ImageIcon iconImg;
  private Container con;
  private Dimension roomImgSize, structureImgSize, encounterImgSize;
  JPanel mainTextPanel, titleNamePanel, startButtonPanel, updateButtonPanel, inputTextPanel, choiceButtonPanel, sellCounterPanel;
  JLabel roomImgLabel, structureImgLabel, encounterImgLabel, titleNameLabel, stringSellCounterLabel, intSellCounterLabel;
  JTextField inputTextField;
  JTextArea mainTextArea;
  private Font titleFont = new Font("Times New Roman", Font.PLAIN, 128), normalFont = new Font("Times New Roman", Font.PLAIN, 25);
  JButton startButton, updateButton;
  ArrayList<JButton> buttonList = new ArrayList<JButton>();
  int[][] buttonListLayout = {
    { 0, 1},
    { 2, 3},
    { 4, 5},
    { 6, 7},
    { 8, 9},
    { 10, 11}
  };

  int y = 5, x = 0, pos = buttonListLayout[y][x];
  public void chageButton(String s){
    if(pos % 2 == 0 && s == "right"){
      x++;
    }
    if(pos % 2 != 0 && s == "left"){
      x=x-1;
    }
    if(pos > 1 && s == "up"){
      y=y-1;
    }
    if(pos < 10 && s == "down"){
      y++;
    }
    pos = buttonListLayout[y][x];

    for(int i = 0; i < buttonList.size(); i++){
      buttonList.get(i).setBorder(BorderFactory.createLineBorder(Color.white, 1));
    }
    for(int i = 0; i < selectedButtons.size(); i++){
      buttonList.get(selectedButtons.get(i)).setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }
    buttonList.get(pos).setBorder(BorderFactory.createLineBorder(Color.white, 4));
    updateSellCounter();
  }

  ArrayList<Integer> selectedButtons = new ArrayList<>();
  public void selctbutton(int me){
    if(!buttonCheck(me)){
      selectedButtons.add(me);
    }
    System.out.println(selectedButtons);
  }

  public boolean buttonCheck(int check) {
    for(int i = 0; i < selectedButtons.size(); i++){
      if(selectedButtons.get(i) == check){
        selectedButtons.remove(i);
        return true;
      }
    }
    return false;
  }

  int sellSum;
  public void updateSellCounter() {
    sellSum = 0;
    for(int i = 0; i < selectedButtons.size(); i++){
      for(int j = 0; j < in.Weapons.size(); j++){
        if(in.Weapons.get(j).getType() == buttonList.get(selectedButtons.get(i)).getText()){
          sellSum+= in.Weapons.get(j).getValue();
        }
      }
      for(int j = 0; j < in.Items.size(); j++){
        if(in.Items.get(j).getName() == buttonList.get(selectedButtons.get(i)).getText()){
          sellSum+= in.Items.get(j).getValue();
        }
      }
    }
    intSellCounterLabel.setText(""+sellSum);
    game.pIn = "sellItemsToShop";
  }
  public UI(Player p, Game g) {
    player = p;
    game = g;
  }
  public void addGameInventory(GameInventory ga) {
    in = ga;
  }

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
    con.add(inputTextPanel);

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


    encounterImgLabel = new JLabel();
    con.add(encounterImgLabel);

    structureImgLabel = new JLabel();
    con.add(structureImgLabel);

    roomImgLabel = new JLabel();
    con.add(roomImgLabel);

    mainTextPanel = new JPanel();
    mainTextPanel.setBounds(10, 420, 500, 220);
    mainTextPanel.setBackground(Color.black);
    con.add(mainTextPanel);

    mainTextArea = new JTextArea("Replace 'enter here' with your player name, if the name has a saved game on this computer, you will continue an old run. When you are ready press enter");
    mainTextArea.setBounds(10, 420, 500, 220);
    mainTextArea.setBackground(Color.black);
    mainTextArea.setForeground(Color.white);
    mainTextArea.setFont(normalFont);
    mainTextArea.setLineWrap(true);
    mainTextArea.setWrapStyleWord(true);
    mainTextArea.setEditable(false);
    mainTextPanel.add(mainTextArea);

    //choice buttons
    choiceButtonPanel = new JPanel();
    choiceButtonPanel.setBounds(550, 100, 300, 375);
    choiceButtonPanel.setBackground(Color.black);
    choiceButtonPanel.setLayout(new GridLayout(6, 2, 10, 10));
    con.add(choiceButtonPanel);

    for(int i = 0; i < 12; i++) {
      JButton button = new JButton(null, null);
      if(i == 11){
        button.setText("Leave");
      }
      button.setBackground(Color.black);
      button.setForeground(Color.white);
      button.setFont(normalFont);
      button.setFocusPainted(false);
      button.addActionListener(cHandler);
      button.setActionCommand("c"+i);
      buttonList.add(button);
      choiceButtonPanel.add(button);
    }

    sellCounterPanel = new JPanel();
    sellCounterPanel.setBounds(550, 475, 150, 150);
    sellCounterPanel.setBackground(Color.black);
    sellCounterPanel.setLayout(new GridLayout(1, 2));
    con.add(sellCounterPanel);

    stringSellCounterLabel = new JLabel("Sell:");
    stringSellCounterLabel.setForeground(Color.white);
    stringSellCounterLabel.setFont(normalFont);
    sellCounterPanel.add(stringSellCounterLabel);

    intSellCounterLabel = new JLabel();
    intSellCounterLabel.setForeground(Color.white);
    intSellCounterLabel.setFont(normalFont);
    sellCounterPanel.add(intSellCounterLabel);

    // choice1 = new JButton("1");
    // choice1.setBackground(Color.black);
    // choice1.setForeground(Color.white);
    // choice1.setFont(normalFont);
    // // choice1.setFocusPainted(false);
    // choice1.addActionListener(cHandler);
    // choice1.setActionCommand("c1");
    // choiceButtonPanel.add(choice1);

    // choice2 = new JButton("2");
    // choice2.setBackground(Color.black);
    // choice2.setForeground(Color.white);
    // choice2.setFont(normalFont);
    // // choice2.setFocusPainted(false);
    // choice2.addActionListener(cHandler);
    // choice2.setActionCommand("c2");
    // choiceButtonPanel.add(choice2);

    // choice3 = new JButton("3");
    // choice3.setBackground(Color.black);
    // choice3.setForeground(Color.white);
    // choice3.setFont(normalFont);
    // // choice3.setFocusPainted(false);
    // choice3.addActionListener(cHandler);
    // choice3.setActionCommand("c3");
    // choiceButtonPanel.add(choice3);

    // choice4 = new JButton("4");
    // choice4.setBackground(Color.black);
    // choice4.setForeground(Color.white);
    // choice4.setFont(normalFont);
    // // choice4.setFocusPainted(false);
    // choice4.addActionListener(cHandler);
    // choice4.setActionCommand("c4");
    // choiceButtonPanel.add(choice4);

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

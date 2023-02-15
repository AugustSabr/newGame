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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UI {
  Game game;
  Player player;
  GameInventory in;
  Encounter en;

  JFrame window;
  private ImageIcon iconImg;
  private Container con;
  private Dimension roomImgSize, structureImgSize, encounterImgSize;
  JPanel mainTextPanel, titleNamePanel, startButtonPanel, updateButtonPanel, inputTextPanel, choiceButtonPanel, itemInfoPanel, statPanel;
  JLabel roomImgLabel, structureImgLabel, encounterImgLabel, titleNameLabel, stringShopCounterLabel, intShopCounterLabel;
  JTextField inputTextField;
  JTextArea mainTextArea;
  private Font titleFont = new Font("Times New Roman", Font.PLAIN, 128), normalFont = new Font("Times New Roman", Font.PLAIN, 25);
  JButton startButton, updateButton;

  ArrayList<JLabel> statLabelList = new ArrayList<JLabel>();
  ArrayList<JButton> buttonList = new ArrayList<JButton>();
  ArrayList<JLabel> itemInfoLabelList = new ArrayList<JLabel>();

  int[][] buttonListLayout = {
    { 0, 1},
    { 2, 3},
    { 4, 5},
    { 6, 7},
    { 8, 9},
    { 10, 11}
  };

  int y=5, x=1, pos = buttonListLayout[y][x];
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
    drawButtons();
  }

  ArrayList<Integer> selectedButtons = new ArrayList<>();
  public void selctbutton(int me){
    if(en.buttonPanalUse == "shop"){
      if(!buttonCheck(me)){
        selectedButtons.add(me);
      }
      updateSellCounter();
    }
    if(en.buttonPanalUse == "inventory"){
      int j = 0;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");

      if(!buttonCheck(me)){
        selectedButtons.clear();
        selectedButtons.add(me);
      }
      for(int i = 0; i < in.Weapons.size(); i++){
        if(in.Weapons.get(i).getType() == buttonList.get(me).getText()){
          buttonList.get(10).setText("Equip");
          game.c10 = "equipWeapon";
          int k = 0;
          itemInfoLabelList.get(k).setText("Name:");k++;
          itemInfoLabelList.get(k).setText(in.Weapons.get(i).getType());k++;
          itemInfoLabelList.get(k).setText("Damage:");k++;
          itemInfoLabelList.get(k).setText(in.Weapons.get(i).getDamage()+"");k++;
          itemInfoLabelList.get(k).setText("Value");k++;
          itemInfoLabelList.get(k).setText(in.Weapons.get(i).getValue()+"");
          break;
        } else {
          buttonList.get(10).setText("");
        }
      }
      for(int i = 0; i < in.Items.size(); i++){
        if(in.Items.get(i).getName() == buttonList.get(me).getText()){
          int k = 0;
          itemInfoLabelList.get(k).setText("Name:");k++;
          itemInfoLabelList.get(k).setText(in.Items.get(i).getName());k++;
          itemInfoLabelList.get(k).setText("Value");k++;
          itemInfoLabelList.get(k).setText(in.Items.get(i).getValue()+"");
          break;
        }
      }
    }
    drawButtons();
    // System.out.println(selectedButtons);
  }
  public void drawButtons(){
    for(int i = 0; i < buttonList.size(); i++){
      buttonList.get(i).setBorder(BorderFactory.createLineBorder(Color.white, 1));
    }
    for(int i = 0; i < selectedButtons.size(); i++){
      buttonList.get(selectedButtons.get(i)).setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }
    buttonList.get(pos).setBorder(BorderFactory.createLineBorder(Color.white, 4));
  }

  public boolean buttonCheck(int check) {
    for(int i = 0; i < selectedButtons.size(); i++){
      if(selectedButtons.get(i) == check){
        // selectedButtons.remove(i);
        return true;
      }
    }
    return false;
  }

  int sum;
  public void updateSellCounter() {
    sum = 0;
    for(int i = 0; i < selectedButtons.size(); i++){
      for(int j = 0; j < in.Weapons.size(); j++){
        if(in.Weapons.get(j).getType() == buttonList.get(selectedButtons.get(i)).getText()){
          sum+= in.Weapons.get(j).getValue();
        }
      }
      for(int j = 0; j < in.Items.size(); j++){
        if(in.Items.get(j).getName() == buttonList.get(selectedButtons.get(i)).getText()){
          sum+= in.Items.get(j).getValue();
        }
      }
    }
    if(stringShopCounterLabel.getText() == "Buy:"){
      sum = sum*2;
    }
    intShopCounterLabel.setText(""+sum);
  }
  public UI(Game g, Player p) {
    game = g;
    player = p;
  }
  public void addGameInventory(GameInventory ga) {
    in = ga;
  }


  public void addEncounter(Encounter e) {
    en = e;
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

    //statPanel
    statPanel = new JPanel();
    statPanel.setBounds(560, 50, 280, 380);
    statPanel.setBackground(Color.black);
    statPanel.setLayout(new GridLayout(8, 2, 0, 0));
    statPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
    con.add(statPanel);

    for(int i = 0; i < 16; i++) {
      JLabel label;
      if(i % 2 == 0){
        label = new JLabel("", SwingConstants.LEFT);
        label.setBorder(new EmptyBorder(0,10,0,0));
      } else{
        label = new JLabel("", SwingConstants.RIGHT);
        label.setBorder(new EmptyBorder(0,0,0,10));
      }
      label.setBackground(Color.black);
      label.setForeground(Color.white);
      label.setFont(normalFont);
      statLabelList.add(label);
      statPanel.add(label);
    }

    //choice buttons
    choiceButtonPanel = new JPanel();
    choiceButtonPanel.setBounds(550, 50, 300, 375);
    choiceButtonPanel.setBackground(Color.black);
    choiceButtonPanel.setLayout(new GridLayout(6, 2, 10, 10));
    con.add(choiceButtonPanel);

    for(int i = 0; i < 12; i++) {
      JButton button = new JButton(null, null);
      if(i==10){
        button.setLayout(new GridLayout(1, 2));

        stringShopCounterLabel = new JLabel("", SwingConstants.CENTER);
        stringShopCounterLabel.setForeground(Color.white);
        stringShopCounterLabel.setFont(normalFont);
        button.add(stringShopCounterLabel);

        intShopCounterLabel = new JLabel("", SwingConstants.CENTER);
        intShopCounterLabel.setForeground(Color.white);
        intShopCounterLabel.setFont(normalFont);
        button.add(intShopCounterLabel);
      }
      button.setBackground(Color.black);
      button.setForeground(Color.white);
      button.setFont(normalFont);
      button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
      button.setFocusPainted(false);
      button.addActionListener(cHandler);
      button.setActionCommand("c"+i);
      buttonList.add(button);
      choiceButtonPanel.add(button);
    }

    //itemInfoPanel
    itemInfoPanel = new JPanel();
    itemInfoPanel.setBounds(550, 435, 300, 100);
    itemInfoPanel.setBackground(Color.black);
    itemInfoPanel.setLayout(new GridLayout(3, 2, 0, 0));
    itemInfoPanel.setBorder(BorderFactory.createLineBorder(Color.red, 1));
    con.add(itemInfoPanel);

    for(int i = 0; i < 6; i++) {
      JLabel label;
      if(i % 2 == 0){
        label = new JLabel("", SwingConstants.LEFT);
        label.setBorder(new EmptyBorder(0,10,0,0));
      } else{
        label = new JLabel("", SwingConstants.RIGHT);
        label.setBorder(new EmptyBorder(0,0,0,10));
      }
      label.setBackground(Color.black);
      label.setForeground(Color.white);
      label.setFont(normalFont);
      itemInfoLabelList.add(label);
      itemInfoPanel.add(label);
    }


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

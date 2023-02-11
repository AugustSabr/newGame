import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class UI {
  JFrame window;
  Container con;
  Dimension roomImgSize, monsterImgSize;
  JLabel roomImgLabel, monsterImgLabel;
  JPanel mainTextPanel;
  JTextArea mainTextArea;
  JScrollPane scroll;

  Font titleFont = new Font("Times New Roman", Font.PLAIN, 128), normalFont = new Font("Times New Roman", Font.PLAIN, 25);

  public void createUI(){
    //window
    window = new JFrame();
    window.setTitle("Game");
    window.setSize(900, 700);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.black);
    window.setLayout(null);
    window.setVisible(true);
    con = window.getContentPane();

    monsterImgLabel = new JLabel();
    con.add(monsterImgLabel);

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

    public void drawRoom(String s){
    roomImgLabel.setIcon(new ImageIcon("rooms/"+s+".png"));
    roomImgSize = roomImgLabel.getPreferredSize(); 
    roomImgLabel.setBounds(10, 10, roomImgSize.width, roomImgSize.height);
  }

  public void drawMonster(String s){
    monsterImgLabel.setIcon(new ImageIcon("monsters/"+s+".png"));
    monsterImgSize = monsterImgLabel.getPreferredSize(); 
    monsterImgLabel.setBounds(10, 10, monsterImgSize.width, monsterImgSize.height);
  }
}

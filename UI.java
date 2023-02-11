import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



//hele filen er lager egt bare de tre displayene jeg bruker iløpet av filen. funcksjonene er alle funksjoner fra librariene jeg bruker
public class UI {
  JFrame window;
  Container con;
  Dimension diceImgSize;
  JLabel diceImgLabel;

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

    diceImgLabel = new JLabel();

    random("i");
    diceImgSize = diceImgLabel.getPreferredSize(); 
    diceImgLabel.setBounds(10, 10, diceImgSize.width, diceImgSize.height);

    con.add(diceImgLabel);

    //setVisible
    con.setVisible(false);
    con.setVisible(true);
    
    diceImgLabel.setVisible(true);
  }

    public void random(String s){
    diceImgLabel.setIcon(new ImageIcon("rooms/"+s+".png")); 
  }
}

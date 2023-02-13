import javax.swing.BorderFactory;
import java.awt.Color;


//ender på va som er synlig
public class VisibilityManager {
  private UI ui;
  private _KeyListener key;
  public VisibilityManager(UI userInterface){
    ui = userInterface;
  }
  public void addKeyListener(_KeyListener k) {
    key = k;
  }
  public void showTitleScreen(){
    //titlescreen
    ui.titleNamePanel.setVisible(true);
    ui.startButtonPanel.setVisible(true);
    ui.startButtonPanel.requestFocus();
    ui.updateButtonPanel.setVisible(true);

    //inputname
    ui.inputTextPanel.setVisible(false);

    //gamescreen
    ui.encounterImgLabel.setVisible(false);
    ui.roomImgLabel.setVisible(false);
    ui.mainTextPanel.setVisible(false);
    ui.choiceButtonPanel.setVisible(false);
    ui.sellCounterPanel.setVisible(false);

  }
  public void enterName(){
    //titlescreen
    ui.titleNamePanel.setVisible(false);
    ui.startButtonPanel.setVisible(false);
    ui.updateButtonPanel.setVisible(false);

    //inputname
    ui.inputTextPanel.setVisible(true);
    ui.inputTextField.requestFocus();

    ui.encounterImgLabel.setVisible(false);
    ui.roomImgLabel.setVisible(false);
    ui.mainTextPanel.setVisible(true);
    ui.choiceButtonPanel.setVisible(false);
    ui.sellCounterPanel.setVisible(false);

    // System.out.println(ui.window.getFocusOwner());
  }
  public void showGamescreen(){
    //titlescreen
    ui.window.requestFocus();
    ui.titleNamePanel.setVisible(false);
    ui.startButtonPanel.setVisible(false);
    ui.updateButtonPanel.setVisible(false);


    //inputname
    ui.inputTextPanel.setVisible(false);
    
    //gamescreen
    ui.encounterImgLabel.setVisible(true);
    ui.roomImgLabel.setVisible(true);
    ui.mainTextPanel.setVisible(true);
    ui.choiceButtonPanel.setVisible(false);
  }
  public void showchoiceButtons(){
    key.walking = false;
    key.inventory = true;
    ui.choiceButtonPanel.setVisible(true);
    ui.sellCounterPanel.setVisible(true);
    // ui.choiceButtonPanel.requestFocus();
  }
  public void dontShowchoiceButtons(){
    key.walking = true;
    key.inventory = false;
    ui.choiceButtonPanel.setVisible(false);
    ui.sellCounterPanel.setVisible(false);
  }
}

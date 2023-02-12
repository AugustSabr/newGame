//ender på va som er synlig
public class VisibilityManager {
  UI ui;
  public VisibilityManager(UI userInterface){
    ui = userInterface;
  }
  public void showTitleScreen(){
    //titlescreen
    ui.titleNamePanel.setVisible(true);
    ui.startButtonPanel.setVisible(true);
    ui.updateButtonPanel.setVisible(true);

    //inputname
    ui.inputTextPanel.setVisible(false);

    //gamescreen
    ui.encounterImgLabel.setVisible(false);
    ui.roomImgLabel.setVisible(false);
    ui.mainTextPanel.setVisible(false);
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
  }
}

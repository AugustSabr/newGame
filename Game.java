import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class Game {
  Player player;
  private UI ui = new UI();
  private VisibilityManager vm = new VisibilityManager(ui);
  private ChoiceHandler cHandler = new ChoiceHandler();
  private Item item = new Item(ui);
  private Map map;
  private _KeyListener key;
  private GameInventory in;
  private Encounter e;

  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    ui.createUI(cHandler);
    vm.showTitleScreen();
  }


  public class ChoiceHandler implements ActionListener{// ChoiceHandler arver fra ActionListener
    public void actionPerformed(ActionEvent event){//ActionCommand bruker for å skille knappene fra hverandre. en slags id.
      String yourChoice = event.getActionCommand();

      switch(yourChoice){//isteden for å endre ActionCommand endrer jeg en tekst variabel nextPosition for å velge hva hovedknappene gjør
        case "start":
          // in = new GameInventory();//lager en inventory av de lokale filene
          // room = new Room(game, ui, vm, in); //lager et room og sender med alle nødvendige objects så room kan endre på de underveis
          vm.enterName(); break;
        case "update": new UpdateLocalFiles(); break;
        case "makePlayer": makePlayer(); break;        }
    }
  }
  
  public void makePlayer(){
    try {
      ObjectInputStream input = new ObjectInputStream(new FileInputStream("saves/" + ui.inputTextField.getText() + ".dat"));
      player = (Player) input.readObject();
      input.close();
      ui.mainTextArea.setText(player.getName() + " is back for more");
    } catch (Exception e){
      player = new Player(ui.inputTextField.getText());
      ui.mainTextArea.setText(player.getName() + " started a new game");

      System.err.println("Couldnt load game. Created new player.");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
    }
    vm.showGamescreen();
    e = new Encounter(ui, item, player);
    map = new Map(ui, e, 3, 0, "east");
    key = new _KeyListener(map, ui, e);
  }
}
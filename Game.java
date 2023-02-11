public class Game {
  private UI ui = new UI();
  private _KeyListener key;
  private Map map;
  private Encounter e;

    public Game() {
      ui.createUI();
      e = new Encounter(ui);
      map = new Map(ui, e, 3, 0, "east");
      key = new _KeyListener(map, ui);
    }
    public static void main(String[] args) {
      new Game();
    }
}
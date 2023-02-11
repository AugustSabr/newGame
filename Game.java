public class Game {
  private UI ui = new UI();
  private KeyListenerTester key;
  private Layout la;

    public Game() {
      ui.createUI();
      la = new Layout(ui);
      key = new KeyListenerTester(la, ui);// choiceHandler er en class jeg lagde selv. sel lenger nede

      // JLabel label;
      // JPanel p = new JPanel();
      // label = new JLabel("Key Listener!");
      // p.add(label);

    }
    public static void main(String[] args) {
      new Game();
    }
}
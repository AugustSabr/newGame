import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class _KeyListener implements KeyListener {
  private Map map;
  private UI ui;
  private Encounter en;
  public String z = "", x = "", c = "";
  public Boolean walking = true, inventory = false;

  public _KeyListener(Map m, UI u, Encounter e) {
    map = m;
    ui = u;
    en = e;
    ui.window.addKeyListener(this);
    ui.choiceButtonPanel.addKeyListener(this);
    e.addKeyListener(this);
    map.addKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if(walking){
        map.move("up");
      }
      if(inventory){
        ui.chageButton("up");
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if(walking){
        map.move("down");
      }
      if(inventory){
        ui.chageButton("down");
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if(walking){
        map.move("right");
      }
      if(inventory){
        ui.chageButton("right");
      }    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if(walking){
        map.move("left");
      }
      if(inventory){
        ui.chageButton("left");
      }
    }

    if (e.getKeyCode() == KeyEvent.VK_Z) {
      if(walking){
        en.selectPosition(z);
      }else if(inventory){
        ui.buttonList.get(ui.pos).doClick();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_X) {
      if(walking){
        en.selectPosition(x);
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_C) {
      if(walking){
        en.selectPosition(c);
      }
    }
    // System.out.println(z);
    // System.out.println(x);
    // System.out.println(c);
  }
}
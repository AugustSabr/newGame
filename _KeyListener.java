import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class _KeyListener implements KeyListener {
    Map map;
    UI ui;
    Encounter en;
    String z, x, c;

    public _KeyListener(Map m, UI u, Encounter e) {
        map = m;
        ui = u;
        en = e;
        ui.window.addKeyListener(this);
        e.addKeyListener(this);
        map.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            map.move("up");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            map.move("down");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            map.move("right");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            map.move("left");
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            System.out.println("Z");
            en.selectPosition(z);
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            System.out.println("X");
        }
        if (e.getKeyCode() == KeyEvent.VK_C) {
            System.out.println("C");
        }
    }
}
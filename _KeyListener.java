import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class _KeyListener implements KeyListener {
    Map map;
    UI ui;

    public _KeyListener(Map m, UI u) {
        map = m;
        ui = u;
        ui.window.addKeyListener(this);
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
    }
}
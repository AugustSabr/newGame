import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class KeyListenerTester extends JFrame implements KeyListener {
    Layout la;
    UI ui;

    public KeyListenerTester(Layout l, UI u) {
        la = l;
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
            // System.out.println("FRONT");

            la.move("up");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // System.out.println("TURN BACK");

            la.move("down");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // System.out.println("TURN RIGHT");

            la.move("right");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // System.out.println("TURN LEFT");

            la.move("left");
        }
    }

    // public static void main(String[] args) {
    //     new KeyListenerTester("Key Listener Tester");
    // }
}
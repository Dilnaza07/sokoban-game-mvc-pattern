import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Controller implements KeyListener, MouseListener {
    private Model model;

    public Controller(Viewer viewer) {
        model = new Model(viewer);
    }

    public Model getModel() {
        return model;
    }

    public void keyTyped(KeyEvent event) {

    }

    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        String direction = "";
        switch (keyCode) {
            case 37:
                direction = "Left";
                break;
            case 38:
                direction = "Up";

                break;
            case 39:
                direction = "Right";

                break;
            case 40:
                direction = "Down";

                break;
            default:
                return;
        }

        model.handleMoveCommand(direction);
    }

    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        model.doClick(x, y);
    }

    public void keyReleased(KeyEvent event) {

    }

    public void mouseClicked(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }
}

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Viewer {
    Canvas canvas;

    public Viewer() {
        Controller controller = new Controller(this);

        Model model = controller.getModel();
        canvas = new Canvas(model);
        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(1000, 700);
        frame.setLocation(400, 100);
        frame.add("Center", canvas);
        frame.setVisible(true);
        frame.addKeyListener(controller);
        frame.addMouseListener(controller);
    }

    public void update() {
        canvas.repaint();
    }

    public void showWonDialog() {
        JOptionPane.showMessageDialog(null, "You WON!");
    }
}

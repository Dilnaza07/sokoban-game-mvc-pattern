import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Canvas extends JPanel {
    private Model model;
    private Image imageGamer;
    private Image imageWall;
    private Image imageBox;
    private Image imageTarget;
    private Image imageError;
    private Font fontForButton;

    public Canvas(Model model) {
        this.model = model;
        Color colorBG = new Color(20, 20, 20);
        setBackground(colorBG);
        File fileGamer = new File("images/gamer.png");
        File fileWall = new File("images/wall.png");
        File fileBox = new File("images/box.png");
        File fileTarget = new File("images/target.png");
        File fileError = new File("images/error.png");
        try {
            imageGamer = ImageIO.read(fileGamer);
            imageWall = ImageIO.read(fileWall);
            imageBox = ImageIO.read(fileBox);
            imageTarget = ImageIO.read(fileTarget);
            imageError = ImageIO.read(fileError);
        } catch (IOException ioe) {
            System.out.println("Error " + ioe);
        }

        fontForButton = new Font("Impact", Font.BOLD, 35);
    }

    public void paint(Graphics g) {
        super.paint(g);

        boolean stateGame = model.getState();

        if (stateGame) {
            drawStartGameButton(g);
            drawDesktop(g);
        } else {
            drawError(g);
        }
    }

    private void drawStartGameButton(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(650, 100, 150, 75);
        g.setColor(Color.RED);
        g.setFont(fontForButton);
        g.drawString("START", 675, 150);

    }

    private void drawError(Graphics g) {
        g.drawImage(imageError, 50, 50, null);
    }


    private void drawDesktop(Graphics g) {
        int start = 50;
        int x = start;
        int y = start;
        int width = 50;
        int height = 50;
        int offset = 0;

        int[][] desktop = model.getDesktop();

        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    g.drawImage(imageGamer, x, y, null);
                } else if (desktop[i][j] == 2) {
                    g.drawImage(imageWall, x, y, null);
                } else if (desktop[i][j] == 3) {
                    g.drawImage(imageBox, x, y, null);
                } else if (desktop[i][j] == 4) {
                    g.drawImage(imageTarget, x, y, null);
                }

                x = x + width + offset;
            }
            x = start;
            y = y + height + offset;
        }
    }

}

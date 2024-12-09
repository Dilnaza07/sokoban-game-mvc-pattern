import java.io.FileInputStream;
import java.io.IOException;

public class Levels {
    private int level;

    public Levels() {
        level = 1;
    }

    public int[][] nextLevel() {
        int[][] array = null;

        switch (level) {
            case 1:
                array = getFirstLevel();
                break;
            case 2:
                array = getLevelFromFile(2);
                break;
            case 3:
                array = getLevelFromFile(3);
                break;
            default:
                level = 1;
                array = getFirstLevel();
        }

        level = level + 1;

        return array;
    }


    private int[][] getFirstLevel() {
        int[][] firstLevel = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 3, 0, 4, 2},
                {2, 0, 1, 0, 0, 0, 3, 0, 4, 2},
                {2, 0, 0, 0, 0, 0, 3, 0, 4, 2},
                {2, 0, 0, 0, 0, 0, 3, 0, 4, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 2, 2, 0, 0, 0, 0, 0, 2},
                {2, 0, 2, 2, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };

        return firstLevel;
    }

    private int[][] getLevelFromFile(int levelNumber) {
        FileInputStream in = null;
        StringBuilder container = null;

        try {
            in = new FileInputStream("./levels/level" + levelNumber + ".sok");
            container = new StringBuilder();
            int unicode;
            while ((unicode = in.read()) != -1) {
                char symbol = (char) unicode;
                if ('0' <= symbol && symbol <= '4' || (symbol == '\n')) {
                    container.append(symbol);
                }
            }
//      System.out.println(container);
        } catch (IOException ioe) {
            System.out.println(ioe);
            container = null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }

        int[][] array = null;
        if (container != null) {
            array = convertToArr(container);
        }

        return array;
    }

    private int[][] convertToArr(StringBuilder container) {
        int countArray = 0;
        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);
            if (element == '\n') {
                countArray = countArray + 1;
            }

            if (i == container.length() - 1) {
                countArray = countArray + 1;
            }
        }

        int[][] array = new int[countArray][];
        int a = 0;
        int b = 0;

        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);
            if (element == '\n') {
                array[b] = new int[a];
                b = b + 1;
                a = 0;
                continue;
            }
            a = a + 1;
            if (i == container.length() - 1) {
                array[b] = new int[a];
            }
        }

        int row = 0;
        int cell = 0;

        for (int t = 0; t < container.length(); t++) {
            char element = container.charAt(t);

            if (element == '\n') {
                row = row + 1;
                cell = 0;
                continue;
            }

            int number = Integer.parseInt("" + element);

            array[row][cell] = number;
            cell = cell + 1;
        }

        return array;
    }

}

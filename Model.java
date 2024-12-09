public class Model {
    private Viewer viewer;
    private int[][] desktop;
    private int indexX;
    private int indexY;
    private int[][] targetIndexes;
    private boolean stateDesktop;
    private Levels levels;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        levels = new Levels();
        initialization();
    }

    private void initialization() {
        desktop = levels.nextLevel();
        stateDesktop = true;
        int countOne = 0;
        int countThree = 0;
        int countFour = 0;

        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    countOne++;
                    indexX = i;
                    indexY = j;
                } else if (desktop[i][j] == 3) {
                    countThree++;
                } else if (desktop[i][j] == 4) {
                    countFour++;
                }
            }
        }

        if ((countOne != 1) || (countThree == 0 || countFour == 0) || (countThree != countFour)) {
            stateDesktop = false;
            return;
        }

        targetIndexes = new int[2][countFour];
        int a = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 4) {
                    targetIndexes[0][a] = i;
                    targetIndexes[1][a] = j;
                    a++;
                }
            }
        }
    }

    public void handleMoveCommand(String direction) {
        if (direction.equals("Left")) {
            move(0, -1);
        } else if (direction.equals("Up")) {
            move(-1, 0);
        } else if (direction.equals("Right")) {
            move(0, 1);
        } else if (direction.equals("Down")) {
            move(1, 0);
        } else {
            return;
        }

        checkTarget();
        viewer.update();
        checkWon();
    }

    private void move(int directionX, int directionY) {
        int nextX = indexX + directionX;
        int nextY = indexY + directionY;
        int afterNextX = indexX + 2 * directionX;
        int afterNextY = indexY + 2 * directionY;

        // Box move
        if (desktop[nextX][nextY] == 3) {
            if (desktop[afterNextX][afterNextY] == 0 || desktop[afterNextX][afterNextY] == 4) {
                desktop[afterNextX][afterNextY] = 3;
                if (desktop[nextX][nextY] == 4) {
                    desktop[nextX][nextY] = 4;
                } else {
                    desktop[nextX][nextY] = 0;
                }
            }
        }

        // Player move
        if (desktop[nextX][nextY] == 0 || desktop[nextX][nextY] == 4) {
            if (desktop[indexX][indexY] == 4) {
                desktop[indexX][indexY] = 4;
            } else {
                desktop[indexX][indexY] = 0;
            }
            indexX = nextX;
            indexY = nextY;
            desktop[indexX][indexY] = 1;
        }
    }

    private void checkTarget() {
        for (int t = 0; t < targetIndexes[0].length; t++) {
            int i = targetIndexes[0][t];
            int j = targetIndexes[1][t];
            if (desktop[i][j] == 0) {
                desktop[i][j] = 4;
            }
        }
    }

    private void checkWon() {
        boolean flag = true;
        for (int t = 0; t < targetIndexes[0].length; t++) {
            int i = targetIndexes[0][t];
            int j = targetIndexes[1][t];
            if (desktop[i][j] != 3) {
                flag = false;
                break;
            }
        }

        if (flag) {
            viewer.showWonDialog();
            initialization();
            viewer.update();
        }
    }

    public boolean getState() {
        return stateDesktop;
    }

    public int[][] getDesktop() {
        return desktop;
    }

    public void doClick(int x, int y) {
        System.out.println(x + " " + y);
    }
}

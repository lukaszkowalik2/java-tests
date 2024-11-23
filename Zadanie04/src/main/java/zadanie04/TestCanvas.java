package zadanie04;

public class TestCanvas extends Canvas {
    private final int[][] pixels;

    TestCanvas(int cols, int rows){
        pixels = new int[rows][cols];
    }

    @Override
    public int getBrightness(Position2D position) {
        return pixels[position.getRow()][position.getCol()];
    }

    @Override
    public void setBrightness(Position2D position, int brightness) {
        pixels[position.getRow()][position.getCol()] = brightness;
    }

    @Override
    public Position2D getMaxPosition() {
        return new Position2D(pixels[0].length - 1, pixels.length - 1);
    }

    public void printCanvas() {
        for (int[] row : pixels) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}

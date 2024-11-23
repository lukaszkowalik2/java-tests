package zadanie04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasFillTest {

    @Test
    public void testFillSimpleCase() {
        TestCanvas canvas = new TestCanvas(6, 6);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                canvas.setBrightness(new Position2D(j, i), 50);
            }
        }

        List<Position2D> neighbours = Arrays.asList(
                new Position2D(-1, 0),
                new Position2D(0, -1)
        );

        CanvasFill filler = new CanvasFill();
        filler.fill(canvas, neighbours, new Position2D(2, 2), 200);

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                assertEquals(200, canvas.getBrightness(new Position2D(j, i)));
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i > 2 || j > 2) {
                    assertEquals(50, canvas.getBrightness(new Position2D(j, i)));
                }
            }
        }
    }
    @Test
    void testFillSinglePixel() {
        TestCanvas canvas = new TestCanvas(3, 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.setBrightness(new Position2D(j, i), 50);
            }
        }

        List<Position2D> neighbours = new ArrayList<>();
        Position2D start = new Position2D(1, 1);

        CanvasFill filler = new CanvasFill();
        filler.fill(canvas, neighbours, start, 200);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    assertEquals(200, canvas.getBrightness(new Position2D(j, i)));
                } else {
                    assertEquals(50, canvas.getBrightness(new Position2D(j, i)));
                }
            }
        }
    }
    @Test
    void testFillEntireCanvas() {
        TestCanvas canvas = new TestCanvas(4, 4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                canvas.setBrightness(new Position2D(j, i), 50);
            }
        }

        List<Position2D> neighbours = List.of(
                new Position2D(0, 1),
                new Position2D(0, -1),
                new Position2D(1, 0),
                new Position2D(-1, 0)
        );

        Position2D start = new Position2D(0, 0);

        CanvasFill filler = new CanvasFill();
        filler.fill(canvas, neighbours, start, 200);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(200, canvas.getBrightness(new Position2D(j, i)));
            }
        }
    }
    @Test
    void testFillBlockedRegion() {
        TestCanvas canvas = new TestCanvas(5, 5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    canvas.setBrightness(new Position2D(j, i), 100); // Blocked
                } else {
                    canvas.setBrightness(new Position2D(j, i), 50);
                }
            }
        }

        List<Position2D> neighbours = List.of(
                new Position2D(0, 1),
                new Position2D(0, -1),
                new Position2D(1, 0),
                new Position2D(-1, 0)
        );

        Position2D start = new Position2D(0, 0);

        CanvasFill filler = new CanvasFill();
        filler.fill(canvas, neighbours, start, 70);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    assertEquals(100, canvas.getBrightness(new Position2D(j, i)));
                } else {
                    assertEquals(70, canvas.getBrightness(new Position2D(j, i)));
                }
            }
        }
    }
}

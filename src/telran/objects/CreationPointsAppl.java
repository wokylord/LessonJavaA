package telran.objects;

import java.io.*;
import java.util.*;

public class CreationPointsAppl {
    public static void main(String[] args) throws Exception {
        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 4));
        points.add(new Point(5, 6));
        points.add(new Point(7, 8));

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("points.data"))) {
            output.writeObject(points);
        }
        System.out.println(points);
    }
}

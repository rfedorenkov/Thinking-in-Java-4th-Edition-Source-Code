package io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Restoring the state of the pretend CAD system.
 * {RunFirst: StoreCADState}
 */
public class RecoverCADState {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("src/io/CADState.out"));
        // Read in the same order they were written:
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}
/* Output:
[class io.Circlecolor[1] xPos[58] yPos[55] dim[93]
, class io.Squarecolor[0] xPos[61] yPos[61] dim[29]
, class io.Linecolor[3] xPos[68] yPos[0] dim[22]
, class io.Circlecolor[1] xPos[7] yPos[88] dim[28]
, class io.Squarecolor[0] xPos[51] yPos[89] dim[9]
, class io.Linecolor[3] xPos[78] yPos[98] dim[61]
, class io.Circlecolor[1] xPos[20] yPos[58] dim[16]
, class io.Squarecolor[0] xPos[40] yPos[11] dim[22]
, class io.Linecolor[3] xPos[4] yPos[83] dim[6]
, class io.Circlecolor[1] xPos[75] yPos[10] dim[42]
]
 */
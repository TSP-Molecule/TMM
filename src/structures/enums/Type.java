package structures.enums;


import javafx.scene.paint.Color;

/**
 *  Enum Type
 *      @author Emily Anible
 *      Stores the type of an element. Set in Elem.java.
 */
public enum Type {
    NONE(0),
    NONMETAL(1),
    NOBLE_GAS(2),
    ALKALI_METAL(3),
    ALKALINE_EARTH_METAL(4),
    METALLOID(5),
    HALOGEN(6),
    METAL(7),
    TRANSITION_METAL(8),
    POST_TRANSITION_METAL(9),
    ACTINIDE(10),
    LANTHANIDE(11);


    private int ordinal = 0;
    private Color fill = null;

    private Color plainCols [] = {
            Color.rgb(188, 255, 128, 1), Color.rgb(240, 128, 128, 1), Color.rgb(255, 209, 128, 1), Color.rgb(166, 160, 67, 1),
            Color.rgb(94, 224, 255, 1), Color.rgb(134, 252, 110, 1), Color.rgb(215, 184, 255, 1), Color.rgb(255, 95, 168, 1),// 55 128
            Color.rgb(255, 114, 247, 1), Color.rgb(230, 180, 180, 1), Color.rgb(195, 195, 195, 1), Color.rgb(120, 120, 120, 1),
            Color.rgb(255, 185, 105, 1)
    };

    Type( int num) {
        ordinal = num;
        fill = plainCols[num];
    }

    public  Color getFill() {
        return fill;
    }

    public  int getOrdinal(int i) {
        return ordinal;
    }
}

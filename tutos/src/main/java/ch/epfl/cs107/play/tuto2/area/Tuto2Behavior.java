package ch.epfl.cs107.play.tuto2.area;

import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.window.Window;

import java.util.HashMap;
import java.util.Map;

public class Tuto2Behavior extends AreaBehavior {

    public Tuto2Behavior (Window window, String name) {
        super(window, name);
        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                Tuto2CellType cellType = Tuto2CellType.toType(this.getRGB( this.getHeight() -1 -y, x));
                Tuto2Cell cell = new Tuto2Cell(x, y, cellType);
                this.setCell(x, y, cell);
            }
        }
    }

    public enum Tuto2CellType {
        NULL (0 , false ),
        WALL ( -16777216 , false ), // #000000 RGB code of black
        IMPASSABLE ( -8750470 , false ), // #7 A7A7A , RGB color of gray
        INTERACT ( -256 , true ), // # FFFF00 , RGB color of yellow
        DOOR ( -195580 , true ), // # FD0404 , RGB color of red
        WALKABLE (-1, true ); // # FFFFFF , RGB color of white
        final int type;
        final boolean isWalkable;
        public static Tuto2CellType toType(int type) {
            switch (type) {
                case -16777216 -> {
                    return WALL;
                }
                case -8750470 -> {
                    return IMPASSABLE;
                }
                case -256 -> {
                    return INTERACT;
                }
                case -195580 -> {
                    return DOOR;
                }
                case -1 -> {
                    return WALKABLE;
                }
                default -> {
                    return NULL;
                }
            }
        }
        Tuto2CellType (int type, boolean isWalkable){
            this.type = type;
            this.isWalkable = isWalkable;
        }
    }
}

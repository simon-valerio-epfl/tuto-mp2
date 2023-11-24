package ch.epfl.cs107.play.tuto2.area;

import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Cell;

public class Tuto2Cell extends Cell {
    private Tuto2Behavior.Tuto2CellType type;
    public Tuto2Cell (int x, int y, Tuto2Behavior.Tuto2CellType type) {
        super(x, y);
        this.type = type;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    protected boolean canLeave(Interactable entity) {
        return true;
    }

    @Override
    protected boolean canEnter(Interactable entity) {
        return true;
    }
}

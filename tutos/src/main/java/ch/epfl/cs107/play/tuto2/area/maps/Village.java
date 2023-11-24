package ch.epfl.cs107.play.tuto2.area.maps;

import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.tuto2.actor.GhostPlayer;
import ch.epfl.cs107.play.tuto2.area.Tuto2Area;

public class Village extends Tuto2Area {

    public void createArea () {
        this.registerActor(new Background(this));
        this.registerActor(new Foreground(this));
    }

    @Override
    public String getTitle() {
        return "zelda/Village";
    }

}

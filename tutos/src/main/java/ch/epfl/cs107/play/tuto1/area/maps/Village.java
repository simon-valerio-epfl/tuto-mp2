package ch.epfl.cs107.play.tuto1.area.maps;

import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.tuto1.actor.SimpleGhost;
import ch.epfl.cs107.play.tuto1.area.SimpleArea;

public class Village extends SimpleArea {

    public void createArea () {
        Vector position = new Vector(20, 10);
        SimpleGhost ghost = new SimpleGhost(position, "ghost.2");

        this.registerActor(ghost);

        this.registerActor(new Background(this));
    }

    @Override
    public String getTitle() {
        return "zelda/Village";
    }

}

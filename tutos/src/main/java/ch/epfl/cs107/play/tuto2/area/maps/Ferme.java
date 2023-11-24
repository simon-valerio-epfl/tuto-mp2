package ch.epfl.cs107.play.tuto2.area.maps;

import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.tuto2.area.Tuto2Area;

public class Ferme extends Tuto2Area {

    public void createArea () {
        this.registerActor(new Background(this));
    }

    @Override
    public String getTitle() {
        return "zelda/Ferme";
    }

}

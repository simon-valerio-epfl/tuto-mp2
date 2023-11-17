package ch.epfl.cs107.play.tuto1;

import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.tuto1.area.maps.Ferme;
import ch.epfl.cs107.play.tuto1.area.maps.Village;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

public class Tuto1 extends AreaGame {

    private void createAreas() {
        addArea(new Ferme());
        addArea(new Village());
    }

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            this.setCurrentArea("zelda/Ferme", true);
            return true;
        } else return false;
    }

    public void end() {
        TextGraphics endText = new TextGraphics("coucou", 0.5f, Color.BLUE);
        endText.draw(this.getWindow());
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public String getTitle() {
        return "Tuto1";
    }

}
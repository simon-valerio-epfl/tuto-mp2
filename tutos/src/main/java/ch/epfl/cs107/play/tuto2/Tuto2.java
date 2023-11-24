package ch.epfl.cs107.play.tuto2;

import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.tuto2.actor.GhostPlayer;
import ch.epfl.cs107.play.tuto2.area.maps.Ferme;
import ch.epfl.cs107.play.tuto2.area.maps.Village;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

public class Tuto2 extends AreaGame {

    private GhostPlayer player;

    private void createAreas() {
        addArea(new Ferme());
        addArea(new Village());
    }

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            this.setCurrentArea("zelda/Ferme", true);
            GhostPlayer player = new GhostPlayer(this.getCurrentArea(), Orientation.DOWN, new DiscreteCoordinates(2, 1), "ghost.2");
            this.player = player;
            player.enterArea(this.getCurrentArea(), new DiscreteCoordinates(2, 10));
            //System.out.println(this.getCurrentArea().registeredActors.contains(player));
            this.getCurrentArea().setViewCandidate(player);
            return true;
        } else return false;
    }

    public void switchArea () {
        this.player.leaveArea(this.getCurrentArea());

        if (this.getCurrentArea().getTitle().equals("zelda/Ferme")) {
            this.setCurrentArea("zelda/Village", false);
            this.player.enterArea(this.getCurrentArea(), new DiscreteCoordinates(5, 15));
        } else if (this.getCurrentArea().getTitle().equals("zelda/Village")) {
            this.setCurrentArea("zelda/Ferme", false);
            this.player.enterArea(this.getCurrentArea(), new DiscreteCoordinates(2, 10));
        }

        this.getCurrentArea().registerActor(this.player);
        this.getCurrentArea().setViewCandidate(this.player);
        this.player.strengthen();
    }

    public void end() {
        TextGraphics endText = new TextGraphics("coucou", 0.5f, Color.BLUE);
        endText.draw(this.getWindow());
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        if (this.player.isWeak()) {
            this.switchArea();
        }
    }

    public String getTitle() {
        return "Tuto2";
    }

}

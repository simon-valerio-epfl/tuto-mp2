package ch.epfl.cs107.play.tuto2.area;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.tuto2.area.Tuto2Behavior;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * ???
 */
public abstract class Tuto2Area extends Area {

    /** ??? */
    private Window window;

    /**
     * ???
     */
    protected abstract void createArea();

    /**
     * ???
     * @param window (Window): display context. Not null
     * @param fileSystem (FileSystem): given file system. Not null
     * @return ???
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        if (super.begin(window, fileSystem)) {
            this.setBehavior(new Tuto2Behavior(window, getTitle()));
            this.createArea();
            return true;
        }
        return false;
    }

    /**
     * ???
     * @return ???
     */
    @Override
    public final float getCameraScaleFactor() {
        return 10f;
    }

}

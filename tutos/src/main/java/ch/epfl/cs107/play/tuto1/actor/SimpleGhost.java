package ch.epfl.cs107.play.tuto1.actor;

import ch.epfl.cs107.play.engine.actor.Entity;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import javax.annotation.processing.Generated;
import java.awt.*;

public class SimpleGhost extends Entity {

    private Sprite sprite;
    private float hp;
    private TextGraphics hpText;

    public SimpleGhost(Vector position, String spriteName) {
        super(position);
        this.sprite = new Sprite(spriteName, 1f, 1f, this);
        this.hp = 10;
        this.hpText = new TextGraphics(Integer.toString((int) this.hp), 0.4f, Color.BLUE);
        this.hpText.setParent(this);
        this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
    }

    public void draw(Canvas canvas) {
        this.hpText.draw(canvas);
        this.sprite.draw(canvas);
    }

    public void update(float deltaTime) {
        hp -= deltaTime;
        hpText = new TextGraphics(Integer.toString((int) hp), 0.4f, Color.BLUE);
    }

    boolean isWeak() {
        return this.hp <= 0;
    }

    void strengthen() {
        this.hp = 10;
    }

}

package ch.epfl.cs107.play.tuto1.actor;

import ch.epfl.cs107.play.engine.actor.Entity;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import javax.annotation.processing.Generated;
import java.awt.Color;

public class SimpleGhost extends Entity {

    private Sprite sprite;
    private float hp;
    private TextGraphics hpText;

    public SimpleGhost(Vector position, String spriteName) {
        super(position);
        this.sprite = new Sprite(spriteName, 1f, 1f, this);
        this.hp = 10;
        this.hpText = new TextGraphics(Integer.toString((int) this.hp), 0.4f, Color.WHITE);
        this.hpText.setParent(this);
        this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
    }

    @Override
    public void draw(Canvas canvas) {
        this.hpText.draw(canvas);
        this.sprite.draw(canvas);
    }

    public void update(float deltaTime) {
        hp -= deltaTime;
        if (this.isWeak()) hp = 0;
        hpText.setText(Integer.toString((int) hp));
    }

    public boolean isWeak() {
        return this.hp <= 0;
    }

    public void strengthen() {
        this.hp = 10;
    }

    public void moveUp(float deltaTime) {
        setCurrentPosition(getPosition().add(0.f, deltaTime));
    }

    public void moveDown(float deltaTime) {
        setCurrentPosition(getPosition().add(0.f, -deltaTime));
    }

    public void moveLeft(float deltaTime) {
        setCurrentPosition(getPosition().add(-deltaTime, 0.f));
    }

    public void moveRight(float deltaTime) {
        setCurrentPosition(getPosition().add(deltaTime, 0.f));
    }



}

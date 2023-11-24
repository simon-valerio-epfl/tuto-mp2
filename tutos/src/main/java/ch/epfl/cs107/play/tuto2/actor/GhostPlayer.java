package ch.epfl.cs107.play.tuto2.actor;

import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GhostPlayer extends MovableAreaEntity {

    private Sprite sprite;
    private float hp;
    private TextGraphics hpText;

    public GhostPlayer (Area area, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(area, orientation, coordinates);
        this.sprite = new Sprite(spriteName, 1f, 1f, this);
        this.hp = 10;
        this.hpText = new TextGraphics(Integer.toString((int) this.hp), 0.4f, Color.WHITE);
        this.hpText.setParent(this);
        this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells () {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public void enterArea (Area area, DiscreteCoordinates coordinates) {
        area.registerActor(this);
        setCurrentPosition(new Vector(coordinates.x, coordinates.y));
        resetMotion();
    }

    public void leaveArea (Area area) {
        area.unregisterActor(this);
    }

    @Override
    public void draw(Canvas canvas) {
        this.hpText.draw(canvas);
        this.sprite.draw(canvas);
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

    public void update(float deltaTime) {

        hp -= deltaTime;
        if (this.isWeak()) hp = 0;
        hpText.setText(Integer.toString((int) hp));

        Keyboard keyboard = this.getOwnerArea().getKeyboard();
        Button upKey = keyboard.get(Keyboard.UP);
        Button downKey = keyboard.get(Keyboard.DOWN);
        Button leftKey = keyboard.get(Keyboard.LEFT);
        Button rightKey = keyboard.get(Keyboard.RIGHT);

        if (upKey.isDown()) {
            if (this.getOrientation() == Orientation.UP) {
                moveUp(deltaTime);
            } else {
                this.orientate(Orientation.UP);
            }
        }
        else if (downKey.isDown()) {
            if (this.getOrientation() == Orientation.DOWN) {
                moveDown(deltaTime);
            } else {
                this.orientate(Orientation.DOWN);
            }
        }
        else if (leftKey.isDown()) {
            if (this.getOrientation() == Orientation.LEFT) {
                moveLeft(deltaTime);
            } else {
                this.orientate(Orientation.LEFT);
            }
        }
        else if (rightKey.isDown()) {
            if (this.getOrientation() == Orientation.RIGHT) {
                moveRight(deltaTime);
            } else {
                this.orientate(Orientation.RIGHT);
            }
        }

        super.update(deltaTime);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

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
    public boolean takeCellSpace() {
        return true;
    }
}

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

    private final static int ANIMATION_DURATION = 8;

    public GhostPlayer (Area area, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(area, orientation, coordinates);
        this.sprite = new Sprite(spriteName, 1f, 1f, this);
        this.hp = 10;
        this.hpText = new TextGraphics(Integer.toString((int) this.hp), 0.4f, Color.WHITE);
        this.hpText.setParent(this);
        this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
        resetMotion();
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells () {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public void enterArea (Area area, DiscreteCoordinates coordinates) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(new Vector(coordinates.x, coordinates.y));
        resetMotion();
    }

    public void leaveArea () {
        getOwnerArea().unregisterActor(this);
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

    public void moveIfKeyPressed (Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!this.isDisplacementOccurs()) {
                this.orientate(orientation);
                this.move(ANIMATION_DURATION);
            }
        }
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

        moveIfKeyPressed(Orientation.UP, upKey);
        moveIfKeyPressed(Orientation.DOWN, downKey);
        moveIfKeyPressed(Orientation.LEFT, leftKey);
        moveIfKeyPressed(Orientation.RIGHT, rightKey);

        super.update(deltaTime);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }
}

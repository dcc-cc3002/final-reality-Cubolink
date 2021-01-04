package com.github.Cubolink.finalreality.gui.spritegroups;

import javafx.scene.Group;

/**
 * Abstract class with common sprite group's behavior.
 */
public abstract class AbstractSpriteGroup implements SpriteGroup {
    protected double cx;
    protected double cy;

    @Override
    public void setCx(double cx) {
        this.cx = cx;
    }
    @Override
    public void setCy(double cy) {
        this.cy = cy;
    }

    @Override
    public double getCx() {
        return cx;
    }

    @Override
    public double getCy() {
        return cy;
    }

    @Override
    public abstract Group getGroup();

    @Override
    public abstract void updateDrawing(double currentTime);

}

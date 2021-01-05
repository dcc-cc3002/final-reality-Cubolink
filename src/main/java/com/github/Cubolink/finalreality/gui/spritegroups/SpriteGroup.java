package com.github.Cubolink.finalreality.gui.spritegroups;

import javafx.scene.Group;

/**
 * Interface for Sprite Groups.
 * Sprite groups can update its drawing and positions.
 */
public interface SpriteGroup {

    /**
     * @return the javafx Group of the sprite group
     */
    Group getGroup();

    /**
     * Updates the sprite's group children drawing, the sprite position or animation for example.
     * @param currentTime to consider when updating the frame animation of the sprite.
     */
    void updateDrawing(double currentTime);

    /**
     * Sets the sprite's x-center position.
     * @param cx x-center position to set.
     */
    void setCx(double cx);

    /**
     * Sets the sprite's y-center position.
     * @param cy y-center position to set.
     */
    void setCy(double cy);

    /**
     * Gets the sprite's x-center position.
     * @return the x-center position.
     */
    double getCx();

    /**
     * Gets the sprite's y-center position.
     * @return the y-center position.
     */
    double getCy();
}

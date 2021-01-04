package com.github.Cubolink.finalreality.gui.spritegroups;

import com.github.Cubolink.finalreality.gui.FinalReality;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Cursor Sprite's Class.
 * This sprite group has a cursor image which is displayed in the position determined by this class.
 */
public class CursorSprite extends AbstractSpriteGroup {
    Group cursorGroup;
    ImageView imageView;

    /**
     * Default constructor for Cursor's Sprite.
     * Instantiates a Cursor Sprite, with its displayable cursor's image.
     * @param imgCursorURL the String URL sprite's image direction to use as sprite.
     * @throws FileNotFoundException when the file doesn't exist.
     */
    public CursorSprite(String imgCursorURL) throws FileNotFoundException {
        imageView = new ImageView(new Image(new FileInputStream(imgCursorURL)));

        cursorGroup = new Group();
        cursorGroup.getChildren().add(imageView);

        cx = 0;
        cy = 0;
    }

    @Override
    public Group getGroup() {
        return cursorGroup;
    }

    @Override
    public void updateDrawing(double currentTime) {
        imageView.setLayoutX(cx + Math.sin(2 * Math.PI * currentTime));
        imageView.setLayoutY(cy);
    }
}

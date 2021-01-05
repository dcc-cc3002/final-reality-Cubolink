package com.github.Cubolink.finalreality.gui.spritegroups;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Class for Character Sprite Groups.
 * This sprite group has an array of images that conforms its displayable animation.
 * It also have displayable text information up and down of the image: The name of the sprite and other optional information.
 */
public class CharacterSpriteGroup extends AbstractSpriteGroup {
    private final Group spriteGroup;
    private final Label spriteNameLabel;
    private final Label spriteSubtitleLabel;
    private final Group currentImageViewGroup;
    private ImageView[] imageViews;
    private ImageView currentImageView;

    private double width;
    private double height;

    private final double dtAnimation;

    /**
     * Default constructor for Character's Sprite Group.
     * Instantiates a group with empty displayable information and images.
     */
    public CharacterSpriteGroup() {

        spriteGroup = new Group();
        currentImageViewGroup = new Group();
        spriteNameLabel = new Label();
        spriteNameLabel.setTextFill(Color.rgb(190, 255, 190));
        spriteSubtitleLabel = new Label();
        spriteSubtitleLabel.setTextFill(Color.rgb(255, 255, 255));
        cx = 0;
        cy = 0;

        dtAnimation = 0.4;

        spriteGroup.getChildren().setAll(spriteNameLabel, spriteSubtitleLabel, currentImageViewGroup);
    }

    /**
     * Sets the string name of the sprite.
     * @param name string to sets as name.
     */
    public void setName(String name) {
        spriteNameLabel.setText(name);
    }

    /**
     * Sets a string to the sprite's sub information display.
     * @param subtitle the text to display.
     */
    public void setSubtitle(String subtitle) {
        spriteSubtitleLabel.setText(subtitle);
    }

    /**
     * Sets an array of Images that will conform the animation of the sprite.
     * @param images to sets as frame images.
     */
    public void setImages(Image[] images) {
        imageViews = new ImageView[images.length];
        for (int i = 0; i < images.length; i++) {
            imageViews[i] = new ImageView(images[i]);
        }
        width = images[0].getWidth();
        height = images[0].getHeight();
        currentImageView = imageViews[0];
        currentImageViewGroup.getChildren().setAll(currentImageView);
        spriteGroup.getChildren().setAll(currentImageViewGroup, spriteNameLabel, spriteSubtitleLabel);
    }

    /**
     * Calculate's the frame index to show as current sprite image.
     * @param currentTime time parameter to chose the index.
     * @return the calculated fram index.
     */
    private int currentImageFrame(double currentTime) {
        return (int) ((currentTime/dtAnimation) % imageViews.length);
    }

    @Override
    public Group getGroup() {
        return spriteGroup;
    }

    @Override
    public void updateDrawing(double currentTime) {
        spriteNameLabel.setLayoutX(width/2);
        spriteNameLabel.setLayoutY(0);

        spriteSubtitleLabel.setLayoutX(width/2);
        spriteSubtitleLabel.setLayoutY(height);

        currentImageView = imageViews[currentImageFrame(currentTime)];
        currentImageViewGroup.getChildren().setAll(currentImageView);

        spriteGroup.setLayoutX(cx);
        spriteGroup.setLayoutY(cy);
    }
}

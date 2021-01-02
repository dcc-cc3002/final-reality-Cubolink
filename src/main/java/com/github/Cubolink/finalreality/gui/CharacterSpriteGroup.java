package com.github.Cubolink.finalreality.gui;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CharacterSpriteGroup implements SpriteGroup{
    private final Group spriteGroup;
    private final Label spriteNameLabel;
    private final Label spriteSubtitleLabel;
    private ImageView[] imageViews;
    private ImageView currentImageView;


    private double cx;
    private double cy;
    private double width;
    private double height;

    private final double dtAnimation;

    public CharacterSpriteGroup() {

        spriteGroup = new Group();
        spriteNameLabel = new Label();
        spriteNameLabel.setTextFill(Color.rgb(190, 255, 190));
        spriteSubtitleLabel = new Label();
        spriteSubtitleLabel.setTextFill(Color.rgb(255, 255, 255));
        cx = 0;
        cy = 0;

        dtAnimation = 0.25;

        spriteGroup.getChildren().setAll(spriteNameLabel, spriteSubtitleLabel);
    }

    public void setCx(double cx) {
        this.cx = cx;
    }
    public void setCy(double cy) {
        this.cy = cy;
    }
    public double getCx() {
        return cx;
    }
    public double getCy() {
        return cy;
    }

    public void setName(String name) {
        spriteNameLabel.setText(name);
    }
    public void setSubtitle(String subtitle) {
        spriteSubtitleLabel.setText(subtitle);
    }

    public void setImages(Image[] images) {
        imageViews = new ImageView[images.length];
        for (int i = 0; i < images.length; i++) {
            imageViews[i] = new ImageView(images[i]);
        }
        width = images[0].getWidth();
        height = images[0].getHeight();
        currentImageView = imageViews[0];
        spriteGroup.getChildren().setAll(currentImageView, spriteNameLabel, spriteSubtitleLabel);
    }

    private int currentImageFrame(double currentTime) {
        return (int) (currentTime/dtAnimation) % imageViews.length;
    }

    @Override
    public Group getGroup() {
        return spriteGroup;
    }

    @Override
    public void updateDrawing(double currentTime) {
        spriteNameLabel.setText("Entity");
        spriteNameLabel.setLayoutX(width/2);
        spriteNameLabel.setLayoutY(0);

        spriteSubtitleLabel.setText("hp/max");
        spriteSubtitleLabel.setLayoutX(width/2);
        spriteSubtitleLabel.setLayoutY(height);

        currentImageView = imageViews[currentImageFrame(currentTime)];

        spriteGroup.setLayoutX(cx);
        spriteGroup.setLayoutY(cy);
    }
}

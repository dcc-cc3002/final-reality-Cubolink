package com.github.Cubolink.finalreality.gui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CursorSprite implements SpriteGroup {
    Group cursorGroup;
    ImageView imageView;
    double cx;
    double cy;

    public CursorSprite(String imgCursorURL) throws FileNotFoundException {
        imageView = new ImageView(new Image(new FileInputStream(imgCursorURL)));

        cursorGroup = new Group();
        cursorGroup.getChildren().add(imageView);

        cx = 0;
        cy = 0;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }
    public void setCy(double cy) {
        this.cy = cy;
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

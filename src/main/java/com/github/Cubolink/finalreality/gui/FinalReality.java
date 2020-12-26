package com.github.Cubolink.finalreality.gui;

import com.github.Cubolink.finalreality.controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public class FinalReality extends Application {
  private static final String RESOURCE_PATH = "src/main/resources/";
  private static final GameController controller = new GameController();
  private static final double width = 850;
  private static final double height = 600;

  private static final Label[] playerCharacterLabels = new Label[controller.getMaxPlayerCharacterNum()];
  private static final Label[] enemyCharacterLabels = new Label[controller.getMaxEnemyCharacterNum()];
  private static final Label phaseInstructionsLabel = new Label();
  private static final Label[] userOptionLabels = new Label[controller.getMaxPlayerCharacterNum()+ controller.getMaxEnemyCharacterNum()];

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    controller.setUp();
    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(true);

    Group root = new Group();

    Group bottomGroup = makeBottomGroup();
    Group userOptionsGroup = makeUserOptionsGroup();
    Group mainVisualDisplayGroup = makeMainVisualDisplayGroup();
    root.getChildren().addAll(mainVisualDisplayGroup, userOptionsGroup, bottomGroup);

    // This sets the size of the Scene to be 400px wide, 200px high
    Scene scene = new Scene(root, width, height);

    setupTimer();

    primaryStage.setScene(scene);
    primaryStage.show();
  }


  /**
   * Creates a button.
   * @param name of the button.
   * @param cx x-coordinate of the center of the button.
   * @param cy y-coordinated of the center of the button.
   * @param w width of the button.
   * @param h height of the button.
   * @param imgNode image that the button will display.
   * @param actionValue action that the button will do when activated.
   * @return the Button created.
   */
  private @NotNull Button setupButton(String name, double cx, double cy, double w, double h,
                                      ImageView imgNode, EventHandler<ActionEvent> actionValue) {
    Button button = new Button(/*name*/);

    button.setLayoutX(cx-w/2);
    button.setLayoutY(cy-h/2);
    imgNode.setFitWidth(w);
    imgNode.setFitHeight(h);
    button.setPrefSize(w, h);

    button.setGraphic(imgNode);
    button.setFocusTraversable(false);
    button.setOnAction(actionValue);

    return button;
  }

  private @NotNull Group makeBottomGroup() throws FileNotFoundException {
    Group bottomGroup = new Group();

    // img nodes
    ImageView img_CLeft = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "C-Left.png")));
    ImageView img_CRight = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "C-Right.png")));
    ImageView img_A = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "A.png")));
    ImageView img_B = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "B.png")));
    // buttons
    double size = Math.min(height, width)/8;
    Button button_A = setupButton("A", 7*width/8, 6*height/8, size, size, img_A, FinalReality::buttonAAction);
    Button button_B = setupButton("B", 5*width/8, 7*height/8, size, size, img_B, FinalReality::buttonBAction);
    Button button_CRight = setupButton("Right", 3*width/8, 7*height/8, size, size, img_CRight, FinalReality::buttonC_RightAction);
    Button button_CLeft = setupButton("Left", width/8, 7*height/8, size, size, img_CLeft, FinalReality::buttonC_LeftAction);
    // add to root
    bottomGroup.getChildren().addAll(button_A, button_B, button_CLeft, button_CRight);

    return bottomGroup;
  }

  private @NotNull Group makeUserOptionsGroup() {
    Group userOptionsGroup = new Group();

    GridPane userOptionsGrid = new GridPane();
    userOptionsGrid.setPadding(new Insets(height/2, width/10, 3*height/4, width/10));
    userOptionsGrid.setHgap(width/20);
    userOptionsGrid.setVgap(height/20);

    phaseInstructionsLabel.setLayoutX(width/20);
    phaseInstructionsLabel.setLayoutY(height/2);
    userOptionsGroup.getChildren().add(phaseInstructionsLabel);

    for (int i = 0; i < userOptionLabels.length; i++) {
      userOptionLabels[i] = new Label("User option "+i);
      GridPane.setConstraints(userOptionLabels[i], i, 1);
    }
    userOptionsGrid.getChildren().addAll(userOptionLabels);

    userOptionsGroup.getChildren().add(userOptionsGrid);
    return userOptionsGroup;
  }

  private @NotNull Group makeMainVisualDisplayGroup() {
    Group mainVisualDisplayGroup = new Group();

    GridPane mainInformationGrid = new GridPane();
    mainInformationGrid.setPadding(new Insets(height/20, width/20, height/2, width/20));
    mainInformationGrid.setHgap(width/20);
    mainInformationGrid.setVgap(height/20);

    for (int i = 0; i < controller.getMaxPlayerCharacterNum(); i++) {
      playerCharacterLabels[i] = new Label("character"+i);
      GridPane.setConstraints(playerCharacterLabels[i], i, 0);
    }
    for (int i = 0; i < controller.getMaxEnemyCharacterNum(); i++) {
      enemyCharacterLabels[i] = new Label("enemy"+i);
      GridPane.setConstraints(enemyCharacterLabels[i], i, 1);
    }

    mainInformationGrid.getChildren().addAll(playerCharacterLabels);
    mainInformationGrid.getChildren().addAll(enemyCharacterLabels);
    mainInformationGrid.setAlignment(Pos.CENTER);

    mainVisualDisplayGroup.getChildren().add(mainInformationGrid);

    return mainVisualDisplayGroup;
  }

  private void setupTimer() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        phaseInstructionsLabel.setText(controller.getPhaseInfo());

        // userOptionLabels update
        String[] phaseOptions = controller.getPhaseOptions();
        for (int i = 0; i < userOptionLabels.length; i++) {
          if (i < phaseOptions.length) {
            userOptionLabels[i].setText(phaseOptions[i]);
          } else {
            userOptionLabels[i].setText("");
          }
        }

        // playerCharactersInfo update
        String[] playerCharactersInfo = controller.getPlayerCharactersInfo();
        for (int i = 0; i < playerCharactersInfo.length; i++) {
          playerCharacterLabels[i].setText(playerCharactersInfo[i]);
        }
        // enemyCharactersInfo update
        String[] enemyCharactersInfo = controller.getEnemyCharactersInfo();
        for (int i = 0; i < enemyCharactersInfo.length; i++) {
          enemyCharacterLabels[i].setText(enemyCharactersInfo[i]);
        }
      }
    };
    timer.start();
  }

  private static void playSound(ActionEvent event) {
    String audioFilePath = RESOURCE_PATH + "sound.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
              new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private static void buttonAAction(ActionEvent event) {
    playSound(event);
    System.out.println("A PRESSED");
    controller.next();
  }
  private static void buttonBAction(ActionEvent event) {
    playSound(event);
    System.out.println("B PRESSED");
    controller.prev();
  }
  private static void buttonC_LeftAction(ActionEvent event) {
    playSound(event);
    System.out.println("C-L PRESSED");
    controller.moveCursorLeft();
  }
  private static void buttonC_RightAction(ActionEvent event) {
    playSound(event);
    System.out.println("C-R PRESSED");
    controller.moveCursorRight();
  }

}
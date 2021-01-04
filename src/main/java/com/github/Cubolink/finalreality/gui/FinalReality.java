package com.github.Cubolink.finalreality.gui;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.gui.spritegroups.CharacterSpriteGroup;
import com.github.Cubolink.finalreality.gui.spritegroups.CursorSprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
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
 * The application displays a battle between the characters of the players and the enemies.
 * Each character has its turn, in which they can equip weapons, attack or wait.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public class FinalReality extends Application {
  private static final String RESOURCE_PATH = "src/main/resources/";
  private static final IGameController controller = new GameController();
  private static final double width = 1000;
  private static final double height = 600;

  private static CursorSprite cursorSprite;
  private static final Label[] playerCharacterLabels = new Label[controller.getMaxPlayerCharacterNum()];
  private static final Label phaseInstructionsLabel = new Label();
  private static final Label[] userOptionLabels = new Label[controller.getMaxPlayerCharacterNum()+ controller.getMaxEnemyCharacterNum()];

  /**
   * Main method of the application.
   * Launchs the application.
   * @param args Arguments passed when launched.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    controller.setUp();
    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(true);

    Group root = new Group();

    Group userOptionsGroup = makeUserOptionsGroup();
    Group playerCharacterInformationGroup = makePlayerCharacterInformationDisplayGroup();
    Group background = makeBackgroundLayer();
    Group spriteLayer = makeSpritesLayer();
    Group cursorLayer = makeCursorLayer();
    Group foreground = makeForegroundLayer();
    root.getChildren().addAll(background, spriteLayer, cursorLayer, playerCharacterInformationGroup, userOptionsGroup, foreground);

    Scene scene = new Scene(root, width, height);
    setOnKeyLink(scene);

    setupTimer();

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @return the width of the window's application.
   */
  public static double getWidth() {
    return width;
  }

  /**
   * @return the height of the window's application.
   */
  public static double getHeight() {
    return height;
  }

  /**
   * Links the Keyboard Keys events to actual actions.
   * @param scene to link the keyboard.
   */
  private void setOnKeyLink(Scene scene) {
    scene.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case LEFT:
          controller.moveCursorLeft();
          break;

        case RIGHT:
          controller.moveCursorRight();
          break;

        case SPACE:
          // sames as ENTER
        case ENTER:
          controller.next();
          break;

        case BACK_SPACE:
          // same as X
        case X:
          controller.prev();
          break;

        case ESCAPE:
          Platform.exit();
      }
    });
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
  private @NotNull Hyperlink setupButton(String name, double cx, double cy, double w, double h,
                                      ImageView imgNode, EventHandler<ActionEvent> actionValue) {
    Hyperlink button = new Hyperlink(/*name*/);

    button.setLayoutX(cx-w/2);
    button.setLayoutY(cy-h/2);
    imgNode.setFitWidth(w);
    imgNode.setFitHeight(h);
    button.setPrefSize(w, h);

    button.setGraphic(imgNode);
    imgNode.setPreserveRatio(true);
    imgNode.autosize();

    button.setFocusTraversable(false);
    button.setOnAction(actionValue);

    return button;
  }

  /**
   * Makes the group of buttons
   * @return the group made.
   * @throws FileNotFoundException when the image files that the buttons use are not found.
   */
  private @NotNull Group makeButtonsGroup() throws FileNotFoundException {
    Group bottomGroup = new Group();

    // img nodes
    ImageView img_CLeft = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "C-Left.png")));
    ImageView img_CRight = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "C-Right.png")));
    ImageView img_A = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "A.png")));
    ImageView img_B = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "B.png")));
    // buttons
    double size = Math.min(height, width)/16;
    Hyperlink button_A = setupButton("A", 15*width/16, 12*height/16, size, size, img_A, FinalReality::buttonAAction);
    Hyperlink button_B = setupButton("B", 14*width/16, 27*height/32, size, size, img_B, FinalReality::buttonBAction);
    Hyperlink button_CRight = setupButton("Right", 13*width/16, 7*height/8, size, size, img_CRight, FinalReality::buttonC_RightAction);
    Hyperlink button_CLeft = setupButton("Left", 12*width/16, 7*height/8, size, size, img_CLeft, FinalReality::buttonC_LeftAction);
    // add to root
    bottomGroup.getChildren().addAll(button_A, button_B, button_CLeft, button_CRight);

    return bottomGroup;
  }

  /**
   * Makes the group that shows the user's options on screen.
   * @return the group made.
   */
  private @NotNull Group makeUserOptionsGroup() {
    Group userOptionsGroup = new Group();

    GridPane userOptionsGrid = new GridPane();
    userOptionsGrid.setPadding(new Insets(7*height/8, width/10, 4*height/4, width/10));
    userOptionsGrid.setHgap(width/20);
    userOptionsGrid.setVgap(height/20);

    phaseInstructionsLabel.setLayoutX(width/20);
    phaseInstructionsLabel.setLayoutY(7*height/8);
    userOptionsGroup.getChildren().add(phaseInstructionsLabel);

    for (int i = 0; i < userOptionLabels.length; i++) {
      userOptionLabels[i] = new Label("User option "+i);
      GridPane.setConstraints(userOptionLabels[i], i, 1);
    }
    userOptionsGrid.getChildren().addAll(userOptionLabels);

    userOptionsGroup.getChildren().add(userOptionsGrid);
    return userOptionsGroup;
  }

  /**
   * Makes the group that shows the user's player characters information on the screen.
   * @return the group made.
   */
  private @NotNull Group makePlayerCharacterInformationDisplayGroup() {
    Group mainVisualDisplayGroup = new Group();

    GridPane mainInformationGrid = new GridPane();
    mainInformationGrid.setPadding(new Insets(3*height/4, width/20, 7*height/8, width/20));
    mainInformationGrid.setHgap(width/20);
    mainInformationGrid.setVgap(height/20);

    for (int i = 0; i < controller.getMaxPlayerCharacterNum(); i++) {
      playerCharacterLabels[i] = new Label("character"+i);
      GridPane.setConstraints(playerCharacterLabels[i], i, 0);
    }

    mainInformationGrid.getChildren().addAll(playerCharacterLabels);
    mainInformationGrid.setAlignment(Pos.CENTER);

    mainVisualDisplayGroup.getChildren().add(mainInformationGrid);

    return mainVisualDisplayGroup;
  }

  /**
   * Makes the background layer, the group which shows the background.
   * @return the group made.
   * @throws FileNotFoundException if the background image to display is not found.
   */
  private @NotNull Group makeBackgroundLayer() throws FileNotFoundException {
    ImageView imageView = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "Background.bmp")));
    imageView.setFitWidth(width);
    imageView.setFitHeight(3*height/4);
    Group background = new Group();
    background.getChildren().add(imageView);

    return background;
  }

  /**
   * Makes the foreground layer, which shows the groups that are in the most front part of the screen.
   * @return the group made.
   * @throws FileNotFoundException if the image files needed are not found.
   */
  private @NotNull Group makeForegroundLayer() throws FileNotFoundException {
    Group foreground = new Group();
    foreground.getChildren().add(makeButtonsGroup());
    return foreground;
  }

  /**
   * Makes the sprite layers with the sprites in this group.
   * @return the group made.
   * @throws FileNotFoundException if the image files needed to make the sprites are not found.
   */
  private @NotNull Group makeSpritesLayer() throws FileNotFoundException {
    Group sprites = new Group();

    CharacterSpriteGroup[] characterSpriteGroups = new CharacterSpriteGroup[controller.getTotalNumberOfCharacters()];

    for (int i = 0; i < characterSpriteGroups.length; i++){
        characterSpriteGroups[i] = new CharacterSpriteGroup();
        sprites.getChildren().add(characterSpriteGroups[i].getGroup());
    }
    controller.linkCharacterSprites(characterSpriteGroups, 7*height/16, height/16);

    return sprites;
  }

  /**
   * Makes the cursor layer with the sprite of the cursor.
   * @return the group.
   * @throws FileNotFoundException if the image file of the cursor is not found.
   */
  private @NotNull Group makeCursorLayer() throws FileNotFoundException {
    cursorSprite = new CursorSprite(RESOURCE_PATH+"cursor.png");
    controller.linkCursorSprite(cursorSprite);
    return cursorSprite.getGroup();
  }

  /**
   * Sets which information will be updated constantly in the application.
   */
  private void setupTimer() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        controller.updateCharacterSpritesInformation();
        controller.updateCursorSpritePosition();
        cursorSprite.updateDrawing(((double) System.currentTimeMillis())/1000);

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
      }
    };
    timer.start();
  }

  /**
   * Plays a sound.
   * @param event which triggers this action.
   */
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

  /**
   * Action that the button A makes.
   * @param event triggered by the button.
   */
  private static void buttonAAction(ActionEvent event) {
    playSound(event);
    controller.next();
  }

  /**
   * Action that the button B makes.
   * @param event triggered by the button.
   */
  private static void buttonBAction(ActionEvent event) {
    playSound(event);
    controller.prev();
  }

  /**
   * Action that the button C (left) makes.
   * @param event triggered by the button.
   */
  private static void buttonC_LeftAction(ActionEvent event) {
    playSound(event);
    controller.moveCursorLeft();
  }

  /**
   * Action that the button C (right) makes.
   * @param event triggered by the button.
   */
  private static void buttonC_RightAction(ActionEvent event) {
    playSound(event);
    controller.moveCursorRight();
  }

}
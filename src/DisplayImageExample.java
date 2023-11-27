import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class DisplayImageExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load the sprite sheet image
        Image spriteSheet = new Image("/img/heros.png");

        // Create an ImageView from the sprite sheet
        ImageView sprite = new ImageView(spriteSheet);

        // Set the viewport to crop the first image (coordinates (20,0), size(65,100))
        sprite.setViewport(new Rectangle2D(20, 0, 70, 100));

        // Set the position on the screen (centered at (200, 300))
        sprite.setX(200);
        sprite.setY(300);

        // Create a Group to hold the ImageView
        Group root = new Group();
        root.getChildren().add(sprite);

        // Create the Scene and set it on the Stage
        Scene scene = new Scene(root, 400, 600); // Adjust the size as needed
        primaryStage.setScene(scene);

        // Set the stage title
        primaryStage.setTitle("Display Image Example");

        // Show the stage
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
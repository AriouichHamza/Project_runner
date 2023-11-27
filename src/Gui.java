import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Gui extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hamza's Game");

        Group root = new Group();
        Pane pane = new Pane(root);
        Camera camera = new Camera(0,0);


        // Use GameScene instead of Scene
        GameScene gameScene = new GameScene(pane, 600, 400, camera);
        primaryStage.setScene(gameScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

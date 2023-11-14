import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private  Camera camera;
    private GameScene gameScene;
    private Hero hero;


   @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");

        Group root = new Group();
        Pane pane = new Pane(root);
        this.camera = new Camera(0,0);
        Screen screen = Screen.getPrimary();
       // Get the bounds of the screen
        Rectangle2D bounds = screen.getVisualBounds();
        Timeline timeline = new Timeline (new KeyFrame(Duration.millis(8), event -> update()));
       timeline.setCycleCount(Timeline.INDEFINITE); // Exécutez en boucle
        timeline.play();


       double width = 600;
       double height = 400;
       Hero hero = new Hero(width/3, height * 0.65,0);
        this.hero = hero;
        this.gameScene = new GameScene(pane, width, height, camera);
        gameScene.render();
        pane.getChildren().add(hero.getSpriteSheet());
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(true);

       Timeline timeline2 = new Timeline (new KeyFrame(Duration.millis(200), event -> updateHero()));
       timeline2.setCycleCount(Timeline.INDEFINITE); // Exécutez en boucle
       timeline2.play();
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                gameScene.setWidth(bounds.getWidth());
                gameScene.setHeight(bounds.getHeight());
                update();

            }
            else {
                gameScene.setWidth(width);
                gameScene.setHeight(height);
                update();
            }
       });
        primaryStage.show();


    }
    private void update() {
        // Mettez à jour la position de la caméra
        camera.setX(camera.getX() + 1);
        // Rendez la scène avec la nouvelle position de la caméra
        gameScene.render();
    }

    /*AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            hero.updateHero();

        }


    };
   timer.start();*/
    private void updateHero() {
        // Mettez à jour la position de la caméra
        hero.setIndex((hero.getIndex() + 1)%hero.getMaxIndex());
        // Rendez la scène avec la nouvelle position de la caméra
        hero.getSpriteSheet().setViewport(hero.createViewport(hero.getIndex()));
    }
    



    public static void main(String[] args) {
        launch(args);
    }
}
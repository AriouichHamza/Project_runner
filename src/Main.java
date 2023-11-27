import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application {

    private  Camera camera;
    private GameScene gameScene;

    private Hero hero;
    private Zombie zombie;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hamza's Game");

        Group root = new Group();
        Pane pane = new Pane(root);
        this.camera = new Camera(0,0);
        Screen screen = Screen.getPrimary();
        // Get the bounds of the screen
        Rectangle2D bounds = screen.getVisualBounds();
        Timeline timeline = new Timeline (new KeyFrame(Duration.millis(3), event -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Exécutez en boucle
        timeline.play();

        double width = 600;
        double height = 400;
        Hero hero = new Hero(width*0.1, height * 0.65, height,  width, 0,6);
        this.hero = hero;
        this.gameScene = new GameScene(pane, width, height, camera);
        gameScene.render();
        pane.getChildren().add(hero.getSpriteSheet());
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(true);
        Timeline timeline2 = new Timeline (new KeyFrame(Duration.millis(200), event -> updateHero()));
        timeline2.setCycleCount(Timeline.INDEFINITE); // Exécutez en boucle
        timeline2.play();


       
       Zombie zombie = new Zombie(width*0.8, height * 0.6, height,  width, 0,4);
       this.zombie = zombie;
       gameScene.render();
       pane.getChildren().add(zombie.getSpriteSheet());
       primaryStage.setScene(gameScene);
       Timeline timeline3 = new Timeline (new KeyFrame(Duration.millis(100), event -> updateZombie()));
       timeline3.setCycleCount(Timeline.INDEFINITE); // Exécutez en boucle
       timeline3.play();

       primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               hero.setAttitude(1);
               updateHero();
               hero.jump();
           }
       });

        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                gameScene.setWidth(bounds.getWidth());
                gameScene.setHeight(bounds.getHeight());
                hero.resizeSpriteSheet(bounds.getWidth(), bounds.getHeight());
                zombie.resizeSpriteSheet(bounds.getWidth(), bounds.getHeight());
                update();
            }
            else {
                gameScene.setWidth(width);
                gameScene.setHeight(height);
                hero.resizeSpriteSheet(width, height);
                zombie.resizeSpriteSheet(width, height);
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



    private void updateHero() {
        // Mettez à jour la position de la caméra
        hero.setIndex((hero.getIndex() + 1) % hero.getMaxIndex());
        if(hero.getAttitude() == 0){hero.setIndexMax(6) ;}
        else if(hero.getAttitude() == 1){ hero.setIndexMax(2) ;}
        else if(hero.getAttitude() == 2){ hero.setIndexMax(6) ;}
        else if(hero.getAttitude() == 3){ hero.setIndexMax(2) ;}

        // Rendez la scène avec la nouvelle position de la caméra
        hero.getSpriteSheet().setViewport(hero.createViewport(hero.getIndex(), hero.getAttitude(), hero.getMaxIndex() ));
    }
    private void updateZombie() {
        // Mettez à jour la position du zombie en suivant la séquence 3 -> 2 -> 1 -> 0
        zombie.setIndex((zombie.getIndex() - 1 + zombie.getMaxIndex()) % zombie.getMaxIndex());
        zombie.running();
        if (zombie.getX() < -120){
            zombie.setX(1600);
        }

        // Rendez la scène avec la nouvelle position du zombie
        zombie.getSpriteSheet().setViewport(zombie.createViewport(zombie.getIndex(), zombie.getAttitude(), zombie.getMaxIndex()));
    }








    public static void main(String[] args) {

        launch(args);
    }
}
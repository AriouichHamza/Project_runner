import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {
    private final StaticThing backgroundLeft;
    private final StaticThing backgroundRight;
    private final Camera camera;
    private int numberOfLives;
    private List<ImageView> hearts;
    private final Pane panel;

    public GameScene(Pane panel, double width, double height, Camera camera) {
        super(panel, width, height);
        this.camera = camera;
        this.backgroundLeft = new StaticThing(width, height,"/img/desert.png");
        this.backgroundRight = new StaticThing(width, height,"/img/desert.png");
        this.numberOfLives = 3;
        this.panel = panel;
        this.hearts = new ArrayList<>();

        this.panel.getChildren().addAll(
                                backgroundLeft.getImageView(),
                                backgroundRight.getImageView());

        double height_offset = height/20;
        double width_offset = width/20;
        for (int i = 0; i < this.numberOfLives; i++)
        {
            StaticThing heart = new StaticThing(width * 0.05, height * 0.05, "/img/heart.png");
            ImageView heart_img = heart.getImageView();
            heart_img.setX(width_offset + width * 0.05 * i);
            heart_img.setY(height_offset);
            this.panel.getChildren().add(heart_img);
            this.hearts.add(heart_img);
        }

    }

    public void removeHeart(){
        int lastIndex = hearts.size() - 1;
        ImageView heart_img = hearts.remove(lastIndex);
        this.panel.getChildren().remove(heart_img);
        this.numberOfLives --;
    }
    public void render(){
        double cameraX = camera.getX();
        double width = backgroundLeft.getImageView().getFitWidth();
        double leftBackgroundX =  - cameraX % width;
        double rightBackgroundX =  width + leftBackgroundX ;
        backgroundLeft.getImageView().setX(leftBackgroundX);
        backgroundLeft.getImageView().setY(0);

        backgroundRight.getImageView().setX(rightBackgroundX);
        backgroundRight.getImageView().setY(0);
    }
    public void setWidth(double width){
        backgroundLeft.getImageView().setFitWidth(width);
        backgroundRight.getImageView().setFitWidth(width);
    }
    public void setHeight(double height){
        backgroundLeft.getImageView().setFitHeight(height);
        backgroundRight.getImageView().setFitHeight(height);
    }









}


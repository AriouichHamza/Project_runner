import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class AnimatedThing {
    private ImageView spriteSheet;
    protected int attitude;

    // Add animation-related parameters
    private int index;
    private int durationBetweenFrames;
    private int maxIndex;
    protected final int windowSizeX;
    protected final int windowSizeY;
    protected final int offsetBetweenFrames;
    private double x;
    private double y;

    private double width;
    private double height;
    private double ratio_w;
    private double ratio_h;

    // Constructor
    public AnimatedThing(double x, double y, double height, double width, int attitude, String fileName, int index, int durationBetweenFrames,
                         int maxIndex, int windowSizeX, int windowSizeY, int offsetBetweenFrames) {
        this.attitude = attitude;
        this.index = index;
        this.durationBetweenFrames = durationBetweenFrames;
        this.maxIndex = maxIndex;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.offsetBetweenFrames = offsetBetweenFrames;

        // Initialize spriteSheet
        Image image = new Image(fileName);
        this.spriteSheet = new ImageView(image);
        this.spriteSheet.setViewport(createViewport(index, attitude, maxIndex));
        this.spriteSheet.setX(x);
        this.spriteSheet.setY(y);
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.ratio_h = 1;
        this.ratio_w = 1;
    }

    abstract public Rectangle2D createViewport(int index, int altitude, int maxIndex);

    public ImageView getSpriteSheet() {
        return spriteSheet;
    }


    public int getIndex() {
        return index;
    }



    public void resizeSpriteSheet(double width, double height) {
        ratio_w = width / this.width;
        ratio_h = height / this.height;
        spriteSheet.setFitWidth((double) windowSizeX * ratio_w);
        spriteSheet.setFitHeight((double) windowSizeY * ratio_h);
        spriteSheet.setX(x * ratio_w);
        spriteSheet.setY(y * ratio_h);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public int getMaxIndex() {
        return maxIndex;

    }
    public void setIndexMax(int maxIndex) {this.maxIndex = maxIndex;}

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void jump(){
        double acc = 0.2;
        attitude = 1;
        y = spriteSheet.getY();
        // Jump animation
        Timeline jumpTimeline = new Timeline();
        jumpTimeline.setCycleCount(5); // Number of iterations
        jumpTimeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(100),
                        event ->
                        {
                            y -= (0.5 * height * ratio_h) * acc;
                            spriteSheet.setY(y);
                        }
                )
        );
        Timeline gravityTimeline = new Timeline();
        gravityTimeline.setCycleCount(5); // Number of iterations
        gravityTimeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(200),
                        event ->
                        {
                            y += (0.5 * height * ratio_h) * acc;
                            spriteSheet.setY(y);
                        }
                )
        );
        jumpTimeline.setOnFinished(e -> {
                                            gravityTimeline.setOnFinished(event -> attitude = 0);
                                            gravityTimeline.play();
                                        }); // Trigger gravityTimeline after jumpTimeline finishes
        jumpTimeline.play();

    }
    public void running(){
        Timeline runningTimeline = new Timeline();
        runningTimeline.setCycleCount(10); // Exécutez en boucle
        runningTimeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(durationBetweenFrames),
                        event ->
                        {
                            x -= 0.0005*width* ratio_w;
                            spriteSheet.setX(x);

                        })


        );


        runningTimeline.play();
    }

}

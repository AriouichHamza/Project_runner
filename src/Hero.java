import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class Hero extends AnimatedThing {
    private boolean dying = false;
    private double y_ori = y;
    public Hero(double x, double y, double height, double width, int alltitude, int maxIndex ) {
        // Assuming default values for index, durationBetweenFrames, maxIndex, windowSize, offsetBetweenFrames
        super(x, y, height, width, alltitude, "/img/heros.png", 0, 100,maxIndex , 85, 100, 22);

    }

    public Rectangle2D createViewport(int index, int attitude, int maxIndex)
    {
        double startX;
        double startY ;
         if (attitude == 0)
        {
            startX = index * windowSizeX;
            startY = attitude ;
        }
        else if ( attitude == 1) {
            startX = index * windowSizeX;
            startY = (attitude+0.2) * (windowSizeY - 20) + 65;
        }
        else if (attitude == 2)
        {
            startX = index * windowSizeX-7;
            startY = (attitude+0.3) * (windowSizeY - 20) + 145;
        }
        else
        {
            startX = index * windowSizeX - 7;
            startY = (attitude) * (windowSizeY - 25) + 100;
        }
        return new Rectangle2D(startX, startY, windowSizeX, windowSizeY);
        }
    public void killed(Timeline timeline_hero) {
        if (dying)
            return;
        dying = true;
        timeline_hero.pause();
        double acc = 0.3;
        y = getSpriteSheet().getY();
        Timeline dyingTimeline = new Timeline();
        dyingTimeline.setCycleCount(5);
        dyingTimeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(120),
                        event ->
                        {
                            y += (0.5 * height * ratio_h) * acc;
                            getSpriteSheet().setY(y);
                            getSpriteSheet().setViewport(createViewport(1, 1, 2));
                        }
                )
        );
        dyingTimeline.setOnFinished(e -> {
            attitude = 0;
            y = y_ori;
            setIndexMax(6);
            setIndex(0);
            getSpriteSheet().setY(y_ori);
            timeline_hero.play();
            dying = false;
        });
        dyingTimeline.play();
    }
    public boolean getDying()
    {
        return dying;
    }

    public void setDying(boolean dying)
    {
        this.dying = dying;
    }
}
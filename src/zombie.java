import javafx.geometry.Rectangle2D;

public class Zombie extends AnimatedThing {
    public Zombie(double x, double y, double height, double width, int alltitude, int maxIndex ) {
        // Assuming default values for index, durationBetweenFrames, maxIndex, windowSize, offsetBetweenFrames
        super(x, y, height, width, alltitude, "/img/zombie.png", 3, 100,maxIndex , 128, 128, 22);

    }




    public Rectangle2D createViewport(int index, int attitude, int maxIndex)
    {
        double startX;
        double startY ;
        startX = index * windowSizeX ;
        startY = (attitude) * (windowSizeY )+ 7;
        return new Rectangle2D(startX, startY, windowSizeX, windowSizeY);
    }







}
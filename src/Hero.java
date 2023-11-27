import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing {
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
    }
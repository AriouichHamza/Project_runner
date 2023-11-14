import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    // Constructor
    public AnimatedThing(double x, double y, int attitude, String fileName, int index, int durationBetweenFrames,
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
        this.spriteSheet.setViewport(createViewport(index));
        this.spriteSheet.setX(x);
        this.spriteSheet.setY(y);
    }

    abstract public Rectangle2D createViewport(int index);
    public ImageView getSpriteSheet() {
        return spriteSheet;
    }


    public int getIndex() {
        return index;
    }

    public int getMaxIndex() {
        return maxIndex;

    }

    public void setIndex(int index) {
        this.index = index;
    }
}

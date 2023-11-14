import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class StaticThing {
    private double sizeX;
    private double sizeY;
    private ImageView imageView;

    public StaticThing(double sizeX, double sizeY, String fileName) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        // Load the image from the file
        Image image = new Image(fileName);

        // Create the ImageView with the loaded image
        this.imageView = new ImageView(image);

        // Set the size of the ImageView
        this.imageView.setFitWidth(sizeX);
        this.imageView.setFitHeight(sizeY);
    }
    public StaticThing(StaticThing thing) {
        this.sizeX = thing.sizeX;
        this.sizeY = thing.sizeY;
        this.imageView = new ImageView(thing.imageView.getImage());
        this.imageView.setFitWidth(sizeX);
        this.imageView.setFitHeight(sizeY);
    }

    public ImageView getImageView() {
        return imageView;
    }


}

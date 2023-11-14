public class Camera {
    // Coordinates
    private int x;
    private int y;

    // Constructor
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    // Overloaded toString method
    @Override
    public String toString() {
        return x + ", " + y;
    }



}

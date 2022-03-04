package Content;


public class Coordinates {

    private final Integer x; // field not null and <= 938

    private final Integer y; // field not null

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX(){
        return x;
    }

    public Integer getY(){
        return y;
    }

    @Override
    public String toString(){
        return String.format("Coordinate X and Y = (%d,%d) ", x, y);
    }
}

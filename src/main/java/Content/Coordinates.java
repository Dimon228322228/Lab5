package Content;

import Annotation.NotNull;
import Annotation.UserAccess;
import Annotation.LowerThen;

public class Coordinates {

    @LowerThen(Meaning = 938)
    @NotNull
    @UserAccess(describe = "Input coordinate X")
    private final Integer x; // field not null and <= 938

    @NotNull
    @UserAccess(describe = "Input coordinate Y")
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

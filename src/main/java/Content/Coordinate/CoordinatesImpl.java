package Content.Coordinate;

import Content.Caster.CasterCoordinatesFromString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * a class implements interface Coordinates, added setters from string format compared to the interface
 */
public class CoordinatesImpl implements Coordinates, Serializable {

    private Integer x; // field not null and <= 938

    private Integer y; // field not null

    /**
     * creates fields coordinates and checks their correctness
     */
    @XmlTransient
    private final CasterCoordinatesFromString casterCoordinatesFromString = new CasterCoordinatesFromString();

    public Integer getX(){
        return x;
    }

    public Integer getY(){
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * set x coordinate from string, used in ConsoleReader
     */
    public void setXStr(String inputStr){
        setX(casterCoordinatesFromString.castX(inputStr));
    }

    /**
     * set y coordinate from string, used in ConsoleReader
     */
    public void setYStr(String inputStr){
        setY(casterCoordinatesFromString.castY(inputStr));
    }

    @Override
    public String toString(){
        return String.format("Coordinate X and Y = (%d,%d) ", x, y);
    }
}

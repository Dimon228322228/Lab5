package Content.Coordinate;


import Content.Caster.CasterCoordinatesFromString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@XmlRootElement
public abstract class RealizedCoordinates implements Coordinates, Serializable {

    private Integer x; // field not null and <= 938

    private Integer y; // field not null

    CasterCoordinatesFromString casterCoordinatesFromString = new CasterCoordinatesFromString();

    public RealizedCoordinates(){}

    @XmlElement(name = "X")
    public Integer getX(){
        return x;
    }

    @XmlElement(name = "Y")
    public Integer getY(){
        return y;
    }

    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    public void setXStr(String inputStr){
        setX(casterCoordinatesFromString.castX(inputStr));
    }

    public void setYStr(String inputStr){
        setY(casterCoordinatesFromString.castY(inputStr));
    }

    @Override
    public String toString(){
        return String.format("Coordinate X and Y = (%d,%d) ", x, y);
    }
}

package Content;

import Annotation.*;

import java.time.LocalDateTime;

public class Person {

    @NotNull
    @UserAccess(describe = "Input owner's name")
    private final String name; // not null and not empty

    @NotNull
    @UserAccess(describe = "Input owner's birthday")
    private final LocalDateTime birthday; // not null

    @GreatThen(Meaning = 1)
    @UserAccess(describe = "Input owner's height")
    private final long height; // > 0

    @GreatThen(Meaning = 1)
    @UserAccess(describe = "Input owner's weight")
    private final int weight; // > 0

    @LowerThen(Meaning = 41)
    @GreatThen(Meaning = 6)
    @NotNull
    @UserAccess(describe = "Input owner's passport ID")
    private final String passportID; // not null, len(line) >= 6 and len(line) <= 41

    public Person(String name, LocalDateTime birthday, long height, int weight, String passportID){
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    @Override
    public String toString(){
        return String.format("Person(owner) name = %s, birthday = %s," +
                                " height = %d, weight = %s, passport ID = %s", name, birthday, height, weight, passportID);
    }

    public String getPassportID(){
        return passportID;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getBirthday(){
        return birthday;
    }

    public long getHeight(){
        return height;
    }

    public int getWeight(){
        return weight;
    }

}

package padev.badhabits.Data;

public class Habit extends AbstractData {

    protected String name;

    public Habit(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Habit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package padev.badhabits.Data;

public class Habit implements IData {

    protected long id;
    protected String name;

    public Habit(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Habit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

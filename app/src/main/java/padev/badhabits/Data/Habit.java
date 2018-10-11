package padev.badhabits.Data;

public class Habit implements IData {

    protected long id;
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

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

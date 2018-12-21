package padev.badhabits.Data;

public class Habit extends AbstractData {

    protected String name;

    protected Boolean time;
    protected Boolean money;
    protected Boolean health;

    public Habit(long id, String name, Boolean time, Boolean money, Boolean health) {

        this.id = id;

        this.name = name;

        this.time = time;
        this.money = money;
        this.health = health;
    }

    public Habit(String name, Boolean time, Boolean money, Boolean health) {

        this.name = name;

        this.time = time;
        this.money = money;
        this.health = health;
    }

    public String getName() {
        return name;
    }
}

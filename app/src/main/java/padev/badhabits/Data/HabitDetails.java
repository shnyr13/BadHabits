package padev.badhabits.Data;

import android.provider.ContactsContract;

import java.util.Date;

public class HabitDetails implements IData  {

    protected long id;
    protected Date date;
    protected float count;
    protected float flat;
    protected float weight;

    public HabitDetails (int id, Date date, float count, float flat, float weight) {
        this.id = id;
        this.date = date;
        this.count = count;
        this.flat = flat;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public float getCount() {
        return count;
    }

    public float getFlat() {
        return flat;
    }

    public float getWeight() {
        return weight;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setFlat(float flat) {
        this.flat = flat;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

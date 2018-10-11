package padev.badhabits.Data;

import java.util.Date;

public class HabitDetails implements IData  {

    protected long id;
    protected long habitId;
    protected int dose;
    protected int concentration;
    protected int weight;

    public HabitDetails (long habitId, int dose, int concentration, int weight) {
        this.id = -1;
        this.habitId = habitId;
        this.dose = dose;
        this.concentration = concentration;
        this.weight = weight;
    }

    public HabitDetails (long id, long habitId, int dose, int concentration, int weight) {
        this.id = id;
        this.habitId = habitId;
        this.dose = dose;
        this.concentration = concentration;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public long getHabitId() { return habitId; }

    public float getDose() {
        return dose;
    }

    public float getConcentration() {
        return concentration;
    }

    public float getWeight() {
        return weight;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public void setConcentration(int concentration) {
        this.concentration = concentration;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

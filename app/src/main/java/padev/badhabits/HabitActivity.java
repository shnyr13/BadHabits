package padev.badhabits;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import padev.badhabits.Data.Habit;

public class HabitActivity extends Activity {

    private Habit habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        ButterKnife.bind(this);

        habit = new Habit(getIntent().getStringExtra("habit_name"), Long.parseLong(getIntent().getStringExtra("habit_id")));

        TextView habitNameTextView = (TextView) findViewById(R.id.activity_habit_habit_name);
        habitNameTextView.setText(habit.getName());
    }
}

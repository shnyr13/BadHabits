package padev.badhabits;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import padev.badhabits.Data.Habit;
import padev.badhabits.Data.HabitDetails;
import padev.badhabits.Data.HabitDetailsCRUD;

public class HabitActivity extends AppCompatActivity {

    private Habit habit;
    private HabitDetailsCRUD habitDetailsCRUD;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        ButterKnife.bind(this);

        habit = new Habit(getIntent().getStringExtra("habit_name"), Long.parseLong(getIntent().getStringExtra("habit_id")));
        habitDetailsCRUD = new HabitDetailsCRUD(this);


        TextView habitNameTextView = (TextView) findViewById(R.id.activity_habit_habit_name);
        habitNameTextView.setText(habit.getName());

        viewPager = (ViewPager) findViewById(R.id.activity_habit_view_pager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), this));

        tabLayout = (TabLayout) findViewById(R.id.activity_habit_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.activity_habit_fab)
    public void addHabit(View view) {

        View promptsView = getLayoutInflater().inflate(R.layout.activity_habit_input_alert, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(promptsView);

        final EditText doseEditText = (EditText) promptsView.findViewById(R.id.activity_habit_input_alert_dose_edit_text);
        final EditText concentrationEditText = (EditText) promptsView.findViewById(R.id.activity_habit_input_alert_concentration_edit_text);
        final EditText weightEditText = (EditText) promptsView.findViewById(R.id.activity_habit_input_alert_weight_edit_text);

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int dose = Integer.parseInt(doseEditText.getText().toString());
                int concentration = Integer.parseInt(concentrationEditText.getText().toString());
                int weight = Integer.parseInt(concentrationEditText.getText().toString());

                HabitDetails habitDetails = new HabitDetails(dose, concentration, weight);

                habitDetailsCRUD.insertData(habitDetails);
            }
        });

        alertDialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void drawGraphic() {
        int currentItem = viewPager.getCurrentItem();

        if (currentItem == 0) {

        } else if (currentItem == 1) {

        } else if (currentItem == 2) {

        }
    }
}

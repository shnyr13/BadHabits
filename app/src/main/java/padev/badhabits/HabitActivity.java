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

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import padev.badhabits.Data.Habit;
import padev.badhabits.Data.HabitDetails;
import padev.badhabits.Data.HabitDetailsCRUD;
import padev.badhabits.Data.IData;

public class HabitActivity extends AppCompatActivity {

    private Habit habit;
    private HabitDetailsCRUD habitDetailsCRUD;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DataPoint[] doseArr;
    private DataPoint[] concentrationArr;
    private DataPoint[] weightArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_habit);
        ButterKnife.bind(this);

        habit = new Habit(Long.parseLong(getIntent().getStringExtra("habit_id")), getIntent().getStringExtra("habit_name"));

        TextView habitNameTextView = findViewById(R.id.activity_habit_habit_name);
        habitNameTextView.setText(habit.getName());

        viewPager = findViewById(R.id.activity_habit_view_pager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), this));

        tabLayout = findViewById(R.id.activity_habit_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // HabitDetailsCRUD is Singleton
        habitDetailsCRUD = HabitDetailsCRUD.getInstance(this);

        ArrayList<IData> habitsDetails =  habitDetailsCRUD.selectDataByHabitId(habit.getId());

        doseArr = new DataPoint[habitsDetails.size()];
        concentrationArr = new DataPoint[habitsDetails.size()];
        weightArr = new DataPoint[habitsDetails.size()];

        int i = 1;
        for (IData data: habitsDetails) {

            doseArr[i - 1] = new DataPoint(i, ((HabitDetails) data).getDose());
            concentrationArr[i - 1] = new DataPoint(i, ((HabitDetails) data).getConcentration());
            weightArr[i - 1] = new DataPoint(i, ((HabitDetails) data).getWeight());

            i++;
        }
    }

    @OnClick(R.id.activity_habit_fab)
    public void addHabit(View view) {

        View promptsView = getLayoutInflater().inflate(R.layout.activity_habit_input_alert, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(promptsView);

        final EditText doseEditText = promptsView.findViewById(R.id.activity_habit_input_alert_dose_edit_text);
        final EditText concentrationEditText = promptsView.findViewById(R.id.activity_habit_input_alert_concentration_edit_text);
        final EditText weightEditText = promptsView.findViewById(R.id.activity_habit_input_alert_weight_edit_text);

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                long habitId = habit.getId();

                int dose = Integer.parseInt(doseEditText.getText().toString());
                int concentration = Integer.parseInt(concentrationEditText.getText().toString());
                int weight = Integer.parseInt(weightEditText.getText().toString());

                HabitDetails habitDetails = new HabitDetails(habitId, dose, concentration, weight);

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

    public DataPoint[] GetPoints() {

        int page = viewPager.getCurrentItem();

        if (page == 0) {
            return doseArr;
        }
        else if (page == 1) {
            return concentrationArr;
        }
        else if (page == 2) {
            return weightArr;
        }
        else {
            return new DataPoint[] {
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)
            };
        }
    }
}

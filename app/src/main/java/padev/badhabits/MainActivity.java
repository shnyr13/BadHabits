package padev.badhabits;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import padev.badhabits.Data.Habit;
import padev.badhabits.Data.CRUD.HabitCRUD;
import padev.badhabits.Data.AbstractData;

public class MainActivity extends Activity {

    private HabitCRUD habitCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for SQLite view
        Stetho.initializeWithDefaults(this);

        ButterKnife.bind(this);

        // HabitCRUD is Singleton
        habitCRUD = HabitCRUD.getInstance(this);

        ArrayList<AbstractData> habits =  habitCRUD.selectAllData();

        for (AbstractData data: habits) {

            Habit habit = (Habit) data;
            createHabitCard(habit);
        }
    }

    @OnClick(R.id.activity_main_fab)
    public void addHabit(View view) {

        View promptsView = getLayoutInflater().inflate(R.layout.activity_main_input_alert, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(promptsView);

        final EditText habitName = (EditText) promptsView.findViewById(R.id.habit_name_input_alert_habit_name_edit_text);

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String hNameStr = habitName.getText().toString();

                Habit habit = new Habit(hNameStr);

                habitCRUD.insertData(habit);

                createHabitCard(habit);
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

    private void createHabitCard(Habit habit) {

        LinearLayout cardViewLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main_habit_card, (LinearLayout) findViewById(R.id.activity_main_habits_list), true);

        View currentView = cardViewLayout.getChildAt(cardViewLayout.getChildCount()-1);

        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitCardClicked(v);
            }
        });

        TextView habitNameTextView = (TextView) currentView.findViewById(R.id.habit_card_habit_name);

        habitNameTextView.setText(habit.getName());
        habitNameTextView.setTag(habit.getId());
    }

    private void habitCardClicked(View view) {

        TextView habitNameTextView = (TextView) view.findViewById(R.id.habit_card_habit_name);

        Habit habit = new Habit(Integer.parseInt(habitNameTextView.getTag().toString()), habitNameTextView.getText().toString());

        Intent intent = new Intent(this, HabitActivity.class);

        intent.putExtra("habit_name", habit.getName());
        intent.putExtra("habit_id", Long.toString(habit.getId()));

        startActivity(intent);
    }
}

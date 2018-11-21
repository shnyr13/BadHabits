package padev.badhabits.application.ui.activity;

import android.annotation.SuppressLint
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import padev.badhabits.Data.AbstractData;
import padev.badhabits.Data.CRUD.HabitCRUD;
import padev.badhabits.Data.Habit;
import padev.badhabits.R;
import padev.badhabits.application.mvp.presenter.home.HomePresenter;
import padev.badhabits.application.mvp.view.IHomeView;
import padev.badhabits.core.view.BaseActivity;

class HomeActivity: BaseActivity(), IHomeView {

    @InjectPresenter
    lateinit var  mHomePresenter: HomePresenter

    private lateinit var habitCRUD: HabitCRUD

    @BindView(R.id.activity_main_fab)
    lateinit var fab: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // for SQLite view
        Stetho.initializeWithDefaults(this)

        ButterKnife.bind(this)

        habitCRUD = HabitCRUD(this)

        val habits =  habitCRUD.selectAllData();

        for (data in habits) {

            val habit = data as Habit
            createHabitCard(habit)
        }
    }


    @SuppressLint("InflateParams")
    @OnClick(R.id.activity_main_fab)
    fun addHabitClicked(view: View) {

        val promptsView = layoutInflater.inflate(R.layout.activity_main_input_alert, null)

        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setView(promptsView)

        val habitName = promptsView.findViewById<EditText>(R.id.habit_name_input_alert_habit_name_edit_text)

        alertDialogBuilder.setCancelable(false)

        alertDialogBuilder.setPositiveButton("Добавить") { _: DialogInterface, _: Int ->

            val hNameStr = habitName.text.toString()

            val habit = Habit(hNameStr)

            habitCRUD.insertData(habit)

            createHabitCard(habit)
        }

        alertDialogBuilder.setNegativeButton("Отмена") { dialog: DialogInterface, _: Int ->
            dialog.cancel()
        }

        val alertDialog = alertDialogBuilder.create()

        alertDialog.show()
    }

    private fun createHabitCard(habit: Habit) {

        val cardViewLayout = (layoutInflater.inflate(R.layout.activity_main_habit_card, findViewById<LinearLayout>(R.id.activity_main_habits_list), true)) as LinearLayout

        val currentView = cardViewLayout.getChildAt(cardViewLayout.childCount -1)

        currentView.setOnClickListener { habitCardClicked(it) }

        val habitNameTextView = currentView.findViewById<TextView>(R.id.habit_card_habit_name)

        habitNameTextView.text = habit.name
        habitNameTextView.tag = habit.id
    }

    private fun habitCardClicked(view: View) {

        val habitNameTextView = view.findViewById<TextView>(R.id.habit_card_habit_name)

        val intent = Intent(this, HabitActivity::class.java)

        intent.putExtra("habit_name", habitNameTextView.text.toString())
        intent.putExtra("habit_id", habitNameTextView.tag.toString())

        startActivity(intent)
    }
}

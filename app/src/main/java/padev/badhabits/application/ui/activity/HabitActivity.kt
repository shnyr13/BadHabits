package padev.badhabits.application.ui.activity;

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jjoe64.graphview.series.DataPoint
import padev.badhabits.Data.CRUD.HabitDetailsCRUD
import padev.badhabits.Data.Habit
import padev.badhabits.Data.HabitDetails
import padev.badhabits.R
import padev.badhabits.Utils.KeyboardsUtils
import padev.badhabits.application.mvp.presenter.habit.HabitPresenter
import padev.badhabits.application.mvp.view.IHabitView
import padev.badhabits.application.ui.fragment.SampleFragmentPagerAdapter
import padev.badhabits.core.view.BaseActivity

class HabitActivity: BaseActivity(), IHabitView {

    @InjectPresenter
    lateinit var mHabitPresenter: HabitPresenter

    private lateinit var habit: Habit

    private lateinit var habitDetailsCRUD: HabitDetailsCRUD

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private var doseArr: ArrayList<DataPoint> = ArrayList()
    private var  concentrationArr: ArrayList<DataPoint> = ArrayList()
    private var weightArr: ArrayList<DataPoint> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_habit)

        ButterKnife.bind(this)

        habitDetailsCRUD = HabitDetailsCRUD(this)

        habit = Habit(intent.getStringExtra("habit_id").toLong(), intent.getStringExtra("habit_name"))

        val habitNameTextView = findViewById<TextView>(R.id.activity_habit_habit_name)
        habitNameTextView.text = habit.name

        viewPager = findViewById(R.id.activity_habit_view_pager)
        viewPager.adapter = SampleFragmentPagerAdapter(supportFragmentManager, this)

        tabLayout = findViewById(R.id.activity_habit_tabs)
        tabLayout.setupWithViewPager(viewPager)

        val habitsDetails = habitDetailsCRUD.selectDataByHabitId(habit.id)

        var i = 1
        for (data in habitsDetails) {

            doseArr.add(DataPoint(i.toDouble(), (data as HabitDetails).dose.toDouble()))
            concentrationArr.add(DataPoint(i.toDouble(), data.concentration.toDouble()))
            weightArr.add(DataPoint(i.toDouble(), data.weight.toDouble()))

            i++
        }
    }

    @OnClick(R.id.activity_habit_fab)
    fun addHabitClicked(view: View) {

        val promptsView = layoutInflater.inflate(R.layout.activity_habit_input_alert, null)

        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setView(promptsView)

        val doseEditText = promptsView.findViewById<EditText>(R.id.activity_habit_input_alert_dose_edit_text)
        val concentrationEditText = promptsView.findViewById<EditText>(R.id.activity_habit_input_alert_concentration_edit_text)
        val weightEditText = promptsView.findViewById<EditText>(R.id.activity_habit_input_alert_weight_edit_text)

        alertDialogBuilder.setCancelable(false)

        alertDialogBuilder.setPositiveButton("Добавить") { _, _ ->

                val habitId = habit.id

                // TODO show intermediate error
                if (doseEditText.text.toString().isEmpty()
                        || concentrationEditText.text.toString().isEmpty()
                        || weightEditText.text.toString().isEmpty())
                    return@setPositiveButton

                val dose = Integer.parseInt(doseEditText.text.toString())
                val concentration = Integer.parseInt(concentrationEditText.text.toString())
                val weight = Integer.parseInt(weightEditText.text.toString())

                val habitDetails = HabitDetails(habitId, dose, concentration, weight)

                habitDetailsCRUD.insertData(habitDetails)
            }

        alertDialogBuilder.setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }

        val alertDialog = alertDialogBuilder.create()

        alertDialog.show()

        // TODO (not working)
        KeyboardsUtils.showSoftKeyboard(doseEditText, this)
    }

    fun getPoints(): Array<DataPoint> {

        val page = viewPager.currentItem

        return /*when (page) {
            0 -> doseArr.toArray() as Array<DataPoint>
            1 -> concentrationArr.toArray() as Array<DataPoint>
            2 -> weightArr.toArray() as Array<DataPoint>
            else -> */arrayOf(
                DataPoint(0.toDouble(), 1.toDouble()),
                DataPoint(1.toDouble(), 5.toDouble()),
                DataPoint(2.toDouble(), 3.toDouble()),
                DataPoint(3.toDouble(), 2.toDouble()),
                DataPoint(4.toDouble(), 6.toDouble())
            )
        //}
    }
}

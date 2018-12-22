package padev.badhabits.application.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import padev.badhabits.application.mvp.model.entity.HabitEntity
import padev.badhabits.R
import padev.badhabits.application.ui.activity.HabitActivity
import padev.badhabits.core.view.BaseFragment

class HabitCardFragment: BaseFragment() {

    lateinit var mHabitEntity: HabitEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_habit_card, container, false)

        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.fragment_habit_card_name)?.text = mHabitEntity.name

        view.findViewById<CardView>(R.id.fragment_habit_card_content_foo)?.setOnClickListener {

            val intent = Intent(this.context, HabitActivity::class.java)

            intent.putExtra("habit_name", mHabitEntity.name)
            intent.putExtra("habit_id", mHabitEntity.id.toString())

            startActivity(intent)
        }


        return view
    }
}
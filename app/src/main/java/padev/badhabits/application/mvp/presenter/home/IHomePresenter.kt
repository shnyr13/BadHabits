package padev.badhabits.application.mvp.presenter.home;

import android.content.Context
import io.reactivex.Observable
import padev.badhabits.application.mvp.model.entity.HabitEntity
import java.util.ArrayList

interface IHomePresenter {

    fun getAllHabits()

    fun habitAddStart()

    fun habitAddPositive (name: String, time: Boolean, money: Boolean, health: Boolean)

    fun habitAddNegative()



    fun getContext(): Context
}

package padev.badhabits.application.mvp.model.interactor.home

interface IHomeInteractor {

    fun getAllHabits()

    fun addHabit(name: String, time: Boolean, money: Boolean, health: Boolean)
}
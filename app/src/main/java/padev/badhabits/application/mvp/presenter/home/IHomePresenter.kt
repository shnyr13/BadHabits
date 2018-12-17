package padev.badhabits.application.mvp.presenter.home;

interface IHomePresenter {

    fun habitAddStart()

    fun habitAddPositive(name: String)

    fun habitAddNegative()
}

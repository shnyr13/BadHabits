package padev.badhabits.application.mvp.presenter.home;

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import padev.badhabits.application.mvp.model.entity.HabitEntity
import padev.badhabits.application.mvp.model.interactor.home.HomeInteractor
import padev.badhabits.application.mvp.view.IHomeView
import java.util.ArrayList

class HomePresenter(var viewState: IHomeView) : IHomePresenter {

    val interactor = HomeInteractor(this)

    val TAG = HomePresenter::class.java.simpleName

    override fun getAllHabits() {

        viewState.showLoading()

        val dispose = interactor.getAllHabits()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( {
                    viewState.showHabit(it)
                    viewState.hideLoading()
                },
                {

                },
                {

                } )

        if (dispose.isDisposed) viewState.hideLoading()
    }

    override fun habitAddStart() {

        viewState.showAddHabitDialog()
    }

    override fun habitAddPositive(name: String, time: Boolean, money: Boolean, health: Boolean) {

        viewState.showLoading()

        val dispose = interactor.addHabit(name, time, money, health)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.showHabit(it)
                    viewState.hideLoading()
                },
                        {

                        },
                        {

                        })
        if (dispose.isDisposed) viewState.hideLoading()
    }

    override fun habitAddNegative() {
        TODO("not implemented") //To change body of created functions use File | SettingsEntity | File Templates.
    }

    override fun getContext(): Context {
        return viewState.getContext()
    }

}

package padev.badhabits.application.mvp.presenter.home;

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import padev.badhabits.application.mvp.view.IHomeView

@InjectViewState
class HomePresenter: MvpPresenter<IHomeView>(), IHomePresenter {

    val TAG = HomePresenter::class.java.simpleName

    override fun habitAddStart() {
        viewState.showAddHabitDialog()
    }

    override fun habitAddPositive(name: String) {
        TODO("not implemented") //To change body of created functions use File | SettingsEntity | File Templates.
    }

    override fun habitAddNegative() {
        TODO("not implemented") //To change body of created functions use File | SettingsEntity | File Templates.
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}

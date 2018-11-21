package padev.badhabits.application.mvp.presenter.home;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import padev.badhabits.application.mvp.view.IHomeView;

@InjectViewState
class HomePresenter: MvpPresenter<IHomeView>(), IHomePresenter {

}

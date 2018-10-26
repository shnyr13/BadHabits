package padev.badhabits.application.mvp.presenter.habit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import padev.badhabits.application.mvp.view.IHabitView;

@InjectViewState
public class HabitPresenter extends MvpPresenter<IHabitView> implements IHabitPresenter {
}

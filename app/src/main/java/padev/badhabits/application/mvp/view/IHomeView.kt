package padev.badhabits.application.mvp.view;

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface IHomeView: MvpView {

    fun showAddHabitDialog()

    fun showLoading()

    fun hideLoading()
}

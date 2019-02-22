package padev.badhabits.application.mvp.model.interactor.habit

import io.reactivex.Observable
import padev.badhabits.application.mvp.model.entity.HabitDetailsEntity
import padev.badhabits.application.mvp.model.entity.HabitEntity
import padev.badhabits.application.mvp.presenter.habit.IHabitPresenter
import padev.badhabits.core.interactor.BaseInteractor

class HabitInteractor(val presenter: IHabitPresenter): BaseInteractor(), IHabitInteractor {


    override fun getHabitDetails(id: Long): Observable<HabitDetailsEntity> {

        return Observable.create {

            it.onNext(HabitDetailsEntity(-1, 1,1,1,1))
        }
    }
}
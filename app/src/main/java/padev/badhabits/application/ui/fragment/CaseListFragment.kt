package padev.badhabits.application.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import padev.badhabits.R
import padev.badhabits.application.mvp.model.entity.HabitDetailsEntity
import padev.badhabits.application.mvp.view.ICaseListView
import padev.badhabits.core.view.BaseFragment

class CaseListFragment: BaseFragment(), ICaseListView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_case_list, container, false)

        super.onViewCreated(view, savedInstanceState)

        return view
    }


    override fun showCase(habitCaseEntity: HabitDetailsEntity) {

        val caseFragment = CaseCardFragment()

        caseFragment.mHabitDetailsEntity = habitCaseEntity

        this.activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.fragment_case_list_content, caseFragment)
                ?.commit()
    }


}
package padev.badhabits.application.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import padev.badhabits.R
import padev.badhabits.application.mvp.view.IResultView
import padev.badhabits.core.view.BaseFragment

class ResultFragment: BaseFragment(), IResultView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_result, container, false)

        super.onViewCreated(view, savedInstanceState)

        return view
    }
}
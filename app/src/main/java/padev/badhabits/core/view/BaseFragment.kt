package padev.badhabits.core.view

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatFragment

open class BaseFragment: MvpAppCompatFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view)
    }
}
package padev.badhabits.core.view

import android.os.Bundle
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity

abstract class BaseActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        ButterKnife.bind(this)

    }

}

package padev.badhabits.application.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.arellomobile.mvp.presenter.InjectPresenter
import padev.badhabits.R
import padev.badhabits.application.mvp.presenter.about.AboutPresenter
import padev.badhabits.application.mvp.view.IAboutView
import padev.badhabits.core.view.BaseActivity

class AboutActivity: BaseActivity(), IAboutView {

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)

        val toolbar = findViewById<Toolbar>(R.id.activity_about_appbar_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = getString(R.string.about)
    }
}
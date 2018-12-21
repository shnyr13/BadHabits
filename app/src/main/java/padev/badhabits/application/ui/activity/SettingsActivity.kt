package padev.badhabits.application.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.arellomobile.mvp.presenter.InjectPresenter
import padev.badhabits.R
import padev.badhabits.application.mvp.presenter.settings.SettingsPresenter
import padev.badhabits.application.mvp.view.ISettingsView
import padev.badhabits.core.view.BaseActivity

class SettingsActivity: BaseActivity(), ISettingsView {

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<Toolbar>(R.id.activity_settings_appbar_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = getString(R.string.settings)
    }
}
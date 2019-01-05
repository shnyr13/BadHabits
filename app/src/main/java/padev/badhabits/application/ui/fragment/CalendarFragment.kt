package padev.badhabits.application.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import padev.badhabits.R
import padev.badhabits.application.mvp.view.ICalendarView
import padev.badhabits.core.view.BaseFragment

class CalendarFragment: BaseFragment(), ICalendarView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        super.onViewCreated(view, savedInstanceState)

        val calendarView = view.findViewById<CalendarView>(R.id.fragment_calendar_view)

        return view
    }
}
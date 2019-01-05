package padev.badhabits.application.ui.fragment.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import padev.badhabits.R
import padev.badhabits.application.ui.fragment.CalendarFragment
import padev.badhabits.application.ui.fragment.CaseListFragment
import padev.badhabits.application.ui.fragment.ResultFragment

class HabitFragmentPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    private val PAGE_COUNT = 3

    private val tabTitles = arrayOf (
            this.context.getString(R.string.habit_tab_calendar),
            this.context.getString(R.string.habit_tab_cases),
            this.context.getString(R.string.habit_tab_result)
    )

    override fun getCount(): Int {

        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {

            0 -> CalendarFragment()

            1 -> CaseListFragment()

            2 -> ResultFragment()

            else -> CalendarFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return tabTitles[position]
    }
}
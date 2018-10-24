package pt.inm.movies.ui.screens

import android.view.MenuItem
import androidx.fragment.app.transaction
import kotlinx.android.synthetic.main.screen_main.*
import org.jetbrains.anko.toast
import pt.inm.movies.R
import pt.inm.movies.ui.fragments.BaseFragment
import pt.inm.movies.ui.fragments.NowPlayingMoviesFragment

class MainScreen : Screen() {

    override fun getLayoutResourceId() = R.layout.screen_main

    override fun doInitializations() {
        initBottomAppBar()
        addListeners()
    }

    private fun addListeners() {
        screenMainFabButton.setOnClickListener{ onFabButtonClicked() }
    }

    private fun onFabButtonClicked() {
        addFragment(NowPlayingMoviesFragment.newInstance())
    }

    private fun initBottomAppBar() {
        screenMainBottomAppBar.replaceMenu(R.menu.main_screen_menu)
        screenMainBottomAppBar.setOnMenuItemClickListener { onMenuItemClicked(it) }
    }

    private fun onMenuItemClicked(menuItem: MenuItem): Boolean {

        when(menuItem.itemId){
            R.id.main_screen_menu_options -> toast("Menu Options")
            R.id.main_screen_menu_search -> toast("Search")
        }

        return true
    }

    private fun addFragment(fragment: BaseFragment<*>){

        supportFragmentManager.transaction(allowStateLoss = true, now = true){
            replace(R.id.screenMainFragmentContainer, fragment)
        }

    }

}
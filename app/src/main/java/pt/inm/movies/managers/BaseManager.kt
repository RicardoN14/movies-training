package pt.inm.movies.managers

import pt.inm.movies.ui.screens.Screen

abstract class BaseManager<out S : Screen> (val screen : S){

    protected val logTag: String = javaClass.simpleName

}
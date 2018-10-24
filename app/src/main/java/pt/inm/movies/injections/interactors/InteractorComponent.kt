package pt.inm.movies.injections.interactors

import dagger.Component
import pt.inm.movies.presenters.implementations.movies.MoviesPresenter
import pt.inm.movies.presenters.implementations.series.SeriesPresenter

@Component(modules = [(InteractorModule::class)])
interface InteractorComponent {
    // inject Your Presenter
    fun inject(moviesPresenter: MoviesPresenter)
    fun inject(seriesPresenter: SeriesPresenter)
}
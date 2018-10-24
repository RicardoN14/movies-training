package pt.inm.movies.injections.interactors

import dagger.Module
import dagger.Provides
import pt.inm.movies.helpers.isMockFlavor
import pt.inm.movies.interactors.mocks.movies.MoviesMockInteractor
import pt.inm.movies.interactors.mocks.series.SeriesMockInteractor
import pt.inm.movies.interactors.server.movies.MoviesServerInteractor
import pt.inm.movies.interactors.server.series.SeriesServerInteractor

@Module
class InteractorModule {
    //Create your Providers

    @Provides
    fun providesMoviesInteractor() = if (isMockFlavor) MoviesMockInteractor() else MoviesServerInteractor()

    @Provides
    fun providesSeriesInteractor() = if (isMockFlavor) SeriesMockInteractor() else SeriesServerInteractor()

}
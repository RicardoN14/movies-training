package pt.inm.movies.ui.screens

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.screen_now_playing_movies.*
import pt.inm.movies.R
import pt.inm.movies.adapters.MoviesAdapter
import pt.inm.movies.alias.PresentersArrayList
import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.extensions.setLinearAdapter
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.http.entities.responses.MovieResponseData
import pt.inm.movies.http.entities.responses.MoviesResponseData
import pt.inm.movies.presenters.implementations.movies.MoviesPresenter
import pt.inm.movies.presenters.interfaces.movies.IMoviesPresenter
import pt.inm.movies.presenters.listeners.movies.IMoviesPresenterListener

class NowPlayingMoviesScreen : ExecuteRequestScreen(), IMoviesPresenterListener {

    private lateinit var moviesPresenter: IMoviesPresenter

    override fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String) {
        moviesPresenter = MoviesPresenter(this, requestContextGroup)
        presenters += moviesPresenter
    }

    override fun getExecuteRequestLayoutResourceId() = R.layout.screen_now_playing_movies

    override fun doExecuteRequestInitializations() {
        moviesPresenter.requestNowPlayingMovies(NowPlayingMoviesQueryStringArgs("1"),
                RequestConfig(showErrorStateView = true))
    }

    private fun initRecyclerView(movies: List<MovieResponseData>) {
        val itemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        val adapter = MoviesAdapter(this, movies)
        screenNowPlayingMoviesRecyclerView.setLinearAdapter(adapter, itemDecoration = itemDecoration)
    }

    override fun onRequestNowPlayingMoviesSuccess(moviesResponseData: MoviesResponseData) {
        initRecyclerView(moviesResponseData.results)
    }

}
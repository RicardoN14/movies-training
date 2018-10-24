package pt.inm.movies.ui.fragments

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_now_playing_movies.*
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
import pt.inm.movies.ui.screens.MainScreen

class NowPlayingMoviesFragment : ExecuteRequestFragment<MainScreen>(), IMoviesPresenterListener {

    private lateinit var moviesPresenter: IMoviesPresenter

    companion object {
        fun newInstance() = NowPlayingMoviesFragment()
    }

    override fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String) {
        moviesPresenter = MoviesPresenter(this, requestContextGroup)
        presenters += moviesPresenter
    }

    override fun getLayoutResourceId() = R.layout.fragment_now_playing_movies

    override fun doInitializations(view: View?) {
        moviesPresenter.requestNowPlayingMovies(NowPlayingMoviesQueryStringArgs("1"),
                RequestConfig(showErrorStateView = true))
    }

    override fun onRequestNowPlayingMoviesSuccess(moviesResponseData: MoviesResponseData) {
        initRecyclerView(moviesResponseData.results)
    }

    private fun initRecyclerView(movies: List<MovieResponseData>) {
        val itemDecoration = DividerItemDecoration(screen, RecyclerView.VERTICAL)
        val adapter = MoviesAdapter(screen, movies)
        fragmentNowPlayingMoviesRecyclerView.setLinearAdapter(adapter, itemDecoration = itemDecoration)
    }
}
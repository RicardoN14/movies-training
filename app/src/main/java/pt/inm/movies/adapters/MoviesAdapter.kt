package pt.inm.movies.adapters

import android.view.View
import kotlinx.android.synthetic.main.movie_item.view.*
import pt.inm.movies.R
import pt.inm.movies.http.entities.responses.MovieResponseData
import pt.inm.movies.ui.screens.Screen

class MoviesAdapter(screen: Screen,
                    items: List<MovieResponseData>)
    : RecyclerViewBaseAdapter<MovieResponseData, MoviesAdapter.MoviesAdapterViewHolder>(screen, items) {

    override fun getItemLayoutRscId() = R.layout.movie_item

    override fun createViewHolder(screen: Screen, view: View) = MoviesAdapterViewHolder(screen, view)

    class MoviesAdapterViewHolder(screen: Screen, view: View)
        : RecyclerViewBaseAdapter.RecyclerViewBaseViewHolder<MovieResponseData>(screen, view) {

        override fun bind(item: MovieResponseData, position: Int) {
            containerView.movieItemTitle.text = item.title
        }
    }

}
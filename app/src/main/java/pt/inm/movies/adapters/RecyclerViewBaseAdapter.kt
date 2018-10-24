package pt.inm.movies.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import pt.inm.movies.ui.screens.Screen

abstract class RecyclerViewBaseAdapter<in I, VH : RecyclerViewBaseAdapter.RecyclerViewBaseViewHolder<I>>(
        protected val screen: Screen,
        private val items: List<I>)
    : RecyclerView.Adapter<VH>() {

    abstract fun getItemLayoutRscId(): Int
    abstract fun createViewHolder(screen: Screen, view: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(screen).inflate(getItemLayoutRscId(), parent, false)
        return createViewHolder(screen, v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    abstract class RecyclerViewBaseViewHolder<in I>(val screen: Screen, override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        abstract fun bind(item: I, position: Int)
    }
}
package pt.inm.movies.interactors.server.series

import pt.inm.movies.interactors.interfaces.series.ISeriesInteractor
import pt.inm.movies.interactors.listeners.series.ISeriesInteractorListener
import pt.inm.movies.interactors.server.base.ServerInteractor

class SeriesServerInteractor : ServerInteractor<ISeriesInteractorListener>(), ISeriesInteractor {

    override fun createWebRequests(requestContextGroup: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
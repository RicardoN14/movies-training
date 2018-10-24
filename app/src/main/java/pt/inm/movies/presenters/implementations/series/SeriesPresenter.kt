package pt.inm.movies.presenters.implementations.series

import pt.inm.movies.injections.interactors.InteractorComponent
import pt.inm.movies.interactors.interfaces.series.ISeriesInteractor
import pt.inm.movies.interactors.listeners.series.ISeriesInteractorListener
import pt.inm.movies.presenters.implementations.base.BasePresenter
import pt.inm.movies.presenters.interfaces.series.ISeriesPresenter
import pt.inm.movies.presenters.listeners.series.ISeriesPresenterListener

class SeriesPresenter(presenterListener: ISeriesPresenterListener,
                      requestContextGroup: String)
    : BasePresenter<ISeriesPresenterListener, ISeriesInteractorListener, ISeriesInteractor>(
        presenterListener,
        requestContextGroup), ISeriesPresenter, ISeriesInteractorListener {

    // TODO Implement your Methods

    override fun injectDependencies(component: InteractorComponent) {
        component.inject(this)
    }

    override fun getInteractorListener() = this

}
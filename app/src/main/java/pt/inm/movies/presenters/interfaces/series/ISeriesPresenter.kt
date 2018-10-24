package pt.inm.movies.presenters.interfaces.series

import pt.inm.movies.interactors.interfaces.series.ISeriesInteractor
import pt.inm.movies.interactors.listeners.series.ISeriesInteractorListener
import pt.inm.movies.presenters.interfaces.base.IBasePresenter
import pt.inm.movies.presenters.listeners.series.ISeriesPresenterListener

interface ISeriesPresenter : IBasePresenter<ISeriesPresenterListener, ISeriesInteractorListener, ISeriesInteractor> {
    // TODO implement your Methods
}
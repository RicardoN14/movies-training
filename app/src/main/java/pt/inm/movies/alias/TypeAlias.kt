package pt.inm.movies.alias

import pt.inm.movies.interactors.interfaces.base.IBaseInteractor
import pt.inm.movies.interactors.listeners.base.IInteractorListener
import pt.inm.movies.presenters.interfaces.base.IBasePresenter
import pt.inm.movies.presenters.listeners.base.IPresenterListener
import pt.inm.webrequests.RequestManager
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.movies.http.entities.responses.HeaderResponseData

typealias PresentersArrayList = ArrayList<IBasePresenter<IPresenterListener,
        out IInteractorListener, out IBaseInteractor<out IInteractorListener>>>

typealias MoviesTrainingRequestManager = RequestManager<IMoviesTrainingWebRequestContext,
        MoviesTrainingWebRequest, HeaderResponseData>

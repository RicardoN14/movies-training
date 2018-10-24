package pt.inm.movies.presenters.implementations.base

import pt.inm.movies.injections.interactors.DaggerInteractorComponent
import pt.inm.movies.injections.interactors.InteractorComponent
import pt.inm.movies.interactors.interfaces.base.IBaseInteractor
import pt.inm.movies.interactors.listeners.base.IInteractorListener
import pt.inm.movies.presenters.interfaces.base.IBasePresenter
import pt.inm.movies.presenters.listeners.base.IPresenterListener
import javax.inject.Inject

abstract class BasePresenter<out PL : IPresenterListener, IL : IInteractorListener,
        I : IBaseInteractor<IL>>(override val presenterListener: PL,
                                 override val requestContextGroup: String)
    : IBasePresenter<PL, IL, I>, IInteractorListener {

    protected val logTag: String = javaClass.simpleName

    @Inject
    override lateinit var interactor: I // Injected by Dagger

    abstract fun injectDependencies(component: InteractorComponent)
    abstract fun getInteractorListener(): IL

    init {
        init()
    }

    private fun init() {
        // Remove comment after have some Interactor in InteractorComponent and InteractorModule
        injectDependencies(DaggerInteractorComponent.create())
        interactor.interactorListener = getInteractorListener()
        interactor.requestContextGroup = requestContextGroup
    }

}
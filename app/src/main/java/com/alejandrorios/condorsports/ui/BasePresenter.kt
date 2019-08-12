package com.alejandrorios.condorsports.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.alejandrorios.condorsports.domain.models.CoroutinesContextProvider
import kotlinx.coroutines.*

/**
 * Created by Alejandro Rios on 2019-07-22
 */
interface BasePresenter<T : BaseView> : LifecycleObserver {

    var view: T?

    fun bind(view: T) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe() {
        view = null
    }
}

interface BaseCoroutinePresenter<T : BaseView> : BasePresenter<T> {

    val parentJob: Job

    val coroutinesContextProvider: CoroutinesContextProvider

    fun launchJobOnMainDispatchers(job: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            job()
        }
    }

    fun <T> launchJob(
        backgroundJob: suspend CoroutineScope.() -> T,
        syncJob: (T) -> Unit
    ) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            try {
                val response = withContext(coroutinesContextProvider.backgroundContext) {
                    backgroundJob()
                }

                syncJob(response)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    override fun unsubscribe() {
        super.unsubscribe()
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}

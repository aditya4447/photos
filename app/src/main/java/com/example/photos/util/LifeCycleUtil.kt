package com.example.photos.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

@Suppress("unused")
fun <T : Any?> LifecycleOwner.observeModel(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this::getLifecycle, observer)
}

inline fun <T : Any?> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>,
                                                  crossinline observer: (T) -> Unit) {
    liveData.observe(this::getLifecycle) { event ->
        event.getContentIfNotHandled()
            ?.let {
                observer.invoke(it)
            }
    }
}

@Suppress("unused")
inline fun LifecycleOwner.observeEvent(liveData: LiveData<Event<Unit>>,
                                       crossinline observer: () -> Unit) {
    liveData.observe(this::getLifecycle) { event ->
        event.getContentIfNotHandled()
            ?.let {
                observer.invoke()
            }
    }
}

package com.example.photos.util

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event<*>

        if (content != other.content) return false
        if (hasBeenHandled != other.hasBeenHandled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + hasBeenHandled.hashCode()
        return result
    }

    override fun toString(): String {
        return "Event($content)"
    }
}

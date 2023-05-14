package ru.viknist.aston_dz_3

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun <T : Any?> bind(@IdRes idRes: Int, activity: AppCompatActivity): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) { activity.findViewById<View>(idRes) as T }
}
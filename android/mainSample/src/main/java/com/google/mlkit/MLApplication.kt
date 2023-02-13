package com.google.mlkit

import android.annotation.SuppressLint
import androidx.multidex.MultiDexApplication
import info.hannes.timber.FileLoggingTree
import timber.log.Timber


class MLApplication : MultiDexApplication() {

    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()

        val oldHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            @Suppress("ControlFlowWithEmptyBody")
            Timber.e(e.cause?.also { } ?: run { e })
            oldHandler?.uncaughtException(t, e)
        }

        externalCacheDir?.let {
            Timber.plant(FileLoggingTree(it, this))
        }

        Crashlytic.init(applicationContext.contentResolver)
    }
}
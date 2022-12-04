package com.google.mlkit

import android.os.Bundle
import info.hannes.github.AppUpdateHelper

class MLMainActivity : NavigationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ml_main)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.contentInfo, SystemInfoFragment())
                .commit()

        AppUpdateHelper.checkForNewVersion(
                this,
                BuildConfig.GIT_REPOSITORY
        )
    }
}
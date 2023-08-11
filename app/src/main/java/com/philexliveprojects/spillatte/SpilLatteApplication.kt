package com.philexliveprojects.spillatte

import android.app.Application
import com.philexliveprojects.spillatte.data.AppContainer
import com.philexliveprojects.spillatte.data.AppDataContainer

class SpilLatteApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
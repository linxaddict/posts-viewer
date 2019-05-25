package com.machineinsight_it.postviewer.di

import com.machineinsight_it.postviewer.ui.main.MainActivity
import com.machineinsight_it.postviewer.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity
}
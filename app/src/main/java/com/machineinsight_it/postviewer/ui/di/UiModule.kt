package com.machineinsight_it.postviewer.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.machineinsight_it.postviewer.ui.posts.detail.PostDetailModule
import com.machineinsight_it.postviewer.ui.posts.list.PostsListModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(
    includes = [
        PostsListModule::class,
        PostDetailModule::class
    ]
)
class UiModule {
    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            requireNotNull(getProvider(modelClass).get()) {
                "Provider for $modelClass returned null"
            }

        @Suppress("UNCHECKED_CAST")
        private fun <T : ViewModel> getProvider(modelClass: Class<T>): Provider<T> =
            try {
                requireNotNull(providers[modelClass] as Provider<T>) {
                    "No ViewModel provider is bound for class $modelClass"
                }
            } catch (cce: ClassCastException) {
                error("Wrong provider type registered for ViewModel type $modelClass")
            }
    }
}
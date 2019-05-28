package com.machineinsight_it.postviewer.ui.base.event

class ResourceTextMessageEvent : SingleLiveEvent<Int>() {
    fun call(message: Int) {
        value = message
    }
}
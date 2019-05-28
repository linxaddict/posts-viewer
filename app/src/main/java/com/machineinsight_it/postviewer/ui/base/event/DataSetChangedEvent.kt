package com.machineinsight_it.postviewer.ui.base.event

class DataSetChangedEvent : SingleLiveEvent<Pair<Int, Int>>() {
    fun call(start: Int, end: Int) {
        value = Pair(start, end)
    }
}
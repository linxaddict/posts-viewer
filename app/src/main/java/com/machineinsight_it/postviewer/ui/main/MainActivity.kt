package com.machineinsight_it.postviewer.ui.main

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.ActivityMainBinding

interface MainScreenController {
    fun setToolbarTitle(@StringRes title: Int)
}

class MainActivity : AppCompatActivity(), MainScreenController {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun setToolbarTitle(@StringRes title: Int) {
        supportActionBar?.setTitle(title)
    }
}

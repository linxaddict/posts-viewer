package com.machineinsight_it.postviewer.ui.base.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibility")
    fun visibility(view: View, bool: Boolean) {
        view.visibility = if (bool) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(view: ImageView, uri: String?) {
        if (!uri.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(uri)
                .into(view)
        }
    }
}
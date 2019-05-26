package com.machineinsight_it.postviewer.ui.posts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.FragmentPostsListBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostsListFragment : Fragment() {
    private lateinit var binding: FragmentPostsListBinding

    @Inject
    lateinit var viewModel: PostsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts_list, container, false)
        return binding.root
    }
}
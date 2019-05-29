package com.machineinsight_it.postviewer.ui.posts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.FragmentPostDetailBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostDetailFragment : Fragment() {
    private lateinit var binding: FragmentPostDetailBinding
    private val args: PostDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: PostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        System.out.println("received post ${args.post}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false)
        binding.model = viewModel
        return binding.root
    }
}
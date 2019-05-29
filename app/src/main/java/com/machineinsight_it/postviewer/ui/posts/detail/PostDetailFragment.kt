package com.machineinsight_it.postviewer.ui.posts.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.FragmentPostDetailBinding
import com.machineinsight_it.postviewer.ui.main.MainScreenController
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostDetailFragment : Fragment() {
    private lateinit var binding: FragmentPostDetailBinding
    private val args: PostDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: PostDetailViewModel

    private var mainScreenController: MainScreenController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        viewModel.setPost(args.post)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false)
        binding.model = viewModel

        val transitionName = "post_${args.post.id}"
        binding.content.transitionName = transitionName

        mainScreenController?.setToolbarTitle(R.string.title_post_detail)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is MainScreenController) {
            mainScreenController = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mainScreenController = null
    }
}
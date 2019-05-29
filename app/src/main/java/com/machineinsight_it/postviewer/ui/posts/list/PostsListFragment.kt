package com.machineinsight_it.postviewer.ui.posts.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.machineinsight_it.postviewer.BR
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.FragmentPostsListBinding
import com.machineinsight_it.postviewer.domain.Post
import com.machineinsight_it.postviewer.ui.main.MainScreenController
import dagger.android.support.AndroidSupportInjection
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.jetbrains.anko.design.longSnackbar
import javax.inject.Inject

interface OnItemClickListener {
    fun onItemClick(view: View, item: PostViewModel)
}

class PostsListFragment : Fragment() {
    private lateinit var binding: FragmentPostsListBinding
    private var mainScreenController: MainScreenController? = null

    private val itemAction = object : OnItemClickListener {
        override fun onItemClick(view: View, item: PostViewModel) {
            val transitionName = "post_${item.post.id}"
            val extras = FragmentNavigatorExtras(
                view to transitionName
            )
            val action = PostsListFragmentDirections.actionPostsListFragmentToPostDetailFragment(item.post)
            view.transitionName = transitionName

            findNavController().navigate(action, extras)
        }
    }

    @Inject
    lateinit var viewModel: PostsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)

        viewModel.fetchingErrorEvent.observe(this, Observer {
            binding.root.longSnackbar(it)
        })

        viewModel.fetchPosts()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts_list, container, false)
        binding.model = viewModel
        binding.itemBinding = ItemBinding.of<PostViewModel>(BR.model, R.layout.row_post)
            .bindExtra(BR.action, itemAction)
        binding.swipeRefresh.setOnRefreshListener { viewModel.fetchPosts() }

        mainScreenController?.setToolbarTitle(R.string.title_posts)

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
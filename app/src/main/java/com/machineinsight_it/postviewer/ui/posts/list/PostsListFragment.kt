package com.machineinsight_it.postviewer.ui.posts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.machineinsight_it.postviewer.BR
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.FragmentPostsListBinding
import com.machineinsight_it.postviewer.databinding.RowPostBinding
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.design.longSnackbar
import javax.inject.Inject

class PostsListFragment : Fragment() {
    private lateinit var binding: FragmentPostsListBinding

    @Inject
    lateinit var viewModel: PostsListViewModel

    private fun handleItemClicked(view: View, item: PostViewModel) {
        val transitionName = "post_${item.post.id}"
        val extras = FragmentNavigatorExtras(
            view to transitionName
        )
        val action = PostsListFragmentDirections.actionPostsListFragmentToPostDetailFragment(item.post)
        view.transitionName = transitionName

        findNavController().navigate(action, extras)
    }

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
        binding.swipeRefresh.setOnRefreshListener { viewModel.fetchPosts() }

        val type = Type<RowPostBinding>(R.layout.row_post)
            .onClick { holder ->
                holder.binding.model?.let {
                    handleItemClicked(holder.binding.root, it)
                }
            }

        LastAdapter(viewModel.posts, BR.model)
            .map<PostViewModel>(type)
            .into(binding.posts)

        return binding.root
    }
}
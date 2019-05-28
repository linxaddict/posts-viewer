package com.machineinsight_it.postviewer.ui.posts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.machineinsight_it.postviewer.BR
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.FragmentPostsListBinding
import dagger.android.support.AndroidSupportInjection
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.jetbrains.anko.design.longSnackbar
import javax.inject.Inject

class PostsListFragment : Fragment() {
    private lateinit var binding: FragmentPostsListBinding

    @Inject
    lateinit var viewModel: PostsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)

        viewModel.fetchingErrorEvent.observe(this, Observer {
            binding.root.longSnackbar(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts_list, container, false)
        binding.model = viewModel
        binding.itemBinding = ItemBinding.of<PostViewModel>(BR.model, R.layout.row_post)

        viewModel.fetchPosts()

        return binding.root
    }
}
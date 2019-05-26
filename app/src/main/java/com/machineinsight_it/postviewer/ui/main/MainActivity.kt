package com.machineinsight_it.postviewer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.databinding.ActivityMainBinding
import com.machineinsight_it.postviewer.ui.posts.detail.PostDetailFragment
import com.machineinsight_it.postviewer.ui.posts.list.PostsListFragment

private const val TAG_POSTS_LIST = "posts_list"
private const val TAG_POST_DETAIL = "post_detail"

class MainActivity : AppCompatActivity() {
    private var postsListFragment: PostsListFragment? = null
    private var postDetailFragment: PostDetailFragment? = null

    private lateinit var binding: ActivityMainBinding

    private fun restorePostsListFragment() {
        val list = supportFragmentManager.findFragmentByTag(TAG_POSTS_LIST)
        list?.let { postsListFragment = it as PostsListFragment }
    }

    private fun restorePostsDetailFragment() {
        val detail = supportFragmentManager.findFragmentByTag(TAG_POST_DETAIL)
        detail?.let { postDetailFragment = it as PostDetailFragment }
    }

    private fun createFragmentsIfRequired() {
        if (postsListFragment == null) {
            postsListFragment = PostsListFragment()
        }

        if (postDetailFragment == null) {
            postDetailFragment = PostDetailFragment()
        }
    }

    private fun showPostsList() {
        postsListFragment?.let {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.content, it,
                    TAG_POSTS_LIST
                )
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState != null) {
            restorePostsListFragment()
            restorePostsDetailFragment()
        } else {
            createFragmentsIfRequired()
            showPostsList()
        }
    }
}

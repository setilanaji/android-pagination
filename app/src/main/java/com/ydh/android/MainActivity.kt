package com.ydh.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ydh.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var userListAdapter: UserAdapter

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this)
            .get(UserViewModel::class.java)
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        userListAdapter = UserAdapter { viewModel.retry() }
        binding.recyclerMainUser.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerMainUser.adapter = userListAdapter
        viewModel.newsList.observe(this, Observer {
            userListAdapter.submitList(it)
        })
    }

    private fun initState() {
//        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
//            progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
//            txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                userListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}
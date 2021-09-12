package com.shabs.pagepoc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var mainListAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupView() {
        lifecycleScope.launchWhenCreated {
            viewModel.listData.collect {
                mainListAdapter.submitData(it)
            }
        }

    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainListAdapter
        }

        recyclerview.adapter = mainListAdapter.withLoadStateHeaderAndFooter(
            header = HeaderFooterAdapter { mainListAdapter.retry() },
            footer = HeaderFooterAdapter { mainListAdapter.retry() })

        mainListAdapter.addLoadStateListener {
            if (it.refresh == LoadState.Loading) {
                Log.d("TAG", "loading")
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
                Log.d("TAG", " not loading")
            }
        }

//        mainListAdapter.notifyItemRemoved() ;
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, MainViewModelFactory(APIService.getApiService()))[MainViewModel::class.java]
    }
}

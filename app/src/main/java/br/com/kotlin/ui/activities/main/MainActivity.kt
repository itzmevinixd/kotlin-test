package br.com.kotlin.ui.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import br.com.kotlin.adapters.RepositoryAdapter
import br.com.kotlin.databinding.ActivityMainBinding
import br.com.kotlin.viewmodels.RepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        repositoryViewModel = ViewModelProvider(this).get(RepositoryViewModel::class.java)

        val adapter = RepositoryAdapter()

        binding.apply {
            listRepository.adapter = adapter

            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        repositoryViewModel.repositories.observe(this) {
            adapter.submitData(this.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progress.isVisible = loadState.source.refresh is LoadState.Loading
                listRepository.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                errorMessage.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                    listRepository.isVisible = false
                    notFound.isVisible = true
                } else notFound.isVisible = false
            }
        }
    }
}
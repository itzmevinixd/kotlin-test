package br.com.kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.kotlin.data.model.Repository
import br.com.kotlin.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    githubRepository: GithubRepository
) : ViewModel() {

    val repositories: LiveData<PagingData<Repository>> =
        githubRepository.getRepositories().cachedIn(viewModelScope).asLiveData()
}
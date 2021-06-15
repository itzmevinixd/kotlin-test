package br.com.kotlin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.kotlin.api.GithubApi
import br.com.kotlin.data.model.Repository
import br.com.kotlin.data.sources.RepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(private val githubApi: GithubApi) {

    fun getRepositories(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RepositorySource(githubApi) }
        ).flow
    }
}
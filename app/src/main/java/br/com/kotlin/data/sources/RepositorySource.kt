package br.com.kotlin.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.kotlin.api.GithubApi
import br.com.kotlin.data.model.Repository
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class RepositorySource(
    private val githubApi: GithubApi
): PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = githubApi.getRepositories(position, params.loadSize)
            val repos = response.items

            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        TODO("Not yet implemented")
    }

}
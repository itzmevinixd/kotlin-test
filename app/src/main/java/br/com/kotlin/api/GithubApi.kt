package br.com.kotlin.api

import br.com.kotlin.data.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun getRepositories(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepositoryResponse

}
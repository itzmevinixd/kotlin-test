package br.com.kotlin.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Repository(

    @JsonProperty("id")
    val id: Long,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("full_name")
    val fullName: String,

    @JsonProperty("stargazers_count")
    val stars: Int,

    @JsonProperty("forks_count")
    val forks: Int,

    @JsonProperty("owner")
    val owner: Owner

) : Parcelable
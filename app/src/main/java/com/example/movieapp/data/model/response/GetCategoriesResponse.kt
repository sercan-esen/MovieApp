package com.example.movieapp.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetCategoriesResponse(
    @SerializedName("genres")
    val genres: List<CategoriesItem>
): Parcelable

@Parcelize
data class CategoriesItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
): Parcelable
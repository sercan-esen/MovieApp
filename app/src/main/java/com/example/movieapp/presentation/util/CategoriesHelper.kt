package com.example.movieapp.presentation.util

import com.example.movieapp.data.model.response.CategoriesItem

object CategoriesHelper {
    private var categoriesList = mutableListOf<CategoriesItem>()

    fun findCategoriesNameById(id: Int?): String = kotlin.run {
        var categoriesName = ""
        categoriesList.forEach {
            if (it.id == id)
                categoriesName = it.name
        }
        categoriesName
    }

    fun setCategoriesList(newList: List<CategoriesItem>) {
        categoriesList = newList.toMutableList()
    }
}
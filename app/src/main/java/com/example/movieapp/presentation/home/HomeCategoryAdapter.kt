package com.example.movieapp.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.model.response.CategoriesItem
import com.example.movieapp.databinding.HomeCategoriesRowBinding

class HomeCategoryAdapter(
    private val context: Context,
    private val listener: (CategoriesItem) -> Unit
) :
    RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {
    private var homeCategoryList: List<CategoriesItem> = emptyList()
    private var selectedItemIndex = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeCategoryAdapter.ViewHolder {
        return ViewHolder(
            HomeCategoriesRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoriesItem = homeCategoryList[position]
        holder.rootView.txtCategoriesName.text = categoriesItem.name

        if (selectedItemIndex == holder.layoutPosition) {
            listener.invoke(categoriesItem)
            holder.rootView.txtCategoriesName.background =
                ContextCompat.getDrawable(context, R.drawable.bg_categorie_selected)
            holder.rootView.txtCategoriesName.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.blue_accent
                )
            )
        } else {
            holder.rootView.txtCategoriesName.background = null
            holder.rootView.txtCategoriesName.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white_grey
                )
            )
        }

        holder.rootView.root.setOnClickListener {
            val previousItemIndex = selectedItemIndex
            selectedItemIndex = holder.adapterPosition
            notifyItemChanged(previousItemIndex)
            notifyItemChanged(selectedItemIndex)
        }
    }

    override fun getItemCount(): Int = homeCategoryList.size

    inner class ViewHolder(view: HomeCategoriesRowBinding) :
        RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<CategoriesItem>) {
        homeCategoryList = list
        notifyDataSetChanged()
    }
}
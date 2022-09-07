package com.example.mycode.Pagination.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycode.Pagination.model.DataModles
import com.example.mycode.R
import com.example.mycode.databinding.ItemsInPagingBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_in_paging.view.*

class PagingAdapter(context: Context) : RecyclerView.Adapter<PagingAdapter.MyViewModel>() {
    lateinit var pagingBinding: ItemsInPagingBinding
     var dataModles = ArrayList<DataModles>()

    class MyViewModel(binding: ItemsInPagingBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        pagingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.items_in_paging,parent,false)
        return MyViewModel(pagingBinding)
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val currentPosition = dataModles[position]
        holder.itemView.firstNamePaging.text = currentPosition.first_name
        holder.itemView.lastNamePaging.text = currentPosition.last_name
        holder.itemView.emailPaging.text = currentPosition.email
        Picasso.get().load(currentPosition.avatar).into(holder.itemView.paging_image)

    }

    override fun getItemCount(): Int {
        return dataModles.size
    }
}
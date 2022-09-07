package com.example.mycode.NavigationComponents.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mycode.NavigationComponents.fragments.fragments.list.ListFragmentDirections
import com.example.mycode.R

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userData = emptyList<com.example.mycode.Room_Databse.model.UserData>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPosition = userData[position]
        holder.itemView.findViewById<TextView>(R.id.user_id).text =
            currentPosition.id.toString().toInt().toString()
        holder.itemView.findViewById<TextView>(R.id.user_first_name).text =
            currentPosition.firstName
        holder.itemView.findViewById<TextView>(R.id.user_last_name).text = currentPosition.lastName
        holder.itemView.findViewById<TextView>(R.id.user_list_age).text =
            currentPosition.age.toString().toInt().toString()

        holder.itemView.findViewById<ConstraintLayout>(R.id.root_layout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentPosition)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<com.example.mycode.Room_Databse.model.UserData>) {
        this.userData = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userData.size
    }
}
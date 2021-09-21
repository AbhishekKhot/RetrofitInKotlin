package com.example.retrofittutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import com.example.retrofittutorial.databinding.RvItemsBinding

class RVadapter:RecyclerView.Adapter<RVadapter.RecyclervViewHolder>() {

    inner class RecyclervViewHolder( val binding: RvItemsBinding):RecyclerView.ViewHolder(binding.root){}


    private val diffCallback=object:DiffUtil.ItemCallback<ApiResponse>(){
        override fun areItemsTheSame(oldItem: ApiResponse, newItem: ApiResponse): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ApiResponse, newItem: ApiResponse): Boolean {
            return oldItem==newItem
        }

    }

    private val differ= AsyncListDiffer(this,diffCallback)

    var ApiResponseList:List<ApiResponse>
    get() = differ.currentList
    set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclervViewHolder {
        return RecyclervViewHolder(RvItemsBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: RecyclervViewHolder, position: Int) {
        holder.binding.apply {
            Name.text=ApiResponseList[position].name
            Email.text=ApiResponseList[position].email
            Body.text=ApiResponseList[position].body
        }
    }

    override fun getItemCount()=ApiResponseList.size
}
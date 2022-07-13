package com.yurii.vytivskyi.trainingapp.view.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yurii.vytivskyi.trainingapp.R
import com.yurii.vytivskyi.trainingapp.data.Result
import com.yurii.vytivskyi.trainingapp.databinding.RecyclerViewItemBinding

class SimpleAdapter(private val mainL: List<Result?>, val mItemClickListener : ItemClickListener):
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onClick(id: Int) {
        }
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerViewItemBinding.bind(item)
        fun bind(result: Result?) = with(binding) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result?.poster_path).into(imageView)
        }

        init {
            item.setOnClickListener {
                mainL[absoluteAdapterPosition]?.id?.let { it -> mItemClickListener.onClick(it) }
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(mainL[position])
        }

        override fun getItemCount(): Int {
            return mainL.size
        }



}
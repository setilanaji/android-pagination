package com.ydh.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bind(userModel: UserModel?) {
        if (userModel != null) {
            itemView.tv_item_user_name.text = userModel.firstName
//            Picasso.get().load(news.image).into(itemView.img_news_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
            return UserViewHolder(view)
        }
    }
}
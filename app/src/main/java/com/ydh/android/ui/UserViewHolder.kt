package com.ydh.android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.android.R
import com.ydh.android.model.UserModel
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder ( view: View) : RecyclerView.ViewHolder(view) {


    fun bind(userModel: UserModel?) {
        if (userModel != null) {
            itemView.tv_item_user_name.text = userModel.firstName + " " + userModel.lastName
            itemView.tv_item_user_email.text = userModel.email
            Glide.with(itemView).load(userModel.avatarImgUrl).into(itemView.image_item_user_profile)
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
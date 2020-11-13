package com.ydh.android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private val retry: () -> Unit)
    : PagedListAdapter<UserModel, RecyclerView.ViewHolder>(UserDiffCallback){ //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//
//        val inflater = LayoutInflater.from(context)
//        val view: View = inflater.inflate(R.layout.user_item, parent, false)
//        return UserViewHolder(view, this.itemListener)
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//
//        val user = items[position]
//        holder.setData(user , position)
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    fun getItem(position: Int): UserModel{
//        return items[position]
//    }
//
//    interface PostItemListener {
//        fun onPostClick(userModel: UserModel)
//    }
//
//    inner class UserViewHolder(var view: View, var itemListener: UserAdapter.PostItemListener): RecyclerView.ViewHolder(view), View.OnClickListener{
//        var name: TextView? = null
//        var email: TextView? = null
//        var image: ImageView? = null
//        var userModel: UserModel? = null
//
//        init {
//
//            name = view.findViewById(R.id.tv_item_user_name)
//            email = view.findViewById(R.id.tv_item_user_email)
//            image = view.findViewById(R.id.image_item_user_profile)
//            view.setOnClickListener(this)
//
//
//        }
//        fun setData(userModel: UserModel, position: Int){
//            userModel?.let {
//                name?.text  = userModel.firstName + " " + userModel.lastName
//                email?.text = userModel.email
//
//               }
//            this.userModel = userModel
//        }
//
//        override fun onClick(v: View?) {
//            val user = getItem(adapterPosition)
//            this.itemListener.onPostClick(user)
//            this@UserAdapter.notifyDataSetChanged()
//        }
//
//    }

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as UserViewHolder).bind(getItem(position))
//        else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem.firstName == newItem.firstName
            }

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }


}
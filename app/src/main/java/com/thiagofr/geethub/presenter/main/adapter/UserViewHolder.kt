package com.thiagofr.geethub.presenter.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thiagofr.geethub.R
import com.thiagofr.geethub.domain.model.User

class UserViewHolder(
    view: View,
    private val onCLick: (User) -> Unit,
) : ViewHolder(view) {

    private val tvLogin: TextView by lazy { view.findViewById(R.id.tv_login) }
    private val imgView: ImageView by lazy { view.findViewById(R.id.img_user) }

    fun bind(user: User) {
        tvLogin.text = user.login
        this.itemView.setOnClickListener {
            onCLick.invoke(user)
        }

        Glide.with(itemView)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_coragem)
            .into(imgView)
    }
}
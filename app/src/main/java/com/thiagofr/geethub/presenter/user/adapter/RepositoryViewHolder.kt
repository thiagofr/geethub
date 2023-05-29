package com.thiagofr.geethub.presenter.user.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.geethub.R
import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.util.invisible
import com.thiagofr.geethub.util.visible

class RepositoryViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val tvName: TextView by lazy { view.findViewById(R.id.tv_name) }
    private val tvFullName: TextView by lazy { view.findViewById(R.id.tv_full_name) }
    private val imgIsPrivate: ImageView by lazy { view.findViewById(R.id.img_private) }

    fun bind(repository: Repository) {

        val resources = itemView.resources

        tvName.text = resources.getString(R.string.name, repository.name)
        tvFullName.text = resources.getString(R.string.full_name, repository.fullName)

        if (repository.isPrivate) {
            imgIsPrivate.visible()
        } else {
            imgIsPrivate.invisible()
        }
    }

}
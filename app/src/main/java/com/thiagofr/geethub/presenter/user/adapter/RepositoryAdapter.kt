package com.thiagofr.geethub.presenter.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.geethub.R
import com.thiagofr.geethub.domain.model.Repository

class RepositoryAdapter(
    private val repositoryList: List<Repository>
) : RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false),
        )
    }

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])
    }
}
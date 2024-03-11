package com.thiagofr.geethub.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.geethub.R
import com.thiagofr.geethub.domain.model.MockResponse

class MockResponseAdapter(
    private val mockResponseList: List<MockResponse> = listOf(),
    private val onItemClick: (MockResponse) -> Unit
) : RecyclerView.Adapter<MockResponseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockResponseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mock_response_item, parent, false)
        return MockResponseViewHolder(view, onItemClick)
    }

    override fun getItemCount() = mockResponseList.size

    override fun onBindViewHolder(holder: MockResponseViewHolder, position: Int) {
        holder.bind(mockResponseList[position])
    }
}

class MockResponseViewHolder(val view: View, val onItemClick: (MockResponse) -> Unit) :
    RecyclerView.ViewHolder(view) {
    val tvDescription: TextView by lazy { view.findViewById(R.id.tv_description) }

    fun bind(item: MockResponse) {
        tvDescription.text = item.description
        view.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

}
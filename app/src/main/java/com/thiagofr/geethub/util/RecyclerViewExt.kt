package com.thiagofr.geethub.util

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.thiagofr.geethub.R

fun RecyclerView.setDividerItemDecoration() {
    val dividerItemDecoration =
        DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)

    AppCompatResources.getDrawable(this.context, R.drawable.divider)?.let { drawable ->
        dividerItemDecoration.setDrawable(
            drawable
        )

        this.addItemDecoration(dividerItemDecoration)
    }
}
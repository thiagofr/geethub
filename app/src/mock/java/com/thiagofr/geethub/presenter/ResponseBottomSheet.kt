package com.thiagofr.geethub.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thiagofr.geethub.R
import com.thiagofr.geethub.domain.model.MockResponse
import com.thiagofr.geethub.presenter.adapter.MockResponseAdapter
import com.thiagofr.geethub.utils.readJsonFromAssets

class ResponseBottomSheet(val pathToResponse: String,val onContinueClick: (MockResponse) -> Unit): BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.response_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getResponses()
    }

    private fun getResponses() {
        val json = readJsonFromAssets("$pathToResponse-responses.json", requireContext())

        val listType = object : TypeToken<List<MockResponse>>() {}.type


        json?.let {
            val mockResponseList = Gson().fromJson<List<MockResponse>>(it, listType)

            view?.findViewById<TextView>(R.id.tv_title)?.apply {
                text = "Selecione a resposta para $pathToResponse"
            }

            view?.findViewById<RecyclerView>(R.id.rv_response)?.apply {
                this.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                this.adapter = MockResponseAdapter(mockResponseList) {
                    onContinueClick.invoke(it)
                    dismiss()
                }
            }
        }
    }
}
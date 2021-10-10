package com.pesh.mentalcare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pesh.mentalcare.IllnessDataSource
import com.pesh.mentalcare.MyIllnessRVAdapter
import com.pesh.mentalcare.R

class IllnessesTypesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_illnesses_types, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val illnessesListRecyclerView: RecyclerView = view.findViewById(R.id.illnessesListRecyclerView)
        illnessesListRecyclerView.layoutManager= LinearLayoutManager(context)
        val illnessAdapter = MyIllnessRVAdapter()
        val data = IllnessDataSource.createDataSet()
        illnessAdapter.submitList(data)
        illnessesListRecyclerView.adapter = illnessAdapter
    }
}
package com.pesh.mentalcare

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pesh.mentalcare.R
import com.pesh.mentalcare.model.IllnessTypes

class MyIllnessRVAdapter : RecyclerView.Adapter<ViewHolder>(){

    private  var illnessItems : List<IllnessTypes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return IllnessesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_illnesses_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is IllnessesViewHolder ->{
                holder.bind(illnessItems.get(position))

            }

        }

    }

    override fun getItemCount(): Int {
        return illnessItems.size
    }
    fun submitList(illnessList: List<IllnessTypes>){
        illnessItems = illnessList
    }

    class IllnessesViewHolder constructor(
            illnessView: View
    ): ViewHolder(illnessView){

        val illnessTypeRV: TextView = illnessView.findViewById(R.id.illnessTypeRV)
        val illnesExplanationRV: TextView = illnessView.findViewById(R.id.illnessExplanationRV)

        fun bind(illnessTypes: IllnessTypes){
            illnessTypeRV.text = illnessTypes.mentalIllness
            illnesExplanationRV.text = illnessTypes.explanation

        }
    }

}
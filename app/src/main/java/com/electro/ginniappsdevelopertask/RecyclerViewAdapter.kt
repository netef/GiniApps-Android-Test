package com.electro.ginniappsdevelopertask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.android.synthetic.main.number_item_big.view.*

class RecyclerViewAdapter(private val list: IntArray) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val SUM_ZERO = 1
    private val SUM_NON_ZERO = 2

    override fun getItemViewType(position: Int) =
        if (checkSumZero(position))
            SUM_ZERO
        else SUM_NON_ZERO


    private fun checkSumZero(position: Int): Boolean {
        val sum = list[position]
        for (num in list.indices) {
            if (list[num] == list[position]) continue
            if (sum + list[num] == 0) return true
        }
        return false
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == SUM_ZERO)
            SumZeroNumberHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.number_item_big, parent, false)
            )
        else
            NonSumZeroNumberHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.number_item, parent, false)
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        if (holder is SumZeroNumberHolder) holder.itemView.textViewBig.text =
            list[position].toString()
        else
            holder.itemView.textView.text = list[position].toString()

    class SumZeroNumberHolder(view: View) : RecyclerView.ViewHolder(view)
    class NonSumZeroNumberHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount() = list.size
}
package com.martha.myapplication.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martha.myapplication.R
import com.martha.myapplication.network.model.DetailTransactionModel
import com.martha.myapplication.network.model.TransactionModel

class TransactionAdapter(val context: Context): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    private val transaction: MutableList<TransactionModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        return TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        return transaction.size
    }

    override fun onBindViewHolder(holder: TransactionAdapter.TransactionViewHolder, position: Int) {
        holder.bindModel(transaction[position])
        setTransactionByDateRecycler(holder.itemRecycler, transaction[position].detailTransaction!!)
    }

    fun setTransaction(data: List<TransactionModel>) {
        transaction.clear()
        transaction.addAll(data)
        notifyDataSetChanged()
    }

    fun setTransactionByDateRecycler(recyclerView: RecyclerView, dt: MutableList<DetailTransactionModel>) {
        var itemDetailTransactionLayoutBinding = DetailTransactionAdapter(context)
        itemDetailTransactionLayoutBinding.setDetailTransaction(dt)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = itemDetailTransactionLayoutBinding
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTxt: TextView = itemView.findViewById(R.id.tv_date)
        val itemRecycler: RecyclerView = itemView.findViewById(R.id.rv_transaction)
        fun bindModel(tr: TransactionModel) {
            dateTxt.text = tr.date
        }
    }
}
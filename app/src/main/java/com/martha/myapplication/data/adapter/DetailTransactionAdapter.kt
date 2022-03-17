package com.martha.myapplication.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.martha.myapplication.R
import com.martha.myapplication.network.model.DetailTransactionModel

class DetailTransactionAdapter(val context: Context): RecyclerView.Adapter<DetailTransactionAdapter.DetailTransactionViewHolder>() {
    private val detailTransaction: MutableList<DetailTransactionModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailTransactionViewHolder {
        return DetailTransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detail_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        return detailTransaction.size
    }

    override fun onBindViewHolder(holder: DetailTransactionAdapter.DetailTransactionViewHolder, position: Int) {
        holder.bindModel(detailTransaction[position])
    }

    fun setDetailTransaction(data: List<DetailTransactionModel>) {
        detailTransaction.clear()
        detailTransaction.addAll(data)
        notifyDataSetChanged()
    }

    inner class DetailTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accnoTxt: TextView = itemView.findViewById(R.id.tv_accno)
        val usernameTxt: TextView = itemView.findViewById(R.id.tv_username)
        val ammountTxt: TextView = itemView.findViewById(R.id.tv_ammount)

        fun bindModel(dtr: DetailTransactionModel) {
            accnoTxt.text = dtr.accountNo
            usernameTxt.text = dtr.accountHolder
            ammountTxt.text = dtr.amount.toString()

            if (dtr.amount!! > 0) {
                ammountTxt.setTextColor(context.resources.getColor(R.color.green))
            } else {
                ammountTxt.text = "- ${dtr.amount.toString()}"
            }
        }
    }
}
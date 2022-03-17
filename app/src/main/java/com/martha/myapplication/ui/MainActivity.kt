package com.martha.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.martha.myapplication.base.BaseActivity
import com.martha.myapplication.data.adapter.TransactionAdapter
import com.martha.myapplication.databinding.ActivityMainBinding
import com.martha.myapplication.network.Resource
import com.martha.myapplication.network.model.DetailTransactionModel
import com.martha.myapplication.network.model.TransactionModel
import com.martha.myapplication.ui.auth.views.LoginActivity
import com.martha.myapplication.ui.transaction.viewmodels.TransactionViewModel
import com.martha.myapplication.ui.transaction.views.TransferActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.HashMap

class MainActivity : BaseActivity<TransactionViewModel, ActivityMainBinding>(){
    private lateinit var transactionAdapter : TransactionAdapter
    val layoutManager = LinearLayoutManager(this)
    var transactionModel = mutableListOf<TransactionModel>()
    var dateList = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initAction()
    }

    fun getFormattedDate(strDate:String): String {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")//old format
            val dateFormat2 = SimpleDateFormat("dd MMM yyyy")//require new format
            val objDate = dateFormat.parse(strDate)
            return dateFormat2.format(objDate)
        } catch (e: Exception) {
            return ""
        }
    }

    fun initView() {
        bind.rvTransaction.layoutManager = layoutManager
        transactionAdapter = TransactionAdapter(this)
        bind.rvTransaction.adapter = transactionAdapter

        viewModel.balance()
        viewModel.balanceResponse.observe(this, Observer{
            when(it) {
                is Resource.Success -> { bind.tvAmmount.text = "SGD ${it.value.balance}" }
                is Resource.Failure -> { Log.e("BL Error", it.message.toString()) }
            }
        })

        viewModel.transactionHistory()
        viewModel.transactionResponse.observe(this, Observer{
            when(it) {
                is Resource.Success -> {
                    it.value.data?.forEach { t->
                        dateList.put(getFormattedDate(t.transactionDate!!), getFormattedDate(t.transactionDate!!))
                    }

                    dateList.forEach { d ->
                        var tm = TransactionModel()
                        var listDetail = mutableListOf<DetailTransactionModel>()
                        tm.date = d.value

                        it.value.data?.forEach { t ->
                            if (d.value == getFormattedDate(t.transactionDate!!)) {
                                var dt = DetailTransactionModel()
                                dt.accountHolder = t.receipient!!.accountHolder
                                dt.accountNo = t.receipient!!.accountNo
                                dt.amount = t.amount
                                listDetail.add(dt)
                            }
                        }

                        tm.detailTransaction = listDetail
                        transactionModel.add(tm)
                    }



                    transactionAdapter.setTransaction(transactionModel)
                }
                is Resource.Failure -> {
                    Log.e("Tr Error", it.message.toString())
                }
            }
        })

        bind.tvAccno.text = runBlocking { prefs.getAccountNo.first() }
        bind.tvUsername.text = runBlocking { prefs.getUsername.first() }
    }

    fun initAction() {
        bind.tvLogout.setOnClickListener {
            lifecycleScope.launch { prefs.setToken("") }
            goToActivity(LoginActivity::class.java, null, true)
        }
        bind.btnTransfer.setOnClickListener { goToActivity(TransferActivity::class.java, null, false) }
    }

    override fun getViewModel() = TransactionViewModel::class.java
    override fun getActivityBinding(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

}
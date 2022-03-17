package com.martha.myapplication.ui.transaction.views

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.martha.myapplication.base.BaseActivity
import com.martha.myapplication.databinding.ActivityTransferBinding
import com.martha.myapplication.network.Resource
import com.martha.myapplication.network.request.TransferRequest
import com.martha.myapplication.ui.transaction.viewmodels.TransactionViewModel


class TransferActivity : BaseActivity<TransactionViewModel, ActivityTransferBinding>(){
    lateinit var arrayPayeesAdapter: ArrayAdapter<String>
    var payeesArray: MutableList<String> = mutableListOf()
    var spinnerMap = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initAction()
    }

    fun initView() {
        viewModel.payees()
        viewModel.payeesResponse.observe(this, Observer {
            when(it){
                is Resource.Success -> {
                    it.value.data?.forEach { p ->
                        spinnerMap.put(p.name!!, p.accountNo!!)
                        payeesArray.add(p.name!!)
                    }

                    loadSpinner()
                }
                is Resource.Failure -> {}
            }
        })

        viewModel.transferResponse.observe(this, Observer {
            when(it){
                is Resource.Success -> {}
                is Resource.Failure -> {
                    if (it.errorCode == 400) {
                        Toast.makeText(this, "insufficient balance", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    fun initAction() {
        bind.btnTransferNow.setOnClickListener {
            var request = TransferRequest()
            request.receipientAccountNo = spinnerMap.get(bind.spPayees.selectedItem.toString())
            request.amount = bind.etAmmount.text.trim().toString().toDouble()
            request.description = bind.etDescription.text.toString().trim()
            viewModel.transfer(request)
        }
        bind.ivBack.setOnClickListener { backToPreviousPage() }
    }

    fun loadSpinner() {
        arrayPayeesAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            payeesArray
        )
        bind.spPayees.adapter = arrayPayeesAdapter
    }

    override fun getViewModel() = TransactionViewModel::class.java
    override fun getActivityBinding(layoutInflater: LayoutInflater) = ActivityTransferBinding.inflate(layoutInflater)
}
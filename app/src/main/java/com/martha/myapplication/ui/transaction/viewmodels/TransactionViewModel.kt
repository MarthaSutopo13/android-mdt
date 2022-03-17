package com.martha.myapplication.ui.transaction.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martha.myapplication.data.repository.TransactionRepository
import com.martha.myapplication.network.Resource
import com.martha.myapplication.network.request.TransferRequest
import com.martha.myapplication.network.responses.BalanceResponse
import com.martha.myapplication.network.responses.PayeesResponse
import com.martha.myapplication.network.responses.TransactionResponse
import com.martha.myapplication.network.responses.TransferResponse
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionRepo: TransactionRepository
) : ViewModel() {

    private val _transactionResponse: MutableLiveData<Resource<TransactionResponse>> = MutableLiveData()
    val transactionResponse: LiveData<Resource<TransactionResponse>>
        get() = _transactionResponse

    private val _balanceResponse: MutableLiveData<Resource<BalanceResponse>> = MutableLiveData()
    val balanceResponse: LiveData<Resource<BalanceResponse>>
        get() = _balanceResponse

    private val _transferResponse: MutableLiveData<Resource<TransferResponse>> = MutableLiveData()
    val transferResponse: LiveData<Resource<TransferResponse>>
        get() = _transferResponse

    private val _payeesResponse: MutableLiveData<Resource<PayeesResponse>> = MutableLiveData()
    val payeesResponse: LiveData<Resource<PayeesResponse>>
        get() = _payeesResponse

    fun transactionHistory()
            = viewModelScope.launch {
        _transactionResponse.value = transactionRepo.transactionHistory()
    }

    fun balance()
            = viewModelScope.launch {
        _balanceResponse.value = transactionRepo.balance()
    }

    fun transfer(tr: TransferRequest)
            = viewModelScope.launch {
        _transferResponse.value = transactionRepo.transfer(tr)
    }

    fun payees()
            = viewModelScope.launch {
        _payeesResponse.value = transactionRepo.payees()
    }
}
package com.martha.myapplication.data.repository

import com.martha.myapplication.base.BaseRepository
import com.martha.myapplication.network.api.BalanceApi
import com.martha.myapplication.network.api.TransactionApi
import com.martha.myapplication.network.request.TransferRequest

class TransactionRepository(
    val service: TransactionApi,
    val balanceService : BalanceApi,
) : BaseRepository() {

    suspend fun transactionHistory() = handlingApiCall {
        service.transactionHistory()
    }

    suspend fun balance() = handlingApiCall {
        balanceService.balance()
    }

    suspend fun transfer(tr: TransferRequest) = handlingApiCall {
        service.transfer(tr)
    }

    suspend fun payees() = handlingApiCall {
        service.payees()
    }
}
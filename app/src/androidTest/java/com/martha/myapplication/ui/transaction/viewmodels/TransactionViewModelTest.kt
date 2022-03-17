package com.martha.myapplication.ui.transaction.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.martha.myapplication.data.repository.TransactionRepository
import com.martha.myapplication.data.repository.UserRepository
import com.martha.myapplication.getOrAwaitValue
import com.martha.myapplication.network.RemoteDataSource
import com.martha.myapplication.network.api.AuthApi
import com.martha.myapplication.network.api.BalanceApi
import com.martha.myapplication.network.api.TransactionApi
import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.ui.auth.viewmodels.UserViewModel
import com.martha.myapplication.util.DataPreferences
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransactionViewModelTest : TestCase(){
    private lateinit var viewModel: TransactionViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val remoteDataSource = RemoteDataSource(context)
        val service = remoteDataSource.buildApi(TransactionApi::class.java)
        val balanceService = remoteDataSource.buildApi(BalanceApi::class.java)

        val transactionRepository = TransactionRepository(service, balanceService)
        viewModel = TransactionViewModel(transactionRepository)
    }

    @Test
    fun testTransactionHistory() {
        val result = viewModel.transactionResponse.getOrAwaitValue()
        Truth.assertThat(result != null).isTrue()
    }

    @Test
    fun testTransfer() {
        val result = viewModel.transferResponse.getOrAwaitValue()
        Truth.assertThat(result != null).isTrue()
    }

    @Test
    fun testBalance() {
        val result = viewModel.balanceResponse.getOrAwaitValue()
        Truth.assertThat(result != null).isTrue()
    }

    @Test
    fun testPayees() {
        val result = viewModel.payeesResponse.getOrAwaitValue()
        Truth.assertThat(result != null).isTrue()
    }
}
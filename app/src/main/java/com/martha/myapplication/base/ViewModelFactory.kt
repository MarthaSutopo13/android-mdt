package com.martha.myapplication.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.martha.myapplication.data.repository.TransactionRepository
import com.martha.myapplication.data.repository.UserRepository
import com.martha.myapplication.network.RemoteDataSource
import com.martha.myapplication.network.api.AuthApi
import com.martha.myapplication.network.api.BalanceApi
import com.martha.myapplication.network.api.TransactionApi
import com.martha.myapplication.ui.auth.viewmodels.UserViewModel
import com.martha.myapplication.ui.transaction.viewmodels.TransactionViewModel
import com.martha.myapplication.util.DataPreferences

class ViewModelFactory(
    var context: Context
) : ViewModelProvider.NewInstanceFactory() {
    protected val remoteDataSource = RemoteDataSource(context)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(
                UserRepository(
                    remoteDataSource.buildApi(AuthApi::class.java),
                    DataPreferences(context)
                )
            ) as T
            modelClass.isAssignableFrom(TransactionViewModel::class.java) -> TransactionViewModel(
                TransactionRepository(
                    remoteDataSource.buildApi(TransactionApi::class.java),
                    remoteDataSource.buildApi(BalanceApi::class.java)
                )
            ) as T
            else -> throw IllegalArgumentException("Viewmodel class is not found")
        }
    }
}

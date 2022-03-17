package com.martha.myapplication.ui.auth.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.martha.myapplication.data.repository.UserRepository
import com.martha.myapplication.getOrAwaitValue
import com.martha.myapplication.network.RemoteDataSource
import com.martha.myapplication.network.api.AuthApi
import com.martha.myapplication.network.request.UserRequest
import com.martha.myapplication.util.DataPreferences
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserViewModelTest : TestCase(){
    private lateinit var viewModel: UserViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = DataPreferences(context)
        val remoteDataSource = RemoteDataSource(context)
        val service = remoteDataSource.buildApi(AuthApi::class.java)

        val userRepository = UserRepository(service, prefs)
        viewModel = UserViewModel(userRepository)
    }

    @Test
    fun testLogin() {
        var request = UserRequest()
        request.username = "test"
        request.password = "asdasd"
        viewModel.login(request)

        val result = viewModel.userResponse.getOrAwaitValue()
        assertThat(result != null).isTrue()
    }

    @Test
    fun testRegister() {
        var request = UserRequest()
        request.username = "test"
        request.password = "asdasd"
        viewModel.register(request)

        val result = viewModel.userResponse.getOrAwaitValue()
        assertThat(result != null).isTrue()
    }
}
package com.eschool.android.ui.auth.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eschool.android.data.common.ApiState
import com.eschool.android.data.di.AuthModule
import com.eschool.android.data.dto.ResponseBase
import com.eschool.android.data.dto.auth.SignUpRequest
import com.eschool.android.data.dto.auth.SignUpResponse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val service = AuthModule.provideService()

    private var _responseState = mutableStateOf(ApiState<ResponseBase<SignUpResponse>>())
    val responseState: State<ApiState<ResponseBase<SignUpResponse>>> = _responseState

    fun signUp(body: SignUpRequest) {
        viewModelScope.launch {
            try {
                _responseState.value = ApiState(isLoading = true)

                val apiResponse = service.signUp(body)

                if (apiResponse.isSuccessful) {
                    val result: ResponseBase<SignUpResponse> = apiResponse.body()!!
                    _responseState.value = ApiState(data = result, code = result.code)
                } else {
                    val errorBody = apiResponse.errorBody()!!
                    val errorBodyReader = errorBody.charStream()

                    val gson = Gson()
                    val errorBodyModel = gson.fromJson(errorBodyReader, ResponseBase::class.java)

                    _responseState.value = ApiState(
                        hasError = true,
                        errorMessage = errorBodyModel.msg,
                        code = errorBodyModel.code
                    )
                }
            } catch (e: Exception) {
                Log.e("SignUpViewModel", "Unknown error", e)
                _responseState.value = ApiState(
                    hasError = true,
                    errorMessage = "Unknown error: $e"
                )
            }
        }
    }

    fun clear() { _responseState.value = ApiState() }
}
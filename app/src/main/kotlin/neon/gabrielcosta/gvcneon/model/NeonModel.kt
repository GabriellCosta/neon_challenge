package neon.gabrielcosta.gvcneon.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import neon.gabrielcosta.gvcneon.TokenManager
import neon.gabrielcosta.gvcneon.entity.dto.SendMoneyDTO
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO
import neon.gabrielcosta.gvcneon.network.ApiResponse
import neon.gabrielcosta.gvcneon.network.NeonApi
import neon.gabrielcosta.gvcneon.network.SimpleApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NeonModel @Inject constructor(private val api: NeonApi,
    private val tokenManager: TokenManager) {

    fun authenticate(name: String, email: String): LiveData<ApiResponse<Boolean>> {
        val generateToken = api.generateToken(name, email)
        val mutableLiveData = MutableLiveData<ApiResponse<Boolean>>()

        generateToken.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                mutableLiveData.postValue(SimpleApiResponse(t))
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                tokenManager.saveToken(response.body()!!)
                mutableLiveData.postValue(object : ApiResponse<Boolean> {
                    override val body: Boolean
                        get() = response.isSuccessful && response.body() != null
                    override val errorMessage: String?
                        get() = response.errorBody()?.string()
                })
            }
        })


        return mutableLiveData
    }

    fun sendMoney(personVO: PersonVO, value: Double) =
        api.sendMoney(personVO.id, tokenManager.getToken(), value)
}
package neon.gabrielcosta.gvcneon.network

import android.arch.lifecycle.LiveData
import neon.gabrielcosta.gvcneon.entity.dto.SendMoneyDTO
import neon.gabrielcosta.gvcneon.entity.dto.TransferResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NeonApi {

    @GET("GenerateToken")
    fun generateToken(@Query("nome") name: String,
        @Query("email") email: String): Call<String>

    @POST("SendMoney")
    fun sendMoney(request: SendMoneyDTO): LiveData<ApiResponse<Boolean>>

    @GET("GetTransfers")
    fun getTransfers(@Query("token") token: String): LiveData<ApiResponse<TransferResponseDTO>>
}
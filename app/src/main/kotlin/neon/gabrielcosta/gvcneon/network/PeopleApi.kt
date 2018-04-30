package neon.gabrielcosta.gvcneon.network

import android.arch.lifecycle.LiveData
import neon.gabrielcosta.gvcneon.entity.vo.PersonDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleApi {

    @GET("fd97a93351ab24e049d51b0d20be8666")
    fun fetchUsers(@Query("results") size: Int): LiveData<ApiResponse<PersonDTO>>
}
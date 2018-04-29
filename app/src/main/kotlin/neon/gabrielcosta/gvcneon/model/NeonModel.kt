package neon.gabrielcosta.gvcneon.model

import android.arch.lifecycle.LiveData
import neon.gabrielcosta.gvcneon.network.ApiResponse
import neon.gabrielcosta.gvcneon.network.NeonApi
import javax.inject.Inject

class NeonModel @Inject constructor(private val api: NeonApi) {

    fun authenticate(name: String, email: String): LiveData<ApiResponse<String>> {
        return api.generateToken(name, email)
    }

}
package neon.gabrielcosta.gvcneon.network

import retrofit2.Response

class ApiResponse<out T> {

    val body: T?
    val errorMessage: String?

    constructor(throwable: Throwable) {
        body = null
        errorMessage = throwable.message
    }

    constructor(response: Response<T>) {
        if (response.success()) {
        body = response.body()
            errorMessage = null
        } else {
            body = null
            errorMessage = response.errorBody()?.string()
        }
    }

    private fun Response<T>.success() = code() == 200

}
package neon.gabrielcosta.gvcneon

import com.orhanobut.hawk.Hawk

class TokenManager {

    private companion object {
        private const val TOKEN = "TOKEN"
    }

    fun getToken(): String = Hawk.get<String>(TOKEN)

    fun saveToken(token: String) = Hawk.put(TOKEN, token)

    fun hasToken() = Hawk.contains(TOKEN)

}
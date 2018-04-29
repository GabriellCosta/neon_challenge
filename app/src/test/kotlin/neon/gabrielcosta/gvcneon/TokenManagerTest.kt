package neon.gabrielcosta.gvcneon

import com.orhanobut.hawk.Hawk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class TokenManagerTest {

    private val tokenManager = TokenManager()

    @Before
    fun setup() {
        Hawk.init(RuntimeEnvironment.application).build()
    }

    @After
    fun tearDown() {
        Hawk.deleteAll()
    }

    @Test
    fun whenTokenSaved_ManagerShouldSayHasToken() {
        tokenManager.saveToken(TOKEN)

        assertTrue(tokenManager.hasToken())
    }

    @Test
    fun whenTokenNotSaved_ManagerShouldSayNotHasToken() {
        assertFalse(tokenManager.hasToken())
    }

    @Test
    fun whenSavedValidToken_ShouldReturnTrue() {
        assertTrue(tokenManager.saveToken(TOKEN))
    }

    @Test
    fun whenRecoverToken_ShouldBeTheSameSaved() {
        tokenManager.saveToken(TOKEN)

        val result = tokenManager.getToken()
        assertEquals(TOKEN, result)
    }

    private companion object {
        private const val TOKEN = "fca5ea1-43cz-44d7-1234-e6f142eaf4f4"
    }
}
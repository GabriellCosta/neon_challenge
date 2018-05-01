package neon.gabrielcosta.gvcneon.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.dagger.RetrofitModule
import neon.gabrielcosta.readFixture
import neon.gabrielcosta.util.RecyclerViewItemCountAssertion
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PeopleListActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(PeopleListActivity::class.java, false, false)

    private val server = MockWebServer()

    @Before
    fun setup() {
        RetrofitModule.url = server.url("/").toString()
    }

    @Test
    fun whenLoaded_ListShouldHave10items() {
        server.enqueue(MockResponse().setBody(readFixture("people_list.json")))
        rule.launchActivity(null)

        onView(withId(R.id.rv_people))
            .check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun whenLoadedWithoutValues_ShouldShowEmptyState() {
        server.enqueue(MockResponse().setBody(readFixture("empty_people.json")))
        rule.launchActivity(null)

        onView(withId(R.id.text_people_empty_state))
            .check(matches(withText("D = Você não tem ninguem para mandar dinheiro")))
    }
}
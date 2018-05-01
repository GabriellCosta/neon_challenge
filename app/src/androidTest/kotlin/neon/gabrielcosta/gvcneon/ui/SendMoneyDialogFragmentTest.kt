package neon.gabrielcosta.gvcneon.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import neon.gabrielcosta.gvcneon.BaseFragmentTestRule
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SendMoneyDialogFragmentTest {

    @Rule
    @JvmField
    val rule = BaseFragmentTestRule<SendMoneyDialogFragment>()

    @Before
    fun setup() {
        val personVO = PersonVO(NAME, EMAIL, PHONE, IMAGE)
        val instance = SendMoneyDialogFragment.getInstance(personVO)
        rule.launchFragment(instance)
    }

    @Test
    fun shouldShowUserName() {
        onView(withId(R.id.dialog_send_money_name))
            .check(matches(withText(NAME)))
    }

    @Test
    fun shouldShowUserPhone() {
        onView(withId(R.id.dialog_send_money_phone))
            .check(matches(withText(PHONE)))
    }

    @Test
    fun whenValueInput_ShouldBeFormatted() {
        onView(withId(R.id.edt_dialog_send_money))
            .perform(ViewActions.typeText("3345"))
            .check(matches(withText("R$ 33,45")))
    }

    private companion object {
        private const val NAME = "Jon Due"
        private const val EMAIL = "jon@gmail.com"
        private const val PHONE = "jon@gmail.com"
        private const val IMAGE = "jon@gmail.com"
    }

}
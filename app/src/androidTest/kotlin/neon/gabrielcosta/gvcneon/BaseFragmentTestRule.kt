package neon.gabrielcosta.gvcneon

import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment

class BaseFragmentTestRule<in FRAGMENT : Fragment>
    : ActivityTestRule<BaseFragmentTestActivity>(BaseFragmentTestActivity::class.java, true, true) {

    private lateinit var fragment: FRAGMENT
    /**
     * Launch a Fragment in a BaseFragmentTestActivity
     * @param fragment Fragment to be inflated
     */
    fun launchFragment(fragment: FRAGMENT) {
        this@BaseFragmentTestRule.fragment = fragment
        activity.runOnUiThread {
            val manager = activity.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.content_container, fragment, fragment.toString())
            transaction.commit()
        }
    }
}
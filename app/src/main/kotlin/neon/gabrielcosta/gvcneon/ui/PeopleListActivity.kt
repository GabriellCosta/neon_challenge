package neon.gabrielcosta.gvcneon.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO
import neon.gabrielcosta.gvcneon.util.configureDefaultToolbar
import neon.gabrielcosta.gvcneon.vm.PeopleViewModel

class PeopleListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        initViewModel()
        configureDefaultToolbar(R.id.toolbar_people, getString(R.string.poeple_title))
    }

    private fun initViewModel() {
        ViewModelProviders.of(this, factory)
            .get(PeopleViewModel::class.java)
            .fetchPeople()
            .observe(this, Observer {
                if (it!!.body != null) {
                    evaluateValue(it.body?.results!!)
                }
            })
    }

    private fun evaluateValue(personVo: List<PersonVO>) {
        if (personVo.isEmpty()) {
            findViewById<View>(R.id.text_people_empty_state).visibility = View.VISIBLE
        } else {

        }
    }

    private fun initList(personVo: List<PersonVO>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_people)
        recyclerView.adapter = PeopleListAdapter(personVo, View.OnClickListener {
            val childLayoutPosition = recyclerView.getChildLayoutPosition(it)
            val personVO = personVo[childLayoutPosition]
            SendMoneyDialogFragment.getInstance(personVO)
                .show(supportFragmentManager, "TAG")
        })
        val linearLayoutManager = LinearLayoutManager(baseContext)
        recyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(baseContext,
            linearLayoutManager.orientation)
        ContextCompat.getDrawable(baseContext,
            R.drawable.item_decoration_divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
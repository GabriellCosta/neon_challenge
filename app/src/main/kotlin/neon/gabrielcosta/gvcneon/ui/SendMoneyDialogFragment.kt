package neon.gabrielcosta.gvcneon.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.dagger.Injectable
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO

class SendMoneyDialogFragment : DialogFragment(), Injectable {

    private lateinit var personVO: PersonVO

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var input: EditText
    private lateinit var close: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personVO = it.get(EXTRA_PERSON) as PersonVO
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_send_money, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews(view)
        initValues(view)
    }

    private fun findViews(view: View) {
        image = view.findViewById(R.id.dialog_send_money_image)
        name = view.findViewById(R.id.dialog_send_money_name)
        phone = view.findViewById(R.id.dialog_send_money_phone)
        input = view.findViewById(R.id.edt_dialog_send_money)
        close = view.findViewById(R.id.img_dialog_send_money_close)
    }

    private fun initValues(view: View) {
        //Glide.with(view).load(personVO.photo).into(image)
        name.text = personVO.name
        phone.text = personVO.phone

        val valorMonetarioWatcher = ValorMonetarioWatcher.Builder()
            .comSimboloReal()
            .comMantemZerosAoLimpar()
            .build()
        input.addTextChangedListener(valorMonetarioWatcher)

        close.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        private const val EXTRA_PERSON = "EXTRA_PERSON"

        fun getInstance(personVO: PersonVO): SendMoneyDialogFragment {
            val dialog = SendMoneyDialogFragment()

            val args = Bundle()
            args.putParcelable(EXTRA_PERSON, personVO)
            dialog.arguments = args

            return dialog
        }
    }
}
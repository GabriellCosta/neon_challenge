package neon.gabrielcosta.gvcneon.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.concrete.canarinho.formatador.FormatadorValor
import br.com.concrete.canarinho.watcher.ValorMonetarioWatcher
import com.bumptech.glide.Glide
import neon.gabrielcosta.gvcneon.R
import neon.gabrielcosta.gvcneon.dagger.Injectable
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO
import neon.gabrielcosta.gvcneon.vm.SendMoneyViewModel
import javax.inject.Inject

class SendMoneyDialogFragment : DialogFragment(), Injectable {

    private lateinit var personVO: PersonVO

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var input: EditText
    private lateinit var close: View
    private lateinit var button: View

    @Inject
    protected lateinit var factory: ViewModelProvider.Factory

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
        initViewModel()
    }

    private fun findViews(view: View) {
        image = view.findViewById(R.id.dialog_send_money_image)
        name = view.findViewById(R.id.dialog_send_money_name)
        phone = view.findViewById(R.id.dialog_send_money_phone)
        input = view.findViewById(R.id.edt_dialog_send_money)
        close = view.findViewById(R.id.img_dialog_send_money_close)
        button = view.findViewById(R.id.btn_dialog_sedn_money)
    }

    private fun initValues(view: View) {
        Glide.with(view).load(personVO.photo).into(image)
        name.text = personVO.name
        phone.text = personVO.phone

        val valorMonetarioWatcher = ValorMonetarioWatcher.Builder()
            .comSimboloReal()
            .comMantemZerosAoLimpar()
            .build()
        input.addTextChangedListener(valorMonetarioWatcher)
        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //TODO: EXTRACT THIS TO A FUNCTION
                button.isEnabled = s != null && FormatadorValor.VALOR_COM_SIMBOLO.desformata(s.toString()).toDouble() > 0.0
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        close.setOnClickListener {
            dismiss()
        }
    }

    private lateinit var viewModel: SendMoneyViewModel

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, factory)
            .get(SendMoneyViewModel::class.java)

        button.setOnClickListener {
            viewModel.sendMoney(personVO, input.text.toString())
                .observe(this, Observer {
                    //TODO: eXTRACT API RESPONSE LOGIC VALIDATION
                    if (it?.body != null) {
                        Toast.makeText(context, "Enviado com sucesso", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Erro ao enviar dinheiro", Toast.LENGTH_LONG).show()
                    }
                })
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
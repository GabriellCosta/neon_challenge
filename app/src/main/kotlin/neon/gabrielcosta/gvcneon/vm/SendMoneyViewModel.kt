package neon.gabrielcosta.gvcneon.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.canarinho.formatador.FormatadorValor
import neo.gabrielcosta.gvcneon.compilerannotation.InjectViewModel
import neon.gabrielcosta.gvcneon.entity.vo.PersonVO
import neon.gabrielcosta.gvcneon.model.NeonModel
import neon.gabrielcosta.gvcneon.network.ApiResponse
import javax.inject.Inject

@InjectViewModel
class SendMoneyViewModel @Inject constructor(private val neonModel: NeonModel) : ViewModel() {

    fun sendMoney(personVO: PersonVO, value: String): LiveData<ApiResponse<Boolean>> {
        return neonModel.sendMoney(personVO,
            FormatadorValor.VALOR_COM_SIMBOLO.desformata(value).toDouble())
    }

}
package neon.gabrielcosta.gvcneon.vm

import android.arch.lifecycle.ViewModel
import neo.gabrielcosta.gvcneon.compilerannotation.InjectViewModel
import neon.gabrielcosta.gvcneon.model.NeonModel
import javax.inject.Inject

@InjectViewModel
class MainViewModel @Inject constructor(private val neonModel: NeonModel) : ViewModel() {

    fun authenticate(name: String, email: String) = neonModel.authenticate(name, email)

}
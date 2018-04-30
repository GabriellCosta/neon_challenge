package neon.gabrielcosta.gvcneon.vm

import android.arch.lifecycle.ViewModel
import neo.gabrielcosta.gvcneon.compilerannotation.InjectViewModel
import neon.gabrielcosta.gvcneon.network.PeopleApi
import javax.inject.Inject

@InjectViewModel
class PeopleViewModel @Inject constructor(private val peopleApi: PeopleApi) : ViewModel() {

    fun fetchPeople() = peopleApi.fetchUsers(15)

}
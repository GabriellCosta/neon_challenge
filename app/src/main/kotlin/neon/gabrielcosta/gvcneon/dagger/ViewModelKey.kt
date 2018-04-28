package neon.gabrielcosta.gvcneon.dagger

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
@MapKey
internal annotation class ViewModelKey(val clazz: KClass<out ViewModel>)
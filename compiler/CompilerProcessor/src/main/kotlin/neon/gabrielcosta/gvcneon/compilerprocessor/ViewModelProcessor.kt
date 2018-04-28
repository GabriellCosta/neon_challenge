package neon.gabrielcosta.gvcneon.compilerprocessor

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import neo.gabrielcosta.gvcneon.compilerannotation.InjectViewModel
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

@SupportedAnnotationTypes("neo.gabrielcosta.gvcneon.compilerannotation.InjectViewModel")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
class ViewModelProcessor : AbstractProcessor() {

    override fun process(set: MutableSet<out TypeElement>,
        roundEnvironment: RoundEnvironment): Boolean {

        val typeSpec = TypeSpec.classBuilder("ViewModelModule")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .addAnnotation(ClassName.bestGuess("dagger.Module"))

        roundEnvironment.getElementsAnnotatedWith(InjectViewModel::class.java)
            .forEach {
                val build = MethodSpec.methodBuilder("bind${it.simpleName}")
                    .addModifiers(Modifier.ABSTRACT)
                    .addAnnotation(ClassName.bestGuess("dagger.Binds"))
                    .addAnnotation(ClassName.bestGuess("dagger.multibindings.IntoMap"))
                    .addAnnotation(AnnotationSpec.builder(ClassName.bestGuess(
                        "neon.gabrielcosta.gvcneon.dagger.ViewModelKey"))
                        .addMember("clazz", "${it.simpleName}.class").build())
                    .addParameter(ParameterSpec.builder(TypeName.get(it.asType()),
                        it.simpleName.toString()).build())
                    .returns(ClassName.bestGuess(
                        "android.arch.lifecycle.ViewModel"))
                    .build()
                typeSpec.addMethod(build)
            }

        try {
            val newClass = typeSpec.build()
            val javaFile = JavaFile.builder(
                "neon.gabrielcosta.gvc.neon.compiler", newClass).build()

            javaFile.writeTo(System.out)
            javaFile.writeTo(processingEnv.filer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return true
    }
}

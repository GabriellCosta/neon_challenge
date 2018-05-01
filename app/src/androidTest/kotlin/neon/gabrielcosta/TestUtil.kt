package neon.gabrielcosta

import android.support.test.InstrumentationRegistry
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun readFixture(path: String): String {
    val open = InstrumentationRegistry.getContext().assets.open("fixtures/$path")
    return convert(open)
}

private fun convert(inputStream: InputStream): String {
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    bufferedReader.forEachLine { stringBuilder.append(it) }

    return stringBuilder.toString()
}
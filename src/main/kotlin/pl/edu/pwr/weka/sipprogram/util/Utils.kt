package pl.edu.pwr.weka.sipprogram.util

import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 22:39
 */

fun getResourceList(path: String): List<String> {
    val stream = FX::class.java.getResourceAsStream(path) ?: return emptyList()
    return stream.bufferedReader()
        .use { it.readLines() }
}
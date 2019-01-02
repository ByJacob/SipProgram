package pl.edu.pwr.weka.sipprogram.gui.controller

import com.beust.klaxon.Klaxon
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.AnimationCanvasFragment
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 23:52
 */

class ScenariosController : Controller() {
    val scenarios = mutableListOf<AnimationCanvasFragment.ScenariosProperties>()

    val fileName = listOf(
        "001_BASIC_REGISTER.json",
        "002_UPDATE_CONTACT_LIST.json",
        "003_CURRENT_CONTACT_LIST.json",
        "004_UNREGISTRATION.json",
        "101_SESSION_ESTABLISHMENT.json",
        "102_SESSION_ESTABLISHMENT_2_PROXY.json",
        "103_SESSION_ESTABLISHMENT_MULTIPLE_AUTH.json"
    )

    init {
        val resourceList = fileName
            .map { this::class.java.getResourceAsStream("/scenarios/$it") }
            .map { Klaxon().parse<AnimationCanvasFragment.ScenariosProperties>(it) }
        resourceList.forEach { scenariosProperties -> scenariosProperties?.let { scenarios.add(it) } }
    }
}
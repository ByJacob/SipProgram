package pl.edu.pwr.weka.sipprogram.gui.controller

import com.beust.klaxon.Klaxon
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.AnimationCanvasFragment
import pl.edu.pwr.weka.sipprogram.util.getResourceList
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 23:52
 */

class ScenariosController: Controller(){
    val scenarios = mutableListOf<AnimationCanvasFragment.ScenariosProperties>()

    init {
        val resourceList = getResourceList("/scenarios")
            .filter { it.takeLast(4).equals("json") }
            .map { this::class.java.getResourceAsStream("/scenarios/$it") }
            .map { Klaxon().parse<AnimationCanvasFragment.ScenariosProperties>(it) }
        resourceList.forEach { scenariosProperties -> scenariosProperties?.let{scenarios.add(it)} }
    }
}
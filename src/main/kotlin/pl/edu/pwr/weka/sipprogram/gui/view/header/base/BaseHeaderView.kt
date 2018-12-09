package pl.edu.pwr.weka.sipprogram.gui.view.header.base

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.util.SvgIcons
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.11.2018 00:21
 */

abstract class BaseHeaderView(name: String) : View(name) {

    val emptyString = SimpleStringProperty("")

    abstract val controller: BaseHeaderController

    inline fun <reified S : T, reified T : Any> createMaterialTextField(value: ObservableValue<S>,
                                                                        helpTextBind: ObservableValue<out String> = emptyString): HBox {
        return hbox {
            jfxtextfield("") {
                bind(value)
                hboxConstraints {
                    hGrow = Priority.ALWAYS
                }
            }
            svgicon(SvgIcons.helpIcon){
                //visibleWhen { helpTextBind.value.isNotEmpty().observable() }
                //managedWhen { helpTextBind.value.isNotEmpty().observable() }
                tooltip {
                    textProperty().bind(helpTextBind)
                }
            }
        }
    }

}
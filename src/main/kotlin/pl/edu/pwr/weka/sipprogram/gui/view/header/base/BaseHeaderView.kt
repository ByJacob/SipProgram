package pl.edu.pwr.weka.sipprogram.gui.view.header.base

import javafx.beans.property.Property
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.collections.ObservableList
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
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

    inline fun <reified S : T, reified T : Any> createTextFieldWithHelp(value: ObservableValue<S>,
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

    fun <T> createVomboBoxWithHelp(items_arg: ObservableList<T>, bindSelect: Property<T>,
                                   helpTextBind: ObservableValue<out String> = emptyString): HBox {
        return hbox {
            jfxcombobox<T> {
                items = items_arg
                bind(bindSelect)
                maxWidth = Double.MAX_VALUE
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
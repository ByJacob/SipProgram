package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleObjectProperty
import pl.edu.pwr.weka.sipprogram.gui.fx.FormRequestFx
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.09.2018 18:45
 */

class FormRequestModel {
    val formRequestFxObjectProperty = SimpleObjectProperty<FormRequestFx>(FormRequestFx())

    init {
        RequestEnum.values().forEach { formRequestFxObjectProperty.get().requestListProperty.add(it) }
    }
}
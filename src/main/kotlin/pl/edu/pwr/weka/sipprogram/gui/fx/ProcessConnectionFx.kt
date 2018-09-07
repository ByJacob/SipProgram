package pl.edu.pwr.weka.sipprogram.gui.fx

import javafx.beans.property.ListProperty
import javafx.beans.property.MapProperty
import javafx.beans.property.SimpleMapProperty
import javafx.beans.property.StringProperty
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:17
 */
class ProcessConnectionFx{
    val map = SimpleMapProperty<String, StringProperty>()
}
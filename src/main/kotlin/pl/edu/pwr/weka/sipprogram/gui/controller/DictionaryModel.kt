package pl.edu.pwr.weka.sipprogram.gui.controller

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 09.12.2018 13:18
 */

class DictionaryItem(name: String, description: String, urlName: String, url: String,
                     itemType: DictionaryType) {
    constructor(itemType: DictionaryType): this("","","","",itemType)

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val descriptionProperty = SimpleStringProperty(description)
    var description by descriptionProperty

    val urlNameProperty = SimpleStringProperty(urlName)
    var urlName by urlNameProperty

    val urlProperty = SimpleStringProperty(url)
    var url by urlProperty

    val itemTypeProperty = SimpleObjectProperty<DictionaryType>(itemType)
    var itemType by itemTypeProperty

}

class DictionaryItemModel : ItemViewModel<DictionaryItem>() {
    val name = bind(DictionaryItem::nameProperty, true)
    val description = bind(DictionaryItem::descriptionProperty, true)
    val urlName = bind(DictionaryItem::urlNameProperty, true)
    val url = bind(DictionaryItem::urlProperty, true)
    val itemType = bind(DictionaryItem::itemTypeProperty, true)
}


//Use Dictionary.properties
enum class DictionaryType(val humanNameKey: String){
    METHODS("methods"),
    OTHER("other"),
    ISSUES("issues"),
    RESPONSE_CODE("response_code");

    override fun toString(): String{
        return humanNameKey
    }

}
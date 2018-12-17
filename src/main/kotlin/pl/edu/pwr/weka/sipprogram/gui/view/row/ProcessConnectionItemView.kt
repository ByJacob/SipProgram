package pl.edu.pwr.weka.sipprogram.gui.view.row

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.geometry.Pos
import pl.edu.pwr.weka.sipprogram.gui.view.FormRequestFragment
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 15.12.2018 18:22
 */
class ProcessConnectionItemView(private val item: FormRequestFragment? = null) : View() {
    override val root = hbox {
        if (item != null) {
            val iconOne =
                    if (!item.model.isSendingRequest.value)
                        FontAwesomeIconView(FontAwesomeIcon.ARROW_RIGHT)
                    else
                        FontAwesomeIconView(FontAwesomeIcon.ARROW_LEFT)
            iconOne.glyphSize = 13
            val iconTwo = FontAwesomeIconView(FontAwesomeIcon.DESKTOP)
            iconTwo.glyphSize = 13
            hbox {
                style {
                    padding = box(0.px, 10.px, 0.px, 0.px)
                    spacing = 5.px
                    alignment = Pos.CENTER
                }
                add(iconOne)
                add(iconTwo)
            }
            vbox {
                spacing = 1.0
                val size = if (item.model.formRequest.method == RequestEnum.ACK) 12.px else 18.px
                label(item.model.formRequest.method.toString()) {
                    style {
                        fontSize = size
                    }
                }
                if (item.model.formRequest.method == RequestEnum.ACK) {
                    label("${item.model.formRequest.statusCode}(${item.model.formRequest.messageStatusCode})") {
                        style {
                            fontSize = size
                        }
                    }
                }
            }
        }
    }
}
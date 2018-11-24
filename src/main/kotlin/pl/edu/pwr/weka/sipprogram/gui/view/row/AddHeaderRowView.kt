package pl.edu.pwr.weka.sipprogram.gui.view.row

import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import pl.edu.pwr.weka.sipprogram.gui.view.row.header.*
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:29
 */
class AddHeaderRowView : View("AddHeaderRowView") {
    val headersList = FXCollections.observableArrayList(
            HeaderEnum.values().toList().sortedBy { it.headerName })
    override val root = form {
        fieldset("Dodaj nowy") {
            val selectedHeader = SimpleObjectProperty<HeaderEnum>()
            field("Rodzaj nagłówka") {
                jfxcombobox<HeaderEnum> {
                    items = headersList
                    bind(selectedHeader)
                    disableProperty().bind(Bindings.isEmpty(items))
                }
            }
            button("Dodaj") {
                action {
                    selectedHeader.get()?.let {
                        fire(AddNewHeaderToFormEvent(selectedHeader.get(),super.scope))
                        headersList.remove(selectedHeader.get())
                    }
                }
            }
        }
    }

    class AddNewHeaderToFormEvent(val header: HeaderEnum, scope: Scope) : FXEvent(EventBus.RunOn.BackgroundThread, scope)
}

enum class HeaderEnum(val headerName: String, val node: Class<*>) {
    //VIA("Via", HeaderViaRowView::class.java),
    MAX_FORWARDS("Max-Forwards", HeaderMaxForwardsRowView::class.java),
    //FROM("From", HeaderFromRowView::class.java),
    //TO("To", HeaderToRowView::class.java),
    //CALL_ID("Call-ID", HeaderCallIdRowView::class.java),
    //C_SEQ("CSeq", HeaderCSeqRowView::class.java),
    USER_AGENT("User-Agent", HeaderUserAgentRowView::class.java),
    CONTACT("Contact", HeaderContactRowView::class.java),
    ALLOW("Allow", HeaderAllowRowView::class.java),
    SERVER("Server", HeaderServerRowView::class.java),
    SUPPORTED("Supported", HeaderSupportedRowView::class.java),
    WWW_AUTHENTICATE("Authorization", HeaderAuthorizationRowView::class.java),
    EXPIRES("Expires", HeaderExpiresRowView::class.java);

    override fun toString(): String {
        return headerName
    }

}
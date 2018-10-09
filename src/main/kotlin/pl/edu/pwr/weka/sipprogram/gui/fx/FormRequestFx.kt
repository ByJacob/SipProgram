package pl.edu.pwr.weka.sipprogram.gui.fx

import javafx.beans.property.*
import javafx.collections.FXCollections
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.QualityOfProtectionEnum
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.09.2018 18:41
 */
class FormRequestFx {
    val requestListProperty = SimpleListProperty<RequestEnum>(FXCollections.observableArrayList())
    val requestProperty = SimpleObjectProperty<RequestEnum>()
    val localAddressProperty = SimpleStringProperty()
    val localPortProperty = SimpleStringProperty()
    val serverAddressProperty = SimpleStringProperty()
    val serverPortProperty = SimpleStringProperty()
    val userProperty = SimpleStringProperty()
    val passwordProperty = SimpleStringProperty()
    val callIdProperty = SimpleStringProperty()
    val seqNumberProperty = SimpleStringProperty()
    val requestTxtStringProperty = SimpleStringProperty()
    //Auth
    val authorizationEnabledProperty = SimpleBooleanProperty(false)
    val typeAuthorizationListProperty = SimpleListProperty<AuthMethodEnum>(FXCollections.observableArrayList())
    val typeAuthorizationProperty = SimpleObjectProperty<AuthMethodEnum>()
    val realmNameProperty = SimpleStringProperty()
    val algorithmPropertyList = SimpleListProperty<AlgorithmEnum>(FXCollections.observableArrayList())
    val algorithmProperty = SimpleObjectProperty<AlgorithmEnum>()
    val nonceProperty = SimpleStringProperty()
    val qopListProperty = SimpleListProperty<QualityOfProtectionEnum>(FXCollections.observableArrayList())
    val qopsProperty = SimpleListProperty<QualityOfProtectionEnum>(FXCollections.observableArrayList())
}
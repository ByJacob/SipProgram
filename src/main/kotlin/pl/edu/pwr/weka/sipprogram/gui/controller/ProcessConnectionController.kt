package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXListView
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import io.datafx.controller.flow.Flow
import io.datafx.controller.flow.FlowHandler
import io.datafx.controller.flow.action.ActionMethod
import io.datafx.controller.flow.action.ActionTrigger
import io.datafx.controller.flow.context.FXMLViewFlowContext
import io.datafx.controller.flow.context.ViewFlowContext
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseController
import pl.edu.pwr.weka.sipprogram.gui.controller.item.ProcessConnectionItemController
import pl.edu.pwr.weka.sipprogram.gui.model.ProcessConnectionModel
import pl.edu.pwr.weka.sipprogram.sip.SipClient
import pl.edu.pwr.weka.sipprogram.sip.request.base.ResponseListener
import tornadofx.*
import java.util.*
import javax.annotation.PostConstruct
import javax.sip.ResponseEvent

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:01
 */

@ViewController(value = "/fxml/ProcessConnection.fxml", title = "ProcessConnection")
class ProcessConnectionController : BaseController(), ResponseListener {

    @ViewNode
    lateinit var controllerBorderPane: BorderPane

    @ViewNode
    @ActionTrigger("createRequestForm")
    lateinit var requestListAddButton: JFXButton

    @ViewNode
    @ActionTrigger("sendAll")
    lateinit var sendButton: JFXButton

    @ViewNode
    lateinit var requestsListView: JFXListView<StackPane>

    @FXMLViewFlowContext
    lateinit var flowContext: ViewFlowContext
    val sipClient = SipClient()

    val model = ProcessConnectionModel()
    val observableList = FXCollections.observableArrayList<StackPane>()
    val nodeFormFlowList = mutableMapOf<String, FlowHandler>()
    var nodeFormFlowIterator = nodeFormFlowList.iterator()

    @PostConstruct
    fun init() {
        requestsListView.items = observableList
        requestsListView.selectionModel.selectedItemProperty().addListener(ChangeListener { observable, oldValue, newValue ->
            if (nodeFormFlowList.containsKey(newValue.id)) {
                controllerBorderPane.center = nodeFormFlowList[newValue.id]!!.containerProperty.get().view
            }
        })
        sipClient.addListener(this)
    }

    @ActionMethod("createRequestForm")
    fun createRequestForm() {
        val formFlowHandler = Flow(FormRequestController::class.java).createHandler()
        controllerBorderPane.center = formFlowHandler.start()
        observableList.add(prepareStackPaneForCell(formFlowHandler))
        requestsListView.selectionModel.select(observableList.lastIndex)
        requestsListView.scrollTo(observableList.lastIndex)
    }

    @ActionMethod("sendAll")
    fun sendAllRequests() {
        nodeFormFlowIterator = nodeFormFlowList.iterator()
        sendRequest()
    }

    private fun sendRequest() {
        if (nodeFormFlowIterator.hasNext()) {
            val formRequestController = nodeFormFlowIterator.next().value
                    .currentViewContext.controller as FormRequestController
            formRequestController.formRequestModel.request.sendRequest(sipClient)
        }
    }

    override fun processResponse(re: ResponseEvent) {
        sendRequest()
    }

    private fun prepareStackPaneForCell(item: FlowHandler): StackPane? {
        val formController = item.currentViewContext.controller as FormRequestController
        val nodeFlow = Flow(ProcessConnectionItemController::class.java).createHandler()
        val nodeView = nodeFlow.start()
        nodeView.id = UUID.randomUUID().toString()
        nodeFormFlowList[nodeView.id] = item
        val cellController = nodeFlow.currentViewContext.controller as ProcessConnectionItemController
        cellController.primaryText.text = formController.formRequestModel.formRequestFxObjectProperty.get()
                .requestProperty.get().toString()
        formController.formRequestModel.formRequestFxObjectProperty.get().requestProperty
                .addListener(ChangeListener { _, _, newValue -> cellController.primaryText.text = newValue.toString() })

        return nodeView
    }
}
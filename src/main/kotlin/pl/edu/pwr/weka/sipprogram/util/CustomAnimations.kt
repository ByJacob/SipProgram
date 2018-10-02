package pl.edu.pwr.weka.sipprogram.util

import javafx.animation.ScaleTransition
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.util.Duration

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.09.2018 20:33
 */
class CustomAnimations {
    companion object {
        fun setVisibleUndManagedAnimated(isEnable: Boolean, node: Node) {
            if (node.managedProperty().value == isEnable)
                return
            val st = ScaleTransition(Duration(200.0), node)
            if (isEnable) {
                node.managedProperty().value = true
                node.visibleProperty().value = true
                st.fromX = 0.0; st.toX = 1.0
                st.fromY = 0.0; st.toY = 1.0
            } else {
                st.fromX = 1.0; st.toX = 0.0
                st.fromY = 1.0; st.toY = 0.0
                st.onFinished = EventHandler {
                    node.managedProperty().value = false
                    node.visibleProperty().value = false
                }
            }
            st.play()
        }
    }
}
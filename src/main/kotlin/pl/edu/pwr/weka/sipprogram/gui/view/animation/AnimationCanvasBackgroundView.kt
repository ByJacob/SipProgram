package pl.edu.pwr.weka.sipprogram.gui.view.animation

import javafx.scene.image.Image
import javafx.scene.text.Font
import pl.edu.pwr.weka.sipprogram.gui.view.ScenariosView
import pl.edu.pwr.weka.sipprogram.util.CanvasResizable

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 18.12.2018 17:11
 */
class AnimationCanvasBackgroundView(private val properties: List<EndPointProperties>) : CanvasResizable() {

    private val firstLayerNothingY = 0.0
    private val secondLayerTextY = firstLayerNothingY + 10.0
    private val thirtyLayerNothingY = secondLayerTextY + 10.0
    private val fourthLayerIconY = thirtyLayerNothingY + 10.0
    private val fifthLayerNothingY = fourthLayerIconY + 40.0
    private val iconSize = 40.0
    val sixthLayerLineY = fifthLayerNothingY + 5

    override fun draw() {

        val endPointsX = ScenariosView.calculateEndPointsX(properties.size, width)
        val oldLineWidth = gc.lineWidth
        gc.font = Font("Roboto", 12.0)
        gc.lineWidth = 0.5
        gc.strokeText(
            properties[0].name.take(8),
            endPointsX[0] - (iconSize / 2),
            secondLayerTextY,
            iconSize
        )
        gc.lineWidth = oldLineWidth
        val image1 = Image("images/animationIcons/" + properties[0].iconName)
        gc.drawImage(
            image1,
            endPointsX[0] - (iconSize / 2),
            fourthLayerIconY,
            iconSize,
            iconSize
        )
        gc.strokeLine(
            endPointsX[0],
            sixthLayerLineY,
            endPointsX[0],
            height - 10
        )
        gc.lineWidth = 0.5
        gc.strokeText(
            properties.last().name.take(8),
            endPointsX.last() - (iconSize / 2),
            secondLayerTextY,
            iconSize
        )
        gc.lineWidth = oldLineWidth
        val image2 = Image("images/animationIcons/" + properties[properties.lastIndex].iconName)
        gc.drawImage(
            image2,
            endPointsX.last() - (iconSize / 2),
            fourthLayerIconY,
            iconSize,
            iconSize
        )
        gc.strokeLine(
            endPointsX.last(),
            sixthLayerLineY,
            endPointsX.last(),
            height - 10
        )
        when (properties.size) {
            3 -> {
                gc.lineWidth = 0.5
                gc.strokeText(
                    properties[1].name.take(8),
                    endPointsX[1] - (iconSize / 2), secondLayerTextY, 40.0
                )
                gc.lineWidth = oldLineWidth
                val image3 = Image("images/animationIcons/" + properties[1].iconName)
                gc.drawImage(image3, endPointsX[1] - (iconSize / 2), fourthLayerIconY, iconSize, iconSize)
                gc.strokeLine(endPointsX[1], sixthLayerLineY, endPointsX[1], height - 10)
            }

            4 -> {
                gc.lineWidth = 0.5
                gc.strokeText(
                    properties[1].name.take(8),
                    endPointsX[1] - (iconSize / 2), secondLayerTextY, 40.0
                )
                gc.lineWidth = oldLineWidth
                val image3 = Image("images/animationIcons/" + properties[1].iconName)
                gc.drawImage(image3, endPointsX[1] - (iconSize / 2), fourthLayerIconY, iconSize, iconSize)
                gc.strokeLine(endPointsX[1], sixthLayerLineY, endPointsX[1], height - 10)

                gc.lineWidth = 0.5
                gc.strokeText(
                    properties[2].name.take(8),
                    (endPointsX[2]) - (iconSize / 2), secondLayerTextY, 40.0
                )
                gc.lineWidth = oldLineWidth
                val image4 = Image("images/animationIcons/" + properties[2].iconName)
                gc.drawImage(image4, (endPointsX[2]) - (iconSize / 2), fourthLayerIconY, iconSize, iconSize)
                gc.strokeLine(endPointsX[2], sixthLayerLineY, endPointsX[2], height - 10)
            }
        }
    }

    override fun minWidth(height: Double): Double {
        return (properties.size+1) * iconSize
    }

    data class EndPointProperties(val name: String, val iconName: String)
}
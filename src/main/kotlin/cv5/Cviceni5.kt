package cv5

import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.util.converter.NumberStringConverter
import tornadofx.*
import java.text.DecimalFormat

fun main() = launch<Application>()

class Application : App(MainView::class) {
    override fun start(stage: Stage) {
        stage.minHeight = 300.0
        stage.minWidth = 720.0
        super.start(stage)
    }
}

class MainView : View() {

    private val redProperty = SimpleDoubleProperty()

    private val blueProperty = SimpleDoubleProperty()

    private val greenProperty = SimpleDoubleProperty()

    override val root = borderpane {
        top = vbox {
            paddingTop = 8.0
            spacing = 2.0
            createSlider(hbox(), redProperty, "Red")
            createSlider(hbox(), greenProperty, "Green")
            createSlider(hbox(), blueProperty, "Blue")
        }

        val vbox = vbox()
        vbox.background = Color.color(
            redProperty.value / 255,
            greenProperty.value / 255,
            blueProperty.value / 255
        ).asBackground()

        //edit unsafe ale w/e
        redProperty.onChange {
            vbox.background = Color.color(it / 255, greenProperty.value / 255, blueProperty.value / 255)
                .asBackground()
        }

        greenProperty.onChange {
            vbox.background = Color.color(redProperty.value / 255, it / 255, blueProperty.value / 255)
                .asBackground()
        }

        blueProperty.onChange {
            vbox.background = Color.color(redProperty.value / 255, greenProperty.value / 255, it / 255)
                .asBackground()
        }

        center = vbox
    }

    private fun createSlider(layout: HBox, property: SimpleDoubleProperty, type: String) {
        layout.apply {
            alignment = Pos.CENTER
            spacing = 4.0

            val defaultValue = 128.0
            val min = 0.0
            val max = 255.0

            val slider = slider(0, 255)
            val valueLabel = label()

            property.onChange {
                val df = DecimalFormat("#.00")
                val value = df.format(slider.value)
                valueLabel.text = "$type = $value"
            }

            property.bind(slider.valueProperty())
            slider.value = defaultValue
            slider.majorTickUnit = 40.0
            slider.isShowTickMarks = true

            val textField = textfield {
                filterInput {
                    it.controlNewText.isDouble()
                            && it.controlNewText.toDouble() >= min
                            && it.controlNewText.toDouble() <= max
                }
                textProperty().bindBidirectional(slider.valueProperty(), NumberStringConverter())
            }

        }
    }
}
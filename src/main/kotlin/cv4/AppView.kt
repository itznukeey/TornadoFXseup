package cv4

import javafx.geometry.Pos
import javafx.scene.control.ToggleGroup
import tornadofx.*

class AppView : View() {

    private var toggleGroup: ToggleGroup = ToggleGroup()

    override val root = borderpane {
        top = vbox {
            alignment = Pos.BASELINE_CENTER
            menubar {
                menu("File") {
                    item("Download data")
                    item("Update firmware")
                    item("Upload files")
                }
                menu("App") {
                    item("Turn on")
                    item("Turn off")
                }
            }
            label("Current speed: 100 mph")
            label("Rotation: 30deg")
        }

        left = vbox {
            alignment = Pos.CENTER_LEFT
            paddingLeft = 10.0
            button("Move forward").alignment
            button("Move backwards")
            button("Rotate left")
            button("Rotate right")
        }

        center = vbox {
            alignment = Pos.CENTER
            spacing = 8.0
            hbox {
                alignment = Pos.CENTER
                text("Battery [%]: ")
                textfield("20")
            }

            hbox {
                alignment = Pos.CENTER
                text("Distance total [km]: ")
                textfield("230")
            }

            hbox {
                alignment = Pos.CENTER
                text("Time running [min]: ")
                textfield("34")
            }

            hbox {
                alignment = Pos.CENTER
                text("Is running: ")
                textfield("true")
            }

            hbox {
                spacing = 4.0
                alignment = Pos.CENTER
                checkbox("Acoustic").isSelected = true
                checkbox("Optical")
                checkbox("Pressure")
            }

            vbox {
                alignment = Pos.CENTER
                spacing = 4.0

                text("Energy source selected")

                hbox {
                    alignment = Pos.CENTER
                    spacing = 4.0
                    radiobutton("Solar", toggleGroup)
                    radiobutton("Battery", toggleGroup)
                    toggleGroup.selectToggle(radiobutton("RTG", toggleGroup))
                }

            }
        }

        right = vbox {
            paddingRight = 10.0
            alignment = Pos.CENTER_RIGHT
            button("Turn on")
            button("Turn off")
            button("Restart")
        }

        bottom = vbox {
            paddingBottom = 10.0
            textarea("Error while inintiating").apply {
                appendText("\nTry resetting the bot...")
                appendText("\nBot booted successfully")
                appendText("\nInitiating movement")
            }
        }
    }
}
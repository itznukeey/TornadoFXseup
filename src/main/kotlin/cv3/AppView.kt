package cv3

import javafx.application.Platform
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import tornadofx.*

class AppView : View("This title is set to window as well") {

    private var visibilityBtn: Button by singleAssign()

    private var consoleOutputBtn: Button by singleAssign()

    private var windowNameChangeBtn: Button by singleAssign()

    private var addLabelBtn: Button by singleAssign()

    private var hideAllButtonsBtn: Button by singleAssign()

    private var changed = 0

    private var vboxBelowButtons: VBox by singleAssign()

    override val root = vbox {
        visibilityBtn = button("Show all") {
            setOnAction {
                consoleOutputBtn.isVisible = true
                windowNameChangeBtn.isVisible = true
                addLabelBtn.isVisible = true
                hideAllButtonsBtn.isVisible = true
            }
        }

        consoleOutputBtn = button("Console output") {
            setOnAction { println("Output") }
            isVisible = false
        }

        windowNameChangeBtn = button("Change title name") {
            setOnAction {
                changed++
                primaryStage.titleProperty().unbind()
                primaryStage.title = "changed: $changed"
            }
            isVisible = false
        }

        addLabelBtn = button("Add labels") {
            isVisible = false
        }

        hideAllButtonsBtn = button("Hide all buttons") {
            setOnAction {
                consoleOutputBtn.isVisible = false
                windowNameChangeBtn.isVisible = false
                addLabelBtn.isVisible = false
                this.isVisible = false
            }
            isVisible = false
        }

        vboxBelowButtons = vbox()
    }.also {
        //? z nejakyho duvodu se to buguje kdyz to je v inicializaci
        addLabelBtn.onHover {
            vboxBelowButtons += label("sample label")
        }
    }


}
package sample

import javafx.scene.control.TextField
import tornadofx.*

class SampleView : View("Hello Tornado") {

    private var firstNameField: TextField by singleAssign()

    override val root = vbox()

    init {
        root.apply {
            initialize()
        }
    }

    private fun initialize() {
        hbox {
            label("Hey guys")
            firstNameField = textfield()
        }

        hbox {
            label("Another label")
            textfield().also {
                it.textProperty().addListener { _, old, new -> println("$old -> $new") }
            }
        }

        hbox {
            label()
            button("First text").apply {
                action { text = "Second text" }
            }
        }
    }
}
package sample2

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*

class ExampleView : View("My View") {

    //Observer
    private var isSelected = SimpleBooleanProperty()

    private var selectedCity = SimpleStringProperty()

    init {
        selectedCity.addListener { _, old, new ->
            println("Selected city changed from $old to $new")
        }
    }

    private val cities = FXCollections.observableArrayList(
        "Pilsen", "Prague", "Paris", "NewYork", "LA", "London"
    )

    override val root = vbox {

        hbox {
            checkbox(property = isSelected, text = "isSelected") {
                action {
                    println("isSelected = ${this@ExampleView.isSelected.value}")
                }
            }
        }

        hbox {
            combobox(values = cities, property = selectedCity)
        }
    }
}

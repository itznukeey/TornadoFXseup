package sample3.model

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import java.time.LocalDateTime

enum class AirplaneType(val title: String) {
    WARPLANE("warplane"),
    SEAPLANE("seaplane"),
    AIRLINER("airliner"),
    PRIVATEJET("private jet");

    override fun toString() = title
}

class Airplane(name: String, type: AirplaneType, weight: Double, flyDistance: Double, firstStart: LocalDateTime?) {

    val name = SimpleStringProperty(name)

    val type = SimpleObjectProperty(type)

    val weight = SimpleDoubleProperty(weight)

    val flyDistance = SimpleDoubleProperty(flyDistance)

    val firstStart = SimpleObjectProperty(firstStart)

}
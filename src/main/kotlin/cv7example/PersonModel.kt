package cv7example

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.onChange
import java.time.LocalDate


enum class Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

class PersonModel(
    name: String,
    surname: String,
    birthday: LocalDate

) {
    val name = SimpleStringProperty(name)

    val surname = SimpleStringProperty(surname)

    val birthday = SimpleObjectProperty(birthday).apply { onChange { println(it) } }

    val day = SimpleObjectProperty(Day.MONDAY)
}
package cv8

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.compareTo

enum class Sex {
    MALE, FEMALE, OTHER;

    fun getSymbol(): String = when (this) {
        MALE -> "\u2642"
        FEMALE -> "\u2640"
        OTHER -> "\u26B2"
    }
}

class AncestorModel(name: String, sex: Sex) : Comparable<AncestorModel> {

    val name = SimpleStringProperty(name)

    val sex = SimpleObjectProperty(sex)

    override fun compareTo(other: AncestorModel) = name.compareTo(other.name)

    override fun toString(): String {
        return "${name.value} : (${sex.value} ${sex.value.getSymbol()})"
    }
}
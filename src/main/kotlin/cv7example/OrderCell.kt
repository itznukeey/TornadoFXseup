package cv7example

import javafx.scene.control.TableCell
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class OrderCell : TableCell<PersonModel, Int>() {

    override fun updateItem(item: Int?, empty: Boolean) {
        super.updateItem(item, empty)
        text = if (empty) {
            null
        } else {
            "${index + 1}"
        }
        textFill = Color.RED
        font = Font.font("Consolas", FontWeight.BOLD, 18.0)
        // font = Font.font("Consolas",FontWeight.BOLD,3.0)
    }
}
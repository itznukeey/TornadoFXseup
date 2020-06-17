package cv7example

import javafx.scene.control.TableCell

class OrderCell : TableCell<PersonModel, Int>() {

    override fun updateItem(item: Int?, empty: Boolean) {
        super.updateItem(item, empty)
        text = if (empty) {
            null
        } else {
            "${index + 1}"
        }
    }
}
package cv7example

import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.input.KeyCode

class ConsumingTextFieldTableCell<S, T> : TextFieldTableCell<S, T>() {

    init {
        setOnKeyReleased {
            if (it.code == KeyCode.DELETE) {
                it.consume()
            }
        }
    }
}
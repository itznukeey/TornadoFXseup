package cv8

import javafx.scene.control.TextField
import javafx.scene.control.TreeCell
import javafx.scene.input.KeyCode

class AncestorDisplayCell : TreeCell<AncestorModel>() {
    private var editTextField: TextField = TextField().apply {
        setOnKeyReleased {
            if (it.code == KeyCode.ENTER) {
                if (this.text.isBlank()) {
                    cancelEdit()
                } else {
                    item.name.set(text)
                    commitEdit(item)
                }
            } else if (it.code == KeyCode.ESCAPE) {
                cancelEdit()
            }
        }
    }

    override fun startEdit() {
        super.startEdit()

        text = null
        editTextField.text = item.name.value
        graphic = editTextField
    }

    override fun updateItem(item: AncestorModel?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty) {
            text = null
            graphic = null
        } else {
            if (isEditing) {
                editTextField.text = item?.name?.value
                text = null
                graphic = editTextField
            }
            else {
                text = item.toString()
                graphic = null
            }
        }
    }

    override fun cancelEdit() {
        super.cancelEdit()
        text = item.toString()
        graphic = null
    }
}
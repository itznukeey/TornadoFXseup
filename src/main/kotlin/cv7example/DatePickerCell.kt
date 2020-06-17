package cv7example

import javafx.scene.control.DatePicker
import javafx.scene.control.TableCell
import javafx.scene.input.KeyCode
import java.time.LocalDate
import java.util.*

class DatePickerCell : TableCell<PersonModel, LocalDate>() {

    private var datePicker: DatePicker? = null

    //editovani bunky
    override fun startEdit() {
        super.startEdit()
        //nastaveni grafiky
        graphic = datePicker
        text = null
    }

    override fun cancelEdit() {
        super.cancelEdit()
        text = item.toString()
        graphic = null
    }

    override fun updateItem(item: LocalDate?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty) {
            text = null
            graphic = null
        } else {
            if (isEditing) {
                datePicker?.value = item
                text = null
                graphic = datePicker
            } else {
                text = item.toString()
                graphic = null
            }
        }
    }

    init {
        if (datePicker == null) {
            datePicker = createDatePicker()
        }
    }

    private fun createDatePicker() = DatePicker().apply {
        setOnAction {
            if (value != null) {
                commitEdit(value)
            } else {
                it.consume()
                cancelEdit()
            }
        }
        setOnKeyReleased {
            if (it.code == KeyCode.DELETE) {
                it.consume()
            }
        }
    }
}
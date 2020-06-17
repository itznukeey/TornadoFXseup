package cv7example

import javafx.beans.binding.StringBinding
import javafx.scene.control.*
import javafx.scene.control.cell.ComboBoxTableCell
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import tornadofx.*
import java.time.LocalDate

class MainView : View() {

    private val personObservableList = mutableListOf<PersonModel>().asObservable().apply {
        add(PersonModel("Karel", "Gigarandom", LocalDate.of(1993, 12, 2)))
        add(PersonModel("Jan", "Ultrarandom", LocalDate.of(1994, 4, 21)))
        add(PersonModel("Tomas", "Megarandom", LocalDate.of(1992, 12, 12)))
        add(PersonModel("Josef", "Kilorandom", LocalDate.of(1991, 12, 31)))
    }

    override val root = borderpane {
        center = tableview<PersonModel> {
            isEditable = true
            items = personObservableList
            setOnKeyReleased { handleKeyCallback(it, this) }
            selectionModel.selectionMode = SelectionMode.MULTIPLE

            column("Order", Int::class) {
                minWidth = 120.0
                setCellFactory { OrderCell() }
            }

            column("Name", PersonModel::name) {
                minWidth = 120.0
                cellFactory = TextFieldTableCell.forTableColumn()
            }

            column("Surname", PersonModel::surname) {
                minWidth = 120.0
                cellFactory = TextFieldTableCell.forTableColumn()
            }

            column("Date of birth", PersonModel::birthday) {
                minWidth = 120.0
                setCellFactory { DatePickerCell() }
                setOnEditCommit { it.rowValue.birthday.value = it.newValue }
            }

            column("Day", PersonModel::day) {
                minWidth = 120.0
                cellFactory = ComboBoxTableCell.forTableColumn(Day.values().asList().asObservable())
            }


            column("Name and surname", String::class) {
                minWidth = 120.0
                setCellValueFactory {
                    object : StringBinding() {
                        init {
                            //binding aby se updatovalo
                            bind(it.value.name, it.value.surname)
                        }

                        override fun computeValue() = "${it.value.name.value} ${it.value.surname.value}"

                    }
                }
            }
        }
    }

    private fun handleKeyCallback(keyEvent: KeyEvent, tableView: TableView<PersonModel>) {
        if (keyEvent.code != KeyCode.DELETE) {
            return
        }

        val selectedItems = tableView.selectionModel.selectedItems
        if (selectedItems.size == 0) {
            Alert(Alert.AlertType.WARNING).apply {
                title = "No items selected"
                headerText = "Error no items selected"
                contentText = "No items were selected for removal, please select any"
                show()
            }
        } else {
            Alert(Alert.AlertType.CONFIRMATION).apply {
                title = "Confirm"
                headerText = "Please confirm to delete items"
                contentText = "These items will be permanently removed"
                val listView = ListView(selectedItems)
                graphic = listView
                showAndWait().filter { it == ButtonType.OK }.ifPresent {
                    tableView.items.removeAll(selectedItems)
                    tableView.selectionModel.clearSelection()
                }
            }
        }
    }
}
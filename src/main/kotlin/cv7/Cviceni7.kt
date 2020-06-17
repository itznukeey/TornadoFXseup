package cv7

import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.*
import javafx.scene.control.cell.ChoiceBoxTableCell
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage
import tornadofx.*

fun main() = launch<Application>()

class Application : App(MainView::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.minWidth = 640.0
        stage.minHeight = 480.0
    }
}

enum class Style(val title: String) {
    BOLD("bold"),
    ITALIC("italic"),
    NORMAL("normal");

    override fun toString() = title
}

class FontInfoModel(name: String, color: Color, style: Style, sizePts: Int, visibility: Boolean) {
    val name = SimpleStringProperty(name)
    val color = SimpleObjectProperty(color)
    val style = SimpleObjectProperty(style)
    val sizePts = SimpleIntegerProperty(sizePts)
    val connectedInfo = SimpleStringProperty("${this.name.value} ${this.style.value.title}").apply {
        this@FontInfoModel.name.onChange {
            value =
                this@FontInfoModel.name.value + " " + this@FontInfoModel.style.value.title
        }
        this@FontInfoModel.style.onChange {
            value = this@FontInfoModel.name.value + " " + this@FontInfoModel.style.value.title
        }
    }
    val visibility = SimpleBooleanProperty(visibility)

}

class MainView : View() {

    private val stubData = mutableListOf(
        FontInfoModel("Arial", Color.WHITE, Style.BOLD, 12, true),
        FontInfoModel("Dejavu Sans Mono", Color.YELLOW, Style.ITALIC, 13, true),
        FontInfoModel("Jetbrains Mono", Color.DARKBLUE, Style.NORMAL, 17, true),
        FontInfoModel("Consolas", Color.BLACK, Style.NORMAL, 14, false)
    ).asObservable()

    override val root = vbox {
        tableview<FontInfoModel> {
            items = stubData
            isEditable = true
            //nastaveni vyberu
            selectionModel.selectionMode = SelectionMode.MULTIPLE
            column("Name", FontInfoModel::name).apply {
                minWidth = 100.0
                cellFactory = TextFieldTableCell.forTableColumn()
            }

            column("Color", FontInfoModel::color).apply {
                minWidth = 100.0
            }

            column("Style", FontInfoModel::style).apply {
                minWidth = 100.0
                cellFactory = ChoiceBoxTableCell.forTableColumn(Style.values().asList().asObservable())
            }

            column("Size", FontInfoModel::sizePts).apply {
                minWidth = 100.0
            }

            column("Connected Stuff", FontInfoModel::connectedInfo).apply {
                minWidth = 100.0
            }

            column("mix", String::class) {
                setCellValueFactory { object : StringBinding() {
                    //binding, takze se pri editu dilcich objektu updatuje
                    init { super.bind(it.value.name, it.value.sizePts) }
                    override fun computeValue() = "${it.value.name.value} : ${it.value.sizePts.value}"
                } }
            }

            //Nastavi delete klavesou
            setOnKeyReleased {
                if (it.code == KeyCode.DELETE) {
                    deleteSelected(this)
                }
            }
        }

    }

    inner class ItemExampleCell : TableCell<FontInfoModel, FontInfoModel>() {
        override fun updateItem(item: FontInfoModel?, empty: Boolean) {
            super.updateItem(item, empty)

            if (empty || item == null || item.visibility.value == false) {
                text = null
                style = ""
            } else {
                println("Hello")
                text = item.name.value
                textFill = item.color.value
                font = Font("Arial", item.sizePts.value.toDouble())
                text = when (item.style.value) {
                    Style.ITALIC -> "-fx-font-weight: italic"
                    Style.BOLD -> "-fx-font-weight: bold"
                    else -> ""
                }
            }
        }
    }

    private fun deleteSelected(tableView: TableView<FontInfoModel>) {
        val selection = tableView.selectionModel.selectedItems
        if (selection.size == 0) {
            Alert(Alert.AlertType.WARNING).apply {
                title = "Error while deleting items"
                headerText = "No items to delete were selected"
                contentText = "Select items for deleting"
                show()
            }
        } else {
            Alert(Alert.AlertType.CONFIRMATION).apply {
                headerText = "This will delete selected"
                title = "Are you sure?"

                //Nastavi jako grafiku listview s itemy ktery se odstrani
                graphic = ListView(listOf(selection).asObservable()).apply {
                    prefWidth = 300.0
                    prefHeight = 200.0
                }
                showAndWait().filter { it == ButtonType.OK }
                    .ifPresent {
                        tableView.items.removeAll(selection)
                        tableView.selectionModel.clearSelection()
                    }
            }
        }
    }

}

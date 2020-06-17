package cv8

import javafx.collections.ListChangeListener
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View() {

    private val children = mutableListOf<TreeItem<AncestorModel>>().apply {
        add(TreeItem(AncestorModel("Cain", Sex.MALE)).apply {
            children.add(TreeItem(AncestorModel("Enoch", Sex.OTHER)).apply {
                children.add(TreeItem(AncestorModel("Joe", Sex.MALE)))
            })
        })
        add(TreeItem(AncestorModel("Seth", Sex.MALE)))
        add(TreeItem(AncestorModel("Abel", Sex.MALE)).apply {
            add(TreeItem(AncestorModel("Enos", Sex.MALE)))
        })
    }

    override val root = borderpane {
        val treeView = treeview<AncestorModel> {
            isEditable = true
            root = TreeItem(AncestorModel("Eve", Sex.FEMALE)).apply {
                children.addAll(this@MainView.children)
            }
            setCellFactory { AncestorDisplayCell() }
        }

        val infoView = createDetailsView()

        val addForm = createNewChildForm(treeView)

        treeView.selectionModel.selectedItems.addListener { _: ListChangeListener.Change<out TreeItem<AncestorModel?>?>? ->
            infoView.text = getItemText(treeView)
        }

        bottom = infoView
        center = treeView
        right = addForm
    }

    private fun createNewChildForm(treeView: TreeView<AncestorModel>): Form {
        return form {
            spacing = 2.0
            minWidth = 120.0
            var textfield: TextField by singleAssign()
            var combobox: ComboBox<Sex> by singleAssign()
            fieldset("Create new Ancestor") {
                alignment = Pos.CENTER
                field("Name") {
                    textfield = textfield()
                }
                field("Gender") {
                    combobox = combobox {
                        items = Sex.values().toList().asObservable()
                        selectionModel.select(0)
                    }
                }
                field {
                    alignment = Pos.CENTER
                    button("Commit") {
                        setOnAction {
                            val selected = treeView.selectionModel.selectedItem
                            if (textfield.text.isBlank()) {
                                return@setOnAction
                            }

                            val result = TreeItem(AncestorModel(textfield.text, combobox.selectedItem!!))
                            if (selected == null) {
                                if (treeView.root == null) {
                                    treeView.root = result
                                }
                                else {
                                    treeView.root.children.add(result)
                                    treeView.root.isExpanded = true
                                }
                            } else {
                                selected.children.add(result)
                                selected.isExpanded = true
                            }
                        }
                    }
                    button("Delete Selected") {
                        setOnAction {
                            val item = treeView.selectionModel.selectedItem
                            if (item != null) {
                                if (treeView.root == item) {
                                    treeView.root = null
                                }
                                else {
                                    val parent = item.parent
                                    parent.children.remove(item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createDetailsView() = textarea {
        prefColumnCount = 20
        isEditable = false
        BorderPane.setMargin(this, Insets(5.0))
    }

    private fun getItemText(treeView: TreeView<AncestorModel>): String {
        val item = treeView.selectionModel.selectedItem ?: return ""
        val items = mutableListOf<AncestorModel>()

        var currentElement = item.parent
        while (currentElement != null) {
            items.add(currentElement.value)
            currentElement = currentElement.parent
        }
        items.reverse()
        items.add(item.value)

        return items.joinToString("\n") { it.toString() }
    }


}
package cv6

import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.cell.TextFieldListCell
import javafx.stage.Stage
import tornadofx.*

fun main() = launch<Application>()

class Application : App(MainView::class) {
    override fun start(stage: Stage) {
        stage.width = 720.0
        stage.height = 480.0
        super.start(stage)
    }
}

data class UserModel(val name: String, var surname: String, var email: String) {
    companion object {
        private var objectId = 0L
        fun getId(): Long {
            val currentId = objectId
            objectId++
            return currentId
        }
    }

    val id = getId()

    override fun toString(): String = name
}

class MainView : View() {

    private val data = FXCollections.observableArrayList(
        UserModel("Tomas", "Blby", "tomam@seznam"),
        UserModel("Karel", "Spatny", "karl@seznam"),
        UserModel("Josef", "Nefunguje", "jo@seznam"),
        UserModel("Haha", "Funguje", "hahahahaha@seznam")
    )

    override val root = borderpane {

        val listView = listview<UserModel> {
            items.addAll(data)
            isEditable = true
        }

        center = listView

        top = hbox {
            paddingBottom = 8.0
            paddingTop = 8.0
            spacing = 8.0
            alignment = Pos.CENTER
            button("Edit selected")
        }
    }
}
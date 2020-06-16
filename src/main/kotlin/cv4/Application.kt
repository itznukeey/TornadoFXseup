package cv4

import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent
import tornadofx.launch

fun main() = launch<Application>()

class Application : App(AppView::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.minWidth = 800.0
        stage.minHeight = 400.0
    }

    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}
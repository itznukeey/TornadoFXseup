package cv8

import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch

fun main() = launch<TreeViewApplication>()

class TreeViewApplication : App(MainView::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.minWidth = 720.toDouble()
        stage.minHeight = 480.toDouble()
    }
}
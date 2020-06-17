package sample3

import javafx.stage.Stage
import tornadofx.*

fun main() = launch<Application>()

class Application : App(MainView::class) {
    override fun start(stage: Stage) {
        stage.minWidth = 480.0
        stage.minHeight = 320.0
        super.start(stage)
    }
}


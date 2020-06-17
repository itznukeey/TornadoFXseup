package cv7example

import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch
import java.util.*

fun main() = launch<Application>()

class Application : App(MainView::class) {
    override fun start(stage: Stage) {
        Locale.setDefault(Locale.CHINA)
        super.start(stage)
        stage.minWidth = 800.0
        stage.minHeight = 480.0
    }
}
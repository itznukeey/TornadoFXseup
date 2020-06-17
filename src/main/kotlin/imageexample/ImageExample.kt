package imageexample

import javafx.scene.image.Image
import tornadofx.*

fun main() = launch<Application>()

class Application : App(ExampleView::class) {

}

class ExampleView : View() {
    override val root = borderpane {
        center = imageview {
            image = Image("176101.jpg",800.0,600.0,false,true)
        }
    }
}
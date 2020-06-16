package cv3

import javafx.scene.control.Button
import tornadofx.*

class AppView : View("This title is set to window as well") {

    private var buttonHelloWorld: Button by singleAssign()

    private var buttonHelloWorldTwin: Button by singleAssign()

    override val root = flowpane {
        buttonHelloWorld = button("Hello world")
        buttonHelloWorldTwin = button("Say lambda 'Hello World'")

        //Action u buttonu === [Android] setOnClickListener
        buttonHelloWorld.setOnAction { println("Hello world") }
        buttonHelloWorldTwin.action { println("Goodbye world") }

    }


}
package Sample

import tornadofx.View
import tornadofx.button
import tornadofx.vbox

class SampleView : View() {

    override val root = vbox {
        button("Ok boomer")
    }
}
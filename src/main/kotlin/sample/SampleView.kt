package sample

import tornadofx.*

class SampleView : View("Hello Tornado") {

    override val root = vbox {
        label(title)
    }
}
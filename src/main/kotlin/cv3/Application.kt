package cv3

import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent

class Application : App(AppView::class) {

    //Override pro ziskani stage, ktere se pak muze nastavit
    override fun start(stage: Stage) {
        super.start(stage)
    }

    //Nastaveni rozliseni sceny
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}
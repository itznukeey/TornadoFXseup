package sample3

import sample3.model.Airplane
import sample3.model.AirplaneType
import tornadofx.*
import java.time.LocalDateTime
import java.util.*

class MainView : View() {

    private val liveData = mutableListOf<Airplane>().asObservable().apply {
        val random = Random()
        val values = AirplaneType.values()
        for (i in 0..10) {
            val type = values[random.nextInt(values.size)]
            add(
                Airplane(
                    "Airplane $i",
                    type,
                    random.nextDouble() * 1e6,
                    random.nextDouble() * 1e5,
                    if (random.nextDouble() > .5) LocalDateTime.now() else null
                )
            )
        }
    }

    override val root = borderpane {
        center = tableview<Airplane> {
            items = liveData

            column("Name", Airplane::name) {
                minWidth = 120.0
            }

            column("Weight", Airplane::weight) {
                minWidth = 120.0
            }

            column("Type", Airplane::type) {
                minWidth = 120.0
            }

            column("Fly distance", Airplane::flyDistance) {
                minWidth = 120.0
            }


        }
    }
}
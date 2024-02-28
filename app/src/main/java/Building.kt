class Building(
    var name: String,
    var mainsElectric: Boolean,
    var solarElectric: Boolean,
    var mainsGas: Boolean,
    var heatPump: Boolean,
    var mainsWater: Boolean,
    var rainHarvest: Boolean,
    var consumptionTotals: ConsumptionTotals,
    var footprint: Footprint,
    var costTotals: CostTotals
) {
    var aSHP: Boolean = false
    var projectList: buildingProjects = buildingProjects()

    fun increaseElectricConsumption(amount: Double) {
        consumptionTotals.electricConsumption += amount
        costTotals.electricCost += amount * 0.45
    }

    fun decreaseElectricConsumption(amount: Double) {
        consumptionTotals.electricConsumption -= amount
    }

    fun increaseHeatConsumption(amount: Double) {
        consumptionTotals.heatConsumption += amount
    }

    fun decreaseHeatConsumption(amount: Double) {
        consumptionTotals.heatConsumption -= amount
    }

    fun increaseWaterConsumption(amount: Double) {
        consumptionTotals.waterConsumption += amount
    }

    fun increaseElectricCO2(amount: Double) {
        footprint.electricCo2 += amount
    }

    fun increaseHeatingCO2(amount: Double) {
        footprint.heatingCo2 += amount
    }

    fun increaseWaterCO2(amount: Double) {
        footprint.waterCo2 += amount
    }

    fun increaseTotalCO2(amount: Double) {
        footprint.total += amount
    }

    fun increaseElectricCost(amount: Double) {
        costTotals.electricCost += amount
    }
}
data class ConsumptionTotals(
    var electricConsumption: Double,
    var heatConsumption: Double,
    var waterConsumption: Double
)

data class Footprint(
    var electricCo2: Double,
    var heatingCo2: Double,
    var waterCo2: Double,
    var total: Double
)

data class CostTotals(
    var electricCost: Double,
    var heatingCost: Double,
    var waterCost: Double,
    var totalCost: Double
)


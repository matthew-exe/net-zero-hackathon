package com.example.finalattempt

import Building

class University(val buildings: List<Building>) {

    private var totalExpenditure: Double
    private var totalFootprint: Double
    var projectCosts = 0
    var staffHappiness = 0
    var energyCosts = EnergyCosts(0.45, 0.107, 0.37)

    init {
        totalExpenditure = getTotalExpenditure()
        totalFootprint = getTotalFootprint()
    }

    private fun getTotalExpenditure(): Double {
        var retTotal = 0.0
        for (building in buildings) {
            retTotal += building.costTotals.totalCost
        }
        return retTotal
    }

    private fun getTotalFootprint(): Double {
        var retTotal = 0.0
        for (building in buildings) {
            retTotal += building.footprint.total
        }
        return retTotal
    }
}

data class EnergyCosts(
    val electric: Double,
    val water: Double,
    val gas: Double
)

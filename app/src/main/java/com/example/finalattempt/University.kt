package com.example.finalattempt

import Building

class University(val buildings: List<Building>) {

    private var totalExpenditure: Double
    private var totalFootprint: Double

    val baseC02: Double
    val baseCosts: Double
    var totalSavings: Double = 0.0
    var projectCosts: Double = 0.0
    var excpectedReduction: Double = 0.0
    var staffHappiness: Double = 0.0
    var energyCosts = EnergyCosts(0.45, 0.107, 0.37)
    var carbonCosts = EnergyCosts(0.193, 0.180, 0.149)

    init {
        totalExpenditure = getTotalExpenditure()
        totalFootprint = getTotalFootprint()
        baseC02 = getTotalFootprint()
        baseCosts = getTotalExpenditure()

    }

    fun getTotalExpenditure(): Double {
        var retTotal = 0.0
        for (building in buildings) {
            retTotal += building.costTotals.totalCost
        }
        return retTotal
    }
    fun getTotalFootprint(): Double {
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

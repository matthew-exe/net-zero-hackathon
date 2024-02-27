import com.example.finalattempt.University

class Projects {
    fun evChargerInstall (university: University, index:Int){
        university.buildings[index].increaseElectricConsumption(13000.00)
        university.buildings[index].increaseElectricCost(13000.00 * university.energyCosts.electric)
        university.projectCosts += 750
        university.staffHappiness += 10
    }

    fun ledBulbReplacement(university: University, index:Int){
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.004)
        university.projectCosts += 5000
    }

    fun ledFixtureReplacement(university: University, index:Int){
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.07)
        university.projectCosts += 85000
    }

    fun lightingControls(university: University, index:Int){
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.02)
        university.projectCosts += 8000
    }

    fun monitorUpgrade(university: University, index:Int){
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.002)
        university.projectCosts += 200
    }

    fun pumpUpgrade(university: University, index:Int){
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.05)
        university.projectCosts += 40000
    }

    fun serverUpgrade(university: University, index:Int){
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.06)
        university.projectCosts += 15000
    }

    fun boilerUpgrade(university: University, index:Int){
        if(!university.buildings[index].aSHP){
            university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.05)
            university.projectCosts += 15000
        }
    }

    fun wallInsulation(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.06)
        university.projectCosts += 40000
    }

    fun roofInsulation(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.08)
        university.projectCosts += 75000
    }

    fun pipeInsulation(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.01)
        university.projectCosts += 2000
    }

    fun airSourceHeatPump(university: University, index:Int){
        if(university.buildings[index].name == "Dorset House"){
            university.buildings[index].aSHP = true
//            university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.)
            university.projectCosts += 175000
        }
    }

    fun heatVentControlUpgrades(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.05)
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.02)
        university.projectCosts += 35000
    }

    fun glazingUpgrade(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.07)
        university.projectCosts += 250000
    }

    fun engagementCampaign(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.01)
        university.buildings[index].decreaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.02)
        university.projectCosts += 500
    }

    fun makeThemFreeze(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.heatConsumption * 0.06)
        university.projectCosts += 0
    }

    fun outsideLightsOff(university: University, index:Int){
        university.buildings[index].decreaseHeatConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.06)
        university.projectCosts += 0
    }



}


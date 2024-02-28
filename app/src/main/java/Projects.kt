import com.example.finalattempt.University

class Projects {
    fun evChargerInstall (university: University, index:Int, units: Int){
        val oldLoad = (13000 * university.energyCosts.electric) * university.buildings[index].projectList.eVChargerInstall
        university.totalSavings += oldLoad
        val newLoad = (13000 * university.energyCosts.electric) * units
        university.totalSavings -= newLoad
        university.projectCosts += (750 * units).toDouble()
        university.projectCosts -= 750 * university.buildings[index].projectList.eVChargerInstall
        university.staffHappiness += (5 * units).toDouble()
        university.staffHappiness -= (5 * university.buildings[index].projectList.eVChargerInstall).toDouble()
        university.buildings[index].projectList.eVChargerInstall = units
    }

    fun ledBulbReplacement(university: University, index:Int){
        if(!(university.buildings[index].projectList.ledBulbReplacement && university.buildings[index].projectList.ledFixtureReplacement)){
            university.buildings[index].projectList.ledBulbReplacement = true
            university.excpectedReduction += (university.buildings[index].consumptionTotals.electricConsumption * 0.04) * university.carbonCosts.electric
            university.projectCosts += 5000
            university.totalSavings -= (university.buildings[index].consumptionTotals.electricConsumption * 0.04) * university.energyCosts.electric
        }
    }

    fun undoLedBulbReplacement(university: University, index:Int){
//        university.buildings[index].increaseElectricConsumption(university.buildings[index].consumptionTotals.electricConsumption * 0.004)
        university.buildings[index].projectList.ledBulbReplacement = false
        university.excpectedReduction -= (university.buildings[index].consumptionTotals.electricConsumption * 0.04) * university.carbonCosts.electric
        university.projectCosts -= 5000
        university.totalSavings += (university.buildings[index].consumptionTotals.electricConsumption * 0.04) * university.energyCosts.electric
    }

    fun ledFixtureReplacement(university: University, index:Int){
        if(!(university.buildings[index].projectList.ledBulbReplacement && university.buildings[index].projectList.ledFixtureReplacement)){
            university.buildings[index].projectList.ledFixtureReplacement = true
            university.excpectedReduction += (university.buildings[index].consumptionTotals.electricConsumption * 0.07) * university.carbonCosts.electric
            university.projectCosts += 85000
            university.totalSavings += (university.buildings[index].consumptionTotals.electricConsumption * 0.07) * university.energyCosts.electric
        }
    }

    fun undoLedFixtureReplacement(university: University, index:Int){
        university.buildings[index].projectList.ledFixtureReplacement = false
        university.excpectedReduction -= (university.buildings[index].consumptionTotals.electricConsumption * 0.07) * university.carbonCosts.electric
        university.totalSavings -= (university.buildings[index].consumptionTotals.electricConsumption * 0.07) * university.energyCosts.electric
        university.projectCosts -= 85000
    }

    fun lightingControls(university: University, index:Int){
        university.buildings[index].projectList.lightControls = true
        university.excpectedReduction += (university.buildings[index].consumptionTotals.electricConsumption * 0.02) * university.carbonCosts.electric
        university.projectCosts += 8000
        university.totalSavings += (university.buildings[index].consumptionTotals.electricConsumption * 0.02) * university.energyCosts.electric
    }

    fun undoLightingControls(university: University, index:Int){
        university.buildings[index].projectList.lightControls = false
        university.excpectedReduction -= (university.buildings[index].consumptionTotals.electricConsumption * 0.02) * university.carbonCosts.electric
        university.projectCosts -= 8000
        university.totalSavings -= (university.buildings[index].consumptionTotals.electricConsumption * 0.02) * university.energyCosts.electric
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


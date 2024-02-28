package com.example.finalattempt

import Building
import ConsumptionTotals
import CostTotals
import Footprint
import Projects
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var pooleGatewayBtn: Button
    private lateinit var dorsetHouseBtn: Button
    private lateinit var kimmeridgeBtn: Button
    private lateinit var fusionBtn: Button
    private lateinit var university: University
    private lateinit var projects: Projects
    val buildingIndexList = listOf("Poole Gateway Building","Dorset House","Kimmeridge House","Fusion Building")
    private lateinit var currentCo2: TextView
    private lateinit var currentCost: TextView
    private lateinit var projectCosts: TextView
    private lateinit var co2Reduction: TextView
    private lateinit var staffHappiness: TextView

    private var pooleGateway = Building(
        "Poole Gateway Building",
        true,
        true,
        true,
        false,
        false,
        false,
        ConsumptionTotals(707810.29, 535457.36, 0.0),
        Footprint(136607.39, 96382.30, 0.0, 232989.71),
        CostTotals(318514.63, 57293.94, 0.0, 375808.57))
    private var dorsetHouse = Building(
        "Dorset House",
        true,
        false,
        true,
        true,
        false,
        false,
        ConsumptionTotals(266511.0, 342284.42, 1201.6),
        Footprint(51436.62, 61611.20, 179.04, 113226.86),
        CostTotals(119929.95, 36624.43, 444.59, 156998.97))
    var kimmeridgeHouse = Building(
        "Kimmeridge House",
        true,
        true,
        true,
        false,
        false,
        false,
        ConsumptionTotals(126365.5, 21420.3, 203.49),
        Footprint(24388.54, 3855.65, 30.32, 28274.52),
        CostTotals(56864.48, 2291.97, 75.29, 59231.74))
    var fusionBuilding = Building(
        "Fusion Building",
        true,
        true,
        true,
        true,
        true,
        true,
        ConsumptionTotals(582220.43, 151366.34, 252576.0),
        Footprint(112368.43, 27245.95, 37633.82, 177248.31),
        CostTotals(261999.19, 16196.2, 93453.12, 371648.51))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        projects = Projects()
        university = University(listOf(pooleGateway, dorsetHouse, kimmeridgeHouse, fusionBuilding))

        projects.evChargerInstall(university, 0)

        pooleGatewayBtn = findViewById(R.id.pooleGatewayBtn)
        dorsetHouseBtn = findViewById(R.id.dorsetHouseBtn)
        kimmeridgeBtn = findViewById(R.id.kimmeridgeBtn)
        fusionBtn = findViewById(R.id.fusionBtn)

        currentCo2 = findViewById(R.id.currentCo2Levels)
        currentCo2.text = "Current CO2: " + university.baseC02
        currentCost = findViewById(R.id.currentFinancialCosts)
        currentCost.text = "Current Financials: " + university.baseCosts
        projectCosts = findViewById(R.id.projectCosts)
        projectCosts.text = "Project Costs: " + university.projectCosts
        co2Reduction = findViewById(R.id.co2Reduction)
        co2Reduction.text = "Estimated cO2 Reduction: " + university.excpectedReduction
        staffHappiness = findViewById(R.id.staffHappiness)
        staffHappiness.text = "Staff Happiness: " + university.staffHappiness

        pooleGatewayBtn.setOnClickListener{
            openBuildingModal(pooleGateway)
        }
        dorsetHouseBtn.setOnClickListener{
            openBuildingModal(dorsetHouse)
        }
        kimmeridgeBtn.setOnClickListener{
            openBuildingModal(kimmeridgeHouse)
        }
        fusionBtn.setOnClickListener{
            openBuildingModal(fusionBuilding)
        }

    }

    private fun openBuildingModal(building: Building) {
        val dialogView = layoutInflater.inflate(R.layout.building_modal, null)
        setCheckBoxes()
        val closeBtn = dialogView.findViewById<Button>(R.id.closeBtn)

        val buildingModalTitle = dialogView.findViewById<TextView>(R.id.buildingModalTitle)
        buildingModalTitle.text = building.name

        val electricConsumptionHolder = dialogView.findViewById<TextView>(R.id.electricConsumptionTotal)
        electricConsumptionHolder.text = "Electric Consumption: " + building.consumptionTotals.electricConsumption.toString() + " KWH"

        val waterConsumptionHolder = dialogView.findViewById<TextView>(R.id.waterConsumptionTotal)
        waterConsumptionHolder.text = "Water Consumption: " + building.consumptionTotals.waterConsumption.toString()  + " M(3)"

        val heatConsumptionHolder = dialogView.findViewById<TextView>(R.id.heatConsumptionTotal)
        heatConsumptionHolder.text = "Gas Consumption: " + building.consumptionTotals.heatConsumption.toString() + " KWH"

        val electricFootprint = dialogView.findViewById<TextView>(R.id.electricFootprint)
        electricFootprint.text = "Electric: " + building.footprint.electricCo2.toString()

        val heatFootprint = dialogView.findViewById<TextView>(R.id.heatFootprint)
        heatFootprint.text = "Gas: " + building.footprint.heatingCo2.toString()

        val waterFootprint = dialogView.findViewById<TextView>(R.id.waterFootprint)
        waterFootprint.text = "Water: " + building.footprint.waterCo2.toString()

        val totalFootprint = dialogView.findViewById<TextView>(R.id.totalFootprint)
        totalFootprint.text = "Total C02: " + building.footprint.total.toString()

        val electricCostHolder = dialogView.findViewById<TextView>(R.id.electricCost)
        electricCostHolder.text = "Electric: " + building.costTotals.electricCost.toString()

        val heatCostHolder = dialogView.findViewById<TextView>(R.id.heatCost)
        heatCostHolder.text = "Gas: " + building.costTotals.heatingCost.toString()

        val waterCostHolder = dialogView.findViewById<TextView>(R.id.waterCost)
        waterCostHolder.text = "Water: " + building.costTotals.waterCost.toString()

        val totalCostHolder = dialogView.findViewById<TextView>(R.id.totalCost)
        totalCostHolder.text = "Total Expenditure: " + building.costTotals.totalCost.toString()

        var ledBulbReplacement = dialogView.findViewById<CheckBox>(R.id.ledBulbCheckBox)
        var ledFixtures = dialogView.findViewById<CheckBox>(R.id.ledFixturesCheckBox)
        if(building.projectList.ledBulbReplacement){
            ledFixtures.isEnabled = false
        }
        if(building.projectList.ledFixtureReplacement){
            ledBulbReplacement.isEnabled = false
        }

        ledBulbReplacement.isChecked = building.projectList.ledBulbReplacement
        ledFixtures.isChecked = building.projectList.ledFixtureReplacement

        ledBulbReplacement.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.ledBulbReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledFixtures.isEnabled = false
            } else {
                projects.undoLedBulbReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledFixtures.isEnabled = true
            }
        }

        ledFixtures.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.ledFixtureReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledBulbReplacement.isEnabled = false
            } else {
                projects.undoLedFixtureReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledBulbReplacement.isEnabled = true
            }
        }


        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
        
        val alertDialog = dialogBuilder.show()

        closeBtn.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    fun setCheckBoxes(){
        val dialogView = layoutInflater.inflate(R.layout.building_modal, null)

        var ledBulbReplacement = dialogView.findViewById<CheckBox>(R.id.ledBulbCheckBox)


        var ledFixtures = dialogView.findViewById<CheckBox>(R.id.ledFixturesCheckBox)

    }

    private fun updateStats(){
        projectCosts = findViewById(R.id.projectCosts)
        projectCosts.text = "Project Costs: " + university.projectCosts
        co2Reduction = findViewById(R.id.co2Reduction)
        co2Reduction.text = "Estimated cO2 Reduction: " + university.excpectedReduction
        staffHappiness = findViewById(R.id.staffHappiness)
        staffHappiness.text = "Staff Happiness: " + university.staffHappiness
    }

}
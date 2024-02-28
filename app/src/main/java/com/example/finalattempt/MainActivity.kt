package com.example.finalattempt

import Building
import ConsumptionTotals
import CostTotals
import Footprint
import Projects
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
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
    private val buildingIndexList = listOf("Poole Gateway Building","Dorset House","Kimmeridge House","Fusion Building")
    private lateinit var currentCo2: TextView
    private lateinit var currentCost: TextView
    private lateinit var projectCosts: TextView
    private lateinit var co2Reduction: TextView
    private lateinit var staffHappiness: TextView
    private lateinit var totalSavings: TextView

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
        CostTotals(318514.63, 57293.94, 0.0, 375808.57),
        876)
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
        CostTotals(119929.95, 36624.43, 444.59, 156998.97),
        8)
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
        CostTotals(56864.48, 2291.97, 75.29, 59231.74),452)
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
        CostTotals(261999.19, 16196.2, 93453.12, 371648.51),
        300)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        projects = Projects()
        university = University(listOf(pooleGateway, dorsetHouse, kimmeridgeHouse, fusionBuilding))

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
        totalSavings = findViewById(R.id.savingsTotal)
        totalSavings.text = "Total Savings: " + university.totalSavings

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


        var ledBulbReplacementCheckBox = dialogView.findViewById<CheckBox>(R.id.ledBulbCheckBox)
        var ledFixturesCheckBox = dialogView.findViewById<CheckBox>(R.id.ledFixturesCheckBox)
        var lightingControlsCheckBox = dialogView.findViewById<CheckBox>(R.id.lightControlCheckbox)
        var computerUpgradeCheckbox = dialogView.findViewById<CheckBox>(R.id.computerUpgradeCheckbox)
        var pumpUpgradeCheckbox = dialogView.findViewById<CheckBox>(R.id.pumpUpgradeCheckbox)
        var ashpUpgradeCheckbox = dialogView.findViewById<CheckBox>(R.id.ashpUpgradeCheckbox)

        if(building.projectList.ledBulbReplacement){
            ledFixturesCheckBox.isEnabled = false
        } else if(building.projectList.ledFixtureReplacement){
            ledBulbReplacementCheckBox.isEnabled = false
        }

        if(building.name == "Dorset House"){
            ashpUpgradeCheckbox.isEnabled = true
        } else {
            ashpUpgradeCheckbox.isEnabled = false
        }

        ledBulbReplacementCheckBox.isChecked = building.projectList.ledBulbReplacement
        ledFixturesCheckBox.isChecked = building.projectList.ledFixtureReplacement
        lightingControlsCheckBox.isChecked = building.projectList.lightControls
        computerUpgradeCheckbox.isChecked = building.projectList.computerUpgrades
        pumpUpgradeCheckbox.isChecked = building.projectList.pumpUpgrades
        ashpUpgradeCheckbox.isChecked = building.projectList.ashp

        ledBulbReplacementCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.ledBulbReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledFixturesCheckBox.isEnabled = false
            } else {
                projects.undoLedBulbReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledFixturesCheckBox.isEnabled = true
            }
        }
        ledFixturesCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.ledFixtureReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledBulbReplacementCheckBox.isEnabled = false
            } else {
                projects.undoLedFixtureReplacement(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
                ledBulbReplacementCheckBox.isEnabled = true
            }
        }
        lightingControlsCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.lightingControls(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            } else {
                projects.undoLightingControls(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            }
        }
        val numberSpinner = dialogView.findViewById<Spinner>(R.id.numberSpinner)
        val numbers = (0..10).map { it.toString() }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numbers)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        numberSpinner.adapter = adapter
        numberSpinner.setSelection(building.projectList.eVChargerInstall)
        numberSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedNumber = parent.getItemAtPosition(position).toString().toInt()
                projects.evChargerInstall(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text), selectedNumber)
                updateStats()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        // Monitor Upgrades!
        computerUpgradeCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.monitorUpgrade(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            } else {
                projects.undoMonitorUpgrade(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            }
        }
        // Pump Upgrace
        pumpUpgradeCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.pumpUpgrade(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            } else {
                projects.undoPumpUpgrade(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            }
        }

        // Airsource Heatpump Upgrade - DORSET HOUSE ONLY
        ashpUpgradeCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                projects.airSourceHeatPump(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                updateStats()
            } else {
                projects.undoAirSourceHeatPump(university, buildingIndexList.indexOf(dialogView.findViewById<TextView>(R.id.buildingModalTitle).text))
                dodgeASHPBug()
                updateStats()

            }
        }

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
        
        val alertDialog = dialogBuilder.show()

        closeBtn.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun updateStats(){
        projectCosts.text = "Project Costs: " + university.projectCosts
        co2Reduction.text = "Estimated cO2 Reduction: " + university.excpectedReduction
        staffHappiness.text = "Staff Happiness: " + university.staffHappiness
        totalSavings.text = "Total Savings: " + university.totalSavings
    }

    private fun dodgeASHPBug(){
        if(university.totalSavings < 0 && university.totalSavings > -13){
            university.totalSavings = 0.0
        }
        if(university.excpectedReduction < 0 && university.excpectedReduction > -13){
            university.excpectedReduction = 0.0
        }
    }
}
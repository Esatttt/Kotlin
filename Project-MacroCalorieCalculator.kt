package com.example.kotlinders

var continueprogram: Boolean = true

var dailyfoodlist: MutableMap<String, Int> = mutableMapOf()

data class foodprofile(val karbfor100: Int, val proteinfor100: Int, val fatfor100: Int)



val foodDB: MutableMap<String, foodprofile> = mutableMapOf()


fun dailyadd() {
    println("What did you eat?")
    var food = readln().lowercase()
    println("How much grams of it?")
    var gram = readln().toInt()
    if(dailyfoodlist[food] != null){
        dailyfoodlist[food] = dailyfoodlist[food]!! + gram
    }
    else{
        dailyfoodlist[food] = gram}
}

fun dbadd(){
    println("What is the food's name?")
    var Name = readln().lowercase()
    println("How much karbs for 100 grams?")
    var Karb: Int = readln().toInt()
    println("How much protein for 100 grams?")
    var Protein: Int = readln().toInt()
    println("How much fat for 100 grams?")
    var Fat: Int = readln().toInt()

    var slot = foodprofile(Karb, Protein, Fat)

    foodDB[Name] = slot
}

fun showcalories() {
    var totalcarb: Int = 0
    var totalprotein: Int = 0
    var totalfat: Int = 0

    for (food in dailyfoodlist.keys) {
        var gramage = dailyfoodlist[food]
        println("--$gramage grams $food--")
        val gram = dailyfoodlist[food]
        val profile = foodDB[food]
        if (gram != null && profile != null) {
            totalcarb += (dailyfoodlist[food]!! * foodDB[food]!!.karbfor100 / 100)
            totalprotein += (dailyfoodlist[food]!! * foodDB[food]!!.proteinfor100 / 100)
            totalfat += (dailyfoodlist[food]!! * foodDB[food]!!.fatfor100 / 100)
        }
    }
    var totalcalories: Number = 4 * totalcarb + 4 * totalprotein + 4 * totalfat
    println("$totalcarb grams of carb, $totalprotein grams of protein and $totalfat grams of fat.")
    println("$totalcalories calories")
}

fun shutdown(){
    continueprogram = false
}

fun main(){
    println("Welcome to MacroMaster Terminal")
    while(continueprogram == true){
        println("Press (1+Enter) for add food to your daily food list from database")
        println("Press (2+Enter) to add food to your database")
        println("Press (3+Enter) to show macros and calories for today")
        println("Press (0+Enter) to shutdown the program")
        println("")
        var userinput = readln().toInt()
        if (userinput == 1){
            dailyadd()
        }
        else if(userinput == 2){
            dbadd()
        }
        else if(userinput == 3){
            showcalories()
        }
        else if (userinput == 0){
            shutdown()
        }
        else{
            println("Wrong Input!")

        }

    }








}






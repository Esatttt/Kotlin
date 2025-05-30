import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.time.LocalDate

fun String.isDigitsOnlyCustom(): Boolean = this.all { it.isDigit() }
//Default Kotlin Android is digit is is broken. So I write to ChatGPT and it created this basic fun.


val gson = Gson()

//New version for the macro counter with JSON implentetion.
//I learned how to save datas JSON, I'll try

data class Food (val name: String, val protein: Int, val karb: Int, val fat: Int){
    val calorie = 9*fat + 4*karb + 4* protein }

//Writing a map with custom class keys is a bad thing with JSON.
//So I built a different arch for solve this.

var dailyfood: MutableMap<String,Int> = mutableMapOf() //We'll save this
var database: MutableList<Food> = mutableListOf()

var databasestrings: MutableSet<String> = database.map { it.name }.toMutableSet() //Declared as set to block mess

val file1 = File("Daily") //   File
val file2 = File("Database") //    System
val file3 = File("Lastdate")
//I prefer moduler archıtecture for much cleaner code.
fun savedata(){
    //I prefer moduler archıtecture for much cleaner code.
    file1.writeText(gson.toJson(dailyfood))
    file2.writeText(gson.toJson(database))
    file3.writeText(gson.toJson(LocalDate.now().toString()))
    println("Saving to: ${file1.absolutePath}")
    println("Saving to: ${file2.absolutePath}")
    println("Saving to: ${file3.absolutePath}")
}
fun loaddata(){
    val dailyfoodToken = object: TypeToken<Map<String,Int>>(){}.type
    val databaseToken = object: TypeToken<List<Food>>(){}.type
    val lastdateToken = object : TypeToken<String>() {}.type

    if (file1.exists() && file3.exists()){
        if(LocalDate.now().toString() == gson.fromJson(file3.readText(), lastdateToken))
        dailyfood = gson.fromJson(file1.readText(),dailyfoodToken) }
    if(file2.exists()){
        database = gson.fromJson(file2.readText(),databaseToken) } }
fun databaseadd(){
    var name = ""
    var protein = ""
    var karb = ""
    var fat = ""
    while (name.isEmpty() || !protein.isDigitsOnlyCustom() || !karb.isDigitsOnlyCustom() || !fat.isDigitsOnlyCustom()){ //For null safety
        println("What is the foods name? / Cancel for exit")
        name = readln().lowercase()
        if (name == "cancel"){return}
        println("How much proteins for 100 grams? ")
        protein = readln()
        println("How much karbs for 100 grams? ")
        karb = readln()
        println("How much fat for 100 grams? ")
        fat = readln()
        if(!name.isEmpty() && protein.isDigitsOnlyCustom() && karb.isDigitsOnlyCustom() && fat.isDigitsOnlyCustom()){ //For null safety
            database.add(Food(name, protein.toInt(), karb.toInt(),fat.toInt()))
             databasestrings.add(name)
            return }
        println("Please enter valid name and only digit text for makros! ") } }
fun dailyfoodadd(){
    showdatabase()
    println("Enter name of the food: / Cancel for exit ")
    var foodname = readln().lowercase()
    if (foodname == "cancel"){return}
    while (!(foodname in databasestrings)) {
        println("Please enter a name that database contains / Read 'Cancel' for cancel ")
        foodname = readln().lowercase()
        if (foodname == "cancel") {
            return
        }
    }
    println("Enter how much grams of it: ")
    var gram = readln()
    while (!gram.isDigitsOnlyCustom()){
        println("Please enter only numbers! ")
        gram = readln()
    }
    if (dailyfood[foodname] == null){
        dailyfood[foodname] = gram.toInt()
    }
    else if(dailyfood[foodname] != null){
        dailyfood[foodname] = dailyfood[foodname]!! + gram.toInt()
    }
    if (dailyfood[foodname]!! < 0){dailyfood[foodname] = 0} }
fun showdatabase(){
    for (food in database){
        println(food.name+ ", " + food.protein.toString() + " of protein, " +
                food.karb.toString() + " of karbs, " + food.fat.toString() + " of fats for 100 grams")
    }
}
fun saveandexit(){
    cont = false
    savedata() }
fun macroshow(){
    var dprotein = 0
    var dkarb = 0
    var dfat = 0
    for ((nutri,gram) in dailyfood){
        val a: Food? = database.find { it.name == nutri }
        dprotein = dprotein + a!!.protein*gram/100
        dkarb = dkarb + a.karb*gram/100
        dfat = dfat + a.fat*gram/100
    }

    println(dprotein.toString() +" of protein")
    println(dkarb.toString()+ " of karb")
    println(dfat.toString()+ " of fat")
    println((4*dprotein + 4*dkarb + 9*dfat).toString() + " calories ")

}
fun dailyclear(){
    dailyfood.clear()
}
fun databaseclear(){
    println("Are you sure want to clear the database? Y/N" )
    var answer = readln().lowercase()
    if (answer == "y"){
    database.clear()}
}
var cont = true
fun main(){
    println("Welcome to the macro calculator")
    loaddata()
    while (cont){
        macroshow()
        println("1 - Add to daily food list")
        println("2 - Add to food database")
        println("3 - To clear daily foods")
        println("4 - Save and exit")
        println("99 - To clear database")
        println("Enter the number: ")
        var order: String = readln()
        while (order != "1" && order != "2" && order != "3" && order != "4" && order != "99"){
            println("Please enter only (1,2,3,4)")
            order = readln()
        }
        if (order == "1"){
            dailyfoodadd()
            macroshow()
        }
        if (order == "2"){
            databaseadd()
        }
        if (order == "3"){
            dailyclear()
        }
        if(order == "4"){
            saveandexit()
        }
        if(order == "99"){
            databaseclear()
        }
    }
}





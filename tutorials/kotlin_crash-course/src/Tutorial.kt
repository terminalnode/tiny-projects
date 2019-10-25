import java.lang.NumberFormatException
import OOP.*

/*Just playing along with the Kotlin Crash Course for Java Developers
* by Coding Master Programming Tutorials:
* https://www.youtube.com/watch?v=pXdY1B-KVJg
*
* This file is mostly stuff from the tutorial, but can be used as a short introduction by itself I guess.*/

fun main() {
    // var declares a mutable variable
    // This time we declare the variable type explicitly as String.
    var greeting : String = "howdy"
    greeting = "hello"

    // val declares an immutable value
    // The value type in this case is not stated explicitly,
    // it's inferred as String at compile time.
    val name = "Earth"
    // name = "Mars"    // impossible, name is not mutable.

    // Strings can be defined in a similar manner to Python f-strings
    // or (perhaps more accurately) shellscript expressions using
    // either $var_name or ${expression} (or ${var_name} but why bother?)
    println("${greeting.capitalize()}, $name")
    
    // Let's make a multi-line string as well just for fun.
    // Make sure to have a linebreak after the first three quotation marks to get the indentation trim right.
    // Whether the last three are on an empty line or on the last line makes no difference.
    val stringReplacements = "string replacements"
    println("""
        Hey there
        this is a single string
        no + characters....
        no bullshit.
        Just....
        STRING
        with ${stringReplacements.toUpperCase()} and everything!
        """.trimIndent())

    // Variables and values are not nullable by default. To make them nullable
    // we must declare the type explicitly and add a question mark after the type.
    var nullableString : String?
    nullableString = null
    println("\nnullableString is: $nullableString")
    nullableString = "not null"
    println("nullableString is: $nullableString")

    // Let's create an array and print it.
    // We need to use brackets here or it will not read the full arr[n] expression.
    // Instead it will be interpreted as System.out.println(arr + "[n]") or whatever.
    val arr = arrayOf(1, 2, 3)
    println("\nInteger array: ${arr[0]}, ${arr[2]}, ${arr[1]}")

    // arrayOf will create an object array, however Kotlin
    // also has support for arrays of primitive datatypes.
    val intArr : IntArray = intArrayOf(0,1,1,2,3,5,8,13,21)
    println("int array: ${intArr[0]}, ${intArr[1]}, ${intArr[2]}, ${intArr[3]}, ${intArr[4]}, ${intArr[5]} [...]")

    // Conditional statement and try-catch
    // if-statements in kotlin are expressions, so we can use them to assign values.
    println("\nPlease tell me your age: ")
    try {
        // Uncomment if you prefer to write shit every time the program runs
        // val age = Integer.valueOf(readLine()) // no stupid scanner
        val age = 30
        val canAdult = if (age >= 18) {
            "You're an adult!"
        } else if (age > 5) {
            "You're but a child!"
        } else {
            "You're a toddler!"
        }
        println(canAdult)

        // Simple if-statement variant:
        // if (age >= 18) {
        //     println("You're an adult!")
        // } else {
        //     println("You're but a child!")
        // }
    } catch (e: NumberFormatException) {
        println("Such childish behaviour, you're definitely not an adult.")
    }

    // when-statements
    println("\nWhat day is today?")
    // Uncomment if you want to write values n shit.
    // val day = readLine()
    val day = "Monday"
    val weekday = when (day.toString().toLowerCase()) { // toString to allow for nullable values, otherwise compile-time error
        // when can match multiple values per line
        "monday", "tuesday" -> "insert Monday+Tuesday-value here"
        // or just single values
        "wednesday" -> "insert Wednesday-value here"
        "thursday" -> "insert Thursday-value here"
        "friday" -> "insert Friday-value here"
        "saturday" -> "insert Saturday-value here"
        "sunday" -> "insert Sunday-value here"
        else -> "that's not a correct weekday!"
    }
    println(weekday)

    // we can also use when to execute operations.
    when (day.toString().toLowerCase()) {
        "monday" ->
            println("What a great day! Friggin' Monday!")
        else ->
            println("What a shitty day! Friggin' ${day}.")
    }

    // when can also be used without any parameter, like this:
    when {
        day.toString().toLowerCase() == "monday" ->
            println("Can confirm, it is Friggin' Monday.")
        else ->
            println("It is a shitty day innit?")
    }
    println()
    
    // let's try a list and a map
    val list = listOf<String>("Kotlin", "Java", "Haskell", "Python")
    val map = mapOf(1 to list[0], 2 to list[1], 3 to list[2], 4 to list[3])
    
    // and some for-each-loops
    // first we'll loop the int array
    for (a in intArr) {
        print("$a, ")
    }

    println()
    // now let's loop our list
    for (a in list) {
        print("$a, ")
    }

    println()
    // aaaand let's loop our map
    for ((key, value) in map) {
        println("$key => $value")
    }
    // now let's loop through a range
    // for (i in 1 until 10) { // 1 inclusive 10 exclusive
    // for (i in 1 .. 10) {    // 1 inclusive 10 inclusive
    // for (i in 10 .. 1) {    // Does not work
    // for (i in 10, 9 .. 1) { // No you beautiful haskellite
    for (i in 10 downTo 1) {   // 10 inclusive 1 inclusive
        print("$i, ")
    }
    println()
    
    // Let's extend the String-class with a new public method
    // This is not possible in Java, but it's very possible in Kotlin.
    fun String.getEmotion(): String {
        return when {
            // last() is a Kotlin thing for getting the last character in a string.
            // Note that this is a class method we're extending String with,
            // and last() is also such an extended function put on top of String.
            // Thus we can call "this", and indeed a "this" is implied when we use
            // other class methods.
            this.last() == '!' -> "Exciting!"
            last() == '?' -> "Curious"
            last() == '.' -> "Calm"
            else -> "Unknown"
        }
    }
    var s = "uhhhh?"
    println("\ns:              $s")
    println("s.getEmotion(): ${s.getEmotion()}")
    s = "uhhh#"
    println("\ns:              $s")
    println("s.getEmotion(): ${s.getEmotion()}")
    // Pretty OP. In fact most of Kotlin is defined as extensions like this.
    // That's why there's no "Kotlin JDK", Kotlin is more or less just Java with Kotlin extension functions.

    // Let's do some OOP
    val p1 = Person()
    // Kotlin automatically creates getters for us, we're not actually
    // accessing the attributes directly even though it might look like it.
    println("\nMy name is ${p1.firstName} ${p1.lastName} and I'm ${p1.age} years old.")
    // setters are also created automatically when applicable.
    p1.age = 91                     // age is a var, not a val. so it's mutable.
    // p1.lastName = "Lollerino"    // lastName is a val, so it's immutable

    // We can pass values into our default constructor using either positional or named arguments.
    // Positional arguments start with the first argument and end with the last argument or the first named argument,
    // i.e. after you start inputting named arguments you can't go back to positional arguments. Makes sense.
    val p2 = Person("Peter", "Jackson", 45)
    val p3 = Person(lastName = "Chomsky", firstName = "Noam", age = 45)
    val p4 = Person("Chris", age = 10, lastName = "Lollerino")

    val p12 = Person(12)
    println("\nMy name is ${p12.firstName} ${p12.lastName} and I'm ${p12.age} years old.")
    println("p12's ID is ${p12.id}")

    val e1 = Employee("Pyttemjuk")
    println("\ne1's id is: ${e1.id}")
    e1.wearClothes()
    val s1 = Student("Newton Kompetensutveckling")
    println("\ns1's id is ${s1.id}")
    s1.wearClothes()
}
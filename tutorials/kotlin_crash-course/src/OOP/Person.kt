package OOP

/*
A java-ish way of defining a class with some default attributes.
This does work, it's just not very Kotlin-y.

class Person {
    val firstName: String = "John"
    val lastName : String = "Smith"
    var age: Int = 19
}
*/

// This is a more Kotlin-y way to do this.
// The constructor keyword isn't strictly necessary if there are no method declarations.

// By default classes in Kotlin are final and can not be inherited from. To be able to
// inherit from this class we will declare it as open. For most classes this is not necessary.

// If we'd prefer to have our class as an abstract class we could change the keyword open to abstract.
// Abstract classes and values are automatically open, however abstract values can not be initialized.
open class Person constructor(val firstName: String = "John",
                         val lastName: String = "Smith",
                         var age: Int = 19) {
    // We get getter and setter methods automatically, but we can also create custom getters and setters if we want to.
    val fullName: String
        get() = "$firstName $lastName"

    // We can also create secondary constructors.
    // Alternative constructors must call the default constructor, hence `this()`
    constructor(year: Int) : this() {
        age = year
        // Note that the primary constructor *is* being called, age is just being changed to something new.
        // We could've also incremented the default age of 19 like this:
        // age += year

        // As such, firstName can not be reassigned since it's immutable.
        // firstName = "Johan"
    }

    // Unlike Java, primary constructors can not run any code inside of it for whatever reason.
    // To achieve this we create an init method. Let's make an ID that combines first and last name with age.

    // We can have multiple init methods in a single class if we want to, however it's important to note that
    // that init blocks are part of the primary constructor. In this example it means that id will be using the
    // default age of 19 even if the secondary constuctor above is called, because the primary constructor runs
    // before the secondary one, and this init block is part of the primary constructor.
    // Hence order of execution is:
    // 1. Primary constructor
    // 2. init
    // 3. Secondary constructor
    // We will declare it as open which will make it not final and thus overrideable in subclasses.
    // Same as with the class, if we wanted to make this abstract instead we can change open for abstract
    open val id : String
    init {
        id = "$lastName-$firstName-$age"
    }
}

// The class Employee inherits from the class Person.
// Note that we would not be able to inherit from Person if we hadn't declared Person to be `open`.

// We will also implement the PersonActions interface we've created in this folder.
// To do multiple inheritance we simply separate all of our inheritances by commas as demonstrated here.
class Employee(company: String) : Person(), PersonActions {
    override fun wearClothes() {
        println("$fullName wears suit and tie.")
    }

    // Subclasses can alter immutable vals from the superclass by using the override keyword, IF these are open.
    override val id = "$lastName-$firstName-$company-$age"
}

class Student(school: String) : Person(), PersonActions {
    override fun wearClothes() {
        println("$fullName wears whatever the hell they want.")
    }

    // Let's override the ID for this one as well.
    override val id = "$lastName-$firstName-student-at-$school-$age"
}

[Naming](#naming)
+ [Classes & Objects](#classes-objects)
+ [Interfaces & Implementations](#interfaces-implementations)
+ [Functions](#functions)
+ [Constants](#constants)
+ [Non-constants](#non-constants)
+ [Backing Properties](#backing-properties)
+ [Android Component Naming Conventions](#android-component-naming-conventions)
+ [Resource Naming Convention](#resource-naming-convention)

[Formatting](#formatting)
+ [Braces](#braces)
  + [Non-empty blocks](#non-empty-blocks)
  + [Empty blocks](#empty-blocks)
+ [Indentation](#indentation)
+ [Line wrapping](#line-wrapping)
  + [Where to break](#where-to-break)
    + [Class header](#class-header)
    + [Functions](#functions)
    + [Expression functions](#expression-functions)
    + [Properties](#properties)
+ [Whitespace](#whitespace)
  + [Vertical](#vertical)
  + [Horizontal](#horizontal)
+ [Class layout](#class-layout)
+ [Enums](#enums)
+ [Annotations](#annotations)
+ [Implicit return/property types](#implicit-return/property-types)
+ [Using strings](#using-strings)
+ [Documentation](#documentation)
  + [Formatting](#formatting)
    + [Paragraphs](#paragraphs)
    + [Block tags](#block-tags)

[Unit Tests](#unit-tests)
+ [Method Naming](#method-naming)
+ [Use static imports for common library methods](#use-static-imports-for-common-library-methods)
+ [Favour AssertJ style asserts](#favour-AssertJ-style-asserts)
+ [Variable Naming](#variable-naming)
+ [Use a Set up method rather than init](#use-a-Set-up-method-rather-than-init)

[XML style rules](#xml-style-rules)
+ [Use self closing tags](#use-self-closing-tags)
+ [ID naming](#id-naming)

[Miscellanious](#miscellanious)
+ [Comments](#comments)
+ [Favour early return statements over nested conditions](#favour-early-return-statements-over-nested-conditions)

# Naming

## Classes & Objects

Class names are written in PascalCase and are typically nouns or noun phrases. For example, `Character` or `ImmutableList`. Interface names may also be nouns or noun phrases (for example, `List`), but may sometimes be adjectives or adjective phrases instead (for example `Readable`).

Test classes are named starting with the name of the class they are testing, and ending with Test. For example, `HashTest` or `HashIntegrationTest`.

## Interfaces & Implementations

Interfaces should follow Class naming conventions and should not use any prefixes e.g `IList`.

Implementation classes should use semantics to describe the implementation specifics and also avoid the use of prefixes and suffixes.

e.g for the `List Interface`, implementation classes would be `ArrayList`, `LinkedList` etc.

In scenarios where an implementation class does not add implementation specifics and there is a 1on1 mapping with an interface, the suffix `Impl` can be used.

## Functions
Functions as well as their parameters should be named following camelCase and are typically verbs or verb phrases. Underscores should be used on test functions only to separate logical components of the name.

Example:

```kotlin
fun processOrder(customerId: Int) { /* ... */ }
```

## Constants
Constant names use `UPPER_SNAKE_CASE`: all uppercase letters, with words separated by underscores. But what is a constant, exactly?

Constants are `val` properties with no custom get function, whose contents are deeply immutable, and whose functions have no detectable side-effects. This includes immutable types and immutable collections of immutable types as well as scalars and string if marked as `const`. If any of an instance’s observable state can change, it is not a constant. Merely intending to never mutate the object is not enough.

Example:

```kotlin
const val NUMBER = 5
val NAMES = listOf("Alice", "Bob")
val AGES = mapOf("Alice" to 35, "Bob" to 32)
val COMMA_JOINER = Joiner.on(',') // Joiner is immutable
val EMPTY_ARRAY = arrayOf()
```
These names are typically nouns or noun phrases.

Constant values can only be defined inside of an object or as a top-level declaration. Values otherwise meeting the requirement of a constant but defined inside of a class must use a non-constant name.

Constants which are scalar values must use the const modifier.

## Non-constants
Non-constant names are written in `camelCase`. These apply to instance properties, local properties, and parameter names.

Example:

```kotlin
val variable = "var"
val nonConstScalar = "non-const"
val mutableCollection: MutableSet = HashSet()
val mutableElements = listOf(mutableInstance)
val mutableValues = mapOf("Alice" to mutableInstance, "Bob" to mutableInstance2)
val logger = Logger.getLogger(MyClass::class.java.name)
val nonEmptyArray = arrayOf("these", "can", "change")
```

Sometimes there is more than one reasonable way to convert an English phrase into camel case, such as when acronyms or unusual constructs like "IPv6" or "iOS" are present. To improve predictability, Google Style specifies the following (nearly) deterministic scheme.

Beginning with the prose form of the name:

1. Convert the phrase to plain ASCII and remove any apostrophes. For example, "Müller's algorithm" might become "Muellers algorithm".
2. Divide this result into words, splitting on spaces and any remaining punctuation (typically hyphens).
   Recommended: if any word already has a conventional camel-case appearance in common usage, split this into its constituent parts (e.g., "AdWords" becomes "ad words"). Note that a word such as "iOS" is not really in camel case per se; it defies any convention, so this recommendation does not apply.
3. Now lowercase everything (including acronyms), then do one of the following: 
Uppercase the first character of each word to yield pascal case.
Uppercase the first character of each word except the first to yield camel case.
4. Finally, join all the words into a single identifier.
Note that the casing of the original words is almost entirely disregarded. Examples:

__BAD:__

```kotlin
XMLHTTPRequest
URL: String? 
findPostByID
```
__GOOD:__

```kotlin
XmlHttpRequest
url: String
findPostById
```
## Backing Properties
When a backing property is needed, its name should exactly match that of the real property except prefixed with an underscore.

Example:
```kotlin
private var _table: Map? = null
 
val table: Map
    get() {
        if (_table == null) {
            _table = HashMap()
        }
        return _table ?: throw AssertionError()
    }
```

## Android Component Naming Conventions
For classes that extend an Android component, the name of the class should end with the name of the component; for example: SignInActivity, SignInFragment, ImageUploaderService, ChangePasswordDialog, ItemAdapter, ItemViewHolder.

Read more here: https://jeroenmols.com/blog/2016/03/07/resourcenaming/

## Resource Naming Convention
Resource IDs and names are written in snake_case.

# Formatting

## Braces
Conditional statements are always required to be enclosed with braces, irrespective of the number of lines required.

__BAD:__

```kotlin
if (someTest)
  doSomething()
if (someTest) doSomethingElse()
```

__GOOD:__

```kotlin
if (someTest) {
  doSomething()
}
if (someTest) { doSomethingElse() }
```
### Non-empty blocks
Braces follow the Kernighan and Ritchie style ("Egyptian brackets") for nonempty blocks and block-like constructs:

*No line break before the opening brace.
*Line break after the opening brace.
*Line break before the closing brace.
*Line break after the closing brace, only if that brace terminates a statement or terminates the body of a function, constructor, or named class. For example, there is no line break after the brace if it is followed by else or a comma.


Example:
```kotlin
return Runnable {
    while (condition()) {
        foo()
    }
}
 
return object : MyClass() {
    override fun foo() {
        if (condition()) {
            try {
                something()
            } catch (e: ProblemException) {
                recover()
            }
        } else if (otherCondition()) {
            somethingElse()
        } else {
            lastThing()
        }
    }
}
```

### Empty blocks
An empty block or block-like construct must be in K&R style.

Example:
```kotlin
try {
    doSomething()
} catch (e: Exception) {} // WRONG!
 
try {
    doSomething()
} catch (e: Exception) {
} // Okay
```
## Indentation

Each time a new block or block-like construct is opened, the indent increases by four spaces. When the block ends, the indent returns to the previous indent level. The indent level applies to both code and comments throughout the block.

## Line wrapping
Code has a column limit of 140 characters. Except as noted below, any line that would exceed this limit must be line-wrapped, as explained below.

Exceptions:

- Lines where obeying the column limit is not possible (for example, a long URL in KDoc)
- package and import statements
- Command lines in a comment that may be cut-and-pasted into a shell

### Where to break
The prime directive of line-wrapping is: prefer to break at a higher syntactic level. Also:

- When a line is broken at a non-assignment operator the break comes before the symbol.
  - This also applies to the following “operator-like” symbols:
  - The dot separator (.).
  - The two colons of a member reference (::).
- When a line is broken at an assignment operator the break comes after the symbol.
- A method or constructor name stays attached to the open parenthesis (() that follows it.
- A comma (,) stays attached to the token that precedes it.
- A lambda arrow (->) stays attached to the argument list that precedes it.

#### Class header
Classes with a few primary constructor parameters can be written in a single line:

Example:
```kotlin
class Person(id: Int, name: String)
```

Classes with longer headers should be formatted so that each primary constructor parameter is in a separate line with indentation. Also, the closing parenthesis should be on a new line. If we use inheritance, then the superclass constructor call or list of implemented interfaces should be located on the same line as the parenthesis:

Example:
```kotlin
class Person(
 id: Int,
 name: String,
 surname: String
) : Human(id, name) { /*...*/ }
```

For multiple interfaces, the superclass constructor call should be located first and then each interface should be located in a different line:

Example:
```kotlin
class Person(
 id: Int,
 name: String,
 surname: String
) : Human(id, name),
 KotlinMaker { /*...*/ }
 ```
For classes with a long supertype list, put a line break after the colon and align all supertype names horizontally:

Example:
```kotlin
class MyFavouriteVeryLongClassHolder :
 MyLongHolder<MyFavouriteVeryLongClass>(),
 SomeOtherInterface,
 AndAnotherOne {
 
 fun foo() { /*...*/ }
}
```
To clearly separate the class header and body when the class header is long, put a blank line following the class header (as in the example above).

#### Functions
When a function signature does not fit on a single line, break each parameter declaration onto its own line. Parameters defined in this format should use a single indent (+4). The closing parenthesis ()) and return type are placed on their own line with no additional indent.

Example:
```kotlin
fun <T> Iterable<T>.joinToString(
    separator: CharSequence = ", ",
    prefix: CharSequence = "",
    postfix: CharSequence = ""
): String {
    // …
}
```

#### Expression functions
When a function contains only a single expression it can be represented as an expression function.

Example:
```kotlin
override fun toString(): String {
    return "Hey"
}

override fun toString(): String = "Hey"
```
The only time an expression function should wrap to multiple lines is when it opens a block.

Example:
```kotlin
fun main() = runBlocking {
  // …
}
```
Otherwise, if an expression function grows to require wrapping, use a normal function body, a return declaration, and normal expression wrapping rules instead.

#### Properties
When a property initializer does not fit on a single line, break after the equals sign (=) and use an indent.

Example:
```kotlin
private val defaultCharset: Charset? =
        EncodingRegistry.getInstance().getDefaultCharsetForPropertiesFiles(file)
```
Properties declaring a get and/or set function should place each on their own line with a normal indent (+4). Format them using the same rules as functions.

Example:
```kotlin
var directory: File? = null
    set(value) {
        // …
    }
```
Read-only properties can use a shorter syntax which fits on a single line. 
```kotlin 
val defaultExtension: String get() = "kt"
```
## Whitespace
### Vertical
A single blank line appears:

- Between consecutive members of a class: properties, constructors, functions, nested classes, etc.
 - Exception: A blank line between two consecutive properties (having no other code between them) is optional. Such blank lines are used as needed to create logical groupings of properties and associate properties with their backing property, if present.
 - Exception: Blank lines between enum constants are covered below.
- Between statements, as needed to organize the code into logical subsections.
- Optionally before the first statement in a function, before the first member of a class, or after the last member of a class (neither encouraged nor discouraged).
Multiple consecutive blank lines are not encouraged or ever required.

### Horizontal
Beyond where required by the language or other style rules, and apart from literals, comments, and KDoc, a single ASCII space also appears in the following places only:

Separating any reserved word, such as if, for, or catch from an open parenthesis (() that follows it on that line.

Example:
```kotlin
// WRONG!
 
for(i in 0..1) {
}
 
// Okay
for (i in 0..1) {
}
```

Separating any reserved word, such as else or catch, from a closing curly brace (}) that precedes it on that line.

Example:
```kotlin
// WRONG!
}else {
}
 
// Okay
} else {
}
```
Before any open curly brace ({).

Example:
```kotlin
// WRONG!
if (list.isEmpty()){
}
 
// Okay
if (list.isEmpty()) {
}
```
On both sides of any binary operator.

Example:
```kotlin
// WRONG!
val two = 1+1
 
// Okay
val two = 1 + 1
This also applies to the following “operator-like” symbols:
```
the arrow in a lambda expression (->).

Example:
```
// WRONG!
ints.map { value->value.toString() }
 
// Okay
ints.map { value -> value.toString() }
```
But not:

the two colons (::) of a member reference.

Example:
```kotlin
// WRONG!
val toString = Any :: toString
 
// Okay
val toString = Any::toString
the dot separator (.).
```
Example:
```kotlin
// WRONG
it . toString()
 
// Okay
it.toString()
```
the range operator (..).

Example:
```kotlin
// WRONG
for (i in 1 .. 4) print(i)
  
// Okay
for (i in 1..4) print(i)
```
Before a colon (:) only if used in a class declaration for specifying a base class or interfaces, or when used in a where clause for generic constraints.

Example:
```kotlin
// WRONG!
class Foo: Runnable
 
// Okay
class Foo : Runnable
 
// WRONG
fun <T: Comparable> max(a: T, b: T)
 
// Okay
fun <T : Comparable> max(a: T, b: T)
 
// WRONG
fun <T> max(a: T, b: T) where T: Comparable<T>
 
// Okay
fun <T> max(a: T, b: T) where T : Comparable<T>
After a comma (,) or colon (:).
```

Example:
```kotlin
// WRONG!
val oneAndTwo = listOf(1,2)
 
// Okay
val oneAndTwo = listOf(1, 2)
 
// WRONG!
class Foo :Runnable
 
// Okay
class Foo : Runnable
On both sides of the double slash (//) that begins an end-of-line comment. Here, multiple spaces are allowed, but not required.
```

Example:
```kotlin
// WRONG!
var debugging = false//disabled by default
 
// Okay
var debugging = false // disabled by default
```
This rule is never interpreted as requiring or forbidding additional space at the start or end of a line; it addresses only interior space.

## Class layout
Generally, the contents of a class is sorted in the following order:
Property declarations and initializer blocks
Secondary constructors
Method declarations
Companion object

Put nested classes next to the code that uses those classes. If the classes are intended to be used externally and aren't referenced inside the class, put them in the end, after the companion object.

## Enums
An enum with no functions and no documentation on its constants may optionally be formatted as a single line.

Example:
```kotlin
enum class Answer { YES, NO, MAYBE }
```
When the constants in an enum are placed on separate lines, a blank line is not required between them except in the case where they define a body.

Example:
```kotlin
enum class Answer {
    YES,
    NO,
    MAYBE {
        override fun toString() = """¯\_(ツ)_/¯"""
    }
}
```
Since enum classes are classes, all other rules for formatting classes apply.

## Annotations
Member or type annotations are placed on separate lines immediately prior to the annotated construct.

Example:
```kotlin
@Retention(SOURCE)
@Target(FUNCTION, PROPERTY_SETTER, FIELD)
annotation class Global
```
Annotations without arguments can be placed on a single line.

Example:
```kotlin
@JvmField @Volatile
var disposable: Disposable? = null
```
When only a single annotation without arguments is present, it may be placed on the same line as the declaration.

Example:
```kotlin
@Volatile var disposable: Disposable? = null
 
@Test fun selectAll() {
    // …
}
```

## Implicit return/property types
If an expression function body or a property initializer is a scalar value or the return type can be clearly inferred from the body then it can be omitted.

Example:
```kotlin
override fun toString(): String = "Hey"
// becomes
override fun toString() = "Hey"

private val icon: Icon = IconLoader.getIcon("/icons/kotlin.png")
// becomes
private val icon = IconLoader.getIcon("/icons/kotlin.png")
```
When writing a library, retain the explicit type declaration when it is part of the public API.

## Using strings
Prefer using string templates to string concatenation.

Example:
```kotlin
var a = 1
// simple name in template:
val s1 = "a is $a"
 
// Prefer this
a = 2
// arbitrary expression in template:
val s2 = "${s1.replace("is", "was")}, but now is $a
```

## Documentation
Note: for guidance on when to use comments please see the comments section below

### Formatting
The basic formatting of KDoc blocks is seen in this example:

Example:
```kotlin
/**
 * Multiple lines of KDoc text are written here,
 * wrapped normally…
 */
fun method(arg: String) {
    // …
}
```
...or in this single-line example:

Example:
```kotlin
/** An especially short bit of KDoc. */
```
The basic form is always acceptable. The single-line form may be substituted when the entirety of the KDoc block (including comment markers) can fit on a single line. Note that this only applies when there are no block tags such as @return.

#### Paragraphs
One blank line—that is, a line containing only the aligned leading asterisk (*)—appears between paragraphs, and before the group of block tags if present.

#### Block tags
Any of the standard “block tags” that are used appear in the order `@constructor`, `@receiver`, `@param`, `@property`, `@return`, `@throws`, `@see`, and these never appear with an empty description. When a block tag doesn’t fit on a single line, continuation lines are indented 4 spaces from the position of the @.

# Unit Tests

## Method Naming
Write method names in plain english inside backticks.

Follow the convention of

 {expectedAction} when {condition} 
For example:
```
@Test
fun `correctly populates CustomerPolicyDataModel when response is successful `() {
    // test code
}
```
Use static imports for common library methods
Use static imports for JUnit and AssertJ asserts.

## Favour AssertJ style asserts over standard JUnit
For example use:
```kotlin
assertThat(policyDataModel?.contributions).isNotNull()
```
Rather than:

``assertNotNull(policyDataModel?.contributions)``

## Variable Naming
Name variables that are mocks with the suffix "Mock" and spies with the suffix "Spy" so that it is clear in the code when mocked objects are being used.

For example:
```kotlin
private val dataRepositoryMock = mockk<DataRepository>()
private val analyticsEventTrackerSpy = spyk<IAnalyticsEventTracker>()
```

## Use a Set up method rather than init
In order to remain consistent with a common testing idiom use a setUp method with the Before annotation rather than init.
```kotlin
@Before
fun setUp() {
    aboutPensionViewModel = AboutPensionViewModel(dataRepositoryMock, analyticsEventTrackerMock)
}
```
To avoid force unwrapping the test subject throughout the test class in this scenario use lateinit.
```kotlin
private lateinit var aboutPensionViewModel: AboutPensionViewModel
```
# XML style rules
Please ensure all xml files you create start with:
 ``<?xml version="1.0" encoding="utf-8"?>``
Use self closing tags
When an XML element doesn't have any contents, you must use self closing tags.

This is good:
```
<TextView
	android:id="@+id/profileTextViewName"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content" />
```
This is bad :
```
<!-- Don\'t do this! -->
<TextView
    android:id="@+id/text_view_profile"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" >
</TextView>
```
## ID naming
IDs should be named in `camelCase`. The reason we have chosen camel case is because we need consistency between IDs across iOS & Android.  

All IDs should be prefixed with the screen or component name and suffixed with the element name. For example: splashScreenSpinnerProgressBar.

Image view example:
```
<ImageView
    android:id="@+id/profileImageView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
Menu example:
```
<menu>
	<item
        android:id="@+id/mainMenuDone"
        android:title="Done" />
</menu>
```
# Miscellaneous

## Comments
Use comments only to explain what you cannot make the code itself explain. 

"Nothing can be quite so helpful as a well-placed comment. Nothing can clutter up a module more than frivolous dogmatic comments. Nothing can be quite so damaging as an old crufty comment that propagates lies and misinformation." - Robert C Marting in Clean Code

__Bad__

```// Check to see if the employee is eligible for full benefits if ((employee.flags & HOURLY_FLAG) && (employee.age > 65))```

__Better__

```kotlin
if (employee.isEligibleForFullBenefits())
```
## Favour early return statements over nested conditions

Instead of:
```kotlin
fun someMethod(input: Int) {
    if (input > 0) {
        // several
        // lines
        // of
        // nested
        // code
    }
}
```
Prefer:
```kotlin
fun someMethod(input: Int) {
    if (input <= 0) return
     
    // several
    // lines
    // of
    // nested
    // code
}
```
Using guard conditions like this avoids unnecessary branching / nesting, and makes the actual intent of the code clearer.
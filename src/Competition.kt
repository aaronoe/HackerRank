import java.util.*

fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)

    val chapterCount = scanner.nextInt()
    val hoursLeft = scanner.nextInt()

    val chapters = arrayListOf<Int>()
    (0 until chapterCount).forEach {
        chapters.add(scanner.nextInt())
    }

    chapters.sort()

    var count = 0
    var acc = 0
    chapters.forEach {
        if (acc + it <= hoursLeft) {
            acc += it
            count++
        } else {
            println(count)
            return
        }
    }
    println(count)
}
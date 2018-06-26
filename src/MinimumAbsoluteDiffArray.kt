import java.util.*

fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)
    val length = scanner.nextInt()

    val input = arrayListOf<Int>()
    (0 until length).forEach {
        input.add(scanner.nextInt())
    }

    var minDiff = Int.MAX_VALUE

    input.sort()

    input.forEachIndexed { index, value ->
        if (index != 0) {
            (Math.abs(value - input[index - 1])).let {
                minDiff = Math.min(minDiff, it)
            }
        }
    }

    println(minDiff)
}
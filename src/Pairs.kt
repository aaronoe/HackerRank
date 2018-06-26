import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val k = scanner.nextInt()

    val set = mutableSetOf<Int>()
    (0 until n).forEach { set.add(scanner.nextInt()) }

    val matches = set.stream()
            .filter { set.contains(Math.abs(it - k)) }
            .count()

    print(matches)
}


import java.util.*
import kotlin.collections.HashMap

typealias Number = Int
typealias Frequency = Int

/**
 * Problem: https://www.hackerrank.com/challenges/missing-numbers/problem
 */
fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val mapA = HashMap<Number, Frequency>()

    (0 until n).forEach {
        val next = scanner.nextInt()
        mapA.compute(next) { _, value ->
            value?.let { value + 1 } ?: 1
        }
    }

    val m = scanner.nextInt()
    val mapB = HashMap<Number, Frequency>()

    (0 until m).forEach {
        val next = scanner.nextInt()
        mapB.compute(next) { _, value ->
            value?.let { value + 1 } ?: 1
        }
    }

    val missingValues = mutableListOf<Number>()

    mapB.forEach { number, frequency ->
        mapA[number]?.let {
            if (frequency > it) missingValues.add(number)
        } ?: missingValues.add(number)
    }

    missingValues.sort()
    missingValues.forEach {
        print(it)
        print(" ")
    }

}
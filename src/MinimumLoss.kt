import java.util.*

typealias Price = Long
typealias ListIndex = Int

/**
 * Problem: https://www.hackerrank.com/challenges/minimum-loss/problem
 */
fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val length = scanner.nextInt()

    val indexedPrices = mutableListOf<Pair<Price, ListIndex>>()

    (0 until length).forEach { index ->
        indexedPrices.add(Pair(scanner.nextLong(), index))
    }

    indexedPrices.sortBy { it.first }

    var maxSaving = Long.MAX_VALUE

    indexedPrices.forEachIndexed { index, pair ->
        if (index != 0) {
            val previous = indexedPrices[index - 1]
            if (pair.second < previous.second &&
                    (pair.first - previous.first) < maxSaving) {
                maxSaving = pair.first - previous.first
            }
        }
    }

    print(maxSaving)

}
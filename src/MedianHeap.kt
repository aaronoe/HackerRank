import java.util.*
import javax.script.Invocable
import javax.script.ScriptEngineManager
import javax.tools.ToolProvider

/**
 * Problem: Heaps: Find the Running Median
 * Link: https://www.hackerrank.com/challenges/ctci-find-the-running-median/problem
 */
fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)
    val length = scanner.nextInt()

    val medianHeap = MedianHeap()

    (0 until length).forEach {
        println(medianHeap.insert(scanner.nextInt()))
    }
}

class MedianHeap {

    private val maxHeap = PriorityQueue<Int>({ one, two ->
        when {
            one > two -> 1
            one < two -> -1
            else -> 0
        }
    })

    private val minHeap = PriorityQueue<Int>({ one, two ->
        when {
            one > two -> -1
            one < two -> 1
            else -> 0
        }
    })

    private var currentMedian: Double = 0.0

    fun insert(value: Int): Double {

        if (value > currentMedian) {
            maxHeap.add(value)
        } else {
            minHeap.add(value)
        }

        if (Math.abs(maxHeap.size - minHeap.size) > 1) {
            if (maxHeap.size > minHeap.size) {
                minHeap.add(maxHeap.poll())
            } else {
                maxHeap.add(minHeap.poll())
            }
        }

        return updateMedian()
    }

    private fun updateMedian(): Double {

        currentMedian = when {
            minHeap.size > maxHeap.size -> minHeap.peek().toDouble()
            minHeap.size < maxHeap.size -> maxHeap.peek().toDouble()
            else -> (minHeap.peek() + maxHeap.peek()).toDouble() / 2
        }

        return currentMedian
    }

}
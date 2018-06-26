import java.util.*
import kotlin.collections.HashMap

/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int = 0) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var currentFirst = l1
        var currentSecond = l2

        var resultHead: ListNode? = null
        var resultCurrent: ListNode? = null
        var currentOverflow = 0

        while (currentFirst != null || currentSecond != null) {
            val firstNumber = currentFirst?.`val` ?: 0
            val secondNumber = currentSecond?.`val` ?: 0

            val (overflow, result) = getSumWithOverflow(firstNumber, secondNumber, currentOverflow)
            currentOverflow = overflow

            if (resultCurrent == null) {
                // first element
                resultCurrent = ListNode(result)
                resultHead = resultCurrent
            } else {
                resultCurrent.next = ListNode(result)
                resultCurrent = resultCurrent.next
            }

            currentFirst = currentFirst?.next
            currentSecond = currentSecond?.next
        }

        return resultHead
    }

    private fun getSumWithOverflow(first: Int, second: Int, overflow: Int): Pair<Int, Int> {
        val sum = first + second + overflow
        return Pair(sum / 10, sum % 10)
    }

}


class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

typealias Word = String
typealias Count = Int

fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)
    val m = scanner.nextInt()
    val n = scanner.nextInt()

    val dictionary = HashMap<Word, Count>()

    (0 until m).forEach {
        val word = scanner.next()
        dictionary.compute(word) { _, count ->
            (count ?: 0) + 1
        }
    }

    (0 until n).forEach {
        val word = scanner.next()
        dictionary.compute(word) { _, count ->
            if (count == null || count <= 0) {
                print("No")
                System.exit(-1)
                0
            } else {
                count - 1
            }
        }
    }

    print("Yes")
}
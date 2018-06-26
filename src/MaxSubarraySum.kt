import java.util.*

fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)
    val queries = scanner.nextInt()

    (0 until queries).forEach {
        performQuery(scanner)
    }

}

fun performQuery(scanner: Scanner) {
    val length = scanner.nextInt()
    val mod = scanner.nextInt()

    val inputs = arrayListOf<Int>()
    val results = IntArray(length)

    (0 until length).forEach {
        inputs.add(scanner.nextInt())
    }

    var maxSum = 0
    (0 until length).forEach { len ->
        inputs.forEachIndexed { index, value ->
            if (index + len < length) {
                ((results[index] + inputs[index + len]) % mod).let {
                    results[index] = it
                    maxSum = Math.max(maxSum, it)
                }
                /*
                val sublist = inputs.subList(index, index + len)
                val sum = sublist.sum() % mod
                maxSum = Math.max(maxSum, sum)
                */
            }
        }
    }

    println(maxSum)
}
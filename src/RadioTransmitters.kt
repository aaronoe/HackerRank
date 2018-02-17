import java.util.*

/**
 * Name: Hackerland Radio Transmitters
 * Link: https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem
 */
fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)
    val numberOfHouses = scanner.nextInt()
    val transmitterRange = scanner.nextInt()

    val houseLocations = TreeSet<House>({ first, second ->
        when {
            first.position > second.position -> 1
            first.position < second.position -> -1
            else -> 0
        }
    })

    (0 until numberOfHouses).forEach {
        houseLocations.add(House(scanner.nextInt()))
    }

    val houseIterator = houseLocations.iterator()

    var previous: House? = null
    var current: House?
    var minTransmitterCount = 0

    while (houseIterator.hasNext()) {
        current = houseIterator.next()
        if (previous == null || (previous.covered && !current.covered )) {
            val maxPosition = current.position + transmitterRange
            val lastInRange = houseLocations.findLast { it.position <= maxPosition }
            lastInRange?.position?.let {
                minTransmitterCount++
                houseLocations.markAllInRange(it, transmitterRange)
            }
        }
        previous = current
    }
    println(minTransmitterCount)
}

fun TreeSet<House>.markAllInRange(position: Int, transmitterRange: Int) {
    forEachIndexed { _, house ->
        if (house.position > position + transmitterRange) return
        if (house.position in (position - transmitterRange..position + transmitterRange)) {
            house.covered = true
        }
    }
}

data class House(val position: Int, var covered: Boolean = false)
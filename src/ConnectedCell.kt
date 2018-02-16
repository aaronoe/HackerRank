import java.util.*
import java.util.concurrent.LinkedBlockingQueue

/**
 * Problem: DFS: Connected Cell in a Grid
 * Link: https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem
 */
fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    val scanner = Scanner(System.`in`)
    val rowCount = scanner.nextInt()
    val colCount = scanner.nextInt()
    var maxIslandCount = 0

    val matrix = arrayListOf<ArrayList<Cell>>()

    (0 until rowCount).forEach { row ->
        val rowList = arrayListOf<Cell>()
        (0 until colCount).forEach { col ->
            rowList.add(Cell(scanner.nextInt(), row, col))
        }
        matrix.add(rowList)
    }

    matrix.forEachIndexed { rowIndex, rows ->
        rows.forEachIndexed { colIndex, _ ->
            findIsland(matrix, rowIndex, colIndex, rowCount, colCount).let {
                maxIslandCount = Math.max(maxIslandCount, it)
            }
        }
    }

    println(maxIslandCount)
}

fun findIsland(matrix: ArrayList<ArrayList<Cell>>, row: Int, col: Int, rowCount: Int, colCount: Int): Int {
    val currentCell = matrix[row][col]
    if (currentCell.visited) return 0

    var islandCount = 0

    val queue = LinkedBlockingQueue<Cell>()
    queue.add(currentCell)
    while (queue.isNotEmpty()) {
        val cell = queue.poll()

        if (!cell.visited && cell.value == 1) {
            islandCount++
            val neighbors = cell.getAdjacentCells(matrix, rowCount, colCount)
            val unvisitedNeighbors = neighbors.filter { !it.visited }
            queue.addAll(unvisitedNeighbors)
        }

        cell.visited = true
    }

    return islandCount
}

fun Cell.getAdjacentCells(matrix: ArrayList<ArrayList<Cell>>, rowCount: Int, colCount: Int): List<Cell> {
    val results = arrayListOf<Cell>()
    (-1..1).forEach { rowIndex ->
        (-1..1).forEach { colIndex ->
            if (rowIndex != 0 || colIndex != 0) {
                val row = this.row + rowIndex
                val col = this.col + colIndex
                if (row in 0..(rowCount - 1) && col in 0..(colCount - 1)) {
                    results.add(matrix[row][col])
                }
            }
        }
    }
    return results
}

data class Cell(val value: Int, val row: Int, val col: Int, var visited: Boolean = false)
import kotlin.math.abs

fun main() {
    fun gather(input: List<String>): Pair<IntArray, IntArray> {
        var left = intArrayOf()
        var right = intArrayOf()
        for (line in input) {
            val s = line.split("\\s+".toRegex())
            if (s.size != 2) {
                throw Exception("Invalid input")
            }
            left = left.plus(s[0].toInt())
            right = right.plus(s[1].toInt())
        }
        return Pair(left,right)
    }
    fun part1(input: List<String>): Int {
        val (left, right) = gather(input)
        left.sort()
        right.sort()
        if (left.isEmpty() || right.isEmpty()) {
            throw Exception("Invalid input")
        }
        var dist = 0
        for (i in 0..<left.size) {
            dist += abs(left[i] - right[i])
        }
        return dist
    }

    fun part2(input: List<String>): Int {
        val (left, right) = gather(input)
        var total = 0
        for (i in 0..<left.size) {
            val num = left[i]
            total += num * right.count { r -> r == num }
        }
        return total
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var safe = 0
        for (line in input) {
            val lineInt = line.split(" ").map { it.toInt() }
            val up = mutableListOf<Boolean>()
            val diffs = mutableListOf<Boolean>()
            for (curr in 0..<lineInt.size) {
                val prev = curr - 1;
                if (prev < 0) {
                    continue
                }
                up.add(lineInt[curr] >= lineInt[prev])
                val diff = abs(lineInt[curr] - lineInt[prev])
                diffs.add(0 < diff && diff < 4)
            }
            if (up.isEmpty()) {
                continue
            }
            if (up.zip(diffs).all { pair -> pair.first == up[0] && pair.second }) {
                safe++
            }
        }
        return safe
    }

    fun part2(input: List<String>): Int {
        fun iter(currInt: List<Int>): Boolean {
            val up = mutableListOf<Boolean>()
            val diffs = mutableListOf<Boolean>()
            for (curr in 0..<currInt.size) {
                val prev = curr - 1;
                if (prev < 0) {
                    continue
                }
                up.add(currInt[curr] >= currInt[prev])
                val diff = abs(currInt[curr] - currInt[prev])
                diffs.add(0 < diff && diff < 4)
            }
            return up.zip(diffs).all { pair -> pair.first == up[0] && pair.second }
        }

        var safe = 0
        for (line in input) {
            val lineInt = line.split(" ").map { it.toInt() }
            var state = mutableListOf<Boolean>()
            state.add(iter(lineInt))
            for (idx in 0..<lineInt.size) {
                var currInt = mutableListOf<Int>()
                for (i in 0..<lineInt.size) {
                    if (idx == i) {
                        continue
                    }
                    currInt.add(lineInt[i])
                }
                state.add(iter(currInt))
            }
            if (state.any { it }) {
                safe++
            }
        }
        return safe
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
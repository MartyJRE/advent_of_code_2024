fun main() {
    fun part1(input: List<String>): Int {
        val map = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        for ((y, line) in input.withIndex()) {
            for (found in Regex("[a-zA-Z0-9]").findAll(line)) {
                if (!map.contains(found.value.first())) {
                    map[found.value.first()] = mutableListOf()
                }
                map[found.value.first()]?.add(Pair(found.range.first, y))
            }
        }
        var problems = 0
        for ((_, locations) in map) {
            for (location in locations) {
                for (other in locations) {
                    if (location.first == other.first && location.second == other.second) {
                        continue
                    }
                    var xDiff = location.first - other.first
                    var yDiff = location.second - other.second
                    if (xDiff > 0) {
                        xDiff *= -1
                    }
                    if (yDiff > 0) {
                        yDiff *= -1
                    }
                    val one = Pair(other.first - xDiff, other.second - yDiff)
                    if (
                        one !in map.values.flatMap { it } &&
                        one.first in 0 until input.first().length &&
                        one.second in 0 until input.size
                    ) {
                        problems++
                    }
                    val two = Pair(location.first + xDiff, location.second + yDiff)
                    if (
                        two !in map.values.flatMap { it } &&
                        two.first in 0 until input.first().length &&
                        two.second in 0 until input.size
                    ) {
                        problems++
                    }
                }
            }
        }
        return problems
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 14)
//    part2(testInput).println()
//    check(part2(testInput) == 6)

    val input = readInput("Day08")
    part1(input).println()
//    part2(input).println()
}
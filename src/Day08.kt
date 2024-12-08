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
            val all = mutableListOf<Pair<Int, Int>>()
            for (location in locations) {
                for (other in locations) {
                    if (location.first == other.first && location.second == other.second) {
                        continue
                    }
                    var xDiff = location.first - other.first
                    var yDiff = location.second - other.second
                    val one = Pair(location.first + xDiff, location.second + yDiff)
                    val two = Pair(other.first - xDiff, other.second - yDiff)
                    all.addAll(listOf(one, two))
                }
            }

            problems += all.count {
                it !in map.flatMap { it.value } &&
                        it.first >= 0 && it.first < input.first().length
                        && it.second >= 0 && it.second < input.size
            }
        }
        problems.println()
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
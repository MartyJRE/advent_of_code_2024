fun main() {
    fun part1(input: List<String>): Int {
        val directions = listOf(
            Pair(0, 1),
            Pair(1, 0),
            Pair(1, 1),
            Pair(1, -1),
            Pair(0, -1),
            Pair(-1, 0),
            Pair(-1, -1),
            Pair(-1, 1)
        )

        var needle = "XMAS"
        var count = 0
        val length = input.size
        val width = input.first().length

        for (y in 0 until length) {
            for (x in 0 until width) {
                if (input[y][x] == 'X') {
                    for (direction in directions) {
                        var found = true
                        for (idx in 0 until needle.length) {
                            val newY = y + idx * direction.first
                            val newX = x + idx * direction.second
                            if (
                                newY !in 0 until length ||
                                newX !in 0 until width ||
                                input[newY][newX] != needle[idx]
                            ) {
                                found = false
                                break
                            }
                        }
                        if (found) count++
                    }
                }
            }
        }
        return count


    }

    fun part2(input: List<String>): Int {
        // :-(
        return 0
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
//    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
//    part2(input).println()
}
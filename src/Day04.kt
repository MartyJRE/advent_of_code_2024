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
        var cnt = 0
        for (y in input.indices) {
            for (x in input.first().indices) {
                if (input[y][x] != 'A') {
                    continue
                }
                var leftRight = ""
                var rightLeft = ""
                if (y - 1 >= 0) {
                    if (x - 1 >= 0) {
                        leftRight += input[y - 1][x - 1]
                    }
                    if (x + 1 < input[y - 1].length) {
                        rightLeft += input[y - 1][x + 1]
                    }
                }
                if (y + 1 < input.size) {
                    if (x - 1 >= 0) {
                        rightLeft += input[y + 1][x - 1]
                    }
                    if (x + 1 < input[y + 1].length) {
                        leftRight += input[y + 1][x + 1]
                    }
                }
                if ((rightLeft == "MS" || rightLeft.reversed() == "MS") && (leftRight == "MS" || leftRight.reversed() == "MS")) {
                    cnt++
                }
            }
        }
        // 1949 too high :(
        return cnt
    }

    val testInput1 = readInput("Day04_test01")
    check(part1(testInput1) == 18)
    val testInput2 = readInput("Day04_test02")
    check(part2(testInput2) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
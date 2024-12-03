fun main() {
    fun part1(input: List<String>): Int {
        return Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
            .findAll(input.joinToString())
            .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    fun part2(input: List<String>): Int {
        return input.joinToString().split("do()").map { it.split("don't()").first() }.sumOf { inp ->
            Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
                .findAll(inp)
                .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
        }
    }

    val testInput1 = readInput("Day03_test01")
    check(part1(testInput1) == 161)
    val testInput2 = readInput("Day03_test02")
    check(part2(testInput2) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
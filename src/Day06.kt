fun main() {
    fun part1(input: List<String>): Int {
        val startY = input.indexOfFirst { it.contains('^') }
        val startX = input[startY].indexOfFirst { it == '^' }
        var x = startX
        var y = startY
        var xDir = 0
        var yDir = -1
        var steps = 0
        var path = input.toMutableList()
        while (0 <= x && x < input.first().length && 0 <= y && y < input.size) {
            var nextX = x + xDir
            var nextY = y + yDir
            var char = input.getOrNull(nextY)?.getOrNull(nextX)
            if (char == '.' || char == '^') {
                path[y] = path[y].replaceRange(x..x, "X")
                x = nextX
                y = nextY
                if (path[y][x] != 'X') {
                    steps++
                }
                continue
            } else if (char == '#') {
                if (xDir == 0 && yDir == -1) {
                    xDir = 1
                    yDir = 0
                } else if (xDir == 1 && yDir == 0) {
                    xDir = 0
                    yDir = 1
                } else if (xDir == 0 && yDir == 1) {
                    xDir = -1
                    yDir = 0
                } else if (xDir == -1 && yDir == 0) {
                    xDir = 0
                    yDir = -1
                } else {
                    throw Error("Invalid direction")
                }
                continue
            } else {
                break
            }
        }
        return steps + 1
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
//    check(part2(testInput) == 123)

    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}
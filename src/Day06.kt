fun main() {
    fun part1(input: List<String>): Int {
        val startY = input.indexOfFirst { it.contains('^') }
        val startX = input[startY].indexOfFirst { it == '^' }
        var x = startX
        var y = startY
        var xDir = 0
        var yDir = -1
        var steps = 0
        var path = mutableMapOf<Pair<Int, Int>, Int>()
        while (0 <= x && x < input.first().length && 0 <= y && y < input.size) {
            var nextX = x + xDir
            var nextY = y + yDir
            var char = input.getOrNull(nextY)?.getOrNull(nextX)
            if (char == '.' || char == '^') {
                var prev = path[Pair(x, y)]
                path[Pair(x, y)] = if (prev != null) prev + 1 else 1
                x = nextX
                y = nextY
                if (path[Pair(x, y)] == null) {
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
        var cnt = 0
        for (ox in input.first().indices) {
            for (oy in input.indices) {
                val startY = input.indexOfFirst { it.contains('^') }
                val startX = input[startY].indexOfFirst { it == '^' }
                if (input[oy][ox] == '#' || input[oy][ox] == '^') continue
                val subInput = input.toMutableList()
                subInput[oy] = subInput[oy].replaceRange(ox..ox, "O")
                var x = startX
                var y = startY
                var xDir = 0
                var yDir = -1
                var steps = 0
                var path = mutableMapOf<Pair<Int, Int>, Int>()
                while (0 <= x && x < subInput.first().length && 0 <= y && y < subInput.size) {
                    if (path.getOrDefault(Pair(x, y), 0) > 3) {
                        cnt++
                        break
                    }
                    var nextX = x + xDir
                    var nextY = y + yDir
                    var char = subInput.getOrNull(nextY)?.getOrNull(nextX)
                    if (char == '.' || char == '^') {
                        var prev = path[Pair(x, y)]
                        path[Pair(x, y)] = if (prev != null) prev + 1 else 1
                        x = nextX
                        y = nextY
                        if (path[Pair(x, y)] == null) {
                            steps++
                        }
                        continue
                    } else if (char == '#' || char == 'O') {
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
            }
        }
        return cnt
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    part2(testInput).println()
    check(part2(testInput) == 6)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
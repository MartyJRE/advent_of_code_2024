fun main() {
    fun part1(input: List<String>): Long {
        var all = 0L
        val parsed = input.associate {
            val parts = it.split(":")
            parts.first().toLong() to parts
                .last()
                .trim()
                .split(" ")
                .map { it.toLong() }
        }
        for ((target, parts) in parsed) {
            var options = listOf('*', '+')
            val permutations = mutableListOf<String>()

            val indices = IntArray(parts.size - 1)
            val current = CharArray(parts.size - 1) { options.first() }
            var idx = parts.size - 2
            while (idx >= 0) {
                permutations.add(String(current))
                idx = parts.size - 2
                while (idx >= 0) {
                    if (indices[idx] < options.size - 1) {
                        indices[idx]++
                        current[idx] = options[indices[idx]]
                        break
                    } else {
                        indices[idx] = 0
                        current[idx] = options.first()
                        idx--
                    }
                }
                if (idx < 0) break

            }
            permutations.println()
            for (permutation in permutations) {
                var option = 0L
                for ((operatorIdx, operator) in permutation.withIndex()) {
                    var left = if (option == 0L) parts[operatorIdx] else option
//                    print(left)
                    var right = parts[operatorIdx + 1]
                    option = if (operator == '+') {
//                        println(" + $right")
                        left + right
                    } else {
//                        println(" * $right")
                        left * right
                    }
//                    println("$target ? $option")
                }

//                permutation.println()
                if (option == target) {
//                    println("Found $target")
                    all += target
                    break
                }

//                println("---")
            }
        }
//        all.println()
        return all
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749L)
//    part2(testInput).println()
//    check(part2(testInput) == 6)

    val input = readInput("Day07")
    part1(input).println()
//    part2(input).println()
}
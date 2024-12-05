fun main() {
    fun part1(input: List<String>): Int {
        return input
            .subList(input.indexOf("") + 1, input.size)
            .map { it.split(",") }
            .sumOf { pages ->
                if (input.subList(0, input.indexOf("")).map {
                        val pair = it.split("|")
                        Pair(pair.first(), pair.last())
                    }
                        .map {
                            Pair(pages.indexOf(it.first), pages.indexOf(it.second))
                        }
                        .filter { it.first != -1 && it.second != -1 }
                        .all { it.first <= it.second }
                ) {
                    pages[pages.size / 2].toInt()
                } else {
                    0
                }
            }
    }

    fun part2(input: List<String>): Int {
        val rules = input.subList(0, input.indexOf("")).map {
            val pair = it.split("|")
            Pair(pair.first(), pair.last())
        }
        var pageLines = input
            .subList(input.indexOf("") + 1, input.size)
            .map { it.split(",") }
            .map { pages ->
                if (input.subList(0, input.indexOf("")).map {
                        val pair = it.split("|")
                        Pair(pair.first(), pair.last())
                    }
                        .map {
                            Pair(pages.indexOf(it.first), pages.indexOf(it.second))
                        }
                        .filter { it.first != -1 && it.second != -1 }
                        .all { it.first <= it.second }
                ) {
                    listOf()
                } else {
                    pages
                }
            }
            .filter { it.isNotEmpty() }

        var total = 0

        while (pageLines.isNotEmpty()) {
            val newPageLines = mutableListOf<MutableList<String>>()
            for (pages in pageLines) {
                val flips = rules
                    .map {
                        Pair(pages.indexOf(it.first), pages.indexOf(it.second))
                    }
                    .filter { it.first != -1 && it.second != -1 }
                    .filter { it.first > it.second }
                var pgs = pages.toMutableList()
                if (flips.isEmpty()) {
                    total += pages[pages.size / 2].toInt()
                    continue
                }
                pgs[flips.first().first] =
                    pgs[flips.first().second].also { pgs[flips.first().second] = pgs[flips.first().first] }

                newPageLines.add(pgs)
            }
            pageLines = newPageLines
        }

        return total
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
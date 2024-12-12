fun main() {
    fun part1(input: List<String>): Long {
        val indexed = input.first().map { it.toString().toInt() }.withIndex()
        var str = ""
        for ((idx, num) in indexed) {
            str += (if (idx % 2 == 0) idx / 2 else '.').toString().repeat(num)
        }
        str.println()
        var strArr = str.toCharArray()
        while (true) {
            val dot = strArr.indexOfFirst { it == '.' }
            val num = strArr.indexOfLast { it != '.' }
            strArr[dot] = strArr[num].also {
                strArr[num] = '.'
            }
            if (dot + 1 == num) {
                break
            }
        }
        var newStr = ""
        for (item in strArr) {
            newStr += item
        }
        newStr.println()
        var total = 0L
        for ((idx, char) in strArr.withIndex()) {
            if (char == '.') break
            if (char == '0') continue
            var addition = idx * char.toString().toLong()
            total += addition
        }
        return total
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 1928L)
//    part2(testInput).println()
//    check(part2(testInput) == 6)

    val input = readInput("Day09")
    part1(input).println()
//    part2(input).println()
}
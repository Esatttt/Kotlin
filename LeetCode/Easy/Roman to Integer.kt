//https://leetcode.com/problems/roman-to-integer/
//That should'nt be condsidered as Easy
class Solution {
    fun romanToInt(s: String): Int {
        var ourcount: Int = 0
        val romentolatin: Map<Char, Int> = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )

        val list = s.toList()
        var bannedlistindexes: MutableList<Int> = mutableListOf()


        for (char in list) {
            if (list.indexOf(char) in bannedlistindexes) {
                continue
            } else if (list.indexOf(char) == list.lastIndex) {
                ourcount = ourcount + romentolatin[char]!!
            } else if (romentolatin[char]!! < romentolatin[list[(list.indexOf(char) + 1)]]!!) {
                bannedlistindexes.add(list.indexOf(list[(list.indexOf(char) + 1)]))
                ourcount = ourcount + romentolatin[list[(list.indexOf(char) + 1)]]!! - romentolatin[char]!!
            } else {
                ourcount = ourcount + romentolatin[char]!!


            }

        }

        return ourcount }
}

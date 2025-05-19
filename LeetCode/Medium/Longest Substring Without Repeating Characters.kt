//https://leetcode.com/problems/longest-substring-without-repeating-characters/
class Solution {
        fun lengthOfLongestSubstring(s: String): Int {
        var stack: MutableList<Char> = mutableListOf() 
        val lengths: MutableSet<Int> = mutableSetOf() //Declared as val and set for much quicker

            for (x in s){
                if (x in stack){
                    lengths.add(stack.size)
                    stack = stack.subList(stack.indexOf(x) + 1, stack.size) 
                    stack.add(x)
                } else{stack.add(x)}
            }

            lengths.add(stack.size)
            return lengths.max()
        }
    }

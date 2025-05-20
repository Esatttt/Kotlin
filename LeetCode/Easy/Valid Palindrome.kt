//https://leetcode.com/problems/valid-palindrome/description/
class Solution {
    fun isPalindrome(s: String): Boolean {
        val withoutspaces: MutableList<Char> = mutableListOf()
        for(char in s){
            if(char.isLetterOrDigit())
            withoutspaces.add(char.lowercaseChar())
        }
        if(withoutspaces == withoutspaces.reversed()){
            return true
        }
        return false
    }
}

//https://leetcode.com/problems/top-k-frequent-elements/description/
class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val numsmap = nums.associateWith { 0 }.toMutableMap()
        for(num in nums){    //Just 1 time reading, quicker than counting by one by one
            numsmap[num] =  numsmap[num]!! + 1  //No problem
        }
        var returnlist: List<Int> = numsmap.entries.sortedByDescending { it.value }.map { it.key } //I got help from AI here, hard
        return returnlist.take(k).toIntArray() 
    }
}

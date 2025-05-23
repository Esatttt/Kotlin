//https://leetcode.com/problems/median-of-two-sorted-arrays/
class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        fun medianfinder(list: List<Int>): Double{
            if (list.size % 2 == 0){
                return (list[list.size/2].toDouble() + list[list.size/2-1].toDouble())/2
            }
            else if(list.size % 2 == 1){
                return list[(list.size-1)/2].toDouble()
            } else{return 0.0}
        }
        return medianfinder(listOf(nums1.toList(),nums2.toList()).flatten().sorted())
    }
}

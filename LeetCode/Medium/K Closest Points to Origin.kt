//leetcode.com/problems/k-closest-points-to-origin/description/
import kotlin.math.sqrt
class Solution {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        fun distancetoorigin(twoelementarray: IntArray): Double{ //Simple distance finder, I should return Double for sensitive distance
            return sqrt((twoelementarray[1]*twoelementarray[1]).toDouble() + (twoelementarray[0]*twoelementarray[0]).toDouble()) }
        val distancesofpoints: MutableMap<IntArray,Double> = mutableMapOf()
        for (point in points){
            distancesofpoints[point] = distancetoorigin(point) }
        return (distancesofpoints.toMap().entries.sortedBy{ it.value}.map { it.key }).take(k).toTypedArray() }
}                                       //I learned something with AI here, it's so complicated

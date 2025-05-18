//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

class Solution {
    fun maxProfit(prices: IntArray): Int{
        var min: Int = prices[0]
        var profits: MutableSet<Int> = mutableSetOf()
        for (index in prices.indices){
            if (prices[index] > min){continue}
            if (prices[index] < min || index == 0){
               profits.add(prices.sliceArray(index..prices.lastIndex).max() - prices[index])
                min = prices[index]
            }
        }
        if (profits.size == 0){return 0}
        return profits.max()
    }
}

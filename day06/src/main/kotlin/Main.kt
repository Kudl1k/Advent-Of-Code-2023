package org.example

import java.io.File
import java.math.BigInteger
import kotlin.time.times


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}


fun getTimeAndDistance(lines: List<String>):List<Pair<BigInteger,BigInteger>>{
    val timeAndDistance = mutableListOf<Pair<BigInteger,BigInteger>>()


    val time: List<String> = lines[0].split("\\s+".toRegex()).drop(1)
    val distance: List<String> = lines[1].split("\\s+".toRegex()).drop(1)

    for (i in time.indices){
        timeAndDistance.add(Pair(time[i].toBigInteger(),distance[i].toBigInteger()))
    }
    return timeAndDistance
}

fun getNumberOfWays(table: List<Pair<BigInteger,BigInteger>>): Int{
    val listOfPossibleWays = mutableListOf<BigInteger>()
    var possibleWays = 0
    for (i in table.indices){
        var j = BigInteger.ZERO
        while (j < table[i].first){
            if (j*(table[i].first-j) > table[i].second){
                possibleWays++
            }
            j++
        }
    }
    return possibleWays
}



fun main() {
    val lines = loadFile("input.txt")
    val table = getTimeAndDistance(lines)
    println(getNumberOfWays(table))
}
package org.example

import java.io.File
import kotlin.time.times


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}


fun getTimeAndDistance(lines: List<String>):List<Pair<Int,Int>>{
    val timeAndDistance = mutableListOf<Pair<Int,Int>>()


    val time: List<String> = lines[0].split("\\s+".toRegex()).drop(1)
    val distance: List<String> = lines[1].split("\\s+".toRegex()).drop(1)

    for (i in time.indices){
        timeAndDistance.add(Pair(time[i].toInt(),distance[i].toInt()))
    }
    return timeAndDistance
}

fun getNumberOfWays(table: List<Pair<Int,Int>>): Int{
    val listOfPossibleWays = mutableListOf<Int>()
    var possibleWays = 0
    for (i in table.indices){
        for (j in 0..table[i].first){
            if (j*(table[i].first-j) > table[i].second){
                possibleWays++
            }
        }
        listOfPossibleWays.add(possibleWays)
        possibleWays = 0
    }
    var result = 1
    for (i in listOfPossibleWays.indices){
        result *= listOfPossibleWays[i]
    }
    return result
}



fun main() {
    val lines = loadFile("input.txt")
    val table = getTimeAndDistance(lines)
    println(getNumberOfWays(table))
}
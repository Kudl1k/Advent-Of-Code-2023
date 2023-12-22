package org.example

import java.io.File

fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}


fun convertToInt(line: List<String>): List<Int>{
    val result = mutableListOf<Int>()
    for (i in line){
        result.add(i.toInt())
    }
    return result
}

fun checkNull(list: List<Int>): Boolean{
    if (list.isEmpty()) return true
    for (i in list){
        if (i != 0){
            return true
        }
    }
    return false
}

fun getHistory(list: List<Int>):Int{
    val history: MutableList<MutableList<Int>> = mutableListOf(list.toMutableList())
    var temp = mutableListOf<Int>()
    while (checkNull(temp)){
        temp = mutableListOf()
        for (i in 0..history[history.size-1].size-2){
            temp.add(history[history.size-1][i+1]-history[history.size-1][i])
        }
        history.add(temp)
    }
    var result = 0
    for (i in history.size-1 downTo 0){
        result += history[i].last()
    }
    return result
}



fun main() {
    val lines = loadFile("input.txt")
    val nums: MutableList<List<Int>> = mutableListOf()
    lines.forEach {
        nums.add(convertToInt(it.split("\\s+".toRegex())))
    }
    val results = mutableListOf<Int>()
    nums.forEach {
        results.add(getHistory(it))
    }
    println(results.sum())
}
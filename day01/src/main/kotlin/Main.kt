package org.example

import java.io.File

fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getNumber(line: String): Int{
    var number = ""
    for (i in line.indices){
        if (!line[i].isDigit()){
            continue
        }
        if (line[i] in '0'..'9'){
            number += line[i]
            break
        }
    }
    for (i in line.length-1 downTo 0){
        if (!line[i].isDigit()){
            continue
        }
        if (line[i] in '0'..'9'){
            number += line[i]
            break
        }
    }
    return if (number.isBlank()){
        0
    } else {
        number.toInt()
    }
}

fun main() {
    val lines = loadFile("input.txt")
    val listOfNums = mutableListOf<Int>()

    lines.forEach {
        listOfNums.add(getNumber(it))
    }
    println(listOfNums.sum())
}
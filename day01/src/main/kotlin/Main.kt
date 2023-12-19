package org.example

import java.io.File

fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getDigitFormNumber(line: String): Int{
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

fun getTextFromNumber(line: String): Int {
    var result = ""
    var temp = ""
    var text = ""

    var listOfPair: List<Pair<String,Int>> = mutableListOf(
        Pair("one",1),
        Pair("two",2),
        Pair("three",3),
        Pair("four",4),
        Pair("five",5),
        Pair("six",6),
        Pair("seven",7),
        Pair("eight",8),
        Pair("nine",9)
    )
    var i = 0
    val flag = false
    loop@while (i < line.length-1){
        if (i+5 < line.length-1){
            temp = line.substring(i,i+5)
        } else {
            break@loop
        }
        if (temp[0].isDigit()){
            text += temp[0]
            i++
            continue
        }
        for (j in listOfPair.indices){
            if (temp.contains(listOfPair[j].first,ignoreCase = true)){
                val index = getStartingIndex(temp,listOfPair[j].first[0])
                i += index
                i += listOfPair[j].first.length-1
                text += listOfPair[j].second
                continue@loop
            }
        }
        i++
    }
    println(text)
    return result.toInt()
}

fun getStartingIndex(part: String,startingChar: Char): Int{
    for (i in part.indices){
        if (part[i] == startingChar){
            return i
        }
    }
    return 0
}



fun main() {
    val lines = loadFile("input.txt")
    val listOfNums = mutableListOf<Int>()
    val listOfText = mutableListOf<Int>()

    lines.forEach {
        listOfNums.add(getDigitFormNumber(it))
    }
    lines.forEach {
        listOfText.add(getTextFromNumber(it))
    }
    println(listOfNums.sum())
}
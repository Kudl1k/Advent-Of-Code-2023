package org.example

import java.io.File


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getNumber(line: String): Boolean{

    var blueHighest: Int = 0
    var redHighest: Int = 0
    var greenHighest: Int = 0
    var input: String = ""
    var tempInt: String = ""
    var game: Int = 0
    for (i in line.indices){
        if (line[i].isDigit()){
            if (line[i] in '1'..'9'){
                tempInt += line[i]
            }
        } else {
            if (line[i] in 'a'..'z'){
                input += line[i]
            } else {
                continue
            }
            when (input){
                "red" -> {
                    if (redHighest < tempInt.toInt()){
                        redHighest = tempInt.toInt()
                    }
                    tempInt = ""
                    input = ""
                }
                "green" -> {
                    if (greenHighest < tempInt.toInt()){
                        greenHighest = tempInt.toInt()
                    }
                    tempInt = ""
                    input = ""
                }
                "blue" -> {
                    if (blueHighest < tempInt.toInt()){
                        blueHighest = tempInt.toInt()
                    }
                    tempInt = ""
                    input = ""
                }
            }
        }
    }

    return redHighest <= 12 && greenHighest <= 13 && blueHighest <= 14
}


fun main() {
    val lines = loadFile("input.txt")
    var result = 0
    for (i in lines.indices){
        val game = i + 1
        val line = lines[i].dropWhile { it != ':' }
        if (getNumber(line)){
            println(game)
            result += game
        }
    }
    println(result)
}
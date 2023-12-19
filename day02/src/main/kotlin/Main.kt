package org.example

import java.io.File


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getNumber(line: String): Boolean{


    var input: String = ""
    var tempInt: String = ""

    var redCounter: Int = 0
    var greenCounter: Int = 0
    var blueCounter: Int = 0


    var game: Int = 0
    for (i in line.indices){
        if (line[i].isDigit()){
            tempInt += line[i]
        } else {
            if (line[i] in 'a'..'z' || line[i] == ';'){
                input += line[i]
            } else {
                continue
            }
            when (input){
                "red" -> {
                    redCounter += tempInt.toInt()
                    if (12 < tempInt.toInt()){
                        print("$tempInt red max value")
                        return false
                    }
                    if (12 < redCounter){
                        print("$redCounter red sum value")
                        return false
                    }
                    tempInt = ""
                    input = ""
                }
                "green" -> {
                    greenCounter += tempInt.toInt()
                    if (13 < tempInt.toInt()){
                        print("$tempInt green max value")
                        return false
                    }
                    if (13 < greenCounter){
                        print("$greenCounter green sum value")
                        return false
                    }
                    tempInt = ""
                    input = ""
                }
                "blue" -> {
                    blueCounter += tempInt.toInt()
                    if (14 < tempInt.toInt()){
                        print("$tempInt blue max value")
                        return false
                    }
                    if (14 < blueCounter){
                        print("$blueCounter blue sum value")
                        return false
                    }
                    tempInt = ""
                    input = ""
                }
                ";" -> {
                    tempInt = ""
                    input = ""
                    greenCounter = 0
                    redCounter = 0
                    blueCounter = 0
                }
            }
        }
    }
    return true
}


fun main() {
    val lines = loadFile("input.txt")
    var result = 0
    for (i in lines.indices){
        val game = i + 1
        val line = lines[i].dropWhile { it != ':' }.drop(1)
        print("game $game :")

        if (getNumber(line)){
            result += game
        }
        println()
    }
    println(result)
}
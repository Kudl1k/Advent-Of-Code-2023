package org.example

import java.io.File


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getPossibleGames(line: String): Boolean{


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
                    if (12 < tempInt.toInt()){
                        return false
                    }

                    tempInt = ""
                    input = ""
                }
                "green" -> {
                    if (13 < tempInt.toInt()){
                        return false
                    }

                    tempInt = ""
                    input = ""
                }
                "blue" -> {
                    if (14 < tempInt.toInt()){
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

fun getMinimumPossible(line: String): Triple<Int,Int,Int>{

    var input: String = ""
    var tempInt: String = ""

    var redCounter: Int = 0
    var greenCounter: Int = 0
    var blueCounter: Int = 0

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
                    if (tempInt.toInt() > redCounter){
                        redCounter = tempInt.toInt()
                    }
                    tempInt = ""
                    input = ""
                }
                "green" -> {
                    if (tempInt.toInt() > greenCounter){
                        greenCounter = tempInt.toInt()
                    }
                    tempInt = ""
                    input = ""
                }
                "blue" -> {
                    if (tempInt.toInt() > blueCounter){
                        blueCounter = tempInt.toInt()
                    }

                    tempInt = ""
                    input = ""
                }
                ";" -> {
                    tempInt = ""
                    input = ""
                }
            }
        }
    }
    return Triple(redCounter,greenCounter,blueCounter)
}



fun main() {
    val lines = loadFile("input.txt")
    var sumOfPossibleGames = 0
    for (i in lines.indices){
        val game = i + 1
        val line = lines[i].dropWhile { it != ':' }.drop(1)
        if (getPossibleGames(line)){
            sumOfPossibleGames += game
        }
    }

    var sumOfCubes = 0
    var temp = 0
    for (i in lines.indices){
        val line = lines[i].dropWhile { it != ':' }.drop(1)
        val cubes = getMinimumPossible(line)
        temp = cubes.first * cubes.second * cubes.third
        sumOfCubes += temp
        temp = 0
    }

    println(sumOfPossibleGames)
    println(sumOfCubes)
}
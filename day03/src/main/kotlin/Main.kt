package org.example

import java.io.File


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getAnswer(lines: List<String>): List<Int>{
    var foundNumber = false
    var number: String = ""
    var listOfNumbers: MutableList<Int> = mutableListOf()
    var flag: Boolean = false
    for (i in lines.indices){
         for (j in lines[i].indices){
            if (lines[i][j].isDigit()){
                foundNumber = true
                number += lines[i][j]

                //top-left
                if (i-1 > 0 && j-1 > 0){
                    if (lines[i-1][j-1] != '.' && lines[i-1][j-1] !in '0'..'9'){
                        flag = true
                    }
                }
                //top-middle
                if (i-1 > 0) {
                    if (lines[i-1][j] != '.' && lines[i-1][j] !in '0'..'9'){
                        flag = true
                    }
                }
                //top-right
                if (j+1 < lines[i].length && i-1 > 0){
                    if (lines[i-1][j+1] != '.' && lines[i-1][j+1] !in '0'..'9'){
                        flag = true
                    }
                }
                //middle-left
                if(j-1 > 0){
                    if (lines[i][j-1] != '.' && lines[i][j-1] !in '0'..'9'){
                        flag = true
                    }
                }
                //midle-right
                if (j+1 < lines[i].length){
                    if (lines[i][j+1] != '.' && lines[i][j+1] !in '0'..'9'){
                        flag = true
                    }
                }
                //bottom-left
                if (i+1 < lines.size && j-1 > 0){
                    if (lines[i+1][j-1] != '.' && lines[i+1][j-1] !in '0'..'9'){
                        flag = true
                    }
                }
                //bottom-middle
                if (i+1 < lines.size){
                    if (lines[i+1][j] != '.' && lines[i+1][j] !in '0'..'9'){
                        flag = true
                    }
                }
                //bottom-right
                if (j+1 < lines[i].length && i+1 < lines.size){
                    if (lines[i+1][j+1] != '.' && lines[i+1][j+1] !in '0'..'9'){
                        flag = true
                    }
                }

            } else {
                foundNumber = false
                if (number.isNotBlank() && flag){
                    listOfNumbers.add(number.toInt())
                }
                flag = false
                number = ""
            }
        }
    }
    return listOfNumbers
}





fun main() {
    val lines = loadFile("input.txt")
    println(getAnswer(lines))
    println(getAnswer(lines).sum())
}
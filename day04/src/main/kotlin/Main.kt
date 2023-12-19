package org.example

import java.io.File


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getRightFormat(line: String): List<String>{
    val temp = mutableListOf<String>()
    var word = ""
    for (i in line.indices){
        if (line[i] in '0'..'9' || line[i] == '|'){
            word += line[i]
        } else {
            if (word.isNotBlank()){
                temp.add(word)
            }
            word = ""
        }
    }
    if (word.isNotBlank()){
        temp.add(word)
    }
    return temp
}

fun getWinningScratchCards(numbers: List<String>): Int{
    val winningNumbers = mutableListOf<String>()
    val scratchedNumbers = mutableListOf<String>()
    var flag = false
    for (i in numbers.indices){
        if (numbers[i] != "|" && !flag){
            winningNumbers.add(numbers[i])
        }
        if (numbers[i] == "|"){
            flag = true
            continue
        }
        if (flag){
            scratchedNumbers.add(numbers[i])
        }
    }
    var points = 0
    for (i in winningNumbers.indices){
        for (j in scratchedNumbers.indices){
            if (winningNumbers[i] == scratchedNumbers[j]){
                if (points == 0){
                    points += 1
                } else {
                    points *= 2
                }
            }
        }
    }
    return points
}



fun main() {
    val lines = mutableListOf<String>()
    val temp = loadFile("input.txt")
    for (i in temp.indices){
        lines.add(temp[i].dropWhile { it != ':' }.dropWhile { it !in '0'..'9' })
    }
    var result = 0
    for (i in lines.indices){
        result += getWinningScratchCards(getRightFormat(lines[i]))
    }
    println(result)
}
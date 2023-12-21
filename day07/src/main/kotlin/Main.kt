package org.example

import java.io.File


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun loadCards(lines: List<List<String>>): List<Pair<String, String>>{
    val result = mutableListOf<Pair<String, String>>()
    for (i in lines.indices){
        result.add(Pair(lines[i][0],lines[i][1]))
    }
    return result
}

fun getRankings(cards: List<Pair<String,String>>){
    val fiveOfKind = mutableListOf<Pair<String,String>>()
    val fourOfKind = mutableListOf<Pair<String,String>>()
    val fullHouse = mutableListOf<Pair<String,String>>()
    val threeOfKind = mutableListOf<Pair<String,String>>()
    val twoPair = mutableListOf<Pair<String,String>>()
    val onePair = mutableListOf<Pair<String,String>>()
    val highCard = mutableListOf<Pair<String,String>>()




    for (i in cards.indices){
        val map = mutableMapOf(
            'A' to 0,
            'K' to 0,
            'Q' to 0,
            'J' to 0,
            'T' to 0,
            '9' to 0,
            '8' to 0,
            '7' to 0,
            '6' to 0,
            '5' to 0,
            '4' to 0,
            '3' to 0,
            '2' to 0
        )
        for (j in cards[i].first.indices){
            for ((k,l) in map){
                if (cards[i].first[j] == k){
                    map[k] = l+1
                }
            }
        }
        for ((k,l) in map){
            if (l == 5){
                fiveOfKind.add(Pair<>)
            }
        }
    }




}


fun main() {
    var lines = loadFile("input.txt")
    val temp = mutableListOf<List<String>>()
    lines.forEach {
        temp.add(it.split(" "))
    }
    val cards = loadCards(temp)

    getRankings(cards)
}
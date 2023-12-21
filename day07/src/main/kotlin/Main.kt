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
    val fiveOfKind = mutableListOf<Pair<MutableMap<Char,Int>,String>>()
    val fourOfKind = mutableListOf<Pair<MutableMap<Char,Int>,String>>()
    val fullHouse = mutableListOf<Pair<MutableMap<Char,Int>,String>>()
    val threeOfKind = mutableListOf<Pair<MutableMap<Char,Int>,String>>()
    val twoPair = mutableListOf<Pair<MutableMap<Char,Int>,String>>()
    val onePair = mutableListOf<Pair<MutableMap<Char,Int>,String>>()
    val highCard = mutableListOf<Pair<MutableMap<Char,Int>,String>>()

    val rankings = mutableListOf<Pair<MutableMap<Char,Int>,String>>()



    for (i in cards.indices){
        var map = mutableMapOf(
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
        rankings.add(Pair(map,cards[i].second))
    }

    loop@for (i in rankings.indices){
        for ((j,k) in rankings[i].first){
            if (k == 5){
                fiveOfKind.add(rankings[i])
                continue@loop
            } else if (k == 4){
                fourOfKind.add(rankings[i])
                continue@loop
            } else if (k == 3){
                for((n,m) in rankings[i].first){
                    if (m == 2){
                        fullHouse.add(rankings[i])
                        continue@loop
                    }
                }
                threeOfKind.add(rankings[i])
                continue@loop
            } else if (k == 2){
                for((n,m) in rankings[i].first){
                    if (m == 3){
                        fullHouse.add(rankings[i])
                        continue@loop
                    }
                }
                twoPair.add(rankings[i])
                continue@loop
            }
            onePair.add(rankings[i])
        }
    }
    println(fiveOfKind)
    println(fourOfKind)
    println(fullHouse)
    println(threeOfKind)
    println(twoPair)
    println(onePair)
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
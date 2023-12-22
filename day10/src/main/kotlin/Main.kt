package org.example

import java.io.File

fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun findStartingPosition(list: List<String>): Pair<Int,Int>{
    for (i in list.indices){
        for (j in list[i].indices){
            if (list[i][j] == 'S'){
                return Pair(i,j)
            }
        }
    }
    return Pair(0,0)
}

fun checkPossibleRoutes(map: List<String>,position: Pair<Int,Int>): Boolean{
    when (map[position.first][position.second]){
        '|' -> {
            if (map[position.first-1][position.second] == 'F' || map[position.first-1][position.second] == '7'){
                return true
            }
            if (map[position.first+1][position.second] == 'L' || map[position.first+1][position.second] == 'J'){
                return true
            }
        }
        '-' -> {
            if (map[position.first][position.second-1] == 'L' || map[position.first+1][position.second-1] == 'F'){
                return true
            }
            if (map[position.first][position.second+1] == 'J' || map[position.first+1][position.second+1] == '7'){
                return true
            }
        }
        'L' -> {
            if (map[position.first-1][position.second] != 'L' && map[position.first-1][position.second] != '.' && map[position.first-1][position.second] != '-' && map[position.first-1][position.second] != 'S'){
                return true
            }
            if (map[position.first][position.second+1] != 'L' && map[position.first][position.second+1] != '.' && map[position.first][position.second+1] != '|' && map[position.first][position.second+1] != 'S'){
                return true
            }
        }
        'J' -> {
            if (map[position.first-1][position.second] != 'J' && map[position.first-1][position.second] != '.' && map[position.first-1][position.second] != '-' && map[position.first-1][position.second] != 'S'){
                return true
            }
            if (map[position.first][position.second-1] != 'J' && map[position.first][position.second-1] != '.' && map[position.first][position.second-1] != '|' && map[position.first][position.second-1] != 'S'){
                return true
            }
        }
        '7' -> {
            if (map[position.first][position.second-1] != '7' && map[position.first][position.second-1] != '.' && map[position.first][position.second-1] != '|' && map[position.first][position.second-1] != 'S'){
                return true
            }
            if (map[position.first-1][position.second] != '7' && map[position.first-1][position.second] != '.' && map[position.first-1][position.second] != '|' && map[position.first-1][position.second] != 'S'){
                return true
            }
        }
        'F' -> {
            if (map[position.first][position.second+1] != 'F' && map[position.first][position.second+1] != '.' && map[position.first][position.second+1] != '|' && map[position.first][position.second+1] != 'S'){
                return true
            }
            if (map[position.first-1][position.second] != 'F' && map[position.first-1][position.second] != '.' && map[position.first-1][position.second] != '|' && map[position.first-1][position.second] != 'S'){
                return true
            }
        }
    }
    return false
}





fun main() {
    val map = loadFile("input.txt")
    val posOfS = findStartingPosition(map)
    println(map[posOfS.first][posOfS.second])

}
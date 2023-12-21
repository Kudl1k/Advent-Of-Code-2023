package org.example

import java.io.File

fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

data class node(
    val code: String,
    val left: String,
    val right: String
)

fun loadNode(line: String): node{
    var code = ""
    var left = ""
    var right = ""

    for (i in line.indices){
        if (line[i] in 'A'..'Z'){
            if (code.length != 3){
                code += line[i]
            } else if (left.length != 3){
                left += line[i]
            } else if (right.length != 3){
                right += line[i]
            }
        }
    }
    return node(code,left, right)
}

fun navigate(direction: String, nodes: List<node>) {
    val nodeMap = nodes.associateBy { it.code }

    var result = 0
    var current = "AAA"

    var i = 0
    while (current != "ZZZ") {
        if (i > direction.length - 1) {
            i = 0
        }

        val dir = direction[i]
        val currentNode = nodeMap[current] ?: error("Node not found")

        when (dir) {
            'L' -> current = currentNode.left
            'R' -> current = currentNode.right
        }

        result++
        i++
    }

    println(result)
}


fun main() {
    val direction = loadFile("input.txt").get(0)
    val nodes = mutableListOf<node>()

    loadFile("input.txt").drop(2).forEach {
        nodes.add(loadNode(it))
    }
    navigate(direction,nodes)

}
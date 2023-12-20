package org.example

import java.io.File
import java.math.BigInteger


fun loadFile(fileName: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(fileName).inputStream()
    inputStream.bufferedReader().forEachLine { lines.add(it) }
    return lines
}

fun getSeeds(line: String):List<BigInteger>{
    val temp = line.dropWhile { it != ':' }.drop(2).split(" ")
    val list: MutableList<BigInteger> = mutableListOf<BigInteger>()
    temp.forEach {
        list.add(it.toBigInteger())
    }
    return list
}

fun loadMap(mapName: String,lines: List<String>): List<Triple<BigInteger,BigInteger,BigInteger>>{
    val mapValues: MutableList<Triple<BigInteger,BigInteger,BigInteger>> = mutableListOf()
    var foundMap = false
    for (i in lines.indices){
        if (lines[i] == mapName){
            foundMap = true
            continue
        }
        if (foundMap && lines[i].isNotBlank()){
            val temp = lines[i].split(" ")
            mapValues.add(Triple(temp[0].toBigInteger(),temp[1].toBigInteger(),temp[2].toBigInteger()))
        }
        if (foundMap && lines[i].isBlank()){
            break
        }
    }
    return mapValues
}

fun convert(seeds: List<BigInteger>,map: List<Triple<BigInteger,BigInteger,BigInteger>>): List<BigInteger>{
    val result = mutableListOf<BigInteger>()

    loop@for (i in seeds.indices){
        for (j in map.indices){
            if (seeds[i] in map[j].second .. map[j].second+map[j].third-(1).toBigInteger()){
                val difference = seeds[i] - map[j].second
                result.add(map[j].first + difference)
                continue@loop
            }
        }
        result.add(seeds[i])
    }
    return result
}



fun main() {
    val lines = loadFile("input.txt")
    var seeds = getSeeds(lines[0])
    val seedSoilMap = loadMap("seed-to-soil map:",lines)
    val soilFertMap = loadMap("soil-to-fertilizer map:",lines)
    val fertWaterMap = loadMap("fertilizer-to-water map:",lines)
    val waterLightMap = loadMap("water-to-light map:",lines)
    val lightTempMap = loadMap("light-to-temperature map:",lines)
    val tempHumidMap = loadMap("temperature-to-humidity map:",lines)
    val humidLocMap = loadMap("humidity-to-location map:",lines)
    seeds = convert(seeds,seedSoilMap)
    seeds = convert(seeds,soilFertMap)
    seeds = convert(seeds,fertWaterMap)
    seeds = convert(seeds,waterLightMap)
    seeds = convert(seeds,lightTempMap)
    seeds = convert(seeds,tempHumidMap)
    seeds = convert(seeds,humidLocMap)
    println(seeds.min())


}
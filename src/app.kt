fun main() {
    println("Convert 5.5 kg to Pounds: ${getConversionLambda("KgsToPounds")(5.5)}")
    val kgToPointsLambda = getConversionLambda("KgsToPounds")
    val poundsToTonnsLambda = getConversionLambda("PoundsToTonns")
    val kgToTonnsLambda = combine(kgToPointsLambda, poundsToTonnsLambda)
    val value = 58.8
    println("$value kgs is ${convert(value, kgToTonnsLambda)} tonns")
}

typealias DoubleConversion = (Double) -> Double

fun convert(x: Double, converter: DoubleConversion): Double {
    val result = converter(x)
    //println("$x is converted to $result")
    return result
}

fun getConversionLambda(str: String): DoubleConversion {
    if (str == "CentigradeToFahrenheit") {
        return { it * 1.8 + 32 }
    } else if (str == "KgsToPounds") {
        return { it * 2.204623 }
    } else if (str == "PoundsToTonns") {
        return { it / 2000.0 }
    } else {
        return { it }
    }
}

fun combine(lambda1: DoubleConversion, lambda2: DoubleConversion): DoubleConversion {
    return { x: Double -> lambda2(lambda1(x)) }
}
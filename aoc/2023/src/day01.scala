package day01

import locations.Directory.currentDir
import inputs.Input.loadFileSync

@main def part1: Unit =
  println(s"The solution is ${part1(loadInput())}")

@main def part2: Unit =
  println(s"The solution is ${part2(loadInput())}")

def loadInput(): String = loadFileSync(s"$currentDir/../input/day01")

def part1(input: String): String =
  def lineToCoordinates(line: String): Int =
    val firstDigit = line.find(_.isDigit).get
    val lastDigit = line.findLast(_.isDigit).get
    s"$firstDigit$lastDigit".toInt

  val result = input
    .linesIterator
    .map(lineToCoordinates(_))
    .sum
  result.toString()
end part1

val stringDigitReprs = Map(
  "one" -> 1,
  "two" -> 2,
  "three" -> 3,
  "four" -> 4,
  "five" -> 5,
  "six" -> 6,
  "seven" -> 7,
  "eight" -> 8,
  "nine" -> 9,
)

val digitReprs = stringDigitReprs ++ (1 to 9).map(i => i.toString() -> i)

def part2(input: String): String =
  val digitReprRegex = digitReprs.keysIterator.mkString("|").r

  def lineToCoordinates(line: String): Int =
    val matchesIter =
      for
        lineTail <- line.tails
        oneMatch <- digitReprRegex.findPrefixOf(lineTail)
      yield
        oneMatch
    val matches = matchesIter.toList

    val firstDigit = digitReprs(matches.head)
    val lastDigit = digitReprs(matches.last)
    s"$firstDigit$lastDigit".toInt
  end lineToCoordinates

  val result = input
    .linesIterator
    .map(lineToCoordinates(_))
    .sum
  result.toString()
end part2

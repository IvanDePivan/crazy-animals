import dijkstra.Dijkstra
import dijkstra.Move
import dijkstra.Node

fun main(args : Array<String>) {
//    val scanner = Scanner(System.`in`)
//    val n = scanner.nextInt()

    val puzzle = "adbecf"
    val largestSolution = "abcdefghi"

    val target = largestSolution.substring(0, puzzle.length)

    val source = Node(puzzle, Move.NONE)

    Dijkstra.calculateShortestPath(source, puzzle, target)

    // A puzzle is a set of characters (a,b,c,d,e,f,g,h,i)
    // Moves (edges) are a,b,x
}
package dijkstra

import java.lang.IllegalArgumentException
import java.util.HashMap
import java.util.LinkedList

class Node(oldState:String, var move: Move) {

    var state: String

    init {
        state = nextState(oldState)
    }

    var shortestPath = LinkedList<Node>()

    var distance: Int = Integer.MAX_VALUE

    var adjacentNodes: MutableMap<Node, Int> by NodeListDelegate()

    fun nextState(currentState:String): String {
        return when(move) {
            Move.A -> swapfirstAndSecond(currentState)
            Move.B -> swapLastTwo(currentState)
            Move.X -> rotateRight(currentState)
            Move.NONE -> currentState
        }
    }

    private fun swapfirstAndSecond(currentState: String): String {
        val result = currentState[1].toString() + currentState[0].toString() + currentState.substring(2)
        return result
    }

    private fun swapLastTwo(currentState: String): String {
        val result = currentState.substring(0, currentState.length-2) + currentState[currentState.length - 1].toString() + currentState[currentState.length - 2].toString()
        return result
    }

    private fun rotateRight(currentState:String): String {
        val subString = currentState.substring(1, currentState.length-1)
        val builder = StringBuilder()

        builder.append(subString[subString.length-1])
        builder.append(subString.substring(0, subString.length-1))

        return currentState.replaceRange(1, currentState.length-1, builder.toString())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        return state.hashCode()
    }


}
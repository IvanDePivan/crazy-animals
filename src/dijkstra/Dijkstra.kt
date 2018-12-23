package dijkstra

import java.util.HashSet
import java.util.LinkedList

object Dijkstra {

    fun calculateShortestPath(source: Node, initialState: String, target: String) {
        if(initialState.equals(target)) {
            println("0")
            return
        }

        source.distance = 0

        val settledNodes = ArrayList<Node>()
        val unsettledNodes = HashSet<Node>()
        var currentState: String
        var bestDistance = Int.MAX_VALUE
        var targetNode = source
        unsettledNodes.add(source)

        while (unsettledNodes.size > 0) {
            val currentNode = unsettledNodes.first()
            unsettledNodes.remove(currentNode)

            currentState = currentNode.state

            for ((key, value) in currentNode.adjacentNodes) {
                val adjacentNode = key
                val edgeWeight = value

                val existingNode:Node? = settledNodes.getOrNull(settledNodes.indexOf(adjacentNode))
                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode)
                if (existingNode == null) {
                    unsettledNodes.add(adjacentNode)
                } else if(adjacentNode.shortestPath.size < existingNode.shortestPath.size) {
                    unsettledNodes.add(adjacentNode)
                }
            }
            settledNodes.add(currentNode)
            if(currentState.equals(target) && currentNode.shortestPath.size < bestDistance) {
                targetNode = currentNode
                bestDistance = targetNode.shortestPath.size
            }
        }
        val moves = targetNode.shortestPath.map { node -> node.move.type }
            .reduce {acc, element -> acc + element}
            .plus(targetNode.move.type)
        println("${moves.length} ${moves}")
    }

    private fun calculateMinimumDistance(evaluationNode: Node, edgeWeight: Int, sourceNode: Node) {
        val sourceDistance = sourceNode.distance
        if (sourceDistance + edgeWeight < evaluationNode.distance) {
            evaluationNode.distance = sourceDistance + edgeWeight
            val shortestPath = LinkedList(sourceNode.shortestPath)
            shortestPath.add(sourceNode)
            evaluationNode.shortestPath = shortestPath
        }
    }
}
package dijkstra

import java.util.HashMap
import kotlin.reflect.KProperty

class NodeListDelegate {
    operator fun getValue(thisRef: Node, property: KProperty<*>): MutableMap<Node, Int> {
        val nodes: MutableMap<Node, Int> = HashMap()
        nodes.put(Node(thisRef.state, Move.A), 1)
        nodes.put(Node(thisRef.state, Move.B), 1)
        nodes.put(Node(thisRef.state, Move.X), 1)

        return nodes
    }

    operator fun setValue(thisRef: Node, property: KProperty<*>, value: MutableMap<Node, Int>) {
        thisRef.adjacentNodes = value
    }
}
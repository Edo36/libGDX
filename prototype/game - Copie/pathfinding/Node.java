package com.rpg.game.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Andrei on 15.08.2016.
 */
    public class Node implements IndexedGraph<Node> {

    @Override
    public int getIndex(Node node) {
        return 0;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        return null;
    }
}

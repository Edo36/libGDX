package com.anotsosimple.game.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Heuristic;

/**
 * Created by Andrei on 09.10.2016.
 */
public class TiledManhattanDistance implements Heuristic<TiledNode> {

    @Override
    public float estimate(TiledNode startNode, TiledNode endNode) {
        return Math.abs(endNode.x - startNode.x) + Math.abs(endNode.y - startNode.y);
    }
}
package com.anotsosimple.game.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;

/**
 * Created by Andrei on 09.10.2016.
 */
public class TiledPathFinding {

    public static void calculatePath(IndexedAStarPathFinder pathFinder, DefaultGraphPath<TiledNode> path, TiledGraph tiledGraph, TiledManhattanDistance manhattanDistance) {
        TiledNode startNode = tiledGraph.nodes.get(0);
        TiledNode endNode = tiledGraph.nodes.get(111);

        path.clear();

        pathFinder.searchNodePath(startNode, endNode, manhattanDistance, path);

        if (path.nodes.size == 0) {
            System.out.println("-----No path found-----");
        } else {
            System.out.println("-----Found path-----");
        }

        // loop through every node in the solution and select it
        for (TiledNode node : path.nodes) {
            node.select();
            System.out.println(node);
        }

        System.out.print(path.nodes.size);
    }
}

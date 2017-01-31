package com.rpg.game.pathfinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.PathFinderRequest;

/**
 * Created by Andrei on 15.08.2016.
 */
public class Pathfinding implements PathFinder {

    @Override
    public boolean searchNodePath(Object startNode, Object endNode, Heuristic heuristic, GraphPath outPath) {
        return false;
    }

    @Override
    public boolean searchConnectionPath(Object startNode, Object endNode, Heuristic heuristic, GraphPath outPath) {
        return false;
    }

    @Override
    public boolean search(PathFinderRequest request, long timeToRun) {
        return false;
    }
}

package com.anotsosimple.game.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Andrei on 09.10.2016.
 */
public class TiledNode {
    public int x, y, index;
    boolean blocked;
    boolean selected;

    private Array<Connection<TiledNode>> connections = new Array<Connection<TiledNode>>();

    public TiledNode(int x, int y, boolean blocked, int index) {
        this.x = x;
        this.y = y;
        this.blocked = blocked;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Array<Connection<TiledNode>> getConnections() {
        return connections;
    }

    public void addConnection(TiledGraph tiledGraph, TiledNode toTiledNode, float cost) {
        if (!toTiledNode.isBlocked())
            connections.add(new TiledConnection(tiledGraph, this, toTiledNode, cost));
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void select() {
        selected = true;
    }
}
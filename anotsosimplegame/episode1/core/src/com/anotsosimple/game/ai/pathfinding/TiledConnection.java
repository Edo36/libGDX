package com.anotsosimple.game.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.DefaultConnection;

/**
 * Created by Andrei on 09.10.2016.
 */
public class TiledConnection extends DefaultConnection<TiledNode> {
    static final float DIAGONAL_COST = (float)Math.sqrt(2);
    static final float NORMAL_COST = 1f;

    private float cost;

    TiledGraph worldMap;

    public TiledConnection(TiledGraph worldMap, TiledNode fromNode, TiledNode toNode, float cost) {
        super(fromNode, toNode);
        this.worldMap = worldMap;
        this.cost = cost;
    }

    @Override
    public float getCost() {
        return cost;
    }
}

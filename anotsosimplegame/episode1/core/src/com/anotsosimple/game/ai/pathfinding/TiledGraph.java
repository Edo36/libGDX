package com.anotsosimple.game.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Andrei on 09.10.2016.
 */
public class TiledGraph implements IndexedGraph<TiledNode> {

    public Array<TiledNode> nodes;
    public com.anotsosimple.game.ai.pathfinding.TiledNode startTiledNode;

    public int columns;
    public int rows;

    public TiledGraph(TiledMap map) {
        parseTiledMap(map);
        createConnections();
        this.startTiledNode = null;
    }

    private void createConnections() {
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++) {

                com.anotsosimple.game.ai.pathfinding.TiledNode n = nodes.get(row * columns + col);

                if (n.isBlocked())
                    continue;


                // left, right
                if (col > 0)
                    n.addConnection(this, nodes.get(row * columns + col - 1), TiledConnection.NORMAL_COST);
                if (col < columns - 1)
                    n.addConnection(this, nodes.get(row * columns + col + 1), TiledConnection.NORMAL_COST);

                // down, up
                if (row > 0)
                    n.addConnection(this, nodes.get((row - 1) * columns + col), TiledConnection.NORMAL_COST);
                if (row < rows - 1)
                    n.addConnection(this, nodes.get((row + 1) * columns + col), TiledConnection.NORMAL_COST);

                // diagonal neighbours
                // upper left
                if (col > 0 && row < rows - 1)
                    n.addConnection(this, nodes.get((row + 1) * columns + col - 1), TiledConnection.DIAGONAL_COST);
                // upper right
                if (col < columns - 1 && row < rows - 1)
                    n.addConnection(this, nodes.get((row + 1) * columns + col + 1), TiledConnection.DIAGONAL_COST);
                // lower left
                if (col > 0 && row > 0)
                    n.addConnection(this, nodes.get((row - 1) * columns + col - 1), TiledConnection.DIAGONAL_COST);
                // lower right
                if (col < columns - 1 && row > 0)
                    n.addConnection(this, nodes.get((row - 1) * columns + col + 1), TiledConnection.DIAGONAL_COST);
            }
    }

    public com.anotsosimple.game.ai.pathfinding.TiledNode getNode(int x, int y) {
        return nodes.get(x * columns + y);
    }

    public com.anotsosimple.game.ai.pathfinding.TiledNode getNode(int index) {
        return nodes.get(index);
    }

    @Override
    public int getIndex(com.anotsosimple.game.ai.pathfinding.TiledNode tiledNode) {
        return tiledNode.getIndex();
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    @Override
    public Array<Connection<TiledNode>> getConnections(com.anotsosimple.game.ai.pathfinding.TiledNode fromNode) {
        return fromNode.getConnections();
    }

    // map parser
    private void parseTiledMap(TiledMap map) {

        // get the layer called blocked, from the .tmx file
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(0);

        columns = tileLayer.getWidth();
        rows = tileLayer.getHeight();

        nodes = new Array<com.anotsosimple.game.ai.pathfinding.TiledNode>(columns * rows);

        int tileWidth = (int) tileLayer.getTileWidth();
        int tileHeight = (int) tileLayer.getTileHeight();

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++) {

                TiledMapTileLayer.Cell cell = tileLayer.getCell(col, row);

                boolean blocked = cell.getTile().getProperties().containsKey("blocked");
                int index = row * columns + col;

                nodes.add(new com.anotsosimple.game.ai.pathfinding.TiledNode(col * tileWidth, row * tileHeight, blocked, index));
                //nodes.add(new TiledNode(col, row, blocked, index));
            }
    }

}

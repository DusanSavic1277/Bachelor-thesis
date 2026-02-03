package pathfinding;

import java.util.ArrayList;
import rs.ac.singidunum.GamePanel;

public class PathFinder {

    GamePanel gp;
    
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    //Pravljenje cvorova(nodes) za savki tile na mapi
    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                node[col][row] = new Node(col, row);
            }
        }
    }

    //Restartovanje prethodnog rezultata 
    public void resetNodes() {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                
                //Restartovanje podataka cvora(node)
                node[col][row].open = false;
                node[col][row].checked = false;
                node[col][row].solid = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        //Podesavanje pocetnih i ciljnih cvorova(node)
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        //Provera kolizije
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
                if (gp.tileM.tile[tileNum].collision) {
                    node[col][row].solid = true;
                }
                getCost(node[col][row]);
            }
        }
    }

    public void getCost(Node node) {
        node.gCost = Math.abs(node.col - startNode.col) + Math.abs(node.row - startNode.row);
        node.hCost = Math.abs(node.col - goalNode.col) + Math.abs(node.row - goalNode.row);
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            openList.remove(currentNode);
            currentNode.checked = true;

            // Check neighbors
            openNeighbor(currentNode.col, currentNode.row - 1);
            openNeighbor(currentNode.col - 1, currentNode.row);
            openNeighbor(currentNode.col, currentNode.row + 1);
            openNeighbor(currentNode.col + 1, currentNode.row);

            // Find the best node
            Node bestNode = null;
            for (Node node : openList) {
                if (bestNode == null || node.fCost < bestNode.fCost
                        || (node.fCost == bestNode.fCost && node.gCost < bestNode.gCost)) {
                    bestNode = node;
                }
            }
            if (bestNode == null) break;

            currentNode = bestNode;
            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }

            step++;
        }
        return goalReached;
    }

    private void openNeighbor(int col, int row) {
        if (col >= 0 && col < gp.maxWorldCol && row >= 0 && row < gp.maxWorldRow) {
            Node neighbor = node[col][row];
            if (!neighbor.open && !neighbor.checked && !neighbor.solid) {
                neighbor.open = true;
                neighbor.parent = currentNode;
                openList.add(neighbor);
            }
        }
    }

    public void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

}
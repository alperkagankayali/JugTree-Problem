import com.scalified.tree.TreeNode;
import com.scalified.tree.multinode.ArrayMultiTreeNode;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.layout.HierarchicalLayout;



public class Main {
    static int red = 0;
    static int green = 100;
    static int blue = 255;
    static int id = 0;
    static Graph graph = new SingleGraph("Jug Tree");
    public static boolean traceback(TreeNode<String> node){
        boolean flag = true;
        TreeNode<String> tempnode = node.parent();
        for(;tempnode != null;){
            if(getJug1(tempnode) == getJug1(node) && getJug2(tempnode) == getJug2(node)){
                flag = false;
                break;
            }
            else{
                tempnode = tempnode.parent();
            }
        }
        return flag;
    }

    public static int getJug1(TreeNode<String> currentNode){
        int x = Integer.parseInt(currentNode.data().substring(5,9).replaceAll("[\\D]", ""));
        return x;
    }

    public static int getJug2(TreeNode<String> currentNode){
        int x = Integer.parseInt(currentNode.data().substring(9,18).replaceAll("[\\D]", ""));
        return x;
    }

    public static void fillFirstJug(TreeNode<String> currentNode) {
        if (getJug1(currentNode) < 10 && getJug1(currentNode)!=8) {
            TreeNode<String> newNode = new ArrayMultiTreeNode<String>("jugone:" + 10 + "/jugtwo:" + getJug2(currentNode)+"/id:" + id);
            currentNode.add(newNode);
            if (!traceback(newNode)) {
                currentNode.remove(newNode);

            } else {
                id++;
                graph.addNode(newNode.data());
                graph.addEdge(currentNode.data()+newNode.data(),currentNode.data(),newNode.data());
                Node n = graph.getNode(newNode.data());
                n.addAttribute("ui.label",newNode.data());
                solutionColoring(newNode);
                System.out.println(newNode.data());
                fillFirstJug(newNode);

            }
        }
        emptyFirstJug(currentNode);
    }
    public static void emptyFirstJug(TreeNode<String> currentNode) {
        if (getJug1(currentNode) > 0 && getJug1(currentNode)!=8) {
            TreeNode<String> newNode = new ArrayMultiTreeNode<String>("jugone:" + 0 + "/jugtwo:" + getJug2(currentNode)+"/id:" + id);
            currentNode.add(newNode);
            if (!traceback(newNode)) {
                currentNode.remove(newNode);

            } else {
                id++;
                graph.addNode(newNode.data());
                graph.addEdge(currentNode.data()+newNode.data(),currentNode.data(),newNode.data());
                Node n = graph.getNode(newNode.data());
                n.addAttribute("ui.label",newNode.data());
                solutionColoring(newNode);
                System.out.println(newNode.data());
                fillFirstJug(newNode);

            }
        }
        fillSecondJug(currentNode);
    }
    public static void fillSecondJug(TreeNode<String> currentNode){
        if(getJug2(currentNode) < 6 && getJug1(currentNode)!=8){
            TreeNode<String> newNode = new ArrayMultiTreeNode<String>("jugone:" + getJug1(currentNode) + "/jugtwo:" + 6 +"/id:" + id);
            currentNode.add(newNode);
            if(!traceback(newNode)){
                currentNode.remove(newNode);

            }
            else{
                id++;
                graph.addNode(newNode.data());
                graph.addEdge(currentNode.data()+newNode.data(),currentNode.data(),newNode.data());
                Node n = graph.getNode(newNode.data());
                n.addAttribute("ui.label",newNode.data());
                solutionColoring(newNode);
                System.out.println(newNode.data());
                fillFirstJug(newNode);

            }
        }
        emptySecondJug(currentNode);
    }
    public static void emptySecondJug(TreeNode<String> currentNode){
        if (getJug2(currentNode) > 0 && getJug1(currentNode)!=8) {

            TreeNode<String> newNode = new ArrayMultiTreeNode<String>("jugone:" + getJug1(currentNode) + "/jugtwo:" + 0+"/id:" + id);
            currentNode.add(newNode);
            if (!traceback(newNode)) {
                currentNode.remove(newNode);

            } else {
                id++;
                graph.addNode(newNode.data());
                graph.addEdge(currentNode.data()+newNode.data(),currentNode.data(),newNode.data());
                Node n = graph.getNode(newNode.data());
                n.addAttribute("ui.label",newNode.data());
                solutionColoring(newNode);
                System.out.println(newNode.data());
                fillFirstJug(newNode);

            }
        }
        moveFirstJug(currentNode);
    }
    public static void moveFirstJug(TreeNode<String> currentNode) {
        if (getJug1(currentNode) < 10 && getJug2(currentNode) > 0) {
            TreeNode<String> newNode;
            if(getJug1(currentNode) + getJug2(currentNode) <10)
                newNode = new ArrayMultiTreeNode<String>("jugone:" + (getJug1(currentNode)+getJug2(currentNode)) + "/jugtwo:" +0+"/id:" + id);
            else
                newNode = new ArrayMultiTreeNode<String>("jugone:" + 10 + "/jugtwo:" +(getJug2(currentNode)+getJug1(currentNode)-10)+"/id:" + id);
            currentNode.add(newNode);
            if (!traceback(newNode)) {
                currentNode.remove(newNode);

            } else {
                id++;
                graph.addNode(newNode.data());
                graph.addEdge(currentNode.data()+newNode.data(),currentNode.data(),newNode.data());
                Node n = graph.getNode(newNode.data());
                n.addAttribute("ui.label",newNode.data());
                solutionColoring(newNode);
                System.out.println(newNode.data());
                fillFirstJug(newNode);

            }
        }
        moveSecondJug(currentNode);
    }
    public static void moveSecondJug(TreeNode<String> currentNode){
        if (getJug2(currentNode) < 6 && getJug1(currentNode) > 0) {
            TreeNode<String> newNode;
            if(getJug1(currentNode) + getJug2(currentNode) >6)
                newNode = new ArrayMultiTreeNode<String>("jugone:" + (getJug2(currentNode) + getJug1(currentNode) - 6) + "/jugtwo:" + 6+"/id:" + id);
            else
                newNode = new ArrayMultiTreeNode<String>("jugone:" + 0 + "/jugtwo:" +(getJug1(currentNode)+getJug2(currentNode))+"/id:" + id);
            currentNode.add(newNode);
            if (!traceback(newNode)) {
                currentNode.remove(newNode);

            } else {
                id++;
                graph.addNode(newNode.data());
                graph.addEdge(currentNode.data()+newNode.data(),currentNode.data(),newNode.data());
                Node n = graph.getNode(newNode.data());
                n.addAttribute("ui.label",newNode.data());
                solutionColoring(newNode);
                System.out.println(newNode.data());
                fillFirstJug(newNode);

            }
        }
    }
    public static void createTree(TreeNode<String> node){
        fillFirstJug(node);
    }

    public static void solutionColoring(TreeNode<String> currentNode){
        if(getJug1(currentNode) == 8){
            TreeNode<String> tempnode = currentNode;

            for(;!tempnode.data().equals("jugone:10/jugtwo:6/id:0");){
                Node n = graph.getNode(tempnode.data());
                n.setAttribute("ui.style","fill-color: rgb(" + red + "," + green + "," + blue+");");
                tempnode = tempnode.parent();
            }
            red = red + 10;
            blue = blue - 10;
        }


    }

    public static void main(String[] args) {
        Viewer viewer = graph.display();
        HierarchicalLayout hl = new HierarchicalLayout();
        viewer.enableAutoLayout(hl);
        TreeNode<String> root = new ArrayMultiTreeNode<String>("jugone:10/jugtwo:6"+"/id:" + id);
        graph.addNode(root.data());
        Node n = graph.getNode(root.data());
        n.setAttribute("ui.class", "big, important");
        createTree(root);
        System.out.println(root.size());
        graph.display();

    }
}

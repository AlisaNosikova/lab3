/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Readers.Reactor;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author User
 */
public class MyTreeModel implements TreeModel {
    private Node root;
    private Node reactorsNode;

 public MyTreeModel() {
    root = new Node("Корневая запись");      
    reactorsNode = new Node("Реакторы");
    root.addChild(reactorsNode);
    
       
}
    public void addReactorsList(ArrayList<Reactor> list){
        addNode(list);
    }
     public void addNode(ArrayList<Reactor> list){
      for (Reactor reactor: list){
        Node node1 = new Node(reactor.getClassName());
        reactorsNode.addChild(node1);
        node1.addChild(new Node("Burnup: " + String.valueOf(reactor.getBurnup())));     
        node1.addChild(new Node("Kpd: " + String.valueOf(reactor.getKpd())));
        node1.addChild(new Node("Enrichment: " + String.valueOf(reactor.getEnrichment())));
        node1.addChild(new Node("Termal Capacity: " + String.valueOf(reactor.getTermal_capacity())));
        node1.addChild(new Node("Electrical Capacity: " + String.valueOf(reactor.getElectrical_capacity())));
        node1.addChild(new Node("Life time: " + String.valueOf(reactor.getLife_time())));
        node1.addChild(new Node("First load: " + String.valueOf(reactor.getFirst_load())));
        node1.addChild(new Node("Источник: " + reactor.getSource()));
    }

 }
    
    @Override
    public Object getRoot() {
        return root;
    }
  
    @Override
    public int getChildCount(Object parent) {
        Node node = (Node) parent;
        return node.getChildren().size();
    }

    @Override
    public Object getChild(Object parent, int index) {
        Node node = (Node) parent;
        return node.getChildren().get(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Node node = (Node) parent;
        return node.getChildren().indexOf(child);
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((Node) node).getChildren().isEmpty();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
}

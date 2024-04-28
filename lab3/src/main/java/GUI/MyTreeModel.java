/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Readers.Reactor;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author User
 */
public class MyTreeModel implements TreeModel {
    private ArrayList<Reactor> reactorsList;
    private Node root;

 public MyTreeModel() {
    root = new Node("Корневая запись");
        
    Node studentsNode = new Node("Реакторы");

    root.addChild(studentsNode);
}
    public void setReactorsList(ArrayList<Reactor> list){
        this.reactorsList = list;
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

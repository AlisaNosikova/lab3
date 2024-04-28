/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.ArrayList;

/**
 *
 * @author User
 */
  class Node {
        private String name;
        private ArrayList<Node> children;

        public Node(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            children.add(child);
        }

        public ArrayList<Node> getChildren() {
            return children;
        }
    public String toString() {
        return name;
    }
     
    }
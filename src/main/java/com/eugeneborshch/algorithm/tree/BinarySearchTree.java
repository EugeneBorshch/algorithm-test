package com.eugeneborshch.algorithm.tree;

import java.util.Comparator;

/**
 * Binary search tree implementation
 * <p/>
 * User: Eugene Borshch
 */

public class BinarySearchTree<T extends Comparable> {

    private BSTNode<T> root;

    public BinarySearchTree(BSTNode<T> root) {
        this.root = root;
    }

    public void add(BSTNode<T> newNode) {
        if (root == null) {
            root = newNode;
        } else {
            insert(root, newNode);
        }
    }

    private void insert(BSTNode<T> currentNode, BSTNode<T> newNode) {

        int compVal = newNode.getValue().compareTo( currentNode.getValue());
        if (compVal == 0)
            return;

        if (compVal < 0) {
            if (currentNode.getLeft() == null) {
                currentNode.setLeft(newNode);
            } else {
                insert(currentNode.getLeft(), newNode);
            }
        }

        if (compVal > 0) {
            if (currentNode.getRight() == null) {
                currentNode.setRight(newNode);
            } else {
                insert(currentNode.getRight(), newNode);
            }
        }
    }


    public void inOrderTraversal(Callback<T> callback) {
        inOrderTraversal(root, callback);

    }

    private void inOrderTraversal(BSTNode<T> currentNode, Callback<T> callback) {
        if (currentNode == null)
            return;

        inOrderTraversal(currentNode.getLeft(), callback);
        callback.execute(currentNode);
        inOrderTraversal(currentNode.getRight(), callback);
    }

    public static interface Callback<T extends Comparable> {

        public void execute(BSTNode<T> node);
    }


}

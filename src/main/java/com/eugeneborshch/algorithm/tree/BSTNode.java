package com.eugeneborshch.algorithm.tree;

import java.util.Comparator;

/**
 * Node of the BST
 * User: Eugene Borshch
 */
public class BSTNode<T extends Comparable> {

    private T value;

    private BSTNode<T> left;
    private BSTNode<T> right;

    public BSTNode(T value) {
        this.value = value;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BSTNode bstNode = (BSTNode) o;

        if (value != null ? !value.equals(bstNode.value) : bstNode.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}

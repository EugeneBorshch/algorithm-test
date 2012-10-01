package com.eugeneborshch.algorithm.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

/**
 * BST test
 * User: Eugene Borshch
 */
public class BSTTest {

    BinarySearchTree<Integer> tree;

    @Before
    public void init() {

        BSTNode<Integer> root = new BSTNode<Integer>(10);
        tree = new BinarySearchTree<Integer>(root);
        tree.add(new BSTNode<Integer>(3));
        tree.add(new BSTNode<Integer>(4));
        tree.add(new BSTNode<Integer>(1));
        tree.add(new BSTNode<Integer>(20));
        tree.add(new BSTNode<Integer>(11));
        tree.add(new BSTNode<Integer>(16));
        tree.add(new BSTNode<Integer>(7));

    }


    @Test
    public void testInOrderTraversal() {

        final ArrayList<Integer> result = new ArrayList<Integer>();

        BinarySearchTree.Callback<Integer> callback = new BinarySearchTree.Callback<Integer>() {

            @Override
            public void execute(BSTNode<Integer> node) {
                result.add(node.getValue());
            }
        };

        tree.inOrderTraversal(callback);


        assertArrayEquals(new Integer[]{1, 3, 4, 7, 10, 11, 16, 20}, result.toArray());

    }
}

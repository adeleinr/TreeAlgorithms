package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *  This is a simple implementation of a Binary Search Tree with the most
 *  common operations.
 *
 *  @author Adelein Rodriguez
 */
public class BST <Value extends Comparable<Value>>{
	
	private Node<Value> root;
	
	public BST(Value value){
		root = new Node<Value>(value);
	}
	public BST(){
		root = null;
	}
	
	/**
	 * Inserts a node into the existing tree.
	 *
	 * @param  newNode node to add
	 * @return whether or not it could add the node
	 */
	public boolean insert(Node<Value> newNode){
		if(newNode == null) return false;
		Node<Value> tree = insert(root, newNode);
		if( root == null){
			root = tree;
		}
		
		return ( tree != null);		
	}
	
	/**
	 * Inserts recursive helper to add a node into the existing tree.
	 *
	 * @param  currNode current node in the tree we are comparing the new node
	 *         against
	 * @param  newNode node to add
	 * @return whether or not it could add the node
	 */
	private Node<Value> insert(Node<Value> currNode, Node<Value> newNode){

		if(currNode == null){
			return new Node<Value>(newNode.value);
		}
		else if (newNode.value.compareTo(currNode.value) < 0){
			currNode.left = insert(currNode.left, newNode);
		}else{
			currNode.right = insert(currNode.right, newNode);
		}
		
		return currNode; 
		
	}
	
	/**
	 * Prints a tree in order (increasing)
	 */
	public void printInOrder(){
		ArrayList<Node<Value>> result = new ArrayList<Node<Value>>();
		printInOrder(this.root, result);
		System.out.println(result);
	}
	
	/**
	 * Print recursive helper
	 * 
	 * @param currNode current node to be printed
	 */
	public void printInOrder(
			Node<Value> currNode, ArrayList<Node<Value>> result){
		if(currNode == null){
			return;
		}
		
		printInOrder(currNode.left, result);
		result.add(currNode);
		printInOrder(currNode.right, result);
	
	}
	
	/**
	 *  Computes the sum of nodes with values between the min and max
	 *  
	 *  @param min lower bound inclusive
	 *  @param max upper bound inclusive
	 *  @return sum of nodes within the range
	 *  
	 */
	public int rangeSum(int min, int max){
		
		return rangeSum(min, max, (Node<Integer>)root);		
	}
	
	/**
	 *  Helper to compute the sum of nodes with values between the min and max
	 *  
	 *  @param min lower bound inclusive
	 *  @param max upper bound inclusive
	 *  @param node current node being considered 
	 *  @return sum of nodes within the range
	 *  
	 */
	public int rangeSum(int min, int max, Node<Integer> node){

		if( node == null){
			return 0;
		}
		int currSum = 0;
        
		if(node.value >= min && node.value <= max){
			currSum = node.value;
		}
		
        return currSum + rangeSum(min, max, node.left)
        		+ rangeSum(min, max, node.right);
		
	}
	
	/**
	 *  Creates a list of lists of all nodes by level
	 *  
	 *  @param root root node
	 *  @return list of lists of nodes per level
	 *  
	 */
	public ArrayList<LinkedList<Node<Value>>> buildLinkedListByLevel(){
		
		ArrayList<LinkedList<Node<Value>>> result =
				new ArrayList<LinkedList<Node<Value>>>();
		LinkedList<Node<Value>> thisLevel =
				new LinkedList<Node<Value>>();
		result.add(thisLevel);

		LinkedList<Node<Value>> nextLevel = null;
		thisLevel.add(this.root);
		
		do{
			nextLevel = new LinkedList<Node<Value>>();
			// Process one level
			for(Node<Value> n : thisLevel){
				if ( n.left != null){
					nextLevel.add(n.left);
				}
				if ( n.right != null){
					nextLevel.add(n.right);
				}				
			}
			if(!nextLevel.isEmpty()){
				result.add(nextLevel);
			}
			thisLevel = nextLevel;
		}while(thisLevel.size() > 0);
		
		return result;
	}
	
	/**
	 *  Prints the nodes by level
	 *  
	 */
	public void printByLevel(){

	    if(this.root == null){
	        return;	        
	    }
	    
	    ArrayList<Node<Value>> currLevelList = new ArrayList<Node<Value>>();

	    currLevelList.add(root);
	    
	    while(currLevelList.size() > 0){
	        System.out.println(currLevelList);
	        ArrayList<Node<Value>> nLevelList = new ArrayList<Node<Value>>();
	        for(Node<Value> currNode: currLevelList){
	            if(currNode.left !=null){
	                nLevelList.add(currNode.left);
	            }
	            if(currNode.right != null){
	                nLevelList.add(currNode.right);
	            }	       
	        }	               
	        currLevelList = nLevelList;	        
	    }
	}
	
	/**
	 *  Prints the nodes by level using a queue
	 *    
	 */
	public void byLevel(){
		if(this.root == null){
		    return;	        
		}
		Queue<Node> level  = new LinkedList<Node>();
		level.add(root);
		while(!level.isEmpty()){
			Node node = level.poll();
			System.out.print(node + " ");
		 	if(node.left!= null){
		 		level.add(node.left);
		 	}
		 	if(node.right!= null){
		 		level.add(node.right);
		 	}
		}
	}
	
	/**
	 *  Find a node
	 *  
	 *  @param x node to be found
	 *  @return whether it found the node or not  
	 */
	public boolean find(Node<Value> x){
		if(x == null){
			return false;
		}
		
		Node<Value> foundNode = find(root,x);
		
		return foundNode != null;
	}
	
	/**
	 *  Helper recursive function to find a node
	 *  
	 *  @param currNode current node in the tree to be compared against
	 *  @param x node to be found
	 *  @return whether it found the node or not  
	 */
	private Node<Value> find(Node<Value> currNode, Node<Value> x){

		if(currNode == null){
			return null;
		}
		if( currNode.equals(x)){
			System.out.println(currNode);
			return currNode;
		}
		else if(x.value.compareTo(currNode.value) > 0){
			return find(currNode.right,x);
		}else{
			return find(currNode.left, x);

		}
		
	}
	/**
	 *  Find the parent of this node if the node were to be deleted
	 *  
	 *  @param currNode current node in the tree to be compared against
	 *  @param x node to be found
	 *  @return parent node.  
	 */
	private Node<Value> findParentNode(Node<Value> currNode, Node<Value> x){
		if(currNode == null){
			return null;
		}
		if( currNode.left.equals(x) || currNode.right.equals(x)){
			return currNode;
		}
		else if(x.value.compareTo(currNode.value) > 0){
			return find(currNode.right,x);
		}else{
			return find(currNode.left, x);

		}		
	}
	
	/**
	 *  Prints nodes alternating by level alternating from left to right  
	 */
	public void printZigZagOrder(Node<Value> root){
		Stack<Node<Value>> currLevelStack = new Stack<Node<Value>>();
		Stack<Node<Value>> nextLevelStack;
		boolean leftOrder = true;
		
		currLevelStack.push(root);
		do{
			nextLevelStack = new Stack<Node<Value>>();
			while(!currLevelStack.isEmpty()){
				Node<Value> currNode = currLevelStack.pop();
				System.out.print(currNode + " ");
				
				if(leftOrder){
					if(currNode.left != null){
						nextLevelStack.push(currNode.left);
					}
					if(currNode.right != null){
						nextLevelStack.push(currNode.right);
					}
				}else{
					if(currNode.right != null){
						nextLevelStack.push(currNode.right);
					}
					if(currNode.left != null){
						nextLevelStack.push(currNode.left);
					}
				}
			}
			// Switch order
			leftOrder = !leftOrder;
			System.out.println();
			currLevelStack = nextLevelStack;
			
		}while(!nextLevelStack.isEmpty());

	}
	
	public static void main(String[] args){
		
		BST<Integer> bst = new BST<Integer>();
		bst.insert(new Node<Integer>(4));
		bst.insert(new Node<Integer>(10));
		bst.insert(new Node<Integer>(1));
		bst.insert(new Node<Integer>(5));
		bst.insert(new Node<Integer>(3));
		
		bst.printInOrder();
		
		Node currNode = new Node(1);
		Node newNode = new Node(2);
			
		System.out.println("Range sum: " + bst.rangeSum(1,5));		
		System.out.println("List of tree by level: "+bst.buildLinkedListByLevel());
		System.out.println("Print tree by level: ");
		bst.printByLevel();
		System.out.println("Print tree in zigzag by level: ");
		bst.printZigZagOrder(bst.root);

		System.out.println(bst.find(currNode)); // True
		System.out.println(bst.find(newNode)); // False
		
	}

}

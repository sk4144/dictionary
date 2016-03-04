/**
 * The class represent a Binary Search Tree 
 * It provides a way of storing generic data in an organized binary way
 * by using BSTNode's to store the references to the data/actual data
 * 
 * This class extends the comparable class and implements the BSTInterface
 * 
 * @author Suebin Kim
 *
 */

public class BST<E extends Comparable<E>> implements BSTInterface<E> { 
	//root of tree
	protected BSTNode <E> root;
	
	/**
	 * Default constructor
	 */
	public BST() {
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter and stores it as a BST
	 * 
	 * @param data to store in the root node
	 */
	public BST(E data) {
		this.root = new BSTNode<E>(data);
	}
	
	
	/**
	 * Adds an item to this binary search tree. 
	 * @param item the item to be added
	 */
	@Override
	public void insert(E item) {

		if (item != null){
			BSTNode <E> root = this.root;
			root = recInsert(root, item);
		}
		
	}
	
	/**
	 * HELPER METHOD:
	 * Adds an item to this binary search tree.
	 * @param the current node used to compare to the new data
	 * @param the item to be added
	 */
	private BSTNode<E> recInsert(BSTNode <E> node, E newData) {

		if (node == null) {		//base case
			node = new BSTNode <E> (newData);
		}
		//recursive calls to find correct placement for the new data
		else if (newData.compareTo(node.getData()) <= 0) {
			node.setLeft(recInsert(node.getLeft(), newData));
		}
		else {
			node.setRight(recInsert(node.getRight(), newData));
		}
		return node;
	}
	
	/**
	 * Removes an item from this binary search tree.
	 * If item is not in the tree, the structure is unchanged. 
	 * @param item the item to be removed
	 */
	@Override
	public void remove(E item) {
		BSTNode <E> root = this.root;
		recRemove(root, item);
		
	}
	
	/**
	 * HELER METHOD:
	 * Removes an item from this binary search tree.
	 * If item is not in the tree, the structure is unchanged. 
	 * @param the node to compare the item data to
	 * @param item the item to be removed
	 * @return node to modify tree
	 */
	private BSTNode<E> recRemove(BSTNode<E> node, E item) {
		//arbitrary boolean to store found flag for base case to do nothing
		boolean found;
		if (node == null) { //base case
			found = false;
		}
		//recursive calls to reset nodes into correct placement
		else if (item.compareTo(node.getData()) < 0) {
			node.setLeft(recRemove(node.getLeft(), item));
		}
		else if (item.compareTo(node.getData()) > 0) {
			node.setRight(recRemove(node.getRight(), item));
		}
		else {
			//call to helper method to remove data
			node = remove(node);
			found = true;
		}
		return node;
		
	}
	
	
	/**
	 * HELPER METHOD:
	 * Removes an item from this binary search tree.
	 * If item is not in the tree, the structure is unchanged. 
	 * @param the node to compare the item data to
	 * @param item the item to be removed
	 * @return node to modify tree
	 */
	private BSTNode<E> remove(BSTNode<E> node) {
		//case if node only has 1 child
		if (node.getLeft() == null) {
			return node.getRight();
		}
		else if (node.getRight() == null) {
			return node.getLeft();
		}
		else {
			//call to helper method to get the predecessor node if node has 2 children
			E data = getPredecessor(node.getLeft());
			node.setData(data);
			node.setLeft(recRemove(node.getLeft(), data));
			return node;
		}
	}

	/**
	 * HELPER METHOD:
	 * Locates the rightmost node in a left subtree
	 * If item is not in the tree, the structure is unchanged. 
	 * @param node to start with
	 * @return data in the rightmost ndoe in left subtree
	 * @throws null pointer exception
	 */
	private E getPredecessor(BSTNode<E> n) throws NullPointerException{

		while(n.getRight() != null) {
			n = n.getRight();
		}
		return n.getData();
	}
	
	/**
	 * Determines if an item is located in this binary search tree. 
	 * @param item the item to be located
	 * @return true if the item is in the tree, false otherwise
	 */
	@Override
	public boolean contains(E item) {
		return contains(item, this.root);
	}
	
	
	/**
	 * HELPER METHOD:
	 * Determines if an item is located in this binary search tree. 
	 * @param item the item to be located
	 * @param current node to compare item to
	 * @return true if the item is in the tree, false otherwise
	 */
	private boolean contains(E item, BSTNode<E> currentNode) {
		if (currentNode == null) {
			return false;
		}
		else if ( item.compareTo(currentNode.getData()) < 0) {
			return contains(item, currentNode.getLeft());
		}
		else if (item.compareTo(currentNode.getData()) > 0) {
			return contains(item, currentNode.getRight());
		}
		else {
			return true;
		}
	}
	

}

	



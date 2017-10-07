
public class BinarySearchTree<E extends Comparable<E>> {
	
	private static class Node<E>{
		private E data;
		private Node<E> left;
		private Node<E> right;
		
		private Node(E data, Node<E> left, Node<E> right){
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}
	
	private Node<E> root;
	
	public void inOrderTraversal(){
		inOrderTraversal(root);
	}
	
	private void inOrderTraversal(Node<E> where){
		if(where != null){
			inOrderTraversal(where.left);
			System.out.println(where.data);
			inOrderTraversal(where.right);
		}
	}
	
	public void add(E newData){
		if(root == null){
			root = new Node<E>(newData, null, null);
		}
		else
			add(newData, root);
	}
	
	private void add(E newData, Node<E> where){
		int c = newData.compareTo(where.data);
		
		if(c < 0 && where.left == null)
			where.left = new Node<E>(newData, null, null);
		else if(c > 0 && where.right == null)
			where.right = new Node<E>(newData, null, null);
		else if(c < 0)
			add(newData, where.left);
		else if(c > 0)
			add(newData, where.right);
			
	}
	
	public void add_book(E newData){
		root = add_book(newData, root);
	}
	
	private Node<E> add_book(E newData, Node<E> where){
		if(where == null)
			return new Node<E>(newData, null, null);
		else{
			int c = newData.compareTo(where.data);
			
			if(c < 0)
				where.left = add_book(newData, where.left);
			else if(c > 0)
				where.right = add_book(newData, where.right);
			
			return where;
		}
	}
	
	public E find(E someItem){
		return find(someItem, root);
	}
	
	private E find(E someItem, Node<E> where){
		if(where == null)
			return null;
		else{
			int c = someItem.compareTo(where.data);
			
			if(c == 0)
				return where.data;
			else if(c < 0)
				return find(someItem, where.left);
			else
				return find(someItem, where.right);
		}
	}
	
	public String toString(){
		return toString(root, "");
	}
	
	private String toString(Node<E> where, String s){
		if(where == null)
			return s + "null";
		else
			return s + where.data + "\n" + toString(where.left, s + " ") + "\n" + toString(where.right, s + " ");
	}
	
	// Method to add Item to Binary Search Tree non-recursively
	public void interationAdd(E newData){
		Node<E> link = root;
		if(link == null)
			root = new Node<>(newData, null, null);
		else{
			//int c = newData.compareTo(link.data);
			while(true){
				int c = newData.compareTo(link.data);
				Node<E> temp = link;
				if(c < 0){
					link = link.left;
					if(link == null){
						temp.left = new Node<>(newData, null, null);
						break;
					}
				}
				if(c > 0){
					link = link.right;
					if(link == null){
						temp.right = new Node<>(newData, null, null);
						break;
					}
				}
				
				if(c == 0)
					break;
			}
		}
	}
	
	/*Non-recursive find method return null if the item is not in the tree
	return item if matched*/
	public E iterationFind(E someItem){
		Node<E> link = root;

		while(true){			
			//Node<E> temp = link;
			if(link == null)
				return null;
			else{
				int c = someItem.compareTo(link.data);
				
				if(c == 0)
					return link.data;
				else if(c < 0)
					link = link.left;
				else
					link = link.right;
					
			}
		}
	}
	
	public void delete(E someItem){
		System.out.println("deleting " + someItem);
		root = delete(someItem, root);
	}
	
	private Node<E> delete(E someItem, Node<E> where){
		if(where == null)
			return null;
		else{
			int c = someItem.compareTo(where.data);
			
			if(c < 0){
				where.left = delete(someItem, where.left);
				return where;
			}
			else if(c > 0){
				where.right = delete(someItem, where.right);
				return where;
			}
			else{
				if(where.left == null && where.right == null){
					System.out.println("(no children)");
					return null;
				}
				else if(where.left != null && where.right == null){
					System.out.println("(left child)");
					return where.left;
				}
				else if(where.left == null && where.right != null){
					System.out.println("(right child)");
					return where.right;
				}else{
					System.out.println("(two childen)");
					where.data = findAndDeleteIOP(where);
					return where;
				}
			}
		}
	}
	
	private E findAndDeleteIOP(Node<E> where){
		Node<E> temp = where.left;
		Node<E> parent = where;
		
		while(temp.right != null){
			parent = temp;
			temp = temp.right;
		}
		
		if(parent == where)
			parent.left = temp.left;
		else
			parent.right = temp.left;
		return temp.data;
	}
	
	public static void main(String[] args){
		/*BinarySearchTree<String> bst = new BinarySearchTree<>();
		String[] s = {"a", "aah", "aahed", "aahing", "aahs"};*/
		/*for(String x : s)
			bst.add(x);
		System.out.println(bst);*/
		
	/*	BinarySearchTree<Integer> number = new BinarySearchTree<>();
		int[] num = {9, 1, 12, 13, 4, 5};
		for(int n : num)
			number.interationAdd(n);
		for(int a : num)
			System.out.println(number.iterationFind(a));*/
		/*System.out.println(number);
		number.inOrderTraversal();*/
		
	/*	for(String t : s)
			bst.interationAdd(t);
		System.out.println(bst);
		
		for(String d : s)
			System.out.println(bst.iterationFind(d));
		System.out.println(bst.iterationFind("culos"));
		if(bst.iterationFind("culos") == null)
			System.out.print("False");*/
		BinarySearchTree<Double> theTree = new BinarySearchTree<>();
		double[] x = {7, 2, 10, 1, 8, 1.5, 7.5, 9,1.2,3};
		for (double d : x)
			theTree.add_book(d);
		
		System.out.println(theTree);
		System.out.println();

		theTree.delete(10.0);
		System.out.println(theTree);
		

	}
}

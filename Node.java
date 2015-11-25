package treeprob;
public class Node <Value extends Comparable<Value>>{
	
	public Node<Value> left, right;
	public Value value;
	
	public Node(Value value){
		this.value = value;
	}
	
	public String toString(){
		return this.value.toString();
	}
	
	public boolean equals(Object o){
		if(o != null && o instanceof Node){
			return (((Node)o).value.equals(this.value));
		}
		return false;
	}
}
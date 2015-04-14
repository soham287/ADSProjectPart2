import java.util.ArrayList;
import java.util.HashMap;
public class BinomialNode {
	int data;
	int index;
	BinaryTrie binaryTrie;
	HashMap<Integer,Integer> Edge;
	ArrayList<BinomialNode> child;
	ArrayList<BinomialNode> parent;
	BinomialNode rightlink;
	BinomialNode leftlink;
	int degree;
	String ip;
	Boolean childCut;
	BinomialNode prevNode;
	BinomialNode(int key,int num){
		data=key;
		index=num;
		child = new ArrayList<BinomialNode>();
		parent= new ArrayList<BinomialNode>();
		binaryTrie= new BinaryTrie();
		rightlink=null;
		leftlink=null;
		degree=0;
		parent =null;
		childCut=false;
		Edge = new HashMap<Integer,Integer>();
		prevNode=null;
		String ip= new String();
	}
	BinomialNode(BinomialNode node){
		data=node.data;
	Edge= node.Edge;
	}
	
	public void addNode(BinomialNode node,BinomialNode minHeapNode){
  (minHeapNode.rightlink).leftlink=node;
  node.rightlink=minHeapNode.rightlink;
  node.leftlink=minHeapNode;
  minHeapNode.rightlink=node;
	}

	public BinomialNode deleteNode(BinomialNode node){  //Delete deletes the present node and returns a pointer to the next node
		BinomialNode nextNode=node.rightlink;
		nextNode.leftlink=node.leftlink;
		(node.leftlink).rightlink=nextNode;
		return nextNode;
	
	}
}

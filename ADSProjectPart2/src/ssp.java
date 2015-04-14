import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class ssp {
	BinomialNode V;
	ArrayList<BinomialNode> verticesList;

	ssp(){

		verticesList = new ArrayList<BinomialNode>();
	}
	
	BinomialNode addVertices(int key,int index){
		V= new BinomialNode(key,index);
		return V;
	}
	
	public void singleSourcePaths(ArrayList<BinomialNode> Vertices,int sourceIndex){
		BinomialNode source = Vertices.get(sourceIndex);
		source.data=0;
		source.prevNode=null;// because it is source
		FibonacciHeap Pqueue = new FibonacciHeap();
		for(BinomialNode v: Vertices){
		Pqueue.insertNode(v);
		Pqueue.nodeCount++; //Buffer to count the number of nodes in the Heap
		}
		while(Pqueue.isEmpty()!=true){
	    BinomialNode u = Pqueue.deleteMin();
	  //  System.out.println("NEW MIN: "+u.data+"    INDEX:"+u.index);
		HashMap<Integer,Integer> adjList = u.Edge;
		if(!adjList.isEmpty()){
			System.out.println("");
			for(int v:adjList.keySet()){
			int weight = adjList.get(v);
			BinomialNode vnode=Vertices.get(v);
			relax(u,vnode,weight,Pqueue);
		}
		}
		}
		
	}
ArrayList<BinomialNode> shortestPath(int sourceIndex,int destinationIndex){
		 ArrayList<BinomialNode> path = new ArrayList<BinomialNode>();
			BinomialNode dest= verticesList.get(destinationIndex);
			System.out.println(dest.index);
			while(dest.prevNode!=null){
				path.add(dest);
				System.out.println("NODES IN PATH"+dest.index);
				dest=dest.prevNode;
				
			}
			path.add(dest);
			System.out.println(" LAST NODES IN PATH"+dest.index);
		Collections.reverse(path);
		return path;
	}
	void relax(BinomialNode u, BinomialNode v, int w,FibonacciHeap q){
		if(v.data>(u.data+w)){
			q.decreaseKey(v,u.data+w);
			v.prevNode=u;
		}
	}
void refreshGraph(){
	for(BinomialNode node:verticesList){
		node.data=Integer.MAX_VALUE;
		node.prevNode=null;
		node.child = new ArrayList<BinomialNode>();
		node.parent= new ArrayList<BinomialNode>();
		node.rightlink=null;
		node.leftlink=null;
		node.degree=0;
		node.parent =null;
		node.childCut=false;
		
	}
}
}
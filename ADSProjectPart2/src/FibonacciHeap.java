import java.util.HashMap;
import java.util.ArrayList;
public class FibonacciHeap {
    static int nodeCount=0;
	BinomialNode HeapNode;
	BinomialNode minHeapNode;
	BinomialNode deletedMinNode;
	BinomialNode headNode;
	FibonacciHeap() {
		minHeapNode = null;
	}
	public void insertNode(BinomialNode node) {
		if(minHeapNode==null){
			minHeapNode=node;
			headNode=node;
			minHeapNode.rightlink=minHeapNode;
			minHeapNode.leftlink=minHeapNode;
		}
		else 
		{
			minHeapNode.addNode(node,minHeapNode);
			if(node.data < minHeapNode.data ){
				minHeapNode=node;
			}

		}
	}
	public void display() {
		BinomialNode start=minHeapNode;
		while (start.rightlink!=minHeapNode){
			System.out.println(start.data+"->");
			start=start.rightlink;
		}
		System.out.println(start.data+"->");
		start=start.rightlink;
   System.out.println("MIN="+minHeapNode.data);
 
	}
	// Parts to be worked on 1) min has child check 2) Hashmap iteration joining check 3) pairwise combine tuning check
	public BinomialNode deleteMin() {
		if(minHeapNode!=null) { //Null Check for Empty Heap
			deletedMinNode=minHeapNode;
			if(nodeCount==1) {// That case when we have just 1 node
			BinomialNode lastMinNode= new BinomialNode(minHeapNode);
			minHeapNode=null;
			return lastMinNode;
			 }
	          
               ArrayList<BinomialNode> minChild= minHeapNode.child;// child always points the head of the child list
               if(minHeapNode==headNode){ //That one scenario where minHeapnode is the Head Node
               	headNode=headNode.leftlink;
               }
               Boolean onlyMinHeapNode=false;
               //That one scenario where theres only 1 node in the min circular list.
              	if(minHeapNode.rightlink==minHeapNode) {
              		onlyMinHeapNode=true;
              	}
               minHeapNode=minHeapNode.deleteNode(minHeapNode);// For now lets keep the min as the next node
               BinomialNode start= minHeapNode;
           // Now to move all children of min node if it has any
               int numChild=minChild.size();
               if(!minChild.isEmpty()){
              for(int count=0;count<numChild;count++){
            	  BinomialNode childNode=minChild.get(count);
            	  insertNode(childNode);
            	  if(childNode.parent!=null){
            	  childNode.parent.remove(0);
            	  }  
              }
              minChild.clear();
			}
			// Now the tricky part ,if minHeapNode is one of it`s children you already have the new min
              if(onlyMinHeapNode==true){
			minHeapNode=minHeapNode.deleteNode(minHeapNode);// For now lets keep the min as the next node
             start= minHeapNode;
              }
            ArrayList<BinomialNode> list = movethroughList(start);
			HashMap<Integer,BinomialNode> map = new HashMap<Integer,BinomialNode>();
			for(BinomialNode startNode: list){
				if(map.get(startNode.degree)==null){
						map.put(startNode.degree,startNode);
                  
					}
					else {
						while(map.get(startNode.degree)!=null){
							startNode=pairwiseCombine(map.get(startNode.degree),startNode);
							map.remove(startNode.degree);
							startNode.degree=startNode.degree+1;//Increase the degree
							if (minHeapNode.parent!=null && minHeapNode.parent.isEmpty()!=true){ //The classic case where the min node and its child have same value;
							minHeapNode=minHeapNode.parent.get(0);
							}
						}
					
						map.put(startNode.degree,startNode);	
					}
				} minHeapNode= null; // Because we are inserting again
			for(int degree : map.keySet()){
				insertNode(map.get(degree));
			//	System.out.println("Degree"+degree+""+map.get(degree).data);
			}
		
	}   nodeCount--;
	return deletedMinNode;
	}
	public BinomialNode pairwiseCombine(BinomialNode node1,BinomialNode node2) {
		BinomialNode parentNode;
		BinomialNode childNode;

		if(node1.data<node2.data) { //node1 parent node2 child
			parentNode=node1;
			childNode=node2;
		}
		else {
			parentNode=node2;
			childNode=node1;	
		}
	
	        //So i have defined children of a parent as an arrayList parent.child gives me the list and list(0) gives me the head.
		   //Also i have defined a parent ArrayList of every child for removing the "POTNIS PROBLEM"
			parentNode.child.add(childNode);
			if(childNode.parent==null){
				childNode.parent= new ArrayList<BinomialNode>();
			}
			if (parentNode.parent ==null){
				parentNode.parent= new ArrayList<BinomialNode>();	
			}
			childNode.parent.add(parentNode);
         	return parentNode;
	}
	
	public ArrayList<BinomialNode> movethroughList(BinomialNode start){ // To put the min circular list into  a list and break it into components
		
		ArrayList<BinomialNode> list = new ArrayList<BinomialNode>();
		//Here start is just the next node to the minHeapNode
		BinomialNode tempMin = start;
		while(start.rightlink!=tempMin){
			list.add(start);
			if(start.data<minHeapNode.data){
				minHeapNode=start;
			}
			start=start.rightlink;
		}
			if(start.data<minHeapNode.data){
				minHeapNode=start;
			}
			list.add(start);
		return list;
	}
	// decreasekey steps-  cut the node add it to min list, change degree of parent,change childcut of parent,set parent pointer to null,if childcut of parent true, cut the tree till
	//you find a node whose childcut is false
	public void decreaseKey(BinomialNode node,int newValue) {
		if(node.parent!=null && node.parent.isEmpty()!= true && node.parent.get(0).data>newValue){
			BinomialNode parentNode = node.parent.get(0);
			node.parent.remove(0);
			node.data=newValue;
			int count;
			for(count=0;count<parentNode.child.size();count++){
				if (parentNode.child.get(count)==node) break;
			}
			insertNode(node);
			parentNode.degree--;//Decrease degree of the node
			parentNode.child.remove(count);
			if(parentNode.childCut==false && parentNode.parent.isEmpty()!=true){
				parentNode.childCut=true;
			}
			else cascadeCut(parentNode);
		}
		else {
			node.data=newValue;
		}
	}
	
	void cascadeCut(BinomialNode node){
		while(node.parent.isEmpty()!=true && node.parent.get(0).childCut==true){
			BinomialNode parentNode = node.parent.get(0);
			node.parent.remove(0);
			int count;
			for(count=0;count<parentNode.child.size();count++){
				if (parentNode.child.get(count)==node) break;
			}
			parentNode.child.remove(count);
			insertNode(node);
			parentNode.degree--; 
			node = parentNode;
		}
	}
	
	Boolean isEmpty(){
		if (minHeapNode==null) return true;
		else return false;
	}

}

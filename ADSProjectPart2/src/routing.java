import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class routing {
	
	BinaryTrieNode BinaryTrie;
	void refreshRouter(int sourceIndex,int destinationIndex,ssp Graph){// Implements Dijsktra on each router to get the min distance to each node.
		Graph.singleSourcePaths(Graph.verticesList,sourceIndex);
		ArrayList<BinomialNode> path = Graph.shortestPath(sourceIndex,destinationIndex);
		System.out.println("DESIRED PATH"+path);
		for(BinomialNode r:path){
			System.out.print(r.index+"->");
		}
	for(BinomialNode routerinPath:path){
		Graph.refreshGraph();
		Graph.singleSourcePaths(Graph.verticesList,routerinPath.index);
		System.out.println("SOURCENODE SENT"+routerinPath.index);
		updateRoutingTable(Graph,routerinPath);
	}
		
		
	   
	}
void updateRoutingTable(ssp Graph,BinomialNode sourceNode){
	for(BinomialNode routerNode:Graph.verticesList){
		if(sourceNode!=routerNode){
		ArrayList<BinomialNode> path = Graph.shortestPath(sourceNode.index,routerNode.index);
		for(BinomialNode r:path){
			System.out.print(r.index+"->");
		}
		int nextHopIndex=(path.indexOf(sourceNode))+1;
		sourceNode.binaryTrie.insert(routerNode.ip,path.get(nextHopIndex));
		}
		System.out.println("");
	}
}
	public static void main(String[] args) throws IOException {
		ssp Graph= new ssp();
		int numVertices=7 ;
		int edgecount=0;
		for(int j=0; j<numVertices;j++){   // Inserting those number of nodes
			BinomialNode v= new BinomialNode(Integer.MAX_VALUE,j);
			Graph.verticesList.add(v);

		}
		try { 
			String sCurrentLine;
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Soham\\Desktop\\InputPart2.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
			edgecount++;
			//System.out.println(sCurrentLine);
			char[] a= sCurrentLine.toCharArray();
			int count=0;
			String c= new String();
			while(a[count]!=' '){
				c=c+a[count];
				count++;
				
			}
			count++;
			c.trim();
			int u=Integer.parseInt(c);
			String d= new String();
			while(a[count]!=' '){
				d=d+a[count];
				count++;
				
			}
			count++;
			d.trim();
			int v=Integer.parseInt(d);

			String e= new String();
			while(count<a.length && a[count]!=' '){
				e=e+a[count];
				count++;
				
			}
			count++;
			e.trim();
			int w=Integer.parseInt(e);
			//System.out.println(u);
			Graph.verticesList.get(u).Edge.put(v,w);
			Graph.verticesList.get(v).Edge.put(u,w);
			sCurrentLine = br.readLine();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		readIp readRouterIP = new readIp();
		readRouterIP.setRouterIP(Graph);
				// TODO Auto-generated method stub
			   // Scanner input = new Scanner(System.in);
				//System.out.println("INPUT VERTICES");
				//int numVertices = input.nextInt();
				
				//System.out.println("INPUT EDGES");
				//int numEdges =input.nextInt();
				//int numEdges= 249750;
		
				int destinationIndex=0;
			     int sourceIndex=4;
			routing routerNodes= new routing();
			routerNodes.refreshRouter(sourceIndex,destinationIndex,Graph);
			/*	 while(!path.isEmpty()){
					System.out.print(path.remove(0).index+ "  ");
				}
					System.out.println("EDGE COUNT"+edgecount);	
		*/
			}

}

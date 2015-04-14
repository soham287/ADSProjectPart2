import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class readIp {
void setRouterIP(ssp Graph) throws IOException{
BinomialNode routerNode=Graph.verticesList.get(0); //First Router
	try { 
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Soham\\Desktop\\IPPart2.txt"));
		while ((sCurrentLine = br.readLine()) != null) {
              routerNode.ip=sCurrentLine;
              sCurrentLine = br.readLine(); //for the space
              if(routerNode.index!=Graph.verticesList.size()-1){
		routerNode=Graph.verticesList.get(routerNode.index+1);
              }
		}
}
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
}

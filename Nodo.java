import java.util.ArrayList;
import java.util.List;

public class Nodo {

	public Nodo parent;
	public List<Nodo> children = new ArrayList<Nodo>();
	public String elemento;
	public static int chilNum;

	public Nodo (String element){
		elemento=element; 
	}

	public void addChild(Nodo childNode){
		if(this.children.size()>=chilNum){
			
		}
		else{
			childNode.parent=this;
			this.children.add(childNode);
		}
	}
}

import java.util.ArrayList;
import java.util.List;

public class Nodo {

	public Nodo parent;
	public List<Nodo> children = new ArrayList<Nodo>();
	public String elemento;
	public static int chilNum;

	/**
	 * This is the constructoe
     * @param element The string contained in the node 
     */
	public Nodo (String element){
		elemento=element; 
	}

	/**
     * This method adds children nodes to each node in the tree
     * @param childNode The child 
     * 
     */

	public void addChild(Nodo childNode){
		/**
		 * If the maximum number of branches is reached do nothing
		 */
		if(this.children.size()>=chilNum){
			
		}
		else{
			childNode.parent=this;
			this.children.add(childNode);
		}
	}
}

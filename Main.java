import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import java.util.*;

/**
 * @author Alfredo Osuna Torres
 * @author Edgar Lopez Valdez
 * 
 * The following code creates a Window in which the user writes the name of a .txt file with the production rules of a grammar
 * once the txt is processed, the user can switch between txt files within the directory
 * and validate if a string belongs to the language through top down parsing.
 */

public class Main{
    public static void main(String[] args) {
       
        HashMap<String, ArrayList<String>> productionRules = new HashMap<String, ArrayList<String>>();
        ArrayList alphabet = new ArrayList<String>();
 
        String str, root;
        
        ArrayList nonTerminal = new ArrayList<String>();
        
        str = "babbbabbaa";

        alphabet.add("a");
        alphabet.add("b");
        
        nonTerminal.add("S");
        nonTerminal.add("A");
        nonTerminal.add("B");
        
        productionRules.put("S", new ArrayList<String>());
        productionRules.put("A", new ArrayList<String>());
        productionRules.put("B", new ArrayList<String>());
        
        productionRules.get("S").add("AbB");
        productionRules.get("S").add("bbS");
        productionRules.get("A").add("bAb");
        productionRules.get("A").add("a");
        productionRules.get("B").add("AaA");
        productionRules.get("B").add("bBB");
        productionRules.get("B").add("b");


        System.out.println(" ");
        System.out.println("Reglas de producción");
        System.out.println(" ");
        System.out.println(productionRules);
        System.out.println(" ");

   
        System.out.println("STRING");
        System.out.println(str);
        
        root = "S";
        int max = 0;
        for(int i = 0; i < productionRules.size();i++){
            if(productionRules.get(nonTerminal.get(i)).size() > max){
                max = productionRules.get(nonTerminal.get(i)).size();
            }
        }

        System.out.println(max);
        Topdown arbol = new Topdown(max);

        arbol.productionRules = productionRules;
        arbol.nonTerminal = nonTerminal;

        for(int i = 0; i < productionRules.get(root).size();i++){
            Nodo a = new Nodo(productionRules.get(root).get(i));
            arbol.root.addChild(a);
        }

        arbol.aceptado = false;
        arbol.validarString(arbol.root, str);
        

        System.out.println(" ");
        System.out.println("ARBOOOOL");
        arbol.print(arbol.root, str);

        System.out.println(" ");
        System.out.println(arbol.aceptado);

        
        
    }

}










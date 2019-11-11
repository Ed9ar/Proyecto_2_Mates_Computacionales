import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import java.util.*;

/**
 * @author Alfredo Osuna Torres
 * @author Edgar Lopez Valdez
 * 
 * The following code creates a Window in which the user writes the name of a .txt file with the specifications 
 * to create an NDFA-lambda, once the txt is processed, the user can switch between txt files within the directory
 * and validate if a string belongs to the language.
 */

public class Main{
    public static void main(String[] args) {
       
        HashMap<String, ArrayList<String>> productionRules = new HashMap<String, ArrayList<String>>();
        ArrayList alphabet = new ArrayList<String>();
        String str;
        ArrayList nonTerminal = new ArrayList<String>();
        Pushdown pushdown = new Pushdown<>("S");
        


        str = "abba";

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

        System.out.println(productionRules);

        pushdown.productionRules = productionRules;
        pushdown.Validate(str);
        //pushdown.printStacks();

        if(str.length() == 0){
            System.out.println("The string is accepted by te automata");
        }
    }
}

    
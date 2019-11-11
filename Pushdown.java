import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import java.util.*;
import java.util.Stack.*;

/**
 * 
 * @param <T>
 */

public class Pushdown<T extends Comparable>{
    HashMap<String, HashMap<String,ArrayList<String>>> transitionTable = new HashMap<String, HashMap<String,ArrayList<String>>>();
    ArrayList<Stack> listStacks = new ArrayList<>();
    HashMap<String, ArrayList<String>> productionRules = new HashMap<String, ArrayList<String>>();
    boolean valid = true;
    Stack<String> stack;
    String temp = "";
    int num;
    

    public Pushdown(String root){
        stack = new Stack<String>(); 
        stack.push(root);
        listStacks.add(stack);
    }

    public void Validate(String str){
        temp = "";
        System.out.println("OVERHERE");
        System.out.println(str);
        if(str.length() > 0){
            num = listStacks.size();
            
            for(int i = 0; i < num  ;i++){
                try{
                    if(String.valueOf(listStacks.get(i).peek()).compareTo(String.valueOf(str.charAt(0))) == 0){
                        System.out.println("AQUIII voy a popear");
                        System.out.println(String.valueOf(listStacks.get(i).peek()));
                        System.out.println(String.valueOf(str.charAt(0)));
                        listStacks.get(i).pop();
                    }
                    
                    else if(productionRules.get(listStacks.get(i).peek()).size() > 0){
                        for(int j = 0; j < productionRules.get(listStacks.get(i).peek()).size();j++){
                            
                            stack = new Stack<String>();
                            
                            
                            for(int k = productionRules.get(listStacks.get(i).peek()).get(j).length() -1 ; k >= 0 ;k--){
                                
                                stack.push(String.valueOf(productionRules.get(listStacks.get(i).peek()).get(j).charAt(k)));
                            }
                            listStacks.add(stack);
                            
                        } 
                    }
                    else{
                        valid = false;
                        System.out.println("No es valido");
                    }
                }catch(NullPointerException ex){
                    if(String.valueOf(listStacks.get(i).peek()).compareTo(String.valueOf(str.charAt(0))) == 0){
                        System.out.println("AQUIII voy a popear");
                        System.out.println(String.valueOf(listStacks.get(i).peek()));
                        System.out.println(String.valueOf(str.charAt(0)));
                        listStacks.get(i).pop();
                    }
                }
                
            }   

            for(int i = 0; i < listStacks.size();i++){
                System.out.println(listStacks.get(i));
            }
            
            
            for(int i = 0; i < str.length()-1; i++){
                System.out.println(temp);
                temp = temp + str.charAt(i);
            }
            System.out.println(temp);
            System.out.println("HOLO");
            str = temp;
            Validate(str);
            
        }
    }

    public void printStacks(){
        for(int i = 0; i < listStacks.size();i++){
            System.out.println(listStacks.get(i));
        }
    }
    
    
        
}


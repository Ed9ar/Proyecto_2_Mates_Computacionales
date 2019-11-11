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
    Stack<String> stack, stack1, tempS;
    String temp = "";
    int num,size;
    

    public Pushdown(String root){
        stack = new Stack<String>(); 
        stack.push(root);
        listStacks.add(stack);
    }

    public void Validate(String str){
        temp = "";
        
        if(str.length() > 0){
            num = listStacks.size();
            
            for(int i = 0; i < num  ;i++){
                try{
                    try{
                        size = productionRules.get(listStacks.get(i).peek()).size();
                        if(String.valueOf(listStacks.get(i).peek()).compareTo(String.valueOf(str.charAt(0))) == 0){
                            System.out.println("AQUIII voy a popear");
                            System.out.println(String.valueOf(listStacks.get(i).peek()));
                            System.out.println(String.valueOf(str.charAt(0)));
                            listStacks.get(i).pop();
                        }
                        
                        else if( size > 0){
                            for(int j = 0; j < size;j++){
                                stack1 = new Stack<String>();
    
                                for(int k = 0; k < listStacks.get(i).size() ; k++){
                                    stack1.push(String.valueOf(listStacks.get(i).get(k)));
                                }
                                System.out.println(stack1);
                                System.out.println("STACKS");
                                
                                stack1.pop();
                                System.out.println(listStacks.get(i));
                                System.out.println("Stack1");
                                
                                temp = String.valueOf(listStacks.get(i).peek());
                                
                                for(int k = productionRules.get(temp).get(j).length() -1 ; k >= 0 ;k--){
                                    stack1.push(String.valueOf(productionRules.get(listStacks.get(i).peek()).get(j).charAt(k)));
                                }
                                System.out.println(stack1);

                                listStacks.add(stack1);
                                
                            } 
                        }
                    }catch(EmptyStackException e){

                    }
                }catch(NullPointerException ex){
                    System.out.println("YAFUE");
                    /*
                    if(String.valueOf(listStacks.get(i).peek()).compareTo(String.valueOf(str.charAt(0))) == 0){
                        System.out.println("AQUIII voy a popear");
                        System.out.println(String.valueOf(listStacks.get(i).peek()));
                        System.out.println(String.valueOf(str.charAt(0)));
                        listStacks.get(i).pop();
                    }*/
                }
                
            }   

            System.out.println("Lista Stacks");
            for(int i = 0; i < listStacks.size();i++){
                System.out.println(listStacks.get(i));
            }
            
            
            for(int i = 0; i < str.length()-1; i++){
                System.out.println(temp);
                temp = temp + str.charAt(i);
            }
            
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


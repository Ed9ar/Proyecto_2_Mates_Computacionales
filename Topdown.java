import java.io.File;
import java.io.FileNotFoundException;
import javax.sound.midi.Receiver;
import java.util.*;
import java.util.ArrayList; 
import java.util.List;

public class Topdown {
	HashMap<String, ArrayList<String>> productionRules = new HashMap<String, ArrayList<String>>();
	ArrayList nonTerminal = new ArrayList<String>();
	Boolean valido = true;
	Nodo nodoNuevo;
	Boolean aceptado = false;
	int correcto = 1;
	int counter = 0;
	int counter1 =0;
	String newStr = "";
	String prefix, sufix,letter;
	public Nodo root = new Nodo("S");

	public Topdown(int maximum){
		Nodo.chilNum=maximum;
		root.parent=null;
	}

	public void print(Nodo root, String str){
		
		System.out.println("");
		System.out.println(root.elemento);
		if(str.equals(root.elemento)){
			aceptado = true;
		}
		if(root.elemento != "S"){
			System.out.println("Parent");
			System.out.println(root.parent.elemento);
		}
		if(root.children.size()!=0){
			for(int i = 0; i < root.children.size();i++){
				Nodo child = root.children.get(i);
				print(child, str);
			}
		}

	}

	public void validarString(Nodo root, String str){
		System.out.println(" ");
		if(root.elemento.length() <= str.length()+3 && aceptado == false){

			if(root.elemento != "S"){
				prefix = "";
				sufix = "";
				System.out.println(root.elemento);
				
				counter = 0;
				counter1 = 0;
				newStr = "";
				//Para Obtener el prefijo
				for(int i = 0; i < root.elemento.length(); i++){
					if(productionRules.get(String.valueOf(root.elemento.charAt(i))) != null && counter == 0){
						letter = String.valueOf(root.elemento.charAt(i));
						counter++;
						break;
					}
					else{
						newStr = newStr+ String.valueOf(root.elemento.charAt(i));
					}
				}

				prefix = newStr;

				newStr = "";
				//Para Obtener el sufijo
				counter = 0;
				for(int i = 0; i < root.elemento.length(); i++){
					if(productionRules.get(String.valueOf(root.elemento.charAt(i))) != null && counter == 0){
						counter++;
					}
					else if(counter >0){
						counter1++;
						newStr = newStr+ String.valueOf(root.elemento.charAt(i));
					}
				}

				sufix = newStr;

				System.out.println("Letter");
				System.out.println(letter);

				valido = true;

				System.out.println(prefix);
				System.out.println(sufix);
				
			
				//Si el prefijo es igual al prefijo del string se aniaden los hijos
				if(counter > 0){
					if(prefix.length() <= str.length()){
						for(int i =0; i < prefix.length();i++){
							if(str.charAt(i) == prefix.charAt(i) || String.valueOf(prefix.charAt(i)) == "" ){
								valido = true;
							}
							else{
								valido = false;
								break;
							}
						}
					}
					
					System.out.println(valido);
					
					//Se junta prefijo, reglas de produccion y sufijo en los nodos que seran hijos
					if(valido == true){
						for(int i = 0; i < productionRules.get(letter).size(); i++){
							if(productionRules.get(letter).get(i) == "lmd"){
								System.out.println("Inserting : " + prefix + sufix);
								Nodo nodoNuevo = new Nodo(prefix  + sufix);
								root.addChild(nodoNuevo);
							}
							else{
								System.out.println("Inserting : " + prefix + productionRules.get(letter).get(i) + sufix);
								Nodo nodoNuevo = new Nodo(prefix + productionRules.get(letter).get(i) + sufix);
								root.addChild(nodoNuevo);
							}
							
						}
					}
					
					
				}
				
			}
				
			
			if(root.children.size()!=0){
				for(int i = 0; i < root.children.size();i++){
					Nodo child = root.children.get(i);
					validarString(child, str);
				}
			}
		}
	}
}

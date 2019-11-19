import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;


public class Ventana extends JFrame{
    private JPanel panelOperaciones,panelBotones;
    private JTextField textField;
    private JButton[][] square = new JButton[4][3];
    private JButton stringVal;
    private JLabel resul, selectText, ops, val;
    String x,y,symbol, result;
    private JLabel Uno,Dos,Tres, checarString;

    HashMap<String, ArrayList<String>> productionRules = new HashMap<String, ArrayList<String>>();
    ArrayList alphabet = new ArrayList<String>();

    String str, root;
    
    ArrayList nonTerminal = new ArrayList<String>();

    
	public Ventana(){
		setSize(500,500);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		setVisible(true);
	}

	public void initComponents(){
        panelOperaciones= new JPanel();
        panelOperaciones.setLayout(new FlowLayout());
        
        
        panelBotones= new JPanel();
        panelBotones.setLayout(new FlowLayout());
        //panelBotones.setSize(400, height);
        selectText = new JLabel("Select a test file");
        
        panelBotones.add(selectText);


        //loop para crear los botones para seleccionar archivo
        for(int i = 0; i < 1; i++) { 
            for(int j = 0; j < 3; j++) {
                square[i][j] = new JButton();
                square[i][j].addActionListener(new BotonListener());
                square[i][j].setLayout(new FlowLayout());
                square[i][j].setBackground(Color.PINK);

                square[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                
                panelBotones.add(square[i][j]);
            }
            
        }

        //Poner labels en los botones
        Uno = new JLabel("Test1.txt");
        square[0][0].add(Uno);
        Dos = new JLabel("Test2.txt");
        square[0][1].add(Dos);
        Tres = new JLabel("Test3.txt");
        square[0][2].add(Tres);
 
        
        add(panelBotones, BorderLayout.CENTER);
                
	}

	public class BotonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){

            panelOperaciones.removeAll();
        
            if (e.getSource() == square[0][0]) {//Si se selecciona test1
                square[0][0].setBackground(Color.YELLOW);
                square[0][1].setBackground(Color.PINK);
                square[0][2].setBackground(Color.PINK);
                System.out.println("Presionaste test1");
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
	            panelOperaciones.add(ops);

            }
            else if(e.getSource() == square[0][1]) {//Si se selecciona test2
                square[0][1].setBackground(Color.YELLOW);
                square[0][0].setBackground(Color.PINK);
                square[0][2].setBackground(Color.PINK);
                System.out.println("Presionaste test2");
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
	            panelOperaciones.add(ops);
            }
            else if(e.getSource() == square[0][2]) {//Si se selecciona test3
                square[0][2].setBackground(Color.YELLOW);
                square[0][1].setBackground(Color.PINK);
                square[0][0].setBackground(Color.PINK);
                System.out.println("Presionaste test3");
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
	            panelOperaciones.add(ops);
                
            }
            else if(e.getSource() == stringVal){//Si se presiona boton de ir para validar string
                str = textField.getText();
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
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
	            panelOperaciones.add(ops);
            }
            textField=new JTextField();
            stringVal = new JButton();
            val = new JLabel("Ir");
            stringVal.addActionListener(new BotonListener());
            stringVal.add(val);
            textField.setPreferredSize(new Dimension(200,50));
            
            panelOperaciones.add(textField);
            panelOperaciones.add(stringVal);

            add(panelOperaciones);
            repaint();
            revalidate();
    }
}

}
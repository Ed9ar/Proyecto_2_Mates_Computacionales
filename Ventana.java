import java.io.File;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException;
import java.util.*;
import javax.sound.midi.Receiver;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.*;


public class Ventana extends JFrame{
    private JPanel panelOperaciones,panelBotones, panelReglas;
    private JTextField textField;
    private JButton[][] square = new JButton[4][3];
    private JButton stringVal, back;
    private JLabel resul, selectText, ops, val, ag;
    Topdown arbol;
    String x,y,symbol, result;
    private JLabel Uno,Dos,Tres, checarString, aceptado;

    HashMap<String, ArrayList<String>> productionRules = new HashMap<String, ArrayList<String>>();
    ArrayList alphabet = new ArrayList<String>();
    String str, nombre, root = "";   
    Scanner scanner = new Scanner(System.in);
    ArrayList nonTerminal = new ArrayList<String>();
    ArrayList strings = new ArrayList<String>();

    
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
        
        selectText = new JLabel("Select a test file");
        
        panelBotones.add(selectText);


        for(int i = 0; i < 1; i++) { 
            for(int j = 0; j < 3; j++) {
                square[i][j] = new JButton();
                square[i][j].setOpaque(true);
                square[i][j].addActionListener(new BotonListener());
                square[i][j].setLayout(new FlowLayout());
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
    
    public void crearArbol(){
        productionRules = new HashMap<String, ArrayList<String>>();
        strings = new ArrayList<String>();
        String nombreArchivo = nombre + ".txt";
  
                File archivo = new File (nombreArchivo);
                try{
                    /**
                    * Scan the user selected file
                    * Add .txt to the string 
                    */
                    scanner = new Scanner(archivo);
                    int numDeLinea=1;
                    while(scanner.hasNextLine()){
                        String linea = scanner.nextLine();
                        Scanner delimitar = new Scanner(linea);
                        /**
                         * Changes case each line of the file 
                         * After line 4 the file shows all the transactions
                         */
                        
                        switch (numDeLinea){
                            /**
                             * Adds all the states of the pushdown
                             */
                            case 1:
                                delimitar.useDelimiter("\\s*,|=>\\s*");
                                while(delimitar.hasNext()){
                                    String l = delimitar.next();
                                    nonTerminal.add(l);
                                    productionRules.put(l, new ArrayList<String>());
                                }
                            break; 
                            /**
                             * Adds the alphabet of the language 
                             */ 
                            case 2:
                                delimitar.useDelimiter("\\s*,|=>\\s*");
                                
                                while(delimitar.hasNext()){
                                    alphabet.add(delimitar.next());
                                }
                            break;
                            /**
                             * Adds root
                             */
                            case 3:
                                root = delimitar.next();
                            
                                
                            break;
                            /**
                             * Adds all the final states 
                             */
                            default:
                                ArrayList <String> arrStr = new ArrayList<String>();
                                delimitar.useDelimiter("\\s*,|->\\s*");
                                while(delimitar.hasNext()){
                                    String s = delimitar.next();
                                    arrStr.add(s);
                                }
                                productionRules.get(arrStr.get(0)).add(arrStr.get(1));
                                strings.add(linea);
                            break;
                            /**
                             * Adds the transactions to the corresponding key
                             */
                            }
                        numDeLinea++;
                    }
                    scanner.close();
                }
                catch(FileNotFoundException ex){
                    /**
                    * Catch the exception if the file does not exist
                    */
                }
                scanner.close();



                System.out.println(" ");
                System.out.println("Reglas de producción");
                System.out.println(" ");
                System.out.println(productionRules);
                System.out.println(" ");


                
                root = "S";
                int max = 0;
                for(int i = 0; i < productionRules.size();i++){
                    if(productionRules.get(nonTerminal.get(i)).size() > max){
                        max = productionRules.get(nonTerminal.get(i)).size();
                    }
                }

                System.out.println(max);
                
                arbol = new Topdown(max);

                arbol.productionRules = productionRules;
                arbol.nonTerminal = nonTerminal;

                for(int i = 0; i < productionRules.get(root).size();i++){
                    Nodo a = new Nodo(productionRules.get(root).get(i));
                    arbol.root.addChild(a);
                }

                arbol.aceptado = false;
    }

	public class BotonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
            /**
             * This will clean the pane if it has something in it 
             */
            panelOperaciones.removeAll();
            try{
                panelReglas.removeAll();
            }catch(NullPointerException ex){

            }
            
            /**
             * This will add the corresponding 
             * production rules for test1
             */
            if (e.getSource() == square[0][0]) {
                nombre = "test1";
                crearArbol();
                System.out.println("Presionaste test1");
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
                panelOperaciones.add(ops);
                textField=new JTextField();
                stringVal = new JButton();
                val = new JLabel("Ir");
                stringVal.addActionListener(new BotonListener());
                stringVal.add(val);
                //stringVal.setBackground(Color.YELLOW);
                
                textField.setPreferredSize(new Dimension(200,50));
                
                panelOperaciones.add(textField);
                panelOperaciones.add(stringVal);

            }
            /**
             * This will add the corresponding 
             * production rules for test2
             */
            else if(e.getSource() == square[0][1]) {//Si se selecciona test2
                nombre = "test2";
                crearArbol();
                System.out.println("Presionaste test2");
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
                panelOperaciones.add(ops);
                textField=new JTextField();
                stringVal = new JButton();
                val = new JLabel("Ir");
                stringVal.addActionListener(new BotonListener());
                stringVal.add(val);
                //stringVal.setBackground(Color.YELLOW);
                
                textField.setPreferredSize(new Dimension(200,50));
                
                panelOperaciones.add(textField);
                panelOperaciones.add(stringVal);
            }
            /**
             * This will add the corresponding 
             * production rules for test3
             */
            else if(e.getSource() == square[0][2]) {//Si se selecciona test3
                nombre = "test3";
                crearArbol();
                System.out.println("Presionaste test3");
                ops = new JLabel("Validar String");
                ops.setPreferredSize( new Dimension(200, 50  ) );
                panelOperaciones.add(ops);
                textField=new JTextField();
                stringVal = new JButton();
                val = new JLabel("Ir");
                stringVal.addActionListener(new BotonListener());
                stringVal.add(val);
                //stringVal.setBackground(Color.YELLOW);
                
                textField.setPreferredSize(new Dimension(200,50));
                
                panelOperaciones.add(textField);
                panelOperaciones.add(stringVal);
                
            }
            /**
             * This will call the validate method for the topdown
             */
            else if(e.getSource() == stringVal){
                str = textField.getText();
                arbol.validarString(arbol.root, str);

                System.out.println(" ");
                System.out.println("ARBOOOOL");
                arbol.print(arbol.root, str);

                System.out.println(" ");
                System.out.println(arbol.aceptado);

                
                if(arbol.aceptado == true){
                    aceptado = new JLabel("The string "+str+" is accepted by the language");
                }
                else if(arbol.aceptado == false){
                    aceptado = new JLabel("The string "+str+" is not accepted by the language");
                }
                
                
                panelOperaciones.add(aceptado);
            
            }
            
            panelReglas= new JPanel();
            int sizes = strings.size();

            panelReglas.setLayout(new GridLayout(sizes,1));

            ops = new JLabel("Production Rules :");
            panelReglas.add(ops);

            ops = new JLabel("                                       ");
            panelReglas.add(ops);

            for(int i = 0; i < sizes; i++){
                ops = new JLabel(String.valueOf(strings.get(i)));
                panelReglas.add(ops);
            }
                

            System.out.println(sizes);
            System.out.println(strings);
            add(panelReglas);
            add(panelOperaciones);
            repaint();
            revalidate();
    }
}

}
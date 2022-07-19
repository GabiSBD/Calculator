import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PanelCalculadora extends JPanel {
    public PanelCalculadora(){

        setLayout(new BorderLayout());

        display=new JTextField("0");
        numeracion=new JPanel();

        numeracion.setLayout(new GridLayout(4,5));

        String[]listaBotones= {"7", "8", "9", "x", "/", "4", "5", "6", "-", "+", "1", "2", "3", ".", "=", "0", "C"};

        for(int i=0;i<listaBotones.length;i++) {
            Pattern patron=Pattern.compile("\\A(/)|(C)|(x)|(-)|(\\+)|(=)");
            Matcher match=patron.matcher(listaBotones[i]);

            if(match.find())botonOperacion(listaBotones[i]);
            else botones(listaBotones[i]);
        }

        add(display,BorderLayout.NORTH);
        add(numeracion,BorderLayout.CENTER);

    }
    private void botones(String textoBoton){
        JButton boton= new JButton(textoBoton);
        boton.addActionListener(new InsertarNum());
        numeracion.add(boton);

    }
    private void botonOperacion(String textoBoton){
        JButton boton= new JButton(textoBoton);
        boton.addActionListener(new InsertarOperacion());
        numeracion.add(boton);
    }
    private class InsertarNum implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String entrada=e.getActionCommand();

            if(startPoint){display.setText(entrada); startPoint=false;}
            else display.setText(display.getText()+entrada);

            ultValor=Double.parseDouble(display.getText());

        }
    }
    private class InsertarOperacion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Operaciones formulas=new Operaciones();
            String operando = e.getActionCommand();
            if (operando.equals("+")){
                resultado+=ultValor;
                cont++;
                display.setText(String.valueOf(resultado));
                ultOperacion="+";
                startPoint=true;
            }
            else if(operando.equals("-")){
                valor=Double.parseDouble(display.getText());
                if(cont==0) resultado=valor;
                else resultado -= ultValor;
                cont++;

                ultOperacion="-";
                display.setText(String.valueOf(resultado));
                startPoint = true;
            }
            else if(operando.equals("x")) {
                valor=Double.parseDouble(display.getText());
                if(cont==0|ultOperacion.equals("C")) resultado=valor;
                else resultado*=ultValor;
                cont++;

                ultOperacion="x";
                display.setText(String.valueOf(resultado));
                startPoint=true;
            }
            else if(operando.equals("/")){
                valor=Double.parseDouble(display.getText());
                if(cont==0|ultOperacion.equals("C"))resultado=valor;
                else resultado/=ultValor;
                cont++;
                ultOperacion="/";
                display.setText(String.valueOf(resultado));
                startPoint=true;
            }
            else if (operando.equals("=")) {
                if(ultOperacion.equals("+")) resultado+=ultValor;cont=0;
                if(ultOperacion.equals("-")) resultado-=ultValor;cont=0;
                if(ultOperacion.equals("x")) resultado*=ultValor;cont=0;
                if(ultOperacion.equals("/")) resultado/=ultValor;cont=0;

                display.setText(String.valueOf(resultado));
                ultValor=0;
            }
            else if (operando.equals("C")){
            display.setText("0");
            resultado=0; ultValor=0; startPoint=true; ultOperacion="";cont=0;
            ultOperacion="C";
            }
        }
        private int cont=0;
        private double valor;
    }

    private JPanel numeracion;
    protected JTextField display;
    protected boolean startPoint=true;
    protected   String ultOperacion="";
    protected double ultValor;
    protected double resultado=0;
}


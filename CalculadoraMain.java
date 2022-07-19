import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculadoraMain {
    public static void main(String[]args){
        FrameCalculadora ventana=new FrameCalculadora();

    }
}
class FrameCalculadora extends JFrame{
    public FrameCalculadora(){
        setTitle("Calculadora Java");
        setBounds(500,300,450,300);
        add(new PanelCalculadora());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}




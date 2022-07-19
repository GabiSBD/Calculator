import javax.swing.*;

public class Operaciones extends PanelCalculadora{

    public void suma(){

    }
    public void igual(){

    }
    public void restart(){

    }
    public void resta(){

    }
    public void multiplica(){

    }
    private void displayResultado(double resultado, boolean startPoint, JTextField display){
        display.setText(String.valueOf(resultado));
        startPoint=true;
    }
}

package view;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;

//import model.entity.Medico;

public abstract class FormEntidade extends JFrame implements ActionListener
{
    private String appNome;
    private int numEntidadeCorrente;

    public void setAppNome(String n)
    {
        appNome = n;
    }

    public void setNumEntidadeCorrente(int n)
    {
        numEntidadeCorrente = n;
    }
    
    
    public String getAppNome()
    {
        return appNome;
    }
    
    public int getNumEntidadeCorrente()
    {
        return numEntidadeCorrente;
    }
    
    
    public abstract void salvarEntidade();
    
    public abstract void alterarEntidade();
    
    public abstract void deletarEntidade();
    
    public abstract void lerEntidades();
    
    public abstract void exibirEntidade(int index);
    
    public abstract int totalEntidades();
    
    public abstract JPanel getSubPainelCampos();
    
    public abstract JLabel getMainPainelName();
    
    //public abstract void EnableComponents();
    
    //public abstract void DisableComponents();

}
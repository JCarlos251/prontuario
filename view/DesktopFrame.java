package view;

import model.Database;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.Dimension;

import view.FormPaciente;
import view.FormMedico;
import view.FormProntuario;
import view.FormHistoricoProntuario;
import view.util.FormUtils;
import java.awt.Toolkit;

public class DesktopFrame extends JFrame implements ActionListener
{
    private final JDesktopPane desktop; 
    private String appNome;
    private Database database;

    private JMenu menuEntidades;
    JMenuItem menuPacientes;
    JMenuItem menuMedicos;    
    JMenuItem menuProntuarios;
    JMenuItem menuHistoricoProntuarios;

    public DesktopFrame(Database db, String n)
    {
        super(n);   
        appNome = n;
        database = db;

        JMenuBar barra = new JMenuBar();
        menuEntidades = new JMenu("Entidades");
        menuPacientes = new JMenuItem("Pacientes");
        menuMedicos = new JMenuItem("Medicos");
        menuProntuarios = new JMenuItem("Prontuarios");
        menuHistoricoProntuarios = new JMenuItem("Historico de Prontuarios");

        menuEntidades.add(menuPacientes);
        menuEntidades.add(menuMedicos);
        menuEntidades.add(menuProntuarios);
        menuEntidades.add(menuHistoricoProntuarios);
        barra.add(menuEntidades);
        setJMenuBar(barra);

        menuPacientes.addActionListener(this);        
        menuMedicos.addActionListener(this);
        menuProntuarios.addActionListener(this);
        menuHistoricoProntuarios.addActionListener(this);

        desktop = new JDesktopPane();
        add(desktop);

        FormUtils.centerForm(this);    

    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(menuPacientes))
        {        
            menuPacientes_Click();
        }

        if(e.getSource().equals(menuMedicos))
        {        
            menuMedicos_Click();
        }        
                
        if(e.getSource().equals(menuProntuarios))
        {        
            menuProntuarios_Click();
        }
        
        if(e.getSource().equals(menuHistoricoProntuarios))
        {        
            menuHistoricoProntuarios_Click();
        }    
        
        
    }

    private void menuPacientes_Click()    
    {               
        this.setEnabled(false);
        FormPaciente formPaciente = new FormPaciente(this,database,appNome);            
        formPaciente.criarExibirForm();         
    }

    private void menuMedicos_Click()
    {               
        this.setEnabled(false);
        FormMedico formMedico = new FormMedico(this,database,appNome);            
        formMedico.criarExibirForm();         
    }
    
    private void menuProntuarios_Click()
    {               
        this.setEnabled(false);
        FormProntuario FormProntuario = new FormProntuario(this,database,appNome);            
        FormProntuario.criarExibirForm();         
    }
    
    private void menuHistoricoProntuarios_Click()
    {               
        this.setEnabled(false);
        FormHistoricoProntuario FormHistoricoProntuario = new FormHistoricoProntuario(this,database,appNome);            
        FormHistoricoProntuario.criarExibirForm();         
    }
    
}
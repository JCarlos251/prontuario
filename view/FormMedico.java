package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

import model.entity.Medico;
import model.Database;
import controller.MedicoController;

import view.util.CrudToolBar;
import view.util.FormUtils;

public class FormMedico extends FormEntidade
{
    private JFrame frame;
    private JFrame formPai;
    private MedicoController controller;
    private Database database;
    private List<Medico> medicoList;

    private CrudToolBar crudToolBar;

    private JPanel painelPrincipal;
    private JPanel subPainel_0;
    private JPanel subPainel_1;
    private JPanel subPainel_2;
    
    //remover beg
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCRM;
    private JComboBox<String> comboEstado;
    private JLabel titlePrincipal;
    private JButton botaoVoltar;
    //remover end
    
    
    public FormMedico (JFrame form, Database database, String n)
    {
        formPai = form;
        this.controller = new MedicoController(database);
        setAppNome(n);
    }     

    public void criarExibirForm()
    {       
        // Criando e configurando a janela do formulario
        frame = new JFrame(getAppNome());        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridLayout(4,1));        

        //title principal
        subPainel_0 = new JPanel(); 
        subPainel_0.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        titlePrincipal = new JLabel("Cadastro");         
        titlePrincipal.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));
        gbc.gridwidth = 1;
        subPainel_0.add(titlePrincipal, gbc);
        painelPrincipal.add(subPainel_0);
        
        //Painel de preenchimento - MEDICO
        subPainel_1 = new JPanel();        
        subPainel_1.setBorder(new TitledBorder(
         new LineBorder(Color.BLACK), "Médico:"));        
        
        subPainel_1.setLayout(new GridBagLayout());         
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,3,3,3);   

        // ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("ID:",SwingConstants.RIGHT),gbc);             
        txtId = new JTextField();
        txtId.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;          
        subPainel_1.add(txtId,gbc);                     

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Nome:",SwingConstants.RIGHT),gbc);             
        txtNome = new JTextField();
        // txtNome.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_1.add(txtNome,gbc);             

        // CRM
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("CRM:",SwingConstants.RIGHT),gbc);             
        txtCRM = new JTextField();
        // txtCRM.setEnabled(false);
        //KeyListener para monitorar e permitir somente a insercao numeros no campo do CRM
        txtCRM.addKeyListener(new java.awt.event.KeyAdapter() 
        {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    long number = Long.parseLong(txtCRM.getText());
                } catch (Exception e) {
                    //Retornar somente os valores numericos inseridos
                    JOptionPane.showMessageDialog(rootPane, "Apenas números são permitidos!");
                    txtCRM.setText(txtCRM.getText().replaceAll("[^\\d.]", ""));
                }
            }
            }   );
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_1.add(txtCRM,gbc);
        
        // Estado
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Estado:",SwingConstants.RIGHT),gbc);             
        comboEstado = new JComboBox<>();
        // comboEstado.setEnabled(false);
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Estado", "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)"  }));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(comboEstado,gbc);

        painelPrincipal.add(subPainel_1);

        //add toolbar
        crudToolBar = new CrudToolBar(this);        
        painelPrincipal.add(crudToolBar);
        
        subPainel_2 = new JPanel(); 
        subPainel_2.setLayout(new FlowLayout( FlowLayout.CENTER,1,1)); 
        botaoVoltar = new JButton("Fechar"); 
        subPainel_2.add(botaoVoltar);
        botaoVoltar.addActionListener(this);
        
        painelPrincipal.add(subPainel_2);
        
        
        frame.add(painelPrincipal);

        frame.pack();
        FormUtils.centerForm(frame);        
        frame.setVisible(true);

        lerEntidades();
        exibirEntidade(getNumEntidadeCorrente());
    }    

    public void actionPerformed(ActionEvent e) 
    {
       if(e.getSource().equals(botaoVoltar))
        {        
            buttonVoltar_Click();
        }
    }

    public Medico getEntidadeCorrente()
    {
        Medico medico = new Medico();
        int id;

        if(txtId.getText().isEmpty() == false)
        {
            id = Integer.parseInt(txtId.getText());
            medico.setId(id);
        }

        if(txtNome.getText().isEmpty() == false)
        {
            medico.setNome(txtNome.getText());
        }

        if(txtCRM.getText().isEmpty() == false)
        {
            medico.setCRM(txtCRM.getText());
        }
        
        if(!comboEstado.getSelectedItem().toString().equals("Selecione o Estado"))
        {
            medico.setEstado(comboEstado.getSelectedItem().toString());
        }

        return medico;
    }

    public void salvarEntidade()
    {
        Medico medico;

        medico = getEntidadeCorrente();        
        Medico medicoCriado = controller.create(medico);
    }

    public void alterarEntidade()
    {
        Medico medico = new Medico();

        medico = getEntidadeCorrente();

        controller.update(medico);

        lerEntidades();
        exibirEntidade(getNumEntidadeCorrente());
    }    

    public void deletarEntidade()
    {
        Medico medico = new Medico();

        medico = getEntidadeCorrente();

        controller.delete(medico);

        lerEntidades();
        exibirEntidade(getNumEntidadeCorrente());
    }    

    public void lerEntidades()
    {
        medicoList = controller.readAll();          

        if(medicoList.isEmpty())
        {
            setNumEntidadeCorrente(-1);
        }
        else
        {
            setNumEntidadeCorrente(0);            
        }
    }     

    public void exibirEntidade(int index)
    {
        Medico medico;

        if (index == -1)
        {
            FormUtils.clearTextFields(this,subPainel_1);    
        }
        else
        {
            medico = medicoList.get(index);
            txtId.setText(Integer.toString(medico.getId()));
            txtNome.setText(medico.getNome());  
            txtCRM.setText(medico.getCRM());
            selectItemByString(medico.getEstado());
        }
    }

    private void selectItemByString(String s) 
    {
        for (int i=0; i<comboEstado.getItemCount(); i++) {
          if (comboEstado.getItemAt(i).toString().equals(s)) {
            comboEstado.setSelectedIndex(i);
            break;
          }
        }
        return;
    }
    
    public int totalEntidades()
    {
        return medicoList.size();
    }    

    public JPanel getSubPainelCampos()
    {
        return subPainel_1;
    }    
    
    public JLabel getMainPainelName()
    {
        return titlePrincipal;
    }
    
    /*public void DisableComponents()
    {
        txtNome.setEnabled(false);
        txtCRM.setEnabled(false);
        comboEstado.setEnabled(false);
    }
    
    public void EnableComponents()
    {
        txtNome.setEnabled(true);
        txtCRM.setEnabled(true);
        comboEstado.setEnabled(true);
    }*/
    
    private void buttonVoltar_Click()
    {
        frame.setVisible(false);
        formPai.setEnabled(true);
        frame.dispose();
    }
    
}
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
import java.util.Calendar;
import java.util.Vector;

import model.entity.Paciente;
import model.Database;
import controller.PacienteController;

import view.util.CrudToolBar;
import view.util.FormUtils;

public class FormPaciente extends FormEntidade
{
    private JFrame frame;
    private JFrame formPai;
    private PacienteController controller;
    private Database database;
    private List<Paciente> pacienteList;

    private CrudToolBar crudToolBar;

    private JPanel painelPrincipal;
    private JPanel subPainel_0;
    private JPanel subPainel_1;
    private JPanel subPainel_2;
    
    //remover beg
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCPF;
    private JComboBox<String> cbDia;
    private JComboBox<String> cbMes;
    private JComboBox<String> cbAno;
    private JRadioButton rbFem;
    private JRadioButton rbMasc;
    private ButtonGroup bgSexo;
    private JLabel titlePrincipal;
    private JButton botaoVoltar;
    //remover end
    
    
    public FormPaciente (JFrame form, Database database, String n)
    {
        formPai = form;
        this.controller = new PacienteController(database);
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
        
        //Painel de preenchimento
        subPainel_1 = new JPanel();        
        subPainel_1.setBorder(new TitledBorder(
         new LineBorder(Color.BLACK), "Paciente:"));        
        
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
        gbc.gridwidth = 2;                  
        subPainel_1.add(txtNome,gbc);             

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("CPF:",SwingConstants.RIGHT),gbc);             
        txtCPF = new JTextField();
        // txtCPF.setEnabled(false);
        //KeyListener para monitorar e permitir somente a insercao numeros no campo do CPF
        txtCPF.addKeyListener(new java.awt.event.KeyAdapter() 
        {

            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    long number = Long.parseLong(txtCPF.getText());
                } catch (Exception e) {
                    //Retornar somente os valores numericos inseridos
                    JOptionPane.showMessageDialog(rootPane, "Apenas números são permitidos!");
                    txtCPF.setText(txtCPF.getText().replaceAll("[^\\d.]", ""));
                }
            }
            }   );
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;                  
        subPainel_1.add(txtCPF,gbc);
        
        // Sexo
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Sexo:",SwingConstants.RIGHT),gbc);             
        bgSexo = new ButtonGroup();
        rbFem = new JRadioButton();
        bgSexo.add(rbFem);
        rbFem.setSelected(true);
        // rbFem.setEnabled(false);
        rbFem.setText("Feminino");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(rbFem,gbc);
        rbMasc = new JRadioButton();
        bgSexo.add(rbMasc);
        // rbMasc.setEnabled(false);
        rbMasc.setText("Masculino");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(rbMasc,gbc);
        
        // Nascimento
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Data de Nascimento:",SwingConstants.RIGHT),gbc);             
        cbDia = new JComboBox<>();
        // cbDia.setEnabled(false);
        cbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;                  
        subPainel_1.add(cbDia,gbc);
        cbMes = new JComboBox<>();
        // cbMes.setEnabled(false);
        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;                  
        subPainel_1.add(cbMes,gbc);
        cbAno = new JComboBox<>();
        // cbAno.setEnabled(false);
        //cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021"}));
            
        //Gerar vetor com os anos a partir de 1900 e seta como padrao o ano atual
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        Vector v=new Vector();
        for (int i = year; i >= 1900; i--) {
            v.add(Integer.toString(i));
        }
        cbAno.setModel(new javax.swing.DefaultComboBoxModel(v));
        cbAno.setSelectedItem(year);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;                  
        subPainel_1.add(cbAno,gbc);

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

    public Paciente getEntidadeCorrente()
    {
        Paciente paciente = new Paciente();
        int id;

        if(txtId.getText().isEmpty() == false)
        {
            id = Integer.parseInt(txtId.getText());
            paciente.setId(id);
        }

        if(txtNome.getText().isEmpty() == false)
        {
            paciente.setNome(txtNome.getText());
        }

        if(txtCPF.getText().isEmpty() == false)
        {
            paciente.setCPF(txtCPF.getText());
        }
        
        if(rbFem.isSelected())
            paciente.setSexo("Feminino");
        else
            paciente.setSexo("Masculino");
            
        String dataNasc = cbDia.getSelectedItem() + "/" + cbMes.getSelectedItem() + "/" + cbAno.getSelectedItem();
        paciente.setDataNascimento(dataNasc);

        return paciente;
    }

    public void salvarEntidade()
    {
        Paciente paciente;

        paciente = getEntidadeCorrente();        
        Paciente pacienteCriado = controller.create(paciente);
    }

    public void alterarEntidade()
    {
        Paciente paciente = new Paciente();

        paciente = getEntidadeCorrente();

        controller.update(paciente);

        lerEntidades();
        exibirEntidade(getNumEntidadeCorrente());
    }    

    public void deletarEntidade()
    {
        Paciente paciente = new Paciente();

        paciente = getEntidadeCorrente();

        controller.delete(paciente);

        lerEntidades();
        exibirEntidade(getNumEntidadeCorrente());
    }    

    public void lerEntidades()
    {
        pacienteList = controller.readAll();          

        if(pacienteList.isEmpty())
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
        Paciente paciente;

        if (index == -1)
        {
            FormUtils.clearTextFields(this,subPainel_1);    
        }
        else
        {
            paciente = pacienteList.get(index);
            txtId.setText(Integer.toString(paciente.getId()));
            txtNome.setText(paciente.getNome());  
            txtCPF.setText(paciente.getCPF());
            if(paciente.getSexo().equals("Feminino")){
                rbFem.setSelected(true);
                //rbMasc.setSelected(false);
            } else {
                //rbFem.setSelected(false);
                rbMasc.setSelected(true);
            }
            //Separar dia, mes e ano da string DataNascimento do BD
            String dataArr[] = paciente.getDataNascimento().split("/");
            selectDiaByString(dataArr[0]);
            selectMesByString(dataArr[1]);
            selectAnoByString(dataArr[2]);
        }
    }

    private void selectDiaByString(String s) 
    {
        for (int i=0; i<cbDia.getItemCount(); i++) {
          if (cbDia.getItemAt(i).toString().equals(s)) {
            cbDia.setSelectedIndex(i);
            break;
          }
        }
        return;
    }
    
    private void selectMesByString(String s) 
    {
        for (int i=0; i<cbMes.getItemCount(); i++) {
          if (cbMes.getItemAt(i).toString().equals(s)) {
            cbMes.setSelectedIndex(i);
            break;
          }
        }
        return;
    }
    
    private void selectAnoByString(String s) 
    {
        for (int i=0; i<cbAno.getItemCount(); i++) {
          if (cbAno.getItemAt(i).toString().equals(s)) {
            cbAno.setSelectedIndex(i);
            break;
          }
        }
        return;
    }
    
    public int totalEntidades()
    {
        return pacienteList.size();
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
        txtCPF.setEnabled(false);
        rbFem.setEnabled(false);
        rbMasc.setEnabled(false);
        cbDia.setEnabled(false);
        cbMes.setEnabled(false);
        cbAno.setEnabled(false);
    }
    
    public void EnableComponents()
    {
        txtNome.setEnabled(true);
        txtCPF.setEnabled(true);
        rbFem.setEnabled(true);
        rbMasc.setEnabled(true);
        cbDia.setEnabled(true);
        cbMes.setEnabled(true);
        cbAno.setEnabled(true);
    }*/
    
    private void buttonVoltar_Click()
    {
        frame.setVisible(false);
        formPai.setEnabled(true);
        frame.dispose();
    }
    
}
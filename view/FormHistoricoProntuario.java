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
import java.util.ArrayList;

import model.entity.Prontuario;
import model.entity.Paciente;
import model.entity.Medico;

import model.Database;
import controller.ProntuarioController;
import controller.PacienteController;
import controller.MedicoController;

import view.util.CrudToolBar;
import view.util.FormUtils;


public class FormHistoricoProntuario extends FormEntidade
{
    private JFrame frame;
    private JFrame formPai;
    private ProntuarioController controller;
    private Database database;
    private List<Prontuario> prontuarioList;
    
    private PacienteController pacienteController;
    private List<Paciente> pacienteList;
    
    private MedicoController medicoController;
    private List<Medico> medicoList;
    
    
    private CrudToolBar crudToolBar;

    private JPanel painelPrincipal;
    private JPanel subPainel_0;
    private JPanel subPainel_1;
    private JPanel subPainel_2;
    private JPanel subPainel_3;
    private JPanel subPainel_4;
    

    private JComboBox<String> cbNome_paciente;
    private JTextField nomeMedico;
    private JTextField txtCPF;
    private JTextField txtCRM;
    private JTextField txtId;
    private JTextArea txtRelato;
    private JComboBox<String> comboEstado;
    private JComboBox<String> cbDia;
    private JComboBox<String> cbMes;
    private JComboBox<String> cbAno;
    private JComboBox<String> cbDia_consulta;
    private JComboBox<String> cbMes_consulta;
    private JComboBox<String> cbAno_consulta;
    private JRadioButton rbFem;
    private JRadioButton rbMasc;
    private JRadioButton rbSim;
    private JRadioButton rbNao;
    private ButtonGroup bgSexo;
    private ButtonGroup bgCovid;
    private JLabel titlePrincipal;
    private JButton botaoVoltar;

    //Arrays com os Ids
    private ArrayList<Integer> array_pac_ids;    
    private ArrayList<Integer> array_med_ids;    
    
    
    public FormHistoricoProntuario (JFrame form, Database database, String n)
    {
        formPai = form;
        this.controller = new ProntuarioController(database);
        setAppNome(n);
        
        
        this.pacienteController = new PacienteController(database);
        this.medicoController = new MedicoController(database);
        
        this.pacienteList = pacienteController.readAll();
        this.medicoList = medicoController.readAll();
    }     

    public void criarExibirForm()
    {       
        // Criando e configurando a janela do formulario
        frame = new JFrame(getAppNome());        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridLayout(6,1));  

        //title principal
        subPainel_0 = new JPanel(); 
        subPainel_0.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        titlePrincipal = new JLabel("Histórico - Prontuário");         
        titlePrincipal.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18));
        gbc.gridwidth = 1;
        subPainel_0.add(titlePrincipal, gbc);
        painelPrincipal.add(subPainel_0);
        
        //Painel de preenchimento - PACIENTE
        subPainel_1 = new JPanel();        
        subPainel_1.setBorder(new TitledBorder(
         new LineBorder(Color.BLACK), "Paciente:"));        
        
        subPainel_1.setLayout(new GridBagLayout());         
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,3,3,3);   

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Nome:",SwingConstants.RIGHT),gbc);             
        cbNome_paciente = new JComboBox<>();
        // cbNome_paciente.setEnabled(false);
        cbNome_paciente.addActionListener(this);
        
        ArrayList<String> arrayP = carregarPacientes();
        cbNome_paciente.setModel(new DefaultComboBoxModel<String>(arrayP.toArray(new String[0])));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;                  
        subPainel_1.add(cbNome_paciente,gbc);             

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("CPF:",SwingConstants.RIGHT),gbc);             
        txtCPF = new JTextField();
        txtCPF.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;                  
        subPainel_1.add(txtCPF,gbc);
        
        // Sexo
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Sexo:",SwingConstants.RIGHT),gbc);             
        bgSexo = new ButtonGroup();
        rbFem = new JRadioButton();
        bgSexo.add(rbFem);
        rbFem.setEnabled(false);
        rbFem.setText("Feminino");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_1.add(rbFem,gbc);
        rbMasc = new JRadioButton();
        bgSexo.add(rbMasc);
        rbMasc.setEnabled(false);
        rbMasc.setText("Masculino");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_1.add(rbMasc,gbc);
        
        // Nascimento
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(new JLabel("Data de Nascimento:",SwingConstants.RIGHT),gbc);             
        cbDia = new JComboBox<>();
        cbDia.setEnabled(false);
        cbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(cbDia,gbc);
        cbMes = new JComboBox<>();
        cbMes.setEnabled(false);
        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(cbMes,gbc);
        cbAno = new JComboBox<>();
        cbAno.setEnabled(false);
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
        gbc.gridy = 3;
        gbc.gridwidth = 1;                  
        subPainel_1.add(cbAno,gbc);

        painelPrincipal.add(subPainel_1);
        
        
        //Painel de preenchimento - MEDICO
        subPainel_2 = new JPanel();        
        subPainel_2.setBorder(new TitledBorder(
         new LineBorder(Color.BLACK), "Médico:"));        
        
        subPainel_2.setLayout(new GridBagLayout());         
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,3,3,3);   
                 

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_2.add(new JLabel("Nome:",SwingConstants.RIGHT),gbc);             
        nomeMedico = new JTextField();
        nomeMedico.setEnabled(false);
        nomeMedico.addActionListener(this);
      
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;                  
        subPainel_2.add(nomeMedico,gbc);             

        // CRM
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_2.add(new JLabel("CRM:",SwingConstants.RIGHT),gbc);             
        txtCRM = new JTextField();
        txtCRM.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_2.add(txtCRM,gbc);
        
        // Estado
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_2.add(new JLabel("Estado:",SwingConstants.RIGHT),gbc);             
        comboEstado = new JComboBox<>();
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)"  }));
        comboEstado.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_2.add(comboEstado,gbc);

        painelPrincipal.add(subPainel_2);

        
        //Painel de preenchimento - PRONTU�?RIO
        subPainel_3 = new JPanel();        
        subPainel_3.setBorder(new TitledBorder(
         new LineBorder(Color.BLACK), "Consulta:"));        
        
        subPainel_3.setLayout(new GridBagLayout());         
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,3,3,3);   

        
        // Data da consulta
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_3.add(new JLabel("Data:",SwingConstants.RIGHT),gbc);             
        cbDia_consulta = new JComboBox<>();
        // cbDia_consulta.setEnabled(false);
        cbDia_consulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_3.add(cbDia_consulta,gbc);
        cbMes_consulta = new JComboBox<>();
        // cbMes_consulta.setEnabled(false);
        cbMes_consulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_3.add(cbMes_consulta,gbc);
        cbAno_consulta = new JComboBox<>();
        // cbAno_consulta.setEnabled(false);
        //cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021"}));
            
        //Gerar vetor com os anos a partir de 1900 e seta como padrao o ano atual
        int day = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH);
        cbAno_consulta.setModel(new javax.swing.DefaultComboBoxModel(v));
        cbAno_consulta.setSelectedItem(year);
        cbMes_consulta.setSelectedItem(month);
        cbDia_consulta.setSelectedItem(day);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;                  
        subPainel_3.add(cbAno_consulta,gbc);
        
        
        // COVID
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_3.add(new JLabel("Testou  positivo para COVID:",SwingConstants.RIGHT),gbc);             
        bgCovid = new ButtonGroup();
        rbSim = new JRadioButton();
        bgCovid.add(rbSim);
        rbSim.setText("Sim");
        // rbSim.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_3.add(rbSim,gbc);
        rbNao = new JRadioButton();
        bgCovid.add(rbNao);
        rbNao.setText("Não");
        // rbNao.setEnabled(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;                  
        subPainel_3.add(rbNao,gbc);
        painelPrincipal.add(subPainel_3);
        
        // Relato
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;                  
        subPainel_3.add(new JLabel("Relato:",SwingConstants.RIGHT),gbc);             
        txtRelato = new JTextArea();
        // txtRelato.setEnabled(false);
        txtRelato.setRows(5);
        txtRelato.setColumns(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;                  
        subPainel_3.add(txtRelato,gbc);
        
        txtId = new JTextField();
        txtId.setVisible(false);
        
        //add toolbar
        crudToolBar = new CrudToolBar(this);
        
        //esconder botoes nao utilizados
        crudToolBar.botaoCriar.setVisible(false);
        crudToolBar.botaoSalvar.setVisible(false);
        crudToolBar.botaoCancelar.setVisible(false);
        crudToolBar.botaoAlterar.setVisible(false);
        crudToolBar.botaoDeletar.setVisible(false);
        painelPrincipal.add(crudToolBar);
        
        subPainel_4 = new JPanel(); 
        subPainel_4.setLayout(new FlowLayout( FlowLayout.CENTER,1,1)); 
        botaoVoltar = new JButton("Fechar"); 
        subPainel_4.add(botaoVoltar);
        botaoVoltar.addActionListener(this);
        
        painelPrincipal.add(subPainel_4);
        
        
        frame.add(painelPrincipal);

        frame.pack();
        FormUtils.centerForm(frame);        
        frame.setVisible(true);
        
        lerEntidades();
        exibirEntidade(getNumEntidadeCorrente());
    }    
    
    public ArrayList<String> carregarPacientes(){
        ArrayList<String> array = new ArrayList<>();
        array_pac_ids = new ArrayList<Integer>();
        for(Paciente p: this.pacienteList){
            array.add(p.getNome());
            array_pac_ids.add(p.getId());
        }        
        return array;
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(botaoVoltar))
        {        
            buttonVoltar_Click();
        }
        
        //Action ao selecionar nome do paciente
        else if(e.getSource().equals(cbNome_paciente))
        {        
            lerEntidades();
            exibirEntidade(getNumEntidadeCorrente());
        }
    }

    public void lerEntidades()
    {
        //prontuarioList = controller.readAll();
        
        prontuarioList = controller.readProntuariosDoPaciente(array_pac_ids.get(cbNome_paciente.getSelectedIndex())); 

        if(prontuarioList.isEmpty())
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
        Prontuario prontuario;

        if (index == -1)
        {
            FormUtils.clearTextFields(this,subPainel_1);    
        }
        else
        {
            prontuario = prontuarioList.get(index);
            txtId.setText(prontuario.getId()+"");
            
            //Paciente
            Paciente paciente = pacienteController.readFromId(prontuario.getPaciente().getId());
                      
            //selectNomePacienteByString(paciente.getNome());
            
            txtCPF.setText(paciente.getCPF());
            
            if(paciente.getSexo().equals("Feminino")){
                rbFem.setSelected(true);
            } else {
                rbMasc.setSelected(true);
            }
            
            //Separar dia, mes e ano da string DataNascimento do BD
            String dataArrP[] = paciente.getDataNascimento().split("/");
            selectDataConsultaByString(cbDia, dataArrP[0]);
            selectDataConsultaByString(cbMes, dataArrP[1]);
            selectDataConsultaByString(cbAno, dataArrP[2]);
            
            
            //Medico
            Medico medico = medicoController.readFromId(prontuario.getMedico().getId());
            nomeMedico.setText(medico.getNome());
            txtCRM.setText(medico.getCRM()+"");
            selectDataConsultaByString(comboEstado, medico.getEstado());
            
            
            //Separar dia, mes e ano da string DataNascimento do BD
            String dataArr[] = prontuario.getDataConsulta().split("/");
            selectDataConsultaByString(cbDia_consulta, dataArr[0]);
            selectDataConsultaByString(cbMes_consulta, dataArr[1]);
            selectDataConsultaByString(cbAno_consulta, dataArr[2]);
            
            if(prontuario.getCovid().equals("Positivo")){
                rbSim.setSelected(true);
            } else {
                rbNao.setSelected(true);
            }
            
            txtRelato.setText(prontuario.getRelato());
            
            }
    }
    
    
    private void buttonVoltar_Click()
    {
        frame.setVisible(false);
        formPai.setEnabled(true);
        frame.dispose();
    }
    
    
    
    
    private void selectDataConsultaByString(JComboBox<String> cb, String s) 
    {
        for (int i=0; i<cb.getItemCount(); i++) {
          if (cb.getItemAt(i).toString().equals(s)) {
            cb.setSelectedIndex(i);
            break;
          }
        }
        return;
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
    
    private void selectNomePacienteByString(String s) 
    {
        for (int i=0; i<cbNome_paciente.getItemCount(); i++) {
          if (cbNome_paciente.getItemAt(i).toString().equals(s)) {
            cbNome_paciente.setSelectedIndex(i);
            break;
          }
        }
        return;
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
    
    public void deletarEntidade(){}
    public void alterarEntidade(){}
    public void salvarEntidade(){}
    
    public int totalEntidades()
    {
        return prontuarioList.size();
    }    

    public JPanel getSubPainelCampos()
    {
        return subPainel_1;
    }    
    
    public JLabel getMainPainelName()
    {
        return titlePrincipal;
    }
    
    

}

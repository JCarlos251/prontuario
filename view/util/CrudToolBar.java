    package view.util;
    
    import javax.swing.*;
    import java.awt.Insets;
    import java.awt.GridBagLayout;
    import java.awt.GridBagConstraints;
    import java.awt.BorderLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.List;
    import java.awt.Component;
    import java.awt.Container;
    import java.awt.Dimension;
    import java.awt.Toolkit;
    
    import model.entity.*;
    import view.FormEntidade;
    import view.util.FormUtils;
    
    public class CrudToolBar extends JPanel implements ActionListener
    {
        private static int ALTURA = 25;
        private static int LARGURA = 95;
        private FormEntidade form;
        private GridBagLayout layout;   
        private GridBagConstraints gbc;
    
        public JButton botaoCriar;
        public JButton botaoSalvar;
        public JButton botaoCancelar;
        public JButton botaoAlterar;
        public JButton botaoDeletar; 
        
        private JButton botaoLerAnterior;
        private JButton botaoLerProximo;
    
        private Dimension tamanhoBotao; // tamanho dos botoes
    
        public CrudToolBar(FormEntidade f)
        {
            this.form = f;
            this.layout = new GridBagLayout();
            this.setLayout(layout);
            this.gbc = new GridBagConstraints();
            this.gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(2,2,2,2);
            this.tamanhoBotao = new Dimension(CrudToolBar.LARGURA,CrudToolBar.ALTURA);
    
            JToolBar toolBar = new JToolBar("Still draggable");
            toolBar.setFloatable(false);
            toolBar.setRollover(true);
            adicionarBotoes();
            associarEventos();
        }
    
        private void adicionarBotoes()
        {
            botaoCriar = criarBotao(0,0,"Novo");
            botaoLerAnterior = criarBotao(1,0,"\u25C4");
            botaoLerProximo = criarBotao(2,0,"\u25BA");
            botaoAlterar = criarBotao(3,0,"Alterar");
            botaoDeletar = criarBotao(4,0,"Deletar");
    
            botaoSalvar = criarBotao(5,0,"Salvar");
            botaoSalvar.setEnabled(false);
            botaoCancelar = criarBotao(6,0,"Cancelar");
            botaoCancelar.setEnabled(false);
        }
    
        private JButton criarBotao(int x, int y, String rotulo)
        {
            this.gbc.gridx = x;
            this.gbc.gridy = y;
            JButton botao = new JButton(rotulo);
            botao.setPreferredSize(tamanhoBotao);
            //not focusable
            botao.setFocusable(false);
            this.add(botao,this.gbc);
    
            return botao;
        }
    
        private void associarEventos()
        {
            Component components [] = this.getComponents();
    
            for (Component c : components)
            {
                if (c instanceof JButton)
                {
                    ((JButton) c).addActionListener(this);
                }                        
            }                            
        }
    
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource().equals(botaoCriar))
            {        
                botaoCriar_Click();
            }
    
            if(e.getSource().equals(botaoSalvar))
            {        
                botaoSalvar_Click();
            }
    
            if(e.getSource().equals(botaoCancelar))
            {        
                botaoCancelar_Click();
            }
    
            if(e.getSource().equals(botaoCancelar))
            {        
                botaoCancelar_Click();
            }        
    
            if(e.getSource().equals(botaoLerAnterior))
            {        
                botaoLerAnterior_Click();
            }        
    
            if(e.getSource().equals(botaoLerProximo))
            {        
                botaoLerProximo_Click();
            } 
    
            if(e.getSource().equals(botaoAlterar))
            {        
                int option;
    
                option = JOptionPane.showConfirmDialog(form,
                    "Voce deseja alterar a entidade atual?",
                    form.getAppNome(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
    
                if (option == JOptionPane.YES_OPTION)
                {           
                    botaoAlterar_Click();
                }            
            } 
            
            if(e.getSource().equals(botaoDeletar))
            {        
                int option;
    
                option = JOptionPane.showConfirmDialog(form,
                    "Voce deseja excluir a entidade atual?",
                    form.getAppNome(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
    
                if (option == JOptionPane.YES_OPTION)
                {           
                    botaoDeletar_Click();
                }            
            }         
    
        }
    
        // Metodos associados aos eventos
        private void botaoCriar_Click()
        {
            botaoSalvar.setEnabled(true);
            botaoCancelar.setEnabled(true);
            botaoCriar.setEnabled(false);
            botaoLerAnterior.setEnabled(false);
            botaoLerProximo.setEnabled(false);
            botaoAlterar.setEnabled(false);
            botaoDeletar.setEnabled(false);       
            
            //form.EnableComponents();
                    
            FormUtils.clearTextFields(form,form.getSubPainelCampos()); 
        }

        private void botaoSalvar_Click()
        {
            botaoSalvar.setEnabled(false);
            botaoCancelar.setEnabled(false);
            botaoCriar.setEnabled(true);
            botaoLerAnterior.setEnabled(true);
            botaoLerProximo.setEnabled(true);
            botaoAlterar.setEnabled(true);
            botaoDeletar.setEnabled(true);
    
            //form.DisableComponents();
            
            form.salvarEntidade();        
            form.lerEntidades();
            form.exibirEntidade(form.getNumEntidadeCorrente());                 
        }
    
        private void botaoCancelar_Click()
        {
            botaoSalvar.setEnabled(false);
            botaoCancelar.setEnabled(false);
            botaoCriar.setEnabled(true);
            botaoLerAnterior.setEnabled(true);
            botaoLerProximo.setEnabled(true);
            botaoAlterar.setEnabled(true);
            botaoDeletar.setEnabled(true);
            
            //form.DisableComponents();
            
            form.lerEntidades();
            form.exibirEntidade(form.getNumEntidadeCorrente());         
        }
    
        private void botaoLerAnterior_Click()
        {
            int numEntidadeCorrente = form.getNumEntidadeCorrente();
    
            if((numEntidadeCorrente-1) >= 0)
            {
                numEntidadeCorrente--;
                form.setNumEntidadeCorrente(numEntidadeCorrente);
                form.exibirEntidade(numEntidadeCorrente);
            }
    
        }
    
        private void botaoLerProximo_Click()
        {
            int numEntidadeCorrente = form.getNumEntidadeCorrente();
    
            if((numEntidadeCorrente+1) < form.totalEntidades())
            {
                numEntidadeCorrente++;
                form.setNumEntidadeCorrente(numEntidadeCorrente);
                form.exibirEntidade(numEntidadeCorrente);
            }
    
        }    
    
        private void botaoAlterar_Click()
        {
            form.alterarEntidade();
        }
    
           private void botaoDeletar_Click()
        {
            form.deletarEntidade();
        }
}
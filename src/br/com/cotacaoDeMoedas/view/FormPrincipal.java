package br.com.cotacaoDeMoedas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;

import br.com.cotacaoDeMoedas.controller.AcessoController;
import br.com.cotacaoDeMoedas.controller.ControllerCotacao;
import br.com.cotacaoDeMoedas.model.Moeda;
import br.com.cotacaoDeMoedas.model.Subject;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class FormPrincipal extends JFrame implements Observer{
		
	private AcessoController controller;
	
	private JPanel contentPane;		
	private String nomeMoeda;
	private String valorMoeda;
	private String fonteMoeda;
	private String imgMoeda;
	
	JLabel lblImgMoeda;
	
	JTextField txtNomeMoeda;
	JTextField txtValorMoeda;	
	JTextField txtFonte;
	JComboBox cbxMoeda;
	JButton btnPesquisar;
	
	private int indexMoeda;
	
	public int getIndexMoeda() {
		return indexMoeda;
	}

	public void setIndexMoeda(int indexMoeda) {
		this.indexMoeda = indexMoeda;
	}

	public FormPrincipal(ControllerCotacao co) {
		
		this.controller = co;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500, 350);
		contentPane = new JPanel();
		contentPane.setLocation(0, -54);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Centraliza o form
		setLocationRelativeTo(null);		
	
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 8, 464, 40);
		panel.setBackground(new Color(0, 102, 153));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COTA\u00C7\u00C3O DE MOEDAS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 191, 23);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 56, 107, 137);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblImgMoeda = new JLabel("");
		lblImgMoeda.setBounds(6, 9, 95, 120);
		panel_1.add(lblImgMoeda);
		lblImgMoeda.setBackground(SystemColor.controlLtHighlight);
		lblImgMoeda.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblMoeda = new JLabel("Moeda:");
		lblMoeda.setBounds(127, 62, 64, 14);
		contentPane.add(lblMoeda);
		
		JLabel lblCotao = new JLabel("Cota\u00E7\u00E3o:");
		lblCotao.setBounds(127, 122, 64, 14);
		contentPane.add(lblCotao);
		
		JLabel lblFonte = new JLabel("Fonte:");
		lblFonte.setBounds(127, 179, 64, 14);
		contentPane.add(lblFonte);
		
		txtValorMoeda = new JTextField();
		txtValorMoeda.setEnabled(true);
		txtValorMoeda.setBounds(201, 59, 273, 20);
		contentPane.add(txtValorMoeda);
		txtValorMoeda.setColumns(10);
		
		txtNomeMoeda = new JTextField();
		txtNomeMoeda.setEnabled(true);
		txtNomeMoeda.setBounds(201, 119, 273, 20);
		contentPane.add(txtNomeMoeda);
		txtNomeMoeda.setColumns(10);
		
		txtFonte = new JTextField();
		txtFonte.setEnabled(true);
		txtFonte.setBounds(201, 173, 273, 20);
		contentPane.add(txtFonte);
		txtFonte.setColumns(10);
		
		cbxMoeda = new JComboBox();
		cbxMoeda.setModel(new DefaultComboBoxModel(new String[] {"Dolar", "Euro", "Peso Argentino", "Libra Esterlina", "Bitcoin"}));
		cbxMoeda.setBounds(127, 242, 112, 31);
		contentPane.add(cbxMoeda);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			//Ação ao clicar no botão de pesquisa
			public void actionPerformed(ActionEvent arg0){				
				int index = cbxMoeda.getSelectedIndex();				
				co.pesquisar(index);
			}
		});
		btnPesquisar.setBounds(249, 241, 112, 32);
		contentPane.add(btnPesquisar);
		
		JLabel lblNewLabel_1 = new JLabel("FONTE : api.promasters.net.br");
		lblNewLabel_1.setBounds(295, 298, 179, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	public void update(String nomeMoeda, String valorMoeda, String fonteMoeda, String imgMoeda)
	{
		this.nomeMoeda = nomeMoeda;
		this.valorMoeda = valorMoeda;
		this.fonteMoeda = fonteMoeda;
		this.imgMoeda = imgMoeda;
		
		display();
	}
	
	public void display() 
	{
		txtNomeMoeda.setText(nomeMoeda);
		txtValorMoeda.setText(valorMoeda);
		txtFonte.setText(fonteMoeda);
		lblImgMoeda.setIcon(new ImageIcon(FormPrincipal.class.getResource(imgMoeda)));
	}
}

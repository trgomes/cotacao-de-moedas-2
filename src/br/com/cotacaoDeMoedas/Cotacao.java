package br.com.cotacaoDeMoedas;

import java.awt.Frame;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.ws.Service.Mode;

import org.json.JSONException;
import org.omg.CORBA.PUBLIC_MEMBER;

import br.com.cotacaoDeMoedas.controller.ControllerCotacao;
import br.com.cotacaoDeMoedas.model.Model;
import br.com.cotacaoDeMoedas.model.Moeda;
import br.com.cotacaoDeMoedas.view.FormPrincipal;

public class Cotacao {

	// http://www.leepoint.net/GUI/structure/40mvc.html

	private static FormPrincipal frmPrincipal;
	private static Model model;
	private static ControllerCotacao controller;
	
	public static void main(String[] args) throws JSONException {	
		
		//Garante uma unica conexao com o bd
		model = model.getInstance();		
		
		controller = new ControllerCotacao(model);	
		
		frmPrincipal = new FormPrincipal(controller);			
		model.registerObserver(frmPrincipal);
		
		frmPrincipal.setVisible(true);		
	
	}
	
}

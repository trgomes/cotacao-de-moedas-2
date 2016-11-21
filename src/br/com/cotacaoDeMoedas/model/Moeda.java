package br.com.cotacaoDeMoedas.model;

public class Moeda {

	private String nome;
	private double valor;
	private double ultimaConsulta;
	private String fonte;
	private String img;
	
	
	public Moeda(String nome, double valor, double ultimaConsulta, String fonte) {
		this.nome = nome;
		this.valor = valor;
		this.ultimaConsulta = ultimaConsulta;
		this.fonte = fonte;
	}
	
	//Setters e Getters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public double getUltimaConsulta() {
		return ultimaConsulta;
	}


	public void setUltimaConsulta(double ultimaConsulta) {
		this.ultimaConsulta = ultimaConsulta;
	}


	public String getFonte() {
		return fonte;
	}


	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	
	public String getImg(){
		return img;
	}
	
	public void setImg(String img){
		this.img = img;
	}
		
	
	public String toString(){
		fonte = getNome() +" "+ getValor() +" "+ getFonte() + "" + getUltimaConsulta(); 
		
		return fonte;		
	}
}

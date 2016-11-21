package br.com.cotacaoDeMoedas.model;

import br.com.cotacaoDeMoedas.view.Observer;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();	
}

package br.com.cotacaoDeMoedas.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import br.com.cotacaoDeMoedas.view.Observer;

public class Model implements Subject {
	private List<Moeda> listMoedas; //Era o bd
	private static Model uniqueInstance;
	
	
	private ArrayList observers;
	private String nomeMoeda;
	private String valorMoeda;
	private String fonteMoeda;
	private String imgMoeda;
	
	private Conection con;
	
	private Model(){
		observers = new ArrayList();
		con = new Conection();
	}
	
	ObjectContainer db = Db4oEmbedded.openFile("moedas.db4o");	
	
	//Garante a conexão unica
	public static Model getInstance(){

		if(uniqueInstance==null){
			System.out.println("Nova instancia");
			uniqueInstance = new Model();
		}
		
		return uniqueInstance;				
	}
	
	
	//Adiciona as objetos(Moeda) da lista obtida atraves da API no db4o
	public void addMoeda(){	
			
		if(excluirMoedas()){
			System.out.println("\nAdicionando dados atualizados...\n");
			
			for(Moeda i: listMoedas){			
				db.store(i);
				System.out.println(i);
			}
		}
						
	}
	
	//Exclui os objetos armazenados no db4o
	public Boolean excluirMoedas(){		
		
		Query query = db.query();
		ObjectSet result = query.execute();	
		
		if(result.size() != 0){
			Moeda moeda = (Moeda) result.get(0);
			
			//Verifica se a ultima consulta na API é igual ao armazenado no banco
			if( moeda.getUltimaConsulta() != listMoedas.get(0).getUltimaConsulta()){
				
				System.out.println("\nExcluindo dados antigos...");
				
				for(Object i: result){
					db.delete(i);
				}

				return true;
			}else{
				System.out.println("\nOs dados estão atualizados. Nada foi excluido!");
			}
		}else{
			System.out.println("\nO bd está vazio. Novos dados serão adicionados.");
			return true;
		}
		
		System.out.println("\nObjetos no banco : " + result.size());
		
		return false;
	}
			
	
	//Moeda especifica buscada no db4o
	public Object moedaEspecifica(int index){
		
		Query query = db.query();
		ObjectSet result = query.execute();	
		
		return result.get(index);		
	}
	
	
		
	//Retorna a lista de moedas
	public List<Moeda> getBD(){
		return listMoedas;
	}
	
	//Salva todos os dados no bd
	public void getData(String url){
		try {
			listMoedas = con.getData(url);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);		
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(nomeMoeda, valorMoeda, fonteMoeda, imgMoeda);
		}
		
	}
	
	public void setValores(String nomeMoeda, String valorMoeda, String fonteMoeda, String imgMoeda) {
		this.nomeMoeda = nomeMoeda;
		this.valorMoeda = valorMoeda;
		this.fonteMoeda = fonteMoeda;
		this.imgMoeda = imgMoeda;
		notifyObservers();
	}
	
}

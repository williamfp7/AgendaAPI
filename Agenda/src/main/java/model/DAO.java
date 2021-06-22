package model;

import java.util.ArrayList;

public interface DAO {
	public void inserirContato(JavaBeans contato);
	public ArrayList<JavaBeans> recuperarContato(JavaBeans contato);
	public ArrayList<JavaBeans> recuperarContato();
	public void alterarContato(JavaBeans contato);
	public void deletarContato(JavaBeans contato);
}

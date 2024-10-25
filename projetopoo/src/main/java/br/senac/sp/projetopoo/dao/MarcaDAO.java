package br.senac.sp.projetopoo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.senac.sp.projetopoo.modelo.Marca;

public class MarcaDAO {
	private Connection conexao;
	private String sql;
	private PreparedStatement stmt;
	
	public MarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	public void inserir(Marca marca) throws SQLException {
		sql = "INSERT INTO MARCA(nomeMarca, logo) VALUES (?, ?)";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, marca.getnomeMarca());
		stmt.setBytes(2, marca.getLogo());
		stmt.execute();
		stmt.close();
		//caso a conexao abrisse cada vez em que tivesse algum comando teria que fechar cada vez conexao.close();
	}
}

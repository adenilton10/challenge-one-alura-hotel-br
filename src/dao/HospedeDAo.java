package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Hospede;

public class HospedeDAo {

	Connection connection;

	public HospedeDAo(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Hospede hospede) {
		try {
			String sql = "INSERT INTO HOSPEDES (NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADE, TELEFONE, IDRESERVA) VALUES(?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setDate(3, hospede.getDataNascimento());
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setString(6, hospede.getIdReserva());
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						hospede.setIdHospede(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Hospede> listar() {

		List<Hospede> hospedes = new ArrayList<>();

		String sql = "SELECT * FROM HOSPEDES";

		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Hospede hospede = new Hospede(rst.getString(2), rst.getString(3), rst.getDate(4),
								rst.getString(5), rst.getString(6), rst.getString(7));
						hospede.setIdHospede(rst.getInt(1));

						hospedes.add(hospede);
					}
				}
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospede> listarComFiltro(String sobrenome) {

		List<Hospede> hospedes = new ArrayList<>();

		String sql = "SELECT * FROM HOSPEDES WHERE SOBRENOME = ?";

		try {
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, sobrenome);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Hospede hospede = new Hospede(rst.getString(2), rst.getString(3), rst.getDate(4),
								rst.getString(5), rst.getString(6), rst.getString(7));
						hospede.setIdHospede(rst.getInt(1));

						hospedes.add(hospede);
					}
				}
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void editar(Hospede hospede) {
		
		
			String sql = "UPDATE HOSPEDES SET NOME = ?, SOBRENOME= ?, DATANASCIMENTO =?, NACIONALIDADE =? ,TELEFONE = ?, IDRESERVA = ? WHERE ID = ?";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setString(1, hospede.getNome());
				pstm.setString(2, hospede.getSobrenome());
				pstm.setDate(3, hospede.getDataNascimento());;
				pstm.setString(4, hospede.getNacionalidade());
				pstm.setString(5, hospede.getTelefone());
				pstm.setString(6, hospede.getIdReserva());
				pstm.setInt(7, hospede.getIdHopede());
				pstm.execute();
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}
	
	public void deletar(Integer id) {
		
		try(PreparedStatement pstm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE ID = ?")){
			pstm.setInt(1, id);
			pstm.execute();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}

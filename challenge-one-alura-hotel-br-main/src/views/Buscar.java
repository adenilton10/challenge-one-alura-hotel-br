package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HospedeController;
import controller.ReservaController;
import modelo.Hospede;
import modelo.Reserva;
import validacao.ValidaDados;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	ReservaController reservaController;
	HospedeController hospedeController;
	ValidaDados validaDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		this.reservaController = new ReservaController();
		this.hospedeController = new HospedeController();
		this.validaDados = new ValidaDados();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		listarReserva();

		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), tbHospedes, null);
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");

		listarHospede();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (panel.getSelectedIndex() == 0) {

					if (txtBuscar.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor digite o id da reserva");
					} else {

						limparTabelaReserva();
						listarReservaComFiltro(Integer.valueOf(txtBuscar.getText().trim()));
					}

				}

				if (panel.getSelectedIndex() == 1) {

					if (txtBuscar.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor digite o sobrenome");
					} else {

						limparTabelaHospede();
						listarHospedeComFiltro(txtBuscar.getText().trim());
					}
				}
			}
		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (panel.getSelectedIndex() == 0) {

					if (validaDadosReserva()) {

						if (tbReservas.getSelectedColumn() != 0) {//verifica se a celula selecionada é idReserva
							JOptionPane.showMessageDialog(null, "selecione o idReserva");
							return;
						} else {
							Vector elementAt = modelo.getDataVector().elementAt(tbReservas.getSelectedRow()); // pega a linha selecionada

							String idSelecionado = modelo
									.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()).toString();
							String dataentrada = elementAt.get(1).toString();
							String datasaida = elementAt.get(2).toString();
							
							Reserva reserva = new Reserva(java.sql.Date.valueOf(dataentrada),
									java.sql.Date.valueOf(datasaida), elementAt.get(3).toString(),
									elementAt.get(4).toString());
							reserva.setId(Integer.valueOf(idSelecionado));

							reservaController.editar(reserva);
							JOptionPane.showMessageDialog(null, "edição concluida com sucesso");
						}
					}
				}
				if (panel.getSelectedIndex() == 1) {

					if (validaDadosHospede()) {

						if (tbHospedes.getSelectedColumn() != 0) {
							JOptionPane.showMessageDialog(null, "selecione o idHospede");
							return;
						} else {

							Vector elementAt = modeloHospedes.getDataVector().elementAt(tbHospedes.getSelectedRow());

							Date dataNascimento = java.sql.Date.valueOf(elementAt.get(3).toString());
							String idSelecionado = tbHospedes
									.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()).toString();

							Hospede hospede = new Hospede(elementAt.get(1).toString(), elementAt.get(2).toString(),
									dataNascimento, elementAt.get(4).toString(), elementAt.get(5).toString(),
									elementAt.get(6).toString());
							hospede.setIdHospede(Integer.valueOf(idSelecionado));

							hospedeController.editar(hospede);
							JOptionPane.showMessageDialog(null, "edição concluida com sucesso");
						}
					}
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (panel.getSelectedIndex() == 0) {

					if (tbReservas.getSelectedColumn() != 0) {
						JOptionPane.showMessageDialog(null, "Selecione o idReserva para deletar.");
						return;
					} else {

						String idSelecionado = modelo
								.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()).toString();

						reservaController.deletar(Integer.valueOf(idSelecionado));
						limparTabelaReserva();
						listarReserva();
						JOptionPane.showMessageDialog(null, "Deletado com sucesso.");
					}
				}

				if (panel.getSelectedIndex() == 1) {

					if (tbHospedes.getSelectedColumn() != 0) {
						JOptionPane.showMessageDialog(null, "Selecione o IDHÓSPEDE pra deletar.");
						return;

					} else {

						String idSelecionado = modeloHospedes
								.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()).toString();
						hospedeController.deletar(Integer.valueOf(idSelecionado));
						limparTabelaHospede();
						listarHospede();
						JOptionPane.showMessageDialog(null, "Deletado com sucesso.");
					}
				}
			}
		});
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	private void listarReserva() {
		List<Reserva> reservas = this.reservaController.listar();

		modelo.addRow(new Object[] { "IDRESERVA", "DATA IN", "DATA OUT", "VALOR", "PAGAMENTO" });
		for (int i = 0; i < reservas.size(); i++) {
			Reserva resevaDaVez = reservas.get(i);
			modelo.addRow(new Object[] { resevaDaVez.getId(), resevaDaVez.getDataEntrada(), resevaDaVez.getDataSaida(),
					resevaDaVez.getValor(), resevaDaVez.getFormaPagamento() });
		}

	}

	private void listarReservaComFiltro(Integer id) {
		List<Reserva> reservas = this.reservaController.listaComFiltro(id);
		modelo.addRow(new Object[] { "IDRESERVA", "DATA IN", "DATA OUT", "VALOR", "PAGAMENTO" });
		
		for (Reserva reserva : reservas) {
			modelo.addRow(new Object[] { reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(),
					reserva.getValor(), reserva.getFormaPagamento() });
		}
	}

	private void listarHospede() {
		List<Hospede> listaHospedes = this.hospedeController.listar();

		modeloHospedes.addRow(new Object[] { "IDHÓSPEDE", "NOME", "SOBRENOME", "DATANASCIMENTO", "NACIONALIDADE",
				"TELEFONE", "IDRESERVA" });
		for (Hospede hospede : listaHospedes) {
			modeloHospedes.addRow(new Object[] { hospede.getIdHopede(), hospede.getNome(), hospede.getSobrenome(),
					hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
					hospede.getIdReserva() });
		}

	}

	private void listarHospedeComFiltro(String sobrenome) {
		List<Hospede> listaHospedes = this.hospedeController.listarComFiltro(sobrenome);

		modeloHospedes.addRow(new Object[] { "IDHÓSPEDE", "NOME", "SOBRENOME", "DATANASCIMENTO", "NACIONALIDADE",
				"TELEFONE", "IDRESERVA" });
		for (Hospede hospede : listaHospedes) {
			modeloHospedes.addRow(new Object[] { hospede.getIdHopede(), hospede.getNome(), hospede.getSobrenome(),
					hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
					hospede.getIdReserva() });
		}
	}

	private void limparTabelaReserva() {

		int TotalLinhas = modelo.getRowCount();

		for (int i = TotalLinhas; i >= 1; i--) {
			modelo.removeRow(i - 1);
		}

	}

	private void limparTabelaHospede() {

		int totalLinhas = modeloHospedes.getRowCount();

		for (int i = totalLinhas; i >= 1; i--) {
			modeloHospedes.removeRow(i - 1);
		}

	}
	
//	private boolean validaDadosReserva2(){
//		boolean valido = false;
//		Vector elementAt = modelo.getDataVector().elementAt(tbReservas.getSelectedRow());
//
//		boolean valor = validaDados.validaString(elementAt.get(3).toString(), "VALOR");
//		boolean id = validaDados.validaInteger(elementAt.get(0).toString(), "ID");
//		boolean dataIn = validaDados.validaDate(elementAt.get(1).toString(), "DATA IN");
//		
//		if(valor && id && dataIn) {
//			return valido = true;
//		}
//		return valido;
//	}
	
	private boolean validaDadosReserva() {
		boolean valido = true;
		Vector elementAt = modelo.getDataVector().elementAt(tbReservas.getSelectedRow());

		if (elementAt.get(1).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo DATA IN estar vazio.");
			valido = false;
		} else {
			try {
				String dataentrada = elementAt.get(1).toString();
				java.sql.Date.valueOf(dataentrada);
			} catch (IllegalArgumentException | NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Campo DATA IN precissa esta no formato: yyyy-MM-dd");
				valido = false;
			}
		}

		if (elementAt.get(2).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo DATA OUT estar vazio.");
			valido = false;
		} else {
			try {
				String dataentrada = elementAt.get(2).toString();
				java.sql.Date.valueOf(dataentrada);
			} catch (IllegalArgumentException | NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Campo DATA OUT precissa esta no formato: yyyy-MM-dd");
				valido = false;
			}
		}

		if (elementAt.get(3).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo VALOR estar vazio.");
			valido = false;
		}

		if (elementAt.get(4).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo PAGAMENTO estar vazio.");
			valido = false;
		}

		if (elementAt.get(0).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo ID estar vazio.");
			valido = false;
		} else {
			try {
				Integer.valueOf(elementAt.get(0).toString());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Campo ID precissa ser numero");
				valido = false;
			}
		}

		return valido;
	}

	private boolean validaDadosHospede() {
		boolean valido = true;
		Vector elementAt = modeloHospedes.getDataVector().elementAt(tbHospedes.getSelectedRow());

		if (elementAt.get(1).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo NOME estar vazio.");
			valido = false;
		}
		
		if (elementAt.get(2).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo SOBRENOME estar vazio.");
			valido = false;
		}
		
		if (elementAt.get(3).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo DATANASCIMENTO estar vazio.");
			valido = false;
		} else {
			try {
				String dataNascimento = elementAt.get(3).toString();
				java.sql.Date.valueOf(dataNascimento);
			} catch (IllegalArgumentException | NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Campo DATANASCIMENTO precissa esta no formato: yyyy-MM-dd");
				valido = false;
			}
		}
		
		if (elementAt.get(4).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo NACIONALIDADE estar vazio.");
			valido = false;
		}
		
		if (elementAt.get(5).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo TELEFONE estar vazio.");
			valido = false;
		}
		
		if (elementAt.get(6).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo IDRESERVA estar vazio.");
			valido = false;
		}
		
		if (elementAt.get(0).toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo ID estar vazio.");
			valido = false;
		} else {
			try {
				Integer.valueOf(elementAt.get(0).toString());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Campo ID precissa ser numero");
				valido = false;
			}
		}
		return valido;
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}

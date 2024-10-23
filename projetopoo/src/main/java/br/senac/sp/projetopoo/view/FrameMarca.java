package br.senac.sp.projetopoo.view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senac.sp.projetopoo.dao.ConnectionFactory;
import br.senac.sp.projetopoo.modelo.Marca;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class FrameMarca extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtId;
	private JLabel lblLogo;
	private Marca nomeMarca;
	private DefaultListModel defaultListModel = new DefaultListModel();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMarca frame = new FrameMarca();
					frame.setVisible(true);
					ConnectionFactory.getConexao();
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameMarca() {
		setTitle("Cadastro de marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(10, 48, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(57, 45, 240, 20);
		contentPane.add(txtNome);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(57, 8, 46, 20);
		contentPane.add(txtId);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic('s');
		btnSalvar.setBounds(79, 112, 89, 23);
		contentPane.add(btnSalvar);
		
		//tratamento de botao
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog
					(FrameMarca.this, "Informe o nome: ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					txtNome.requestFocus();
				}else {
					nomeMarca = new Marca();
					nomeMarca.setnomeMarca(txtNome.getText().trim());
					nomeMarca.getnomeMarca();
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic('x');
		btnExcluir.setBounds(178, 112, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setMnemonic('l');
		btnLimpar.setBounds(277, 112, 89, 23);
		contentPane.add(btnLimpar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setOpaque(true);
		lblLogo.setBackground(SystemColor.activeCaptionBorder);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBounds(333, 11, 91, 55);
		contentPane.add(lblLogo);
		
		
		JList list = new JList();
		list.setBounds(10, 146, 414, 104);
		contentPane.add(list);
		list.setModel(defaultListModel);

		//Metodo para inserir objetos na defaultListModel que serao exibido na jlist.
		defaultListModel.addElement(nomeMarca);
		//Metodo usado para pegar o valor selecionado na list retornando um Object.
		list.getSelectedValue();
		
	}
}

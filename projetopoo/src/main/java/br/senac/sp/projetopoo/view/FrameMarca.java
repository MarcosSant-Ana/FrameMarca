package br.senac.sp.projetopoo.view;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.senac.sp.projetopoo.dao.ConnectionFactory;
import br.senac.sp.projetopoo.dao.MarcaDAO;
import br.senac.sp.projetopoo.modelo.Marca;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.nio.file.Files;
import java.sql.SQLException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrameMarca extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtId;
	private JLabel lblLogo;
	private Marca marca;
	private MarcaDAO dao;
	private JFileChooser chooser;
	private FileFilter imageFilter;
	private File selecionado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMarca frame = new FrameMarca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameMarca() {
		dao = new MarcaDAO(ConnectionFactory.getConexao());
		chooser = new JFileChooser();

		// cria um filtro de imagens, e cria um vetor de strings no
		// ImageIO.getReaderFileSuffixes()
		imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

		setTitle("Cadastro de marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// criando os texto/titulos
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(10, 48, 46, 14);
		contentPane.add(lblNome);

		// criando os campos de texto
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(57, 45, 250, 30);
		contentPane.add(txtNome);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(57, 8, 46, 20);
		contentPane.add(txtId);

		// criação dos botões
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setMnemonic('s');
		btnSalvar.setBounds(79, 112, 89, 23);
		contentPane.add(btnSalvar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic('e');
		btnExcluir.setBounds(178, 112, 89, 23);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setMnemonic('l');
		btnLimpar.setBounds(277, 112, 89, 23);
		contentPane.add(btnLimpar);

		// cria um label do campo da imagem logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setOpaque(true);
		lblLogo.setBackground(SystemColor.activeCaptionBorder);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBounds(345, 11, 79, 78);
		contentPane.add(lblLogo);

		// da ação ao clicar no campo da imagem para selecionar nos arquivos da maquina
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chooser.setFileFilter(imageFilter);
					// dialog assume a execução
					if (chooser.showOpenDialog(FrameMarca.this) == JFileChooser.APPROVE_OPTION) {
						selecionado = chooser.getSelectedFile();
						try {
							BufferedImage bufImg = ImageIO.read(selecionado);
							Image imagem = bufImg.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
									Image.SCALE_SMOOTH);
							ImageIcon imgLabel = new ImageIcon(imagem);
							lblLogo.setIcon(imgLabel);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		// cria a ação para o botão salvar
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(FrameMarca.this, "Informe o nome: ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					txtNome.requestFocus();
				} else {
					// setar a marca, try catch pra tratar o erro que foi jogado pra cima na classe
					// marcaDAO
					marca = new Marca();
					marca.setnomeMarca(txtNome.getText().trim());
					try {
						if (selecionado != null) {
							byte[] imagemBytes = Files.readAllBytes(selecionado.toPath());
							marca.setLogo(imagemBytes);
						}
						dao.inserir(marca);
						limpar();
					} catch (SQLException | IOException e1) {
						JOptionPane.showMessageDialog(FrameMarca.this, e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	// metodo limpar, seta todos os campos de texto da janela pra "";
	private void limpar() {
		txtId.setText("");
		txtNome.setText("");
		lblLogo.setIcon(null);
		marca = null;
		txtNome.requestFocus();
	}
}

package br.com.cod3r.cm.visao;

import javax.swing.JFrame;

import br.com.cod3r.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame { // como TelaPrincipal herda de JFrame, entao TelaPrincipal é um JFrame e eu não preciso instanciar um JFrame 

	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
		add(new PainelTabuleiro(tabuleiro));
		
		setTitle("Campo Minado"); // titulo
		setSize(690, 438); // tamanho
		setLocationRelativeTo(null); // para ficar centralizado na tela
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // encerra a operação assim que a janela for fechada
		setVisible(true);  // primeiro comando, para que a tela fique visivel
	}
	
	public static void main(String[] args) {	
		new TelaPrincipal();
	}
}

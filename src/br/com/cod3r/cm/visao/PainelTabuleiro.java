package br.com.cod3r.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel { // classe que vai representar visualmente um tabuleiro

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		
		setLayout(new GridLayout(
				tabuleiro.getLinhas(), tabuleiro.getColunas())); // comando para definir como os componentes visuais serão organizados na tela
		
		tabuleiro.paraCadaCampo(campo -> add(new BotaoCampo(campo))); // criar um botao para cada campo do tabuleiro
		
		tabuleiro.registrarObservador(evento -> {
			
			SwingUtilities.invokeLater(() -> {
				if(evento.isGanhou()) {
					JOptionPane.showMessageDialog(this, "GANHOU MEU FI :)");
				} else {
					JOptionPane.showMessageDialog(this, "FOI LOST PARCERO :(");
				}
				
				tabuleiro.reiniciar();
			});
		});
	}
}

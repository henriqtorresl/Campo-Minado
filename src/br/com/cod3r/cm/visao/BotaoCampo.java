package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;
										  //classe que vai representar visualmente os botões do jogo
@SuppressWarnings("serial")				  // a classe BotaoCampo é responsável por "traduzir" em termos visuais tudo aquilo que acontece na classe Campo
public class BotaoCampo extends JButton 
		implements CampoObservador, MouseListener{ 

	// esses números no construtor representam o RGB (RED, GREEN, BLUE), o quanto eu qro de cada cor rgb
	private final Color BG_PADRAO = new Color(184, 184, 184); // BG -> Backgroud
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	
	private Campo campo;
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		
		
		addMouseListener(this);
		campo.registrarObservador(this); // esse "this" diz respeito a classe BotaoCampo
	}

	// metodo que vai chamar todos os metodos que vão mecher no estilo do botao:
	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch(evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}
		// comando que garante que não vai ter nenhum problema de renderização:
		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();
		});
	}
	
	private void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if (campo.isMinado()) {
			setBackground(BG_EXPLODIR); // se tiver minado ele vai setar o campo como vermelho
			return; // return para sair do if apos setar o campo como vermelho
		}
		
		setBackground(BG_PADRAO);
		
		switch(campo.minasNaVizinhanca()) {
		case 1:
			setForeground(TEXTO_VERDE);
			break;
		case 2: 
			setForeground(Color.BLUE);
			break;
		case 3: 
			setForeground(Color.YELLOW);
			break;
		case 4: 
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}
		
		String valor = !campo.vizinhancaSegura() ?   // se a vizinhança não for segura, eu mostro as minas na vizinhanca (o metodo minas na vizinhança esta concatenada com uma string vazia, pois a variavel valor tem q retornar uma string)
				campo.minasNaVizinhanca() + "" : ""; // se for segura, eu mostro uma string vazia
		setText(valor);
	}

	// Metodos da Interface dos eventos do Mouse (MouseListener)
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) { // clique no botão esquerdo
			campo.abrir();
		} else { // clique em qualquer outro botão
			campo.alterarMarcacao();
		}
	}

	// metodos da interface MouseListener que não vou precisar usar:
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
		
}

package br.com.cod3r.cm.modelo;

@FunctionalInterface
public interface CampoObservador {

	public void eventoOcorreu(Campo campo, CampoEvento evento);
	// basicamente, quando um evento acontecer, eu preciso saber qual foi o campo em que ocorreu o evento e qual foi o evento
}

package servico;

import java.util.List;

import dao.DaoFactory;
import dao.ParticipacaoDao;
import dao.Transaction;
import dominio.Participacao;

public class ParticipacaoServico {
	private ParticipacaoDao dao;

	public ParticipacaoServico() {
		dao = DaoFactory.criarParticipacaoDao();
	}

	public void inserir(Participacao x) throws ServicoException {
		try {
			Participacao aux = dao.buscarExato(x.getPersonagem(), x.getArtista(), x.getFilme());
			
			if(aux != null) {
				throw new ServicoException("Personagem ja existe para " + x.getArtista().getNome() + " no filme " + x.getFilme().getTitulo(), 1);
			}
			
			Transaction.begin();
			dao.inserirAtualizar(x);
			Transaction.commit();
		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.roolback();
			}
		}
	}
	
	public void atualizar(Participacao x) throws ServicoException {
		try {
			Participacao aux = dao.buscarExatoDiferente(x.getCodParticipacao(), x.getPersonagem(), x.getArtista(), x.getFilme());
			
			if(aux != null) {
				throw new ServicoException("Personagem ja existe para " + x.getArtista().getNome() + " no filme " + x.getFilme().getTitulo(), 1);
			}
			
			Transaction.begin();
			dao.inserirAtualizar(x);
			Transaction.commit();
		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.roolback();
			}
		}
	}

	public void excluir(Participacao x) {
		try {
			Transaction.begin();
			dao.excluir(x);
			Transaction.commit();
		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.roolback();
			}
		}
	}

	public Participacao buscar(int cod) {
		return dao.buscar(cod);
	}

	public List<Participacao> buscarTodos() {
		return dao.buscarTodos();
	}

}

package servico;

import java.util.ArrayList;
import java.util.List;

import dao.ArtistaDao;
import dao.DaoFactory;
import dao.Transaction;
import dominio.Artista;

public class ArtistaServico {
	private ArtistaDao dao;

	public ArtistaServico() {
		dao = DaoFactory.criarArtistaDao();
	}

	public void validar(Artista x) throws ValidacaoException {
		List<String> erros = new ArrayList<>();
		
		if (x.getNome() == null) {
			erros.add("Favor preencher campo nome!");
		}
		
		if (x.getNacionalidade() == null) {
			erros.add("Favor preencher campo nacionalidade!");
		}
		
		if (x.getNascimento() == null) {
			erros.add("Favor preencher campo nascimento com valor valido!");
		}
		
		if (x.getCache() == null) {
			erros.add("Favor preencher campo cache com valor valido!");
		}
		
		if(!erros.isEmpty()) {
			throw new ValidacaoException("Erro de validacao!", erros);
		}
 	}

	public void inserir(Artista x) throws ServicoException {
		try {
			Artista aux = dao.buscaNomeExato(x.getNome());

			if (aux != null) {
				throw new ServicoException("Ja existe um artista com esse nome ue", 1);
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

	public void atualizar(Artista x) throws ServicoException {
		try {
			Artista aux = dao.buscaNomeExatoDiferente(x.getCodArtista(), x.getNome());

			if (aux != null) {
				throw new ServicoException("Ja existe um artista com esse nome ue", 1);
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

	public void excluir(Artista x) throws ServicoException {
		try {
			x = dao.buscar(x.getCodArtista());
			if (!x.getParticipacoes().isEmpty()) {
				throw new ServicoException("Erro ! Nao se pode excluir artistas com participacoes", 2);

			}

			Transaction.begin();
			dao.excluir(x);
			Transaction.commit();
		} catch (RuntimeException e) {
			if (Transaction.isActive()) {
				Transaction.roolback();
			}
		}
	}

	public Artista buscar(int cod) {
		return dao.buscar(cod);
	}

	public List<Artista> buscarTodos() {
		return dao.buscarTodos();
	}

	public List<Artista> buscarTodosOrdenadosPorNome() {
		return dao.buscarTodosOrdenadosPorNome();
	}

	public List<Artista> buscarPorNome(String trecho) {
		return dao.buscarPorNome(trecho);
	}
}

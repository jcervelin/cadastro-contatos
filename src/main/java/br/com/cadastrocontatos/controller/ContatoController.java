package br.com.cadastrocontatos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrocontatos.model.Contato;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/contatos")
public class ContatoController {
	
	private List<Contato> Contatos = new ArrayList();


	ContatoController() {
		this.Contatos = buildContatos();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Contato> getContatos() {
		return this.Contatos;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Contato getContato(@PathVariable("id") Long id) {
		return this.Contatos.stream().filter(Contato -> Contato.getId() == id).findFirst().orElse(null);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Contato saveContato(@RequestBody Contato Contato) {
		Long nextId = 0L;
		if (this.Contatos.size() != 0) {
			Contato lastContato = this.Contatos.stream().skip(this.Contatos.size() - 1).findFirst().orElse(null);
			nextId = lastContato.getId() + 1;
		}

		Contato.setId(nextId);
		this.Contatos.add(Contato);
		return Contato;

	}

	@RequestMapping(method = RequestMethod.PUT)
	public Contato updateContato(@RequestBody Contato contato) {
		Contato modifiedContato = this.Contatos.stream().filter(u -> u.getId() == contato.getId()).findFirst().orElse(null);
		modifiedContato.setNome(contato.getNome());
		modifiedContato.setTelefone(contato.getTelefone());
		modifiedContato.setEmail(contato.getEmail());
		modifiedContato.setTipoContato(contato.getTipoContato());
		return modifiedContato;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteContato(@PathVariable Long id) {
		Contato deleteContato = this.Contatos.stream().filter(Contato -> Contato.getId() == id).findFirst().orElse(null);
		if (deleteContato != null) {
			this.Contatos.remove(deleteContato);
			return true;
		} else  {
			return false;
		}
	}

	List<Contato> buildContatos() {
		List<Contato> Contatos = new ArrayList<>();

		Contato Contato1 = buildContato(1L, "John Doe", "11943399491", "john@email.com","trabalho");
		Contato Contato2 = buildContato(2L, "Jon Smith", "11943399492", "smith@email.com","trabalho");
		Contato Contato3 = buildContato(3L, "Will Craig", "11943399493", "will@email.com","trabalho");
		Contato Contato4 = buildContato(4L, "Sam Lernorad", "11943399494", "sam@email.com","trabalho");
		Contato Contato5 = buildContato(5L, "Ross Doe", "11943399495", "ross@email.com","trabalho");

		Contatos.add(Contato1);
		Contatos.add(Contato2);
		Contatos.add(Contato3);
		Contatos.add(Contato4);
		Contatos.add(Contato5);

		return Contatos;

	}

	Contato buildContato(Long id, String nome, String telefone, String email, String tipoContato) {
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		contato.setTipoContato(tipoContato);
		return contato;
	}

}

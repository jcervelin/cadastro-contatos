package br.com.cadastrocontatos;

import java.util.Arrays;
import java.util.List;

public class Capitulo2 {

	public static void main(String... args) {
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		for (Usuario u : usuarios) {
			System.out.println(u.getNome());
		}
	}

}

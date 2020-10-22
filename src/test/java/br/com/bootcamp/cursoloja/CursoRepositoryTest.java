package br.com.bootcamp.cursoloja;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import br.com.bootcamp.cursoloja.model.Curso;
import br.com.bootcamp.cursoloja.repository.CursoRepository;

@SpringBootTest
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;

	@Test
	void findByNomeSimples() {
		List<Curso> cursos = cursoRepository.findByNome("Curso de Java");
		Assertions.assertThat(cursos).hasSize(1);
	}

	@Test
	void findByNomeLike() {
		List<Curso> cursos = cursoRepository.findByNomeLike("%Java");
		Assertions.assertThat(cursos).hasSize(1);
	}

	@Test
	void findByNomeLikeOrdenado() {
		List<Curso> cursos = cursoRepository.findByNomeLikeOrderByNomeAscPrecoDesc("Curso%");
		Assertions.assertThat(cursos).hasSize(2);
	}

	@Test
	void findByNomeComQuery() {
		List<Curso> cursos = cursoRepository.buscaPorNome("Curso de Java");
		Assertions.assertThat(cursos).hasSize(1);
	}

	@Test
	void findByNomeComQueryNativa() {
		List<Curso> cursos = cursoRepository.buscaPorNomeNativo("Curso de Java");
		Assertions.assertThat(cursos).hasSize(1);
	}

	@Test
	void findByNomeNamedQuery() {
		List<Curso> cursos = cursoRepository.filtraPorNome("Curso de Java");
		Assertions.assertThat(cursos).hasSize(1);
	}

	@Test
	void findByExample() {

		Curso filtro = new Curso();
		filtro.setNome("java");
		filtro.setPreco(123.45);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

		Example<Curso> exemplo = Example.of(filtro, matcher);

		List<Curso> cursos = cursoRepository.findAll(exemplo);
		Assertions.assertThat(cursos).hasSize(1);
	}
}

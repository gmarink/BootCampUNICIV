package br.com.bootcampo.cursoloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.bootcampo.cursoloja.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

}

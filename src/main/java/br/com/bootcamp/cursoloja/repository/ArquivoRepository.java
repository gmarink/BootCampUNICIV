package br.com.bootcamp.cursoloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bootcamp.cursoloja.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, String>{

}

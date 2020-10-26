package br.com.bootcamp.cursoloja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bootcamp.cursoloja.model.ERole;
import br.com.bootcamp.cursoloja.model.Role;

 

public interface RoleRepositorio extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(ERole roleUser);

}

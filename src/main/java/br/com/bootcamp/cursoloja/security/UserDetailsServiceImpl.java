package br.com.bootcamp.cursoloja.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bootcamp.cursoloja.model.Usuario;
import br.com.bootcamp.cursoloja.repository.UsuarioRepositorio;

 

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository
				.findByUsername(username)
				      	.orElseThrow(() -> new UsernameNotFoundException(
				"Usuário não encontrado"));
		
		return UserDetailsImpl.build(user);
	}

}

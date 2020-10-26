package br.com.bootcamp.cursoloja.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bootcamp.cursoloja.controller.request.CursoRequest;
import br.com.bootcamp.cursoloja.controller.response.CursoResponse;
import br.com.bootcamp.cursoloja.model.Arquivo;
import br.com.bootcamp.cursoloja.model.Curso;
import br.com.bootcamp.cursoloja.service.CursoService;

@RestController
@Validated
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@GetMapping
	public List<CursoResponse> todos(CursoRequest cursoRequest){
		
		Curso curso = modelMapper.map(cursoRequest, Curso.class);
			List<Curso> cursos = cursoService.filtraPor(curso);
			List<CursoResponse> cursosResponse = cursos
												.stream()
												.map((c)-> {
												return new CursoResponse(c);
												}).collect(Collectors.toList());
	return cursosResponse;
	}
	
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CursoResponse salvar(
			@RequestPart("curso") 
			@Valid CursoRequest cursoRequest,
			
			@RequestPart(value = "file", required = false)
			MultipartFile arquivoResquest){
		
		 
		Arquivo arquivo =null;
		try {
			
			String nomeArquivo = arquivoResquest.getOriginalFilename();
			String tipo = arquivoResquest.getContentType();
			byte[] conteudo = arquivoResquest.getBytes();
			
			arquivo = new Arquivo(nomeArquivo, tipo, conteudo);
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
		//lib que mapeia classe
		Curso curso = modelMapper.map(cursoRequest, Curso.class);
		 	curso.setArquivo(arquivo);
		 
		Curso cursoSalvo = cursoService.salvar(curso);
		return new CursoResponse(cursoSalvo);
	}
	
	
 
	@PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CursoResponse alterar(
			@PathVariable Integer id, 
			@RequestPart("curso") CursoRequest cursoRequest,
			@RequestPart(value = "file", required = false) MultipartFile arquivoRequest){
	 
		
		Arquivo arquivo = getArquivo(arquivoRequest);
		Curso curso = modelMapper.map(cursoRequest, Curso.class);
		
		if(arquivo != null) {
		curso.setArquivo(arquivo);	
		}
		
		Curso cursoAlterado = cursoService.alterar(id, curso);
		return new CursoResponse(cursoAlterado);
	}


	private Arquivo getArquivo(MultipartFile arquivoResquest) {
		Arquivo arquivo =null;
		try {
			
			String nomeArquivo = arquivoResquest.getOriginalFilename();
			String tipo = arquivoResquest.getContentType();
			byte[] conteudo = arquivoResquest.getBytes();
			
			arquivo = new Arquivo(nomeArquivo, tipo, conteudo);
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
		return arquivo;
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer id) {
		cursoService.excluir(id);
	}
	

}

package com.florencio.user.back.end.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.florencio.user.back.end.dto.UserDTO;

@RestController
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public String getMessage() {
		return "Spring boot is working !";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserDTO> getUsers() {
		return usuarios;
	}

	@RequestMapping(value = "users/{cpf}", method = RequestMethod.GET)
	public UserDTO getUsersFiltro(@PathVariable String cpf) {
		for (UserDTO userFilter : usuarios) {
			if (userFilter.getCpf().equals(cpf)) {
				return userFilter;
			}
		}
		return null;
	}

	@RequestMapping(value = "newUsers", method = RequestMethod.POST)
	UserDTO inserir(@RequestBody UserDTO userDTO) {
		userDTO.setDataCadastro(new Date());
		usuarios.add(userDTO);
		return userDTO;
	}

	@RequestMapping(value= "users/{cpf}", method = RequestMethod.DELETE)
	public boolean remover (@PathVariable String cpf) {
		for (UserDTO userFilter: usuarios) {
			if( userFilter.getCpf().equals(cpf)) {
				usuarios.remove(userFilter);
				return true;
			}
		}
		return false;
	}

	public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

	@PostConstruct
	public void initiateList() {
		UserDTO userDTO = new UserDTO();
		userDTO.setNome("Eduardo");
		userDTO.setCpf("123");
		userDTO.setEndereco("Rua a");
		userDTO.setEmail("eduardo@email.com");
		userDTO.setTelefone("1234-3454");
		userDTO.setDataCadastro(new Date());

		UserDTO userDTO2 = new UserDTO();
		userDTO2.setNome("Luiz");
		userDTO2.setCpf("456");
		userDTO2.setEndereco("Rua b");
		userDTO2.setEmail("luiz@email.com");
		userDTO2.setTelefone("1234-3454");
		userDTO2.setDataCadastro(new Date());

		UserDTO userDTO3 = new UserDTO();
		userDTO3.setNome("Bruna");
		userDTO3.setCpf("678");
		userDTO3.setEndereco("Rua c");
		userDTO3.setEmail("bruna@email.com");
		userDTO3.setTelefone("1234-3454");
		userDTO3.setDataCadastro(new Date());

		usuarios.add(userDTO);
		usuarios.add(userDTO2);
		usuarios.add(userDTO3);
	}

}

package com.chubb.test.service;

import com.chubb.test.model.dtos.ClientDto;

import java.util.List;

public interface IClientService {

	public List<ClientDto> findAll();

	public int save(ClientDto client);

	public boolean deleteById(int id);

}

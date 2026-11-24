package com.chubb.test.respository;

import com.chubb.test.model.Client;

import java.util.List;

public interface IClientRepository {
	
  public List<Client> findAll(); 

  public int save(Client client);

  public int delete(int id);
  
}

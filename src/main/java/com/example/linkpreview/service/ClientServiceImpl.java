package com.example.linkpreview.service;
/*
import com.example.linkpreview.Entity.Client;
import com.example.linkpreview.exceptions.UserNotFoundException;
import com.example.linkpreview.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client client) {
            clientRepository.save(client);
    }

    @Override
    public String getPassword(String userName) throws UserNotFoundException {
       Client client= clientRepository.findById(userName).get();

      if(client==null){
          throw new UserNotFoundException("Userd with userName "+userName+" is Not found");
      }
       return  client.getSecretCode();
    }
}

 */

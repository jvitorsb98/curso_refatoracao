package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsCommand implements Command{
    @Override
    public void execute() {
        try {
            ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration();
            PetService petService = new PetService(clientHttpConfiguration);
            petService.listarPets();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

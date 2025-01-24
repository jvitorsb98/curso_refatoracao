package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class ListarAbrigoCommand implements Command{


    @Override
    public void execute() {
        try {
            ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration();
            AbrigoService abrigoService = new AbrigoService(clientHttpConfiguration);
            abrigoService.listarAbrigos();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

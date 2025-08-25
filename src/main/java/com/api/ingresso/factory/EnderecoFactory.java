package com.api.ingresso.factory;

import java.util.Locale;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.embeddable.Endereco;
import com.api.ingresso.dto.EnderecoDTO;
import com.github.javafaker.Faker;

@Component
public class EnderecoFactory {

   private final Faker faker = new Faker(Locale.forLanguageTag("pt-BR"));

    public EnderecoDTO criarEnderecoDTOAleatorio() {
        return new EnderecoDTO(
            /**
             *                 endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getComplemento(),
                endereco.getNumero()
             */
            faker.address().streetAddress(),            
            faker.address().cityName(),                 
            faker.number().digits(8),             
            faker.address().city(),                     
            faker.address().stateAbbr(),                
            faker.address().secondaryAddress(),         
            faker.address().buildingNumber()        
        );
    }
    

    public Endereco criarEnderecoEmbeddableAleatorio() {
        return new Endereco(
            faker.address().streetAddress(),            
            faker.address().cityName(),                 
            faker.number().digits(8),             
            faker.address().city(),                     
            faker.address().stateAbbr(),                
            faker.address().secondaryAddress(),         
            faker.address().buildingNumber()    
        );
    }
}
package com.senai.Conta_Bancaria.infrastructure.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI contaBancariaOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api - Conta Bancária")
                        .description("Sistema para gestão de contas bancária, incluindo criação de contas, saques, depósitos, transferências e rendimentos")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Conta Bancária ")
                                .email("suporte@banco.com")
                        )
                );

    }

}

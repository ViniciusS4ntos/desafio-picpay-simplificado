package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.Controller.dtos.out.AuthorizationResponse;
import com.vinicius.PicPaySimplificado.infras.exceptions.BusinessException;import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizationService {

    private final RestClient restClient = RestClient.create();

    public boolean isAuthorized() {

        try {

            AuthorizationResponse response = restClient.get()
                    .uri("https://util.devi.tools/api/v2/authorize")
                    .retrieve()
                    .body(AuthorizationResponse.class);

            return response != null
                    && response.data() != null
                    && response.data().authorization();

        } catch (Exception e) {
            throw new BusinessException("Serviço autorizador indisponível.");
        }
    }
}
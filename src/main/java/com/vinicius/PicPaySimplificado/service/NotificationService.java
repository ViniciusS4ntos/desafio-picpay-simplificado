package com.vinicius.PicPaySimplificado.service;

import com.vinicius.PicPaySimplificado.Controller.dtos.out.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final RestClient restClient;
    private final String NOTIFY_URL = "https://util.devi.tools/api/v1/notify";

    public NotificationService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Async
    @Retryable(
            retryFor = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void sendNotification(String email, String message) {
        log.info("Tentando enviar notificação para: {}", email);

        NotificationDTO payload = new NotificationDTO(email, message);

        restClient.post()
                .uri(NOTIFY_URL)
                .body(payload)
                .retrieve()
                .toBodilessEntity();

        log.info("Notificação enviada com sucesso para: {}", email);
    }

    @Recover
    public void fallback(Exception e, String email, String message) {
        log.error("FALHA CRÍTICA: Não foi possível enviar a notificação para {} após 3 tentativas. Erro: {}", email, e.getMessage());
        // Aqui você pode logar em tabela de auditoria ou banco de dados
    }
}

package com.petclinic.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InvoiceService {

    private final RestClient restClient;

    @Value("${visit.price}")
    private double amount;

    @Autowired
    public InvoiceService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Invoice generateInvoice(Long id) {
        Invoice invoice = restClient
                .get()
                .uri("/visits/" + id)
                .retrieve()
                .body((Invoice.class));

        if (invoice == null) {
            throw new RuntimeException("Visit not found");
        }
        invoice.setAmount(amount);

        return invoice;
    }
}

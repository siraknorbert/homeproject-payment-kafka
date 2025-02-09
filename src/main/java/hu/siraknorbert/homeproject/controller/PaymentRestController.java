package hu.siraknorbert.homeproject.controller;

import hu.siraknorbert.homeproject.constant.ApiConstants;
import hu.siraknorbert.homeproject.dto.MoneyTransactionDto;
import hu.siraknorbert.homeproject.dto.PaymentTransferInitiateRequestDto;
import hu.siraknorbert.homeproject.exception.checked.CheckedException;
import hu.siraknorbert.homeproject.service.PaymentTransferInitiateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = ApiConstants.Documentation.PAYMENT_REST_NAME,
        description = ApiConstants.Documentation.PAYMENT_REST_DESCRIPTION)
@RequestMapping(ApiConstants.Path.API_PAYMENT)
@RequiredArgsConstructor
public class PaymentRestController {
    private final PaymentTransferInitiateService paymentTransferInitiateService;

    @Operation(
            summary = ApiConstants.Documentation.PAYMENT_TRANSFER_SUMMARY,
            description = ApiConstants.Documentation.GENERIC_AUTHENTICATED_ENDPOINT_DESCRIPTION)
    @PostMapping(
            path = ApiConstants.Path.TRANSFER,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MoneyTransactionDto> postPaymentTransfer(
            @Valid @RequestBody PaymentTransferInitiateRequestDto request) throws CheckedException {
        return ResponseEntity.ok(paymentTransferInitiateService.processInitiatePaymentTransfer(request));
    }
}

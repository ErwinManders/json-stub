package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Debit card data class.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebitCard extends AbstractCard {
    @NonNull
    private Limit atmLimit;
    @NonNull
    private Limit posLimit;

    public DebitCard(final String id, final String status, final long cardNumber, final int sequenceNumber, final String cardHolder, final Limit atmLimit, final Limit posLimit) {
        super(id, status, cardNumber, sequenceNumber, cardHolder);
        this.atmLimit = atmLimit;
        this.posLimit = posLimit;
    }

    @Data
    @RequiredArgsConstructor
    public static class Limit {
        @NonNull
        private long limit;
        @NonNull
        private String periodUnit;
    }
}

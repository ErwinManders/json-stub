package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    public DebitCard(final String id, final Limit atmLimit, final Limit posLimit) {
        super(id);
        this.atmLimit = atmLimit;
        this.posLimit = posLimit;
    }

    @Data
    public static class Limit {
        private long limit;
        @NonNull
        private String periodUnit;
    }
}

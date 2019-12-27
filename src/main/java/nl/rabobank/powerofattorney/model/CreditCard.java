package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * Credit card data class.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCard extends AbstractCard {
    @NonNull
    private long monthlyLimit;

    public CreditCard(final String id, final String status, final long cardNumber, final int sequenceNumber, final String cardHolder, final long monthlyLimit) {
        super(id, status, cardNumber, sequenceNumber, cardHolder);
        this.monthlyLimit = monthlyLimit;
    }
}

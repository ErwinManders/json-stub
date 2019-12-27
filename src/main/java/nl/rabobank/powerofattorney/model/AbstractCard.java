package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Abstract Card data class.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AbstractCard {
    @NonNull
    private String id;
    @NonNull
    private String status;
    @NonNull
    private long cardNumber;
    @NonNull
    private int sequenceNumber;
    @NonNull
    private String cardHolder;
}

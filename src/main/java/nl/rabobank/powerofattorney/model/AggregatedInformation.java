package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Aggregated information data class.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregatedInformation {
    private String id;
    private String grantor;
    private Account account;
    private String direction;
    private List<String> authorizations;
    private List<DebitCard> debitCards;
}

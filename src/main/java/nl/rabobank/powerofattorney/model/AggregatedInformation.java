package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Authorization data class.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregatedInformation {
    private String id;
    private String grantor;
    private String accountNumber;
    private String direction;
    private List<String> authorizations;
}

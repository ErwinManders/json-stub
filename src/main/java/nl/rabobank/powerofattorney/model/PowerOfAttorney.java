package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Power of attorney data class.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerOfAttorney {
    private String id;
    private String grantor;
    private String grantee;
    private String account;
    private String direction;
    private String[] authorizations;
    private Card[] cards;
}

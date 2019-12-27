package nl.rabobank.powerofattorney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NonNull;

/**
 * Account data class.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    @NonNull
    private String id;
    @NonNull
    private String owner;
    @NonNull
    private long balance;
    @NonNull
    private String created;
    private String ended;
}

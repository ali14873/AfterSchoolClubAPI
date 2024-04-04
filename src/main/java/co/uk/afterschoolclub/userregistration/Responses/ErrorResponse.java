package co.uk.afterschoolclub.userregistration.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    private String message;
    private String path;
}

package co.uk.afterschoolclub.userregistration.Session;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringDTO {
    private String repeat;
    private Integer interval;
    private String weekDays; // for "weekly" repeat type
    private Integer day; // for "monthly" and "yearly" repeat type
    private Integer month; // for "yearly" repeat type
}

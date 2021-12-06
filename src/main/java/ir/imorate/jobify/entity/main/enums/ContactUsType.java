package ir.imorate.jobify.entity.main.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContactUsType {

    CRITICISM("criticism"),
    RECOMMENDATION("recommendation"),
    QUESTION("question");

    private String title;

}

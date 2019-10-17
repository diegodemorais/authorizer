package nubank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nubank.validator.BusinessValidator;

public class Base {

    @JsonSerialize
    private String[] violations;
    @JsonIgnore
    private BusinessValidator validator;

    public Base(BusinessValidator validator) {
        this.initilizeViolations();
        this.setValidator(validator);
    }

    public String[] getViolations() {
        return violations;
    }

    void setViolations(String violations) {
        this.violations = new String[] { violations } ;
    }

    void initilizeViolations() {
        this.violations = new String[] { } ;
    }

    BusinessValidator getValidator() {
        return validator;
    }

    void setValidator(BusinessValidator validator) {
        this.validator = validator;
    }
}

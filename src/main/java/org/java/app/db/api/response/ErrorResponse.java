package org.java.app.db.api.response;

import java.util.Map;

public class ErrorResponse {
    private Map<String, String> errors;

    public ErrorResponse(Map<String, String> errors) {
      this.errors = errors;
    }

    // getter e setter per il campo errors
    public Map<String, String> getErrors() {
      return errors;
    }

    public void setErrors(Map<String, String> errors) {
      this.errors = errors;
    }
}

package org.java.app.db.api.response;

import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private Map<String, List<String>> errors;

    public ErrorResponse(Map<String, List<String>> errors) {
      this.errors = errors;
    }

    // getter e setter per il campo errors
    public Map<String, List<String>> getErrors() {
      return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
      this.errors = errors;
    }
}

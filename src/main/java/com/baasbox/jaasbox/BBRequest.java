package com.baasbox.jaasbox;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;

public abstract class BBRequest<R extends BBResponse> {

  protected abstract void makeRequest(BaasboxAPI api, Callback<R> callback);


  protected abstract static class BaseBuilder<T extends BBRequest> {
    protected Map<String, String> params;

    public BaseBuilder() {
      params = new HashMap<String, String>();
    }

    /**
     * Actually validates and builds the request.
     * If there are missing/bad parameters, an IllegalArgumentException will be thrown.
     * @return
     */
    public T build() {
      validate();

      // if a param was empty or null, remove it from the param list.
      Map<String, String> validParamsOnly = new HashMap<String, String>();
      for (Map.Entry<String, String> entry : params.entrySet()) {
        if (entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
          validParamsOnly.put(entry.getKey(), entry.getValue());
        }
      }
      params = validParamsOnly;
      return createInstance();
    }

    protected abstract T createInstance();

    /**
     * Validates all the parameters in this request before it's built (it is considered a 100% valid request after it is built).
     * If something about the parameters is incorrect, IllegalArgumentException should be thrown.
     */
    protected abstract void validate();

  }
}

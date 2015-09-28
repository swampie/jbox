package com.baasbox.jaasbox;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

import com.baasbox.jaasbox.http.ErrorResponse;

public class BBRetrofitCallback<T extends BBResponse> implements Callback<T> {

  private final BBCallback<T> parent;
  private final JboxHttp httpInterface;

  public BBRetrofitCallback(BBCallback<T> parent, JboxHttp httpInterface) {
    this.parent = parent;
    this.httpInterface = httpInterface;
  }

  @Override
  public void success(T t, Response response) {
    parent.success(t);
  }

  @Override
  public void failure(RetrofitError retrofitError) {
    Throwable ex = retrofitError;
    // do some logic to figure out why this error occurred
    String reason = "Unknown reason for exception, see stack trace";
    Response response = retrofitError.getResponse();
    switch (retrofitError.getKind()) {
      case CONVERSION:
        reason = "Encountered an error converting to a Java object from response JSON";
        break;

      case NETWORK:
        reason = "A network error occurred";
        break;

      case HTTP:
        try {
          ErrorResponse errorResponse = (ErrorResponse) httpInterface.getJsonConverter()
            .fromBody(response.getBody(),
              ErrorResponse.class);
          reason = (errorResponse.message == null ? "" : errorResponse.message);
        } catch (ConversionException | ClassCastException e) {
          // response did not have a formatted error response...should not happen
          ex = e;
          reason = "Response was not in the proper format.";
        }
        break;
      case UNEXPECTED:
      default:
        break;
    }
    parent.failure(new BBException(reason, response == null ? null : response.getStatus(), ex));
  }
}
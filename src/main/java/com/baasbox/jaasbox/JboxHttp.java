package com.baasbox.jaasbox;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JboxHttp {

  private String baseUrl;
  private String appcode;
  private final Converter jsonConverter;
  private RequestExecutor requestExecutor;
  private BaasboxAPI api;

  public JboxHttp(Client httpClient, String baseUrl,
    Integer threadPoolCount, AuthPolicy policy) {
    ObjectMapper mapper = new ObjectMapper();
    requestExecutor = new RequestExecutor(threadPoolCount);
    // Properties not present in the POJO are ignored instead of throwing exceptions
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    // An empty string ("") is interpreted as null
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    // when we intercept a request, this object adds the proper auth headers

    jsonConverter = new JacksonConverter(mapper);
    // create the API from a template interface using Retrofit
    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(baseUrl)
      .setClient(httpClient).setConverter(jsonConverter).build();
    api = adapter.create(BaasboxAPI.class);
    this.baseUrl = baseUrl;
	}

  /**
   * Makes a request just like an async request, but uses a synchronous callback to do so.
   */
  public <T extends BBResponse> T sendRequest(BBRequest<T> req) throws BBException {
    final BBCallback.SyncFCCallback<T> callback = new BBCallback.SyncFCCallback<T>();
    sendRequest(req, callback);
    try {
      return callback.get();
    } catch (InterruptedException e) {
      throw new BBException("Interrupted while waiting for a result!", e);
    }
  }

  public <T extends BBResponse> void sendRequest(BBRequest<T> req, BBCallback<T> callback) {
    if (callback == null) {

      // if the user didn't specify a callback, create a no-op callback instead
      callback = new BBCallback<T>() {
        @Override
        public void success(T response) {
        }

        @Override
        public void failure(BBException exception) {
        }
      };
    }
    // make a retrofit request with a callback that will call FCCallback
    requestExecutor.sendRequestAsync(api, req, new BBRetrofitCallback<T>(callback, this));
  }

  public RequestExecutor getRequestExecutor() {

    return requestExecutor;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public Converter getJsonConverter() {
    return jsonConverter;
  }

  protected void setRequestExecutor(RequestExecutor handler) {
    requestExecutor = handler;
  }

}

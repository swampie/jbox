package com.baasbox.jaasbox.auth;

import java.io.IOException;
import java.util.function.Supplier;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class BasicAuthInterceptor extends SupplierInterceptor<Credentials> {

  public BasicAuthInterceptor(Supplier<Credentials> supplier) {
    super(supplier);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request r = chain.request();
    Request.Builder builder = r.newBuilder();
    String value = calculateHeaderForBasicAuth(supplier.get());
    if (StringUtils.isNotBlank(value)) {
      builder.addHeader("Authorization", value);
    }
    return chain.proceed(builder.build());
  }

  private String calculateHeaderForBasicAuth(Credentials credentials) {
    byte[] encoding = Base64.encodeBase64(String.format("%s:%s", credentials.getUsername(), credentials.getPassword()).getBytes());
    String headerValue = String.format("Basic %s", new String(encoding));
    return headerValue;
  }

}

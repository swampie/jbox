package com.baasbox.jaasbox.auth;

import java.io.IOException;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class SessionTokenInterceptor extends SupplierInterceptor<String> {

  public SessionTokenInterceptor(Supplier<String> supplier) {
    super(supplier);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request r = chain.request();
    String token = supplier.get();
    if (StringUtils.isNoneBlank(token)) {
      r = r.newBuilder().addHeader("X-BB-SESSION", token).build();
    }
    return chain.proceed(r);
  }

}

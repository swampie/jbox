package com.baasbox.jaasbox.auth;

import java.io.IOException;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class AppCodeInterceptor extends SupplierInterceptor<String> {
  public AppCodeInterceptor(Supplier<String> supplier) {
    super(supplier);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request r = chain.request();
    String token = supplier.get();
    if (StringUtils.isNoneBlank(token)) {
      r = r.newBuilder().addHeader("X-BAASBOX-APPCODE", token).build();
    }
    return chain.proceed(r);
  }
}

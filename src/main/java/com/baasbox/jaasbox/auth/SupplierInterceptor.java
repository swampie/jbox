package com.baasbox.jaasbox.auth;

import java.util.function.Supplier;

import com.squareup.okhttp.Interceptor;

public abstract class SupplierInterceptor<T> implements Interceptor {

  protected Supplier<T> supplier;

  public SupplierInterceptor(Supplier<T> supplier) {
    this.supplier = supplier;
  }

}

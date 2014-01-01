/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Basic implementation of the <code>ModelStringProvider</code> interface.
 * Simply calls toSring on the value.
 */
public class BaseModelStringProvider<M extends ModelData> implements ModelStringProvider<M> {

  public String getStringValue(M model, String property) {
    Object value = model.get(property);
    return value == null ? "" : value.toString();
  }

}

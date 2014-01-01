/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * A <code>ModelData</code> instance used in a <code>SimpleComboBox</code>.
 * 
 * @param <T> the data type
 */
public class SimpleComboValue<T> extends BaseModelData {

  protected SimpleComboValue() {

  }
  
  protected SimpleComboValue(T value) {
    setValue(value);
  }

  /**
   * Returns the value.
   * 
   * @return the value
   */
  @SuppressWarnings("unchecked")
  public T getValue() {
    return (T) get("value");
  }

  /**
   * Sets the value.
   * 
   * @param value the value
   */
  public void setValue(T value) {
    set("value", value);
  }
}

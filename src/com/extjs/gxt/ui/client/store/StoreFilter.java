/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.store;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Interface for store filters.
 * 
 * @param <T> the model type
 */
public interface StoreFilter<T extends ModelData> {

  /**
   * Determines if the given record should be selected.
   * 
   * @param store the source store
   * @param parent the parent item, only applies to TreeStores
   * @param item the item
   * @param property the active property
   * @return true to select, false to filter
   */
  public boolean select(Store<T> store, T parent, T item, String property);

}

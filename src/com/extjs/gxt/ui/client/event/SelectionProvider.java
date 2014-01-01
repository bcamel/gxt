/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Interface for objects that provide a selection.
 * 
 * @param <M> the model type
 */
public interface SelectionProvider<M extends ModelData> {

  /**
   * Returns the current selection.
   * 
   * @return the current selection
   */
  public List<M> getSelection();

  /**
   * Add a selection changed listener.
   * 
   * @param listener a selection changed listener
   */
  public void addSelectionChangedListener(SelectionChangedListener<M> listener);

  /**
   * Removes a selection changed listener.
   * 
   * @param listener a selection changed listener
   */
  public void removeSelectionListener(SelectionChangedListener<M> listener);

  /**
   * Sets the current selection for this selection provider.
   * 
   * @param selection the new selection
   */
  public void setSelection(List<M> selection);
}

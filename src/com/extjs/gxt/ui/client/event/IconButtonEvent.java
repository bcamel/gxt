/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.google.gwt.user.client.Event;

/**
 * <code>IconButton</code> event type.
 */
public class IconButtonEvent extends BoxComponentEvent {

  private IconButton iconButton;

  public IconButtonEvent(IconButton iconButton) {
    super(iconButton);
    this.iconButton = iconButton;
  }

  public IconButtonEvent(IconButton iconButton, Event event) {
    this(iconButton);
    setEvent(event);
  }

  public IconButton getIconButton() {
    return iconButton;
  }

  public void setIconButton(IconButton iconButton) {
    this.iconButton = iconButton;
  }

}
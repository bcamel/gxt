/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;


/**
 * Event interface for widget events.
 */
public class WidgetListener implements Listener<ComponentEvent> {

  public void handleEvent(ComponentEvent e) {
    EventType type = e.getType();
    if (type == Events.Resize) {
      widgetResized(e);
    } else if (type == Events.Attach) {
      widgetAttached(e);
    } else if (type == Events.Detach) {
      widgetDetached(e);
    } 
  }

  /**
   * Fires after a widget is moved or resized.
   * 
   * @param ce an event containing information about the event
   */
  public void widgetResized(ComponentEvent ce) {

  }

  /**
   * Fires after a widget is attached.
   * 
   * @param ce an event containing information about the event
   */
  public void widgetAttached(ComponentEvent ce) {

  }

  /**
   * Fires after a widget is detached.
   * 
   * @param ce an event containing information about the event
   */
  public void widgetDetached(ComponentEvent ce) {

  }

}

/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.util.BaseEventPreview;
import com.google.gwt.user.client.Event.NativePreviewEvent;

/**
 * BaseEventPreview event type.
 * 
 * <p/>
 * Note: For a given event, only the fields which are appropriate will be filled
 * in. The appropriate fields for each event are documented by the event source.
 * 
 * @see BaseEventPreview
 */
public class PreviewEvent extends DomEvent {

  private BaseEventPreview preview;

  public PreviewEvent(BaseEventPreview preview, NativePreviewEvent event) {
    super(event);
    this.preview = preview;
  }
  
  /**
   * Returns the native preview event.
   * 
   * @return the native preview preview
   */
  public NativePreviewEvent getNativePreviewEvent() {
    return (NativePreviewEvent) getSource();
  }

  /**
   * Returns the source event preview.
   * 
   * @return the event preview
   */
  public BaseEventPreview getPreview() {
    return preview;
  }

  /**
   * Sets the source event preview.
   * 
   * @param preview the source event preview
   */
  public void setPreview(BaseEventPreview preview) {
    this.preview = preview;
  }

}

/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.charts.client.model;

/**
 * Provides the chart scale given a minimum and maximum value.
 */
public interface ScaleProvider {

  public static ScaleProvider DEFAULT_SCALE_PROVIDER = new ScaleProvider() {
    public Scale calcScale(double min, double max) {
      if (min == 0 && max == 0) {
        return new Scale(-1, 1, 1);
      }
      min = Math.floor(min * (min > 0 ? .9 : 1.1));
      max = Math.ceil(max * (max > 0 ? 1.1 : .9));
      return new Scale(min, max, Math.max(Math.abs(min), Math.abs(max)) / 10);
    }
  };

  public static ScaleProvider ROUNDED_NEAREST_SCALE_PROVIDER = new ScaleProvider() {
    public Scale calcScale(double min, double max) {
      Scale scale = DEFAULT_SCALE_PROVIDER.calcScale(min, max);
      scale.setInterval(Math.round(scale.getInterval()));
      return scale;
    }
  };

  /**
   * Returns the scale for the given minimum and maximum value.
   * 
   * @param min the minimum value
   * @param max the maximum value
   * @return the scale
   */
  public Scale calcScale(double min, double max);

}

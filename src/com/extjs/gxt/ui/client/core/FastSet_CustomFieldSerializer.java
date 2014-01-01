/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.core;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Collection_CustomFieldSerializerBase;

public class FastSet_CustomFieldSerializer {
  public static void deserialize(SerializationStreamReader streamReader, FastSet instance)
      throws SerializationException {
    Collection_CustomFieldSerializerBase.deserialize(streamReader, instance);
  }

  public static void serialize(SerializationStreamWriter streamWriter, FastSet instance) throws SerializationException {
    Collection_CustomFieldSerializerBase.serialize(streamWriter, instance);
  }
}

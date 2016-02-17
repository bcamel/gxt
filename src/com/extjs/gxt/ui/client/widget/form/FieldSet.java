/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldSetEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentHelper;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;

/**
 * A container that wraps its content in a HTML fieldset.
 * 
 * <dl>
 * <dt><b>Events:</b></dt>
 * 
 * <dd><b>BeforeExpand</b> : FieldSetEvent(fieldSet)<br>
 * <div>Fires before the panel is expanded. Listeners can cancel the action by
 * calling {@link BaseEvent#setCancelled(boolean)}.</div>
 * <ul>
 * <li>fieldSet : this</li>
 * </ul>
 * </dd>
 * 
 * <dd><b>Expand</b> : FieldSetEvent(fieldSet)<br>
 * <div>Fires after the panel is expanded</div>
 * <ul>
 * <li>fieldSet : this</li>
 * </ul>
 * </dd>
 * 
 * <dd><b>BeforeCollapse</b> : FieldSetEvent(fieldSet)<br>
 * <div>Fires before the panel is collpased. Listeners can cancel the action by
 * calling {@link BaseEvent#setCancelled(boolean)}.</div>
 * <ul>
 * <li>fieldSet : this</li>
 * </ul>
 * </dd>
 * 
 * <dd><b>Collapse</b> : FieldSetEvent(fieldSet)<br>
 * <div>Fires after the panel is collapsed.</div>
 * <ul>
 * <li>fieldSet : this</li>
 * </ul>
 * </dd>
 * </dl>
 * 
 * <dl>
 * <dt>Inherited Events:</dt>
 * <dd>LayoutContainer AfterLayout</dt>
 * <dd>ScrollContainer Scroll</dd>
 * <dd>Container BeforeAdd</dd>
 * <dd>Container Add</dd>
 * <dd>Container BeforeRemove</dd>
 * <dd>Container Remove</dd>
 * <dd>BoxComponent Move</dd>
 * <dd>BoxComponent Resize</dd>
 * <dd>Component Enable</dd>
 * <dd>Component Disable</dd>
 * <dd>Component BeforeHide</dd>
 * <dd>Component Hide</dd>
 * <dd>Component BeforeShow</dd>
 * <dd>Component Show</dd>
 * <dd>Component Attach</dd>
 * <dd>Component Detach</dd>
 * <dd>Component BeforeRender</dd>
 * <dd>Component Render</dd>
 * <dd>Component BrowserEvent</dd>
 * <dd>Component BeforeStateRestore</dd>
 * <dd>Component StateRestore</dd>
 * <dd>Component BeforeStateSave</dd>
 * <dd>Component SaveState</dd>
 * </dl>
 */
public class FieldSet extends LayoutContainer {

  private El body;
  private InputElement checkbox;
  private String checkboxName;
  private boolean checkboxToggle;
  private ToolButton collapseBtn;
  private boolean collapsed;
  private boolean collapsible;
  private Element heading;
  private El legend;
  private String html;

  /**
   * Creates a new fieldset.
   */
  public FieldSet() {
    baseStyle = "x-fieldset";
    enableLayout = true;
    getFocusSupport().setIgnore(false);
  }

  /**
   * Collapses the fieldset.
   */
  public void collapse() {
    if (rendered) {
      if (collapsible && !collapsed) {
        if (fireEvent(Events.BeforeCollapse)) {
          onCollapse();
        }
      }
    } else {
      collapsed = true;
    }
  }

  /**
   * Expands the fieldset.
   */
  public void expand() {
    if (rendered) {
      if (collapsible && collapsed) {
        if (fireEvent(Events.BeforeExpand)) {
          onExpand();
        }
      }
    } else {
      collapsed = false;
    }
  }

  /**
   * Returns the checkbox name.
   * 
   * @return the checkbox name
   */
  public String getCheckboxName() {
    return checkboxName;
  }

  /**
   * Returns the panel heading.
   * 
   * @return the heading
   */
  public String getHeadingHtml() {
    return html;
  }

  @Override
  public El getLayoutTarget() {
    return body;
  }

  @Override
  public boolean insert(Component item, int index) {
    return super.insert(item, index);
  }

  /**
   * Returns true if checkbox toggle is enabled.
   * 
   * @return the checkbox toggle state
   */
  public boolean isCheckboxToggle() {
    return checkboxToggle;
  }

  /**
   * Returns true if the fieldset is collapsible.
   * 
   * @return true if callapsible
   */
  public boolean isCollapsible() {
    return collapsible;
  }

  /**
   * Returns <code>true</code> if the panel is expanded.
   * 
   * @return the expand state
   */
  public boolean isExpanded() {
    return !collapsed;
  }

  @Override
  public void onComponentEvent(ComponentEvent ce) {
    super.onComponentEvent(ce);
    if (ce.getEventTypeInt() == Event.ONCLICK) {
      onClick(ce);
    }
  }

  /**
   * The name to assign to the fieldset's checkbox if
   * {@link #setCheckboxToggle(boolean)} = true.
   * 
   * @param checkboxName the name
   */
  public void setCheckboxName(String checkboxName) {
    this.checkboxName = checkboxName;
  }

  /**
   * True to render a checkbox into the fieldset frame just in front of the
   * legend (defaults to false, pre-render). The fieldset will be expanded or
   * collapsed when the checkbox is toggled.
   * 
   * @param checkboxToggle true for checkbox toggle
   */
  public void setCheckboxToggle(boolean checkboxToggle) {
    this.checkboxToggle = checkboxToggle;
    this.collapsible = true;
  }

  /**
   * Sets whether the fieldset is collapsible (defaults to false, pre-render).
   * 
   * @param collapsible true for collapse
   */
  public void setCollapsible(boolean collapsible) {
    this.collapsible = collapsible;
  }

  /**
   * Sets the panel's expand state.
   * 
   * @param expand <code>true<code> true to expand
   */
  public void setExpanded(boolean expand) {
    if (expand) {
      expand();
    } else {
      collapse();
    }

  }

  /**
   * Sets the panel heading as HTML.
   * 
   * @param html the heading text as HTML
   */
  public void setHeadingHtml(String html) {
    this.html = html;
    if (rendered) {
      heading.setInnerHTML(html);
    }
  }

  @Override
  protected ComponentEvent createComponentEvent(Event event) {
    return new FieldSetEvent(this, event);
  }

  @Override
  protected void doAttachChildren() {
    super.doAttachChildren();
    ComponentHelper.doAttach(collapseBtn);
  }

  @Override
  protected void doDetachChildren() {
    super.doDetachChildren();
    ComponentHelper.doDetach(collapseBtn);
  }

  @Override
  protected void notifyHide() {
    if (!collapsed) {
      super.notifyHide();
    }
  }

  @Override
  protected void notifyShow() {
    if (!collapsed) {
      super.notifyShow();
    }
  }

  protected void onClick(ComponentEvent ce) {
    if (checkboxToggle && ce.getTarget() == (Element) checkbox.cast()) {
      setExpanded(!isExpanded());
      boolean isExpanded = isExpanded();
      checkbox.setChecked(isExpanded);
      checkbox.setDefaultChecked(isExpanded);
    }
  }

  protected void onCollapse() {
    collapsed = true;
    if (checkboxToggle && checkbox != null) {
      checkbox.setChecked(false);
    }
    body.setVisible(false);
    addStyleName("x-panel-collapsed");

    for (Component c : getItems()) {
      if (!isComponentHidden(c) && c.isRendered()) {
        doNotify(c, false);
      }
    }

    updateIconTitles();

    FieldSetEvent fe = new FieldSetEvent(this);
    fireEvent(Events.Collapse, fe);
  }

  @Override
  protected void onDisable() {
    super.onDisable();
    if (collapseBtn != null) {
      collapseBtn.disable();
    } else if (checkbox != null) {
      checkbox.setDisabled(true);
    }
  }

  @Override
  protected void onEnable() {
    super.onEnable();
    if (collapseBtn != null) {
      collapseBtn.enable();
    } else if (checkbox != null) {
      checkbox.setDisabled(false);
    }
  }

  protected void onExpand() {
    collapsed = false;
    if (checkboxToggle && checkbox != null) {
      checkbox.setChecked(true);
    }
    body.setVisible(true);
    removeStyleName("x-panel-collapsed");

    for (Component c : getItems()) {
      if (!isComponentHidden(c) && c.isRendered()) {
        doNotify(c, true);
      }
    }

    updateIconTitles();

    FieldSetEvent fe = new FieldSetEvent(this);
    fireEvent(Events.Expand, fe);
  }

  @Override
  protected void onFocus(ComponentEvent ce) {
    super.onFocus(ce);
    if (GXT.isFocusManagerEnabled()) {
      if (checkboxToggle && checkbox != null) {
        checkbox.focus();
      } else if(collapseBtn != null) {
        collapseBtn.focus();
      }
    }
  }

  @Override
  protected void onRender(Element parent, int pos) {
    setElement(DOM.createFieldSet(), parent, pos);

    legend = new El(DOM.createLegend());
    legend.addStyleName("x-fieldset-header");

    if (checkboxToggle && collapsible) {
      checkbox = DOM.createInputCheck().cast();
      sinkEvents(Event.ONCLICK);
      if (checkboxName != null) {
        checkbox.setAttribute("name", checkboxName);
      }
      legend.appendChild((Element) checkbox.cast());
      checkbox.setDefaultChecked(!collapsed);
      checkbox.setChecked(!collapsed);
      if (GXT.isAriaEnabled()) {
        checkbox.setTitle("Expand " + html);
      }
    } else if (!checkboxToggle && collapsible) {
      collapseBtn = new ToolButton("x-tool-toggle");
      collapseBtn.addListener(Events.Select, new Listener<ComponentEvent>() {
        public void handleEvent(ComponentEvent be) {
          setExpanded(!isExpanded());
        }
      });
      collapseBtn.render(legend.dom);
      collapseBtn.getAriaSupport().setRole("checkbox");
      if (GXT.isAriaEnabled()) {
        collapseBtn.setTitle("Expand " + html);
      }
      ComponentHelper.setParent(this, collapseBtn);
    }

    heading = DOM.createSpan();
    heading.setClassName("x-fieldset-header-text");
    legend.appendChild(heading);
    getElement().appendChild(legend.dom);

    body = el().appendChild(DOM.createDiv());

    if (html != null) {
      setHeadingHtml(html);
    }

    if (collapsed) {
      onCollapse();
    }

    updateIconTitles();

    if (GXT.isFocusManagerEnabled() && !getFocusSupport().isIgnore()) {
      el().setTabIndex(0);
      el().setElementAttribute("hideFocus", "true");
      sinkEvents(Event.FOCUSEVENTS);
    }
  }

  @Override
  protected void onResize(int width, int height) {
    super.onResize(width, height);
    Size frameSize = el().getFrameSize();

    if (isAutoWidth()) {
      getLayoutTarget().setWidth("auto");
    } else if (width != -1) {
      getLayoutTarget().setWidth(width - frameSize.width, true);
    }
    if (isAutoHeight()) {
      getLayoutTarget().setHeight("auto");
    } else if (height != -1) {
      getLayoutTarget().setHeight(
          height - frameSize.height - legend.getHeight() - (GXT.isIE ? legend.getMargins("b") : 0), true);
    }
  }

  protected void updateIconTitles() {
    if (GXT.isAriaEnabled()) {
      String txt = "Expand " + html;
      if (checkbox != null) {
        checkbox.setTitle(txt);
      }
      if (collapseBtn != null) {
        collapseBtn.setTitle(txt);
        collapseBtn.getAriaSupport().setState("aria-checked", !collapsed ? "true" : "false");
      }
    }
  }

  private native void doNotify(Component c, boolean show) /*-{
    if(show){
			c.@com.extjs.gxt.ui.client.widget.Component::notifyShow()()
		} else {
			c.@com.extjs.gxt.ui.client.widget.Component::notifyHide()();
		}
  }-*/;

  private native boolean isComponentHidden(Component c) /*-{
		return c.@com.extjs.gxt.ui.client.widget.Component::hidden;
  }-*/;

}

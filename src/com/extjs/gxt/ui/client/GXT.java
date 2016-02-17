/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client;

import java.util.Map;

import com.extjs.gxt.ui.client.aria.FocusManager;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.image.XImages;
import com.extjs.gxt.ui.client.messages.XMessages;
import com.extjs.gxt.ui.client.state.CookieProvider;
import com.extjs.gxt.ui.client.state.StateManager;
import com.extjs.gxt.ui.client.util.CSS;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Accessibility;

/**
 * GXT core utilities and functions.
 */
public class GXT {

  /**
   * URL to a 1x1 transparent gif image used by GXT to create inline icons with
   * CSS background images. Default value is '/images/default/shared/clear.gif';
   */
  public static String BLANK_IMAGE_URL;

  /**
   * GXT images.
   */
  public static XImages IMAGES = (XImages) GWT.create(XImages.class);

  /**
   * <code>true</code> if is air.
   */
  public static boolean isAir;

  /**
   * <code>true</code> if is borderbox.
   */
  public static boolean isBorderBox;

  /**
   * <code>true</code> if the browser is chrome.
   */
  public static boolean isChrome;

  /**
   * <code>true</code> if the browser is gecko.
   */
  public static boolean isGecko;

  /**
   * <code>true</code> if the browser is gecko2.
   */
  public static boolean isGecko2;

  /**
   * <code>true</code> if the browser is gecko3.
   */
  public static boolean isGecko3;

  /**
   * <code>true</code> if the browser is gecko3.5.
   */
  public static boolean isGecko35;

  /**
   * True if the OS high contrast mode is enabled.
   */
  public static boolean isHighContrastMode = false;

  /**
   * <code>true</code> if the browser is ie.
   */
  public static boolean isIE;

  /**
   * <code>true</code> if the browser is ie6.
   */
  public static boolean isIE6;

  /**
   * <code>true</code> if the browser is ie7.
   */
  public static boolean isIE7;

  /**
   * <code>true</code> if the browser is ie8.
   */
  public static boolean isIE8;

  /**
   * <code>true</code> if the browser is ie9.
   */
  public static boolean isIE9;

  /**
   * <code>true</code> if the browser is ie10.
   */
  public static boolean isIE10;

  /**
   * <code>true</code> if linux os.
   */
  public static boolean isLinux;

  /**
   * <code>true</code> if mac os.
   */
  public static boolean isMac;

  /**
   * <code>true</code> if the browser is opera.
   */
  public static boolean isOpera;

  /**
   * <code>true</code> if the browser is safari.
   */
  public static boolean isSafari;

  /**
   * <code>true</code> if the browser is safari2.
   */
  public static boolean isSafari2;

  /**
   * <code>true</code> if the browser is safari3.
   */
  public static boolean isSafari3;

  /**
   * <code>true</code> if the browser is safari4.
   */
  public static boolean isSafari4;

  /**
   * <code>true</code> if using https.
   */
  public static boolean isSecure;

  /**
   * <code>true</code> if the browser is in strict mode.
   */
  public static boolean isStrict;

  /**
   * <code>true</code> if the browser uses the webkit engine.
   */
  public static boolean isWebKit;

  /**
   * <code>true</code> if windows os.
   */
  public static boolean isWindows;

  /**
   * GXT messages.
   */
  public static XMessages MESSAGES = (XMessages) GWT.create(XMessages.class);

  /**
   * Path to GXT resources (defaults to 'gxt').
   */
  public static String RESOURCES_URL = "gxt";

  /**
   * URL to a blank file used by GXT when in secure mode for iframe src to
   * prevent the IE insecure content. Default value is 'blank.html'.
   */
  public static String SSL_SECURE_URL = GWT.getModuleBaseURL() + "blank.html";

  /**
   * <code>true</code> if the browser uses shims.
   */
  public static boolean useShims;
  private static boolean ariaEnabled, focusManagerEnabled;
  private static Theme defaultTheme;
  private static boolean forceTheme;
  private static boolean initialized;

  private static Version version;

  /**
   * Returns the auto id prefix.
   * 
   * @return the auto id prefix
   */
  public static String getAutoIdPrefix() {
    return XDOM.getAutoIdPrefix();
  }

  /**
   * Returns the current theme id.
   * 
   * @return the theme id
   */
  public static String getThemeId() {
    Map<String, Object> map = StateManager.get().getMap(GWT.getModuleBaseURL() + "theme");
    if (map != null) {
      return map.get("id").toString();
    }
    return null;
  }

  /**
   * Returns the browser's user agent.
   * 
   * @return the user agent
   */
  public native static String getUserAgent() /*-{
		return $wnd.navigator.userAgent.toLowerCase();
  }-*/;

  /**
   * Returns the version information.
   * 
   * @return the version information
   */
  public static Version getVersion() {
    if (version == null) {
      version = new Version();
    }
    return version;
  }

  /**
   * Hides the loading panel.
   * 
   * @param id the loading panel id
   */
  public static void hideLoadingPanel(String id) {
    final Element loading = XDOM.getElementById(id);
    if (loading != null) {
      Timer t = new Timer() {
        @Override
        public void run() {
          El.fly(loading).hide();
        }
      };
      t.schedule(500);
    }
  }

  /**
   * Initializes GXT.
   */
  public static void init() {
    if (initialized) {
      return;
    }
    initialized = true;
    
    String stylesheetUrl = GWT.getModuleBaseForStaticFiles() + "gxt231/css/" + 
        (LocaleInfo.getCurrentLocale().isRTL() ? "gxt-all-rtl.css" : "gxt-all.css");
    
    CSS.addStyleSheet("gxt-global", stylesheetUrl);

    Element div = DOM.createDiv();
    div.setClassName("x-contrast-test");
    XDOM.getBody().appendChild(div);
    if ("none".equals(XDOM.getComputedStyle(div, "backgroundImage"))) {
      isHighContrastMode = true;
      XDOM.getBodyEl().addStyleName("x-contrast");
    }
    XDOM.getBody().removeChild(div);

    String ua = getUserAgent();

    isOpera = ua.indexOf("opera") != -1;

    isIE = !isOpera && ua.indexOf("msie") != -1;
    int docMode = getDocumentMode();
    
    if (!isOpera && isIE) {
      if (docMode >= 10) {
        isIE10 = true;
      } else if (docMode == 9) {
        isIE9 = true;
      } else if (docMode == 8) {
        isIE8 = true;
      } else {
        isIE6 = !isOpera && ua.indexOf("msie 6") != -1;
        isIE7 = !isOpera && ua.indexOf("msie 7") != -1;
      }
    }
    isChrome = !isIE && ua.indexOf("chrome") != -1;

    isWebKit = ua.indexOf("webkit") != -1;

    isSafari = !isChrome && ua.indexOf("safari") != -1;
    isSafari3 = isSafari && ua.indexOf("version/3") != -1;
    isSafari4 = isSafari && ua.indexOf("version/4") != -1;
    isSafari2 = isSafari && !isSafari3 && !isSafari4;

    isGecko = !isWebKit && ua.indexOf("gecko") != -1;

    isGecko35 = isGecko && ua.indexOf("rv:1.9.1") != -1;
    isGecko3 = isGecko && ua.indexOf("rv:1.9.") != -1;

    isGecko2 = isGecko && ua.indexOf("rv:1.8.") != -1;

    isWindows = (ua.indexOf("windows") != -1 || ua.indexOf("win32") != -1);
    isMac = (ua.indexOf("macintosh") != -1 || ua.indexOf("mac os x") != -1);
    isAir = (ua.indexOf("adobeair") != -1);
    isLinux = (ua.indexOf("linux") != -1);

    // don't override if set to true
    if (!useShims) {
      useShims = isIE6 || (isMac && isGecko2);
    }

    isStrict = Document.get().isCSS1Compat();

    isBorderBox = El.isBorderBox(DOM.createDiv());

    isSecure = Window.Location.getProtocol().toLowerCase().startsWith("https");
    if (BLANK_IMAGE_URL == null) {
      if (isIE8 || (isGecko && !isSecure)) {
        BLANK_IMAGE_URL = "data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==";
      } else {
        BLANK_IMAGE_URL = GWT.getModuleBaseURL() + "clear.gif";
      }
    }

    El bodyEl = XDOM.getBodyEl();

    if (isBorderBox) {
      bodyEl.addStyleName("ext-border-box");
    }

    if (isIE) {
      bodyEl.addStyleName("ext-ie");
      String cls = (isIE6 ? "ext-ie6" : (isIE7 ? "ext-ie7" : (isIE8 ? "ext-ie8" : (isIE9 ? "ext-ie9" : null))));

      bodyEl.addStyleName(cls);
      if (isIE7 && isIE8compatibility()) {
        bodyEl.addStyleName("ext-ie8-compatibility");
      }
    } else if (isGecko) {
      bodyEl.addStyleName("ext-gecko");
      String cls = (isGecko2 ? "ext-gecko2" : (isGecko3 ? "ext-gecko3" : null));
      bodyEl.addStyleName(cls);
    } else if (isOpera) {
      bodyEl.addStyleName("ext-opera");
    } else if (isWebKit) {
      bodyEl.addStyleName("ext-webkit");
      if (isSafari) {
        bodyEl.addStyleName("ext-safari");
      } else if (isChrome) {
        bodyEl.addStyleName("ext-chrome");
      }
    }
    if (isWindows) {
      bodyEl.addStyleName("ext-windows");
    } else if (isMac) {
      bodyEl.addStyleName("ext-mac");
    } else if (isLinux) {
      bodyEl.addStyleName("ext-linux");
    }

    if (StateManager.get().getProvider() == null) {
      StateManager.get().setProvider(new CookieProvider("/", null, null, isSecure));
    }

    Map<String, Object> theme = StateManager.get().getMap(GWT.getModuleBaseURL() + "theme");
    if ((defaultTheme != null && forceTheme) || (theme == null && defaultTheme != null)) {
      theme = defaultTheme.asMap();
    }
    
    if (theme != null) {
      final String themeId = String.valueOf(theme.get("id"));
      Theme t = ThemeManager.findTheme(themeId);
      if (t != null) {
        t.init();
        String fileName = theme.get("file").toString();
	      if (!fileName.contains("gxt-all.css") && !fileName.contains("gxt-all-rtl.css")) {
	    	  if(LocaleInfo.getCurrentLocale().isRTL())
	    	  {
	    		  int p=fileName.lastIndexOf(".css");
	    		  if(p!=-1)
	    		  {
	    			  fileName=fileName.substring(0,p)+"-rtl.css";
	    		  }
	    	  }
          CSS.addStyleSheet(themeId, fileName);
        }
        bodyEl.addStyleName("x-theme-" + themeId);
      }

      StateManager.get().set(GWT.getModuleBaseURL() + "theme", theme);
    }

    if (isStrict) { // add to the parent to allow for selectors like
      // ".ext-strict .ext-ie"
      Element p = (Element) XDOM.getBody().getParentElement();
      if (p != null) {
        El.fly(p).addStyleName("ext-strict");
      }
    }

    if (isIE6) {
      removeBackgroundFlicker();
    }
  }

  /**
   * Returns the ARIA enabled state.
   * 
   * @return true if enabled, false otherwise
   */
  public static boolean isAriaEnabled() {
    return ariaEnabled;
  }

  /**
   * Returns the focus managed enabled state.
   * 
   * @return true if enabled, false otherwise
   */
  public static boolean isFocusManagerEnabled() {
    return focusManagerEnabled;
  }

  /**
   * True to enable ARIA functionality. Enabling ARIA will also cause the focus manager to enabled.
   * 
   * @param enable true to enable
   */
  public static void setAriaEnabled(boolean enable) {
    ariaEnabled = enable;
    Accessibility.setRole(XDOM.getBody(), enable ? "application" : "");
    setFocusManagerEnabled(enable);
  }


  /**
   * Sets the auto id prefix which is prepended to the auto id counter when
   * generating auto ids (defaults to 'x-auto').
   * 
   * @param autoIdPrefix the auto id prefix
   */
  public static void setAutoIdPrefix(String autoIdPrefix) {
    XDOM.setAutoIdPrefix(autoIdPrefix);
  }

  /**
   * Sets the default theme which will be used if the user does not have a theme
   * selected with the state provider.
   * 
   * @param theme the default theme
   * @param force true to force the theme, ignoring the the theme saved with the
   *          state manager
   */
  public static void setDefaultTheme(Theme theme, boolean force) {
    defaultTheme = theme;
    forceTheme = force;
  }

  /**
   * True to enable the focus manager.
   * 
   * @param enable true to enable
   */
  public static void setFocusManagerEnabled(boolean enable) {
    focusManagerEnabled = enable;
    if (enable) {
      FocusManager.get().enable();
    } else {
      FocusManager.get().disable();
    }
  }

  /**
   * Changes the theme. A theme's stylehseets should be given a class = to the
   * theme id. Any stylesheets that have a class that do not match the id be
   * removed (stylesheets with no class specified are ignored). The method will
   * reload the application after changing themes.
   * 
   * @param theme the new theme name.
   */
  public static void switchTheme(Theme theme) {
    StateManager.get().set(GWT.getModuleBaseURL() + "theme", theme.asMap());
    XDOM.reload();
  }

  private static native int getDocumentMode() /*-{
		return $doc.documentMode || -1
  }-*/;

  private native static boolean isIE8compatibility() /*-{
		if (@com.extjs.gxt.ui.client.GXT::isIE7) {
			if ($doc.documentMode) {
				return true;
			}
		}
		return false;
}-*/;

  private native static void removeBackgroundFlicker() /*-{
    try{
			$doc.execCommand("BackgroundImageCache", false, true);
    }catch(e){}
  }-*/;

}

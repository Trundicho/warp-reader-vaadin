package de.trundicho.warp.reader.view.ui;

import com.vaadin.server.Page;

/**
 * This class is only needed because it's tricky to use vaadins valo theme and extend it using css files.
 */
public class CssStyler {

    public void applyCssStyles(){
        Page.Styles styles = Page.getCurrent().getStyles();
        styles.add(".middleLetter{color: red;}");
        styles.add(".warpRegion {padding: 20px 0px 30px;width: 410px}");
        styles.add(".leftWarpPanel {width: 205px;position: relative;font-size: 2.0em;font-weight: bold;color: black;text-align: right;}");
        styles.add(".rightWarpPanel {margin-left:-12px;width: 205px;position: relative;font-size: 2.0em;font-weight: bold;color: black;text-align: left;}");
        styles.add("input {font-size: 1.1em !important;}");
        styles.add("textarea {font-size: 1.4em !important;}");
        styles.add("#textArea {height: 100px;width: 300px;}");
        styles.add(".valo .v-slider-handle:before, .valo .v-slider-handle:after { height: 30px;width: 30px;margin-top:2px;}");
    }
}

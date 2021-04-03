package uk.easys.calculations.anychart.charts;

import uk.easys.calculations.anychart.APIlib;
import uk.easys.calculations.anychart.chart.common.dataentry.DataEntry;
import uk.easys.calculations.anychart.core.SeparateChart;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;

// class

/**
 * Pie (Donut) chart class.<br/>
 * <b>Note:</b> Use {@link uk.easys.calculations.anychart.charts.Pie} method to get an instance of this class:
 */
public class Pie extends SeparateChart {

    protected Pie() {

    }

    public static Pie instantiate() {
        return new Pie("new anychart.charts.pie()");
    }


    public Pie(String jsChart) {
        jsBase = "pie" + ++variableIndex;
        APIlib.getInstance().addJSLine(jsBase + " = " + jsChart + ";");
    }

    public String getJsBase() {
        return jsBase;
    }


    /**
     * Getter for the accessibility setting.
     */
    public uk.easys.calculations.anychart.core.utils.ChartA11y a11y() {
        return new uk.easys.calculations.anychart.core.utils.ChartA11y(jsBase + ".a11y()");
    }

    /**
     * Setter for the accessibility setting.
     */
    public Pie a11y(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".a11y(%s);", settings));

        return this;
    }

    /**
     * Setter for the accessibility setting.
     */
    public Pie a11y(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".a11y(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for animation settings.
     */
    public uk.easys.calculations.anychart.core.utils.Animation animation() {
        return new uk.easys.calculations.anychart.core.utils.Animation(jsBase + ".animation()");
    }

    /**
     * Setter for animation settings by one value.
     */
    public Pie animation(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".animation(%s);", settings));

        return this;
    }

    /**
     * Setter for animation settings by one value.
     */
    public Pie animation(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".animation(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for animation settings using several parameters.
     */
    public Pie animation(Boolean enabled, Number duration) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".animation(%s, %s);", enabled, duration));

        return this;
    }

    /**
     * Getter for the autoRedraw flag. <br/>
     * Flag whether to automatically call chart.draw() on any changes or not.
     */
    public void autoRedraw() {
        APIlib.getInstance().addJSLine(jsBase + ".autoRedraw();");
    }

    /**
     * Setter for the autoRedraw flag.<br/>
     * Flag whether to automatically call chart.draw() on any changes or not.
     */
    public Pie autoRedraw(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".autoRedraw(%s);", enabled));

        return this;
    }

    /**
     * Getter for the chart background.
     */
    public uk.easys.calculations.anychart.core.ui.Background background() {
        return new uk.easys.calculations.anychart.core.ui.Background(jsBase + ".background()");
    }

    /**
     * Setter for the chart background.
     */
    public Pie background(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".background(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for element bottom bound settings.
     */
    public void bottom() {
        APIlib.getInstance().addJSLine(jsBase + ".bottom();");
    }

    /**
     * Setter for element bottom bound settings.
     */
    public Pie bottom(Number bottom) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bottom(%s);", bottom));

        return this;
    }

    /**
     * Setter for element bottom bound settings.
     */
    public Pie bottom(String bottom) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bottom(%s);", wrapQuotes(bottom)));

        return this;
    }

    /**
     * Getter for element bounds settings.
     */
    public uk.easys.calculations.anychart.core.utils.Bounds bounds() {
        return new uk.easys.calculations.anychart.core.utils.Bounds(jsBase + ".bounds()");
    }

    /**
     * Setter for bounds of the element using one parameter.
     */
    public Pie bounds(uk.easys.calculations.anychart.utils.RectObj bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }

    /**
     * Setter for bounds of the element using one parameter.
     */
    public Pie bounds(uk.easys.calculations.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }

    /**
     * Setter for bounds of the element using one parameter.
     */
    public Pie bounds(uk.easys.calculations.anychart.core.utils.Bounds bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(Number x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }

    /**
     * Setter for element bounds settings.
     */
    public Pie bounds(String x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }

    /**
     * Stops current marquee action if any.
     */
    public Pie cancelMarquee() {
        APIlib.getInstance().addJSLine(jsBase + ".cancelMarquee();");

        return this;
    }

    /**
     * Getter for center settings.
     */
    public uk.easys.calculations.anychart.core.ui.Center center() {
        return new uk.easys.calculations.anychart.core.ui.Center(jsBase + ".center()");
    }

    /**
     * Setter for center settings.
     */
    public Pie center(String centerSettings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".center(%s);", wrapQuotes(centerSettings)));

        return this;
    }

    /**
     * Getter for the outside labels connector length.
     */
    public void connectorLength() {
        APIlib.getInstance().addJSLine(jsBase + ".connectorLength();");
    }

    /**
     * Setter for the outside labels connector length.<br/>
     * <b>Note:</b> Works only with outside labels mode.
     */
    public Pie connectorLength(Number length) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorLength(%s);", length));

        return this;
    }

    /**
     * Setter for the outside labels connector length.<br/>
     * <b>Note:</b> Works only with outside labels mode.
     */
    public Pie connectorLength(String length) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorLength(%s);", wrapQuotes(length)));

        return this;
    }

    /**
     * Getter for outside labels connectors stroke settings.
     */
    public void connectorStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".connectorStroke();");
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(String value, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(String value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke settings.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs} <br/>
     * <b>Note: </b> Works only with outside labels mode.
     */
    public Pie connectorStroke(String value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for outside labels connectors stroke using an object.
     */
    public Pie connectorStroke(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for the element's container.
     */
    public uk.easys.calculations.anychart.graphics.vector.Layer container() {
        return new uk.easys.calculations.anychart.graphics.vector.Layer(jsBase + ".container()");
    }

    /**
     * Setter for the element's container.
     */
    public Pie container(uk.easys.calculations.anychart.graphics.vector.Layer element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the element's container.
     */
    public Pie container(uk.easys.calculations.anychart.graphics.vector.Stage element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the element's container.
     */
    public Pie container(String element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", wrapQuotes(element)));

        return this;
    }

    /**
     * Getter for the context menu.
     */
    public uk.easys.calculations.anychart.ui.ContextMenu contextMenu() {
        return new uk.easys.calculations.anychart.ui.ContextMenu(jsBase + ".contextMenu()");
    }

    /**
     * Setter for the context menu.
     */
    public Pie contextMenu(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".contextMenu(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for the context menu.
     */
    public Pie contextMenu(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".contextMenu(%s);", settings));

        return this;
    }

    /**
     * Getter for the credits.
     */
    public uk.easys.calculations.anychart.core.ui.ChartCredits credits() {
        return new uk.easys.calculations.anychart.core.ui.ChartCredits(jsBase + ".credits()");
    }

    /**
     * Setter for the chart credits.
     * {docs:Quick_Start/Credits}Learn more about credits settings.{docs}
     */
    public Pie credits(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".credits(%s);", wrapQuotes(value)));

        return this;
    }

    /**
     * Setter for the chart credits.
     * {docs:Quick_Start/Credits}Learn more about credits settings.{docs}
     */
    public Pie credits(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".credits(%s);", value));

        return this;
    }

    /**
     *
     */
    public uk.easys.calculations.anychart.data.View data(List<DataEntry> data) {
        return new uk.easys.calculations.anychart.data.View(String.format(Locale.US, jsBase + ".data(%s)", arrayToString(data)));
    }

    /**
     * Disposes charts.
     */
    public void dispose() {
        APIlib.getInstance().addJSLine(jsBase + ".dispose();");
    }

    /**
     * Starts the rendering of the chart into the container.
     */
    public Pie draw(Boolean async) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".draw(%s);", async));

        return this;
    }

    /**
     * Getter for the element state (enabled or disabled).
     */
    public void enabled() {
        APIlib.getInstance().addJSLine(jsBase + ".enabled();");
    }

    /**
     * Setter for the element enabled state.
     */
    public Pie enabled(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".enabled(%s);", enabled));

        return this;
    }

    /**
     * Getter for the value of the exploded pie slice.
     */
    public void explode() {
        APIlib.getInstance().addJSLine(jsBase + ".explode();");
    }

    /**
     * Setter for the value of the exploded pie slice.<br/>
     * <b>Note:</b> Works only with exploded points mode.
     */
    public Pie explode(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".explode(%s);", wrapQuotes(value)));

        return this;
    }

    /**
     * Setter for the value of the exploded pie slice.<br/>
     * <b>Note:</b> Works only with exploded points mode.
     */
    public Pie explode(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".explode(%s);", value));

        return this;
    }

    /**
     *
     */
    public Pie explodeSlice(Number index, Boolean explode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".explodeSlice(%s, %s);", index, explode));

        return this;
    }

    /**
     *
     */
    public Pie explodeSlices(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".explodeSlices(%s);", value));

        return this;
    }

    /**
     * Getter for the export charts.
     */
    public uk.easys.calculations.anychart.core.utils.Exports exports() {
        return new uk.easys.calculations.anychart.core.utils.Exports(jsBase + ".exports()");
    }

    /**
     * Setter for the export charts.
     */
    public Pie exports(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".exports(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for the pie fill.
     */
    public void fill() {
        APIlib.getInstance().addJSLine(jsBase + ".fill();");
    }

    /**
     * Setter for fill settings using an array, an object or a string.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(uk.easys.calculations.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }

    /**
     * Setter for fill settings using an array, an object or a string.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(uk.easys.calculations.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }

    /**
     * Setter for fill settings using an array, an object or a string.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }

    /**
     * Fill color with opacity.
     */
    public Pie fill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, uk.easys.calculations.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(String[] keys, Number angle, uk.easys.calculations.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }

    /**
     * Radial gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, uk.easys.calculations.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }

    /**
     * Radial gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie fill(String[] keys, Number cx, Number cy, uk.easys.calculations.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }

    /**
     * Getter for the displaying of the label on hover event.
     */
    public void forceHoverLabels() {
        APIlib.getInstance().addJSLine(jsBase + ".forceHoverLabels();");
    }

    /**
     * Setter for the displaying of the label on hover event.
     */
    public Pie forceHoverLabels(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".forceHoverLabels(%s);", enabled));

        return this;
    }

    /**
     * Getter for the fullscreen mode.
     */
    public void fullScreen() {
        APIlib.getInstance().addJSLine(jsBase + ".fullScreen();");
    }

    /**
     * Setter for the fullscreen mode.
     */
    public Pie fullScreen(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fullScreen(%s);", enabled));

        return this;
    }

    /**
     *
     */
    public void getCenterPoint() {
        APIlib.getInstance().addJSLine(jsBase + ".getCenterPoint();");
    }

    /**
     * Returns pixel bounds of the element due to parent bounds and self bounds settings.
     */
    public uk.easys.calculations.anychart.math.Rect getPixelBounds() {
        return new uk.easys.calculations.anychart.math.Rect(jsBase + ".getPixelBounds()");
    }

    /**
     * Getter for the explode value.<br/>
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} is called.
     */
    public void getPixelExplode() {
        APIlib.getInstance().addJSLine(jsBase + ".getPixelExplode();");
    }

    /**
     * Getter for the pie pixel inner radius.
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} is called.
     */
    public void getPixelInnerRadius() {
        APIlib.getInstance().addJSLine(jsBase + ".getPixelInnerRadius();");
    }

    /**
     * Getter for the pie pixel outer radius.<br/>
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} method is called.
     */
    public void getPixelRadius() {
        APIlib.getInstance().addJSLine(jsBase + ".getPixelRadius();");
    }

    /**
     * Gets wrapped point by index.
     */
    public uk.easys.calculations.anychart.core.PiePoint getPoint(Number index) {
        return new uk.easys.calculations.anychart.core.PiePoint(String.format(Locale.US, jsBase + ".getPoint(%s)", index));
    }

    /**
     * Getter for the selected points.
     */
    public void getSelectedPoints() {
        APIlib.getInstance().addJSLine(jsBase + ".getSelectedPoints();");
    }

    /**
     * Getter for a statistical value by the key.
     */
    public void getStat(uk.easys.calculations.anychart.enums.Statistics key) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".getStat(%s);", (key != null) ? key.getJsBase() : null));
    }

    /**
     * Getter for a statistical value by the key.
     */
    public void getStat(String key) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".getStat(%s);", wrapQuotes(key)));
    }

    /**
     * Returns chart type.
     */
    public void getType() {
        APIlib.getInstance().addJSLine(jsBase + ".getType();");
    }

    /**
     * Converts the global coordinates to local coordinates.
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.core.Chart#draw} is called.
     */
    public void globalToLocal(Number xCoord, Number yCoord) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".globalToLocal(%s, %s);", xCoord, yCoord));
    }

    /**
     * Getter for the last values set by grouping function or null.
     */
    public void group() {
        APIlib.getInstance().addJSLine(jsBase + ".group();");
    }

    /**
     * Setter for the points grouping function.
     */
    public Pie group(String settings, String name) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".group(%s, %s);", wrapQuotes(settings), wrapQuotes(name)));

        return this;
    }

    /**
     * Getter for the hatch fill settings.
     */
    public uk.easys.calculations.anychart.graphics.vector.PatternFill hatchFill() {
        return new uk.easys.calculations.anychart.graphics.vector.PatternFill(jsBase + ".hatchFill()");
    }

    /**
     * Setter for the hatch fill settings.
     */
    public Pie hatchFill(uk.easys.calculations.anychart.graphics.vector.hatchfill.HatchFillType type, String color, Number thickness, Number size) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFill(%s, %s, %s, %s);", (type != null) ? type.getJsBase() : null, wrapQuotes(color), thickness, size));

        return this;
    }

    /**
     * Setter for the hatch fill settings.
     */
    public Pie hatchFill(String type, String color, Number thickness, Number size) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFill(%s, %s, %s, %s);", wrapQuotes(type), wrapQuotes(color), thickness, size));

        return this;
    }

    /**
     * Setter for hatch fill settings using function.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie hatchFill(String hatchFillFunction) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFill(%s);", wrapQuotes(hatchFillFunction)));

        return this;
    }

    /**
     * Setter for hatch fill settings using pattern fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie hatchFill(uk.easys.calculations.anychart.graphics.vector.PatternFill patternFill) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFill(%s);", (patternFill != null) ? patternFill.getJsBase() : null));

        return this;
    }

    /**
     * Setter for hatch fill settings using an instance.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie hatchFill(uk.easys.calculations.anychart.graphics.vector.HatchFill settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFill(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }

    /**
     * Setter for hatch fill using boolean.
     * {docs:Graphics/Hatch_Fill_Settings}Learn more about hatch fill settings.{docs}
     */
    public Pie hatchFill(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFill(%s);", enabled));

        return this;
    }

    /**
     * Getter for hatch fill palette settings.
     */
    public uk.easys.calculations.anychart.palettes.HatchFills hatchFillPalette() {
        return new uk.easys.calculations.anychart.palettes.HatchFills(jsBase + ".hatchFillPalette()");
    }

    /**
     * Setter for hatch fill palette settings.<br/>
     * <b>Note:</b> Works only with {@link uk.easys.calculations.anychart.charts.Pie#hatchFill} method.
     */
    public Pie hatchFillPalette(uk.easys.calculations.anychart.graphics.vector.hatchfill.HatchFillType[] settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFillPalette(%s);", arrayToString(settings)));

        return this;
    }

    /**
     * Setter for hatch fill palette settings.<br/>
     * <b>Note:</b> Works only with {@link uk.easys.calculations.anychart.charts.Pie#hatchFill} method.
     */
    public Pie hatchFillPalette(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFillPalette(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for hatch fill palette settings.<br/>
     * <b>Note:</b> Works only with {@link uk.easys.calculations.anychart.charts.Pie#hatchFill} method.
     */
    public Pie hatchFillPalette(uk.easys.calculations.anychart.palettes.HatchFills settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFillPalette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }

    /**
     * Getter for element height settings.
     */
    public void height() {
        APIlib.getInstance().addJSLine(jsBase + ".height();");
    }

    /**
     * Setter for element height setting.
     */
    public Pie height(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", height));

        return this;
    }

    /**
     * Setter for element height setting.
     */
    public Pie height(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", wrapQuotes(height)));

        return this;
    }

    /**
     * Setter for the hover state on a slice by index.
     */
    public Pie hover(Number index) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hover(%s);", index));

        return this;
    }

    /**
     * Getter for hovered state settings.
     */
    public uk.easys.calculations.anychart.core.StateSettings hovered() {
        return new uk.easys.calculations.anychart.core.StateSettings(jsBase + ".hovered()");
    }

    /**
     * Setter for hovered state settings.
     */
    public Pie hovered(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hovered(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for chart id.
     */
    public void id() {
        APIlib.getInstance().addJSLine(jsBase + ".id();");
    }

    /**
     * Setter for chart id.
     */
    public Pie id(String id) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".id(%s);", wrapQuotes(id)));

        return this;
    }

    /**
     * Gets marquee process running value.
     */
    public void inMarquee() {
        APIlib.getInstance().addJSLine(jsBase + ".inMarquee();");
    }

    /**
     * Getter for the inner radius in case of a Donut chart.
     */
    public void innerRadius() {
        APIlib.getInstance().addJSLine(jsBase + ".innerRadius();");
    }

    /**
     * Setter for the inner radius in case of a Donut chart.
     */
    public Pie innerRadius(String radius) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".innerRadius(%s);", wrapQuotes(radius)));

        return this;
    }

    /**
     * Setter for the inner radius in case of a Donut chart.
     */
    public Pie innerRadius(Number radius) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".innerRadius(%s);", radius));

        return this;
    }

    /**
     * Getter for the inside labels offset settings.
     */
    public void insideLabelsOffset() {
        APIlib.getInstance().addJSLine(jsBase + ".insideLabelsOffset();");
    }

    /**
     * Setter for inside labels space settings.<br/>
     * <b>Note:</b> Works only with inside labels mode.
     */
    public Pie insideLabelsOffset(Number offset) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".insideLabelsOffset(%s);", offset));

        return this;
    }

    /**
     * Setter for inside labels space settings.<br/>
     * <b>Note:</b> Works only with inside labels mode.
     */
    public Pie insideLabelsOffset(String offset) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".insideLabelsOffset(%s);", wrapQuotes(offset)));

        return this;
    }

    /**
     * Getter for interactivity settings for the chart.
     */
    public uk.easys.calculations.anychart.core.utils.Interactivity interactivity() {
        return new uk.easys.calculations.anychart.core.utils.Interactivity(jsBase + ".interactivity()");
    }

    /**
     * Setter for interactivity settings for the chart.
     */
    public Pie interactivity(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".interactivity(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for interactivity settings for the chart.
     */
    public Pie interactivity(uk.easys.calculations.anychart.enums.HoverMode settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".interactivity(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }

    /**
     * Whether the fullscreen mode available in the browser or not.
     */
    public void isFullScreenAvailable() {
        APIlib.getInstance().addJSLine(jsBase + ".isFullScreenAvailable();");
    }

    /**
     * Getter for the chart label.
     */
    public uk.easys.calculations.anychart.core.ui.Label label(String index) {
        return new uk.easys.calculations.anychart.core.ui.Label(String.format(Locale.US, jsBase + ".label(%s)", wrapQuotes(index)));
    }

    /**
     * Getter for the chart label.
     */
    public uk.easys.calculations.anychart.core.ui.Label label(Number index) {
        return new uk.easys.calculations.anychart.core.ui.Label(String.format(Locale.US, jsBase + ".label(%s)", index));
    }

    /**
     * Setter for the chart label.
     */
    public Pie label(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s);", settings));

        return this;
    }

    /**
     * Setter for the chart label.
     */
    public Pie label(String index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", wrapQuotes(index), settings));

        return this;
    }

    /**
     * Setter for the chart label.
     */
    public Pie label(String index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", wrapQuotes(index), wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for the chart label.
     */
    public Pie label(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", index, settings));

        return this;
    }

    /**
     * Setter for the chart label.
     */
    public Pie label(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for the pie labels.
     */
    public uk.easys.calculations.anychart.core.ui.LabelsFactory labels() {
        return new uk.easys.calculations.anychart.core.ui.LabelsFactory(jsBase + ".labels()");
    }

    /**
     * Setter for the pie labels.
     */
    public Pie labels(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".labels(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for the pie labels.
     */
    public Pie labels(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".labels(%s);", settings));

        return this;
    }

    /**
     * Getter for element left bound settings.
     */
    public void left() {
        APIlib.getInstance().addJSLine(jsBase + ".left();");
    }

    /**
     * Setter for element left bound settings.
     */
    public Pie left(Number left) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".left(%s);", left));

        return this;
    }

    /**
     * Setter for element left bound settings.
     */
    public Pie left(String left) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".left(%s);", wrapQuotes(left)));

        return this;
    }

    /**
     * Getter for the chart legend.
     */
    public uk.easys.calculations.anychart.core.ui.Legend legend() {
        return new uk.easys.calculations.anychart.core.ui.Legend(jsBase + ".legend()");
    }

    /**
     * Setter for chart legend settings.
     */
    public Pie legend(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".legend(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for chart legend settings.
     */
    public Pie legend(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".legend(%s);", settings));

        return this;
    }

    /**
     * Converts the local coordinates to global coordinates.
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.core.Chart#draw} is called.
     */
    public void localToGlobal(Number xCoord, Number yCoord) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".localToGlobal(%s, %s);", xCoord, yCoord));
    }

    /**
     * Getter for the chart margin.<br/>
     * <img src='/si/8.4.0/anychart.core.Chart.prototype.margin.png' width='352' height='351'/>
     */
    public uk.easys.calculations.anychart.core.utils.Margin margin() {
        return new uk.easys.calculations.anychart.core.utils.Margin(jsBase + ".margin()");
    }

    /**
     * Setter for the chart margin in pixels using a single complex object.
     */
    public Pie margin(Number[] margin) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s);", Arrays.toString(margin)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using a single complex object.
     */
    public Pie margin(String[] margin) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s);", arrayToStringWrapQuotes(margin)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using a single complex object.
     */
    public Pie margin(String margin) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s);", wrapQuotes(margin)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(String value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public Pie margin(Number value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, value3, value4));

        return this;
    }

    /**
     * Getter for the maximum height.
     */
    public void maxHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".maxHeight();");
    }

    /**
     * Setter for the maximum height.
     */
    public Pie maxHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", height));

        return this;
    }

    /**
     * Setter for the maximum height.
     */
    public Pie maxHeight(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", wrapQuotes(height)));

        return this;
    }

    /**
     * Getter for the maximum width.
     */
    public void maxWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".maxWidth();");
    }

    /**
     * Setter for the maximum width.
     */
    public Pie maxWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxWidth(%s);", width));

        return this;
    }

    /**
     * Setter for the maximum width.
     */
    public Pie maxWidth(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxWidth(%s);", wrapQuotes(width)));

        return this;
    }

    /**
     * Getter for the minimum height.
     */
    public void minHeight() {
        APIlib.getInstance().addJSLine(jsBase + ".minHeight();");
    }

    /**
     * Setter for the minimum height.
     */
    public Pie minHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", height));

        return this;
    }

    /**
     * Setter for the minimum height.
     */
    public Pie minHeight(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", wrapQuotes(height)));

        return this;
    }

    /**
     * Getter for the minimum width.
     */
    public void minWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".minWidth();");
    }

    /**
     * Setter for the minimum width.
     */
    public Pie minWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", width));

        return this;
    }

    /**
     * Setter for the minimum width.
     */
    public Pie minWidth(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", wrapQuotes(width)));

        return this;
    }

    /**
     * Getter for noData settings.
     */
    public uk.easys.calculations.anychart.core.NoDataSettings noData() {
        return new uk.easys.calculations.anychart.core.NoDataSettings(jsBase + ".noData()");
    }

    /**
     * Setter for noData settings.<br/>
     * {docs:Working_with_Data/No_Data_Label} Learn more about "No data" feature {docs}
     */
    public Pie noData(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".noData(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for normal state settings.
     */
    public uk.easys.calculations.anychart.core.StateSettings normal() {
        return new uk.easys.calculations.anychart.core.StateSettings(jsBase + ".normal()");
    }

    /**
     * Setter for normal state settings.
     */
    public Pie normal(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".normal(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for outline settings.
     */
    public uk.easys.calculations.anychart.core.ui.Outline outline() {
        return new uk.easys.calculations.anychart.core.ui.Outline(jsBase + ".outline()");
    }

    /**
     * Setter for outline settings.
     */
    public Pie outline(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".outline(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for outline settings.
     */
    public Pie outline(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".outline(%s);", settings));

        return this;
    }

    /**
     * Getter for the outside labels connector critical angle settings.
     */
    public void outsideLabelsCriticalAngle() {
        APIlib.getInstance().addJSLine(jsBase + ".outsideLabelsCriticalAngle();");
    }

    /**
     * Setter for the outside labels connector critical angle settings.<br/>
     * <b>Note:</b> Works only with outside labels mode.
     */
    public Pie outsideLabelsCriticalAngle(Number angle) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".outsideLabelsCriticalAngle(%s);", angle));

        return this;
    }

    /**
     * Setter for the outside labels connector critical angle settings.<br/>
     * <b>Note:</b> Works only with outside labels mode.
     */
    public Pie outsideLabelsCriticalAngle(String angle) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".outsideLabelsCriticalAngle(%s);", wrapQuotes(angle)));

        return this;
    }

    /**
     *
     */
    public void outsideLabelsSpace() {
        APIlib.getInstance().addJSLine(jsBase + ".outsideLabelsSpace();");
    }

    /**
     *
     */
    public Pie outsideLabelsSpace(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".outsideLabelsSpace(%s);", value));

        return this;
    }

    /**
     *
     */
    public Pie outsideLabelsSpace(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".outsideLabelsSpace(%s);", wrapQuotes(value)));

        return this;
    }

    /**
     * Getter for the overlap mode for labels.
     */
    public void overlapMode() {
        APIlib.getInstance().addJSLine(jsBase + ".overlapMode();");
    }

    /**
     * Setter for the overlap mode for labels.
     */
    public Pie overlapMode(uk.easys.calculations.anychart.enums.LabelsOverlapMode value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".overlapMode(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the overlap mode for labels.
     */
    public Pie overlapMode(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".overlapMode(%s);", wrapQuotes(value)));

        return this;
    }

    /**
     * Setter for the overlap mode for labels.
     */
    public Pie overlapMode(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".overlapMode(%s);", value));

        return this;
    }

    /**
     * Getter for the chart padding.<br/>
     * <img src='/si/8.4.0/anychart.core.Chart.prototype.padding.png' width='352' height='351'/>
     */
    public uk.easys.calculations.anychart.core.utils.Padding padding() {
        return new uk.easys.calculations.anychart.core.utils.Padding(jsBase + ".padding()");
    }

    /**
     * Setter for the chart paddings in pixels using a single value.
     */
    public Pie padding(Number[] padding) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s);", Arrays.toString(padding)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using a single value.
     */
    public Pie padding(String[] padding) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s);", arrayToStringWrapQuotes(padding)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using a single value.
     */
    public Pie padding(String padding) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s);", wrapQuotes(padding)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(String value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), value4));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, value3, wrapQuotes(value4)));

        return this;
    }

    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public Pie padding(Number value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, value3, value4));

        return this;
    }

    /**
     * Getter for the pie palette.
     */
    public uk.easys.calculations.anychart.palettes.RangeColors palette() {
        return new uk.easys.calculations.anychart.palettes.RangeColors(jsBase + ".palette()");
    }

    /**
     * Setter for the pie palette.
     * <b>Note</b>: You can use predefined palettes from {@link uk.easys.calculations.anychart.palettes}.
     */
    public Pie palette(uk.easys.calculations.anychart.palettes.RangeColors settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie palette.
     * <b>Note</b>: You can use predefined palettes from {@link uk.easys.calculations.anychart.palettes}.
     */
    public Pie palette(uk.easys.calculations.anychart.palettes.DistinctColors settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie palette.
     * <b>Note</b>: You can use predefined palettes from {@link uk.easys.calculations.anychart.palettes}.
     */
    public Pie palette(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for the pie palette.
     * <b>Note</b>: You can use predefined palettes from {@link uk.easys.calculations.anychart.palettes}.
     */
    public Pie palette(String[] settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", arrayToStringWrapQuotes(settings)));

        return this;
    }

    /**
     * Prints all elements on related stage.
     */
    public void print(uk.easys.calculations.anychart.graphics.vector.PaperSize paperSizeOrOptions, Boolean landscape) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".print(%s, %s);", (paperSizeOrOptions != null) ? paperSizeOrOptions.getJsBase() : null, landscape));
    }

    /**
     * Prints all elements on related stage.
     */
    public void print(String paperSizeOrOptions, Boolean landscape) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".print(%s, %s);", wrapQuotes(paperSizeOrOptions), landscape));
    }

    /**
     * Getter for the pie outer radius.
     */
    public void radius() {
        APIlib.getInstance().addJSLine(jsBase + ".radius();");
    }

    /**
     * Setter for the outer pie radius.
     */
    public Pie radius(String radius) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".radius(%s);", wrapQuotes(radius)));

        return this;
    }

    /**
     * Setter for the outer pie radius.
     */
    public Pie radius(Number radius) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".radius(%s);", radius));

        return this;
    }

    /**
     * Removes all listeners from an object. You can also optionally remove listeners of some particular type.
     */
    public void removeAllListeners(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeAllListeners(%s);", wrapQuotes(type)));
    }

    /**
     * Getter for element right bound settings.
     */
    public void right() {
        APIlib.getInstance().addJSLine(jsBase + ".right();");
    }

    /**
     * Setter for element right bound setting.
     */
    public Pie right(Number right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", right));

        return this;
    }

    /**
     * Setter for element right bound setting.
     */
    public Pie right(String right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", wrapQuotes(right)));

        return this;
    }

    /**
     * Saves the current chart as JPEG image.
     */
    public void saveAsJpg(Number width, Number height, Number quality, Boolean forceTransparentWhite, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsJpg(%s, %s, %s, %s, %s);", width, height, quality, forceTransparentWhite, wrapQuotes(filename)));
    }

    /**
     * Saves the current chart as JPEG image.
     */
    public void saveAsJpg(String width, Number height, Number quality, Boolean forceTransparentWhite, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsJpg(%s, %s, %s, %s, %s);", wrapQuotes(width), height, quality, forceTransparentWhite, wrapQuotes(filename)));
    }

    /**
     * Saves chart config as JSON document.
     */
    public void saveAsJson(String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsJson(%s);", wrapQuotes(filename)));
    }

    /**
     * Saves the current chart as PDF image.
     */
    public void saveAsPdf(Number paperSizeOrWidthOrOptions, Boolean landscape, Number x, Number y, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsPdf(%s, %s, %s, %s, %s);", paperSizeOrWidthOrOptions, landscape, x, y, wrapQuotes(filename)));
    }

    /**
     * Saves the current chart as PDF image.
     */
    public void saveAsPdf(String paperSizeOrWidthOrOptions, Boolean landscape, Number x, Number y, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsPdf(%s, %s, %s, %s, %s);", wrapQuotes(paperSizeOrWidthOrOptions), landscape, x, y, wrapQuotes(filename)));
    }

    /**
     * Saves the current chart as PNG image.
     */
    public void saveAsPng(Number width, Number height, Number quality, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsPng(%s, %s, %s, %s);", width, height, quality, wrapQuotes(filename)));
    }

    /**
     * Saves the current chart as PNG image.
     */
    public void saveAsPng(String width, Number height, Number quality, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsPng(%s, %s, %s, %s);", wrapQuotes(width), height, quality, wrapQuotes(filename)));
    }

    /**
     * Saves the current chart as SVG image.
     */
    public void saveAsSvg(String paperSize, Boolean landscape, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsSvg(%s, %s, %s);", wrapQuotes(paperSize), landscape, wrapQuotes(filename)));
    }

    /**
     * Saves the stage as SVG image using width and height.
     */
    public void saveAsSvg(Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsSvg(%s, %s);", width, height));
    }

    /**
     * Saves chart data as an Excel document.
     */
    public void saveAsXlsx(uk.easys.calculations.anychart.enums.ChartDataExportMode chartDataExportMode, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsXlsx(%s, %s);", (chartDataExportMode != null) ? chartDataExportMode.getJsBase() : null, wrapQuotes(filename)));
    }

    /**
     * Saves chart data as an Excel document.
     */
    public void saveAsXlsx(String chartDataExportMode, String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsXlsx(%s, %s);", wrapQuotes(chartDataExportMode), wrapQuotes(filename)));
    }

    /**
     * Saves chart config as XML document.
     */
    public void saveAsXml(String filename) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".saveAsXml(%s);", wrapQuotes(filename)));
    }

    /**
     * Selects all points of the series.<br/>
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} is called.
     */
    public Pie select() {
        APIlib.getInstance().addJSLine(jsBase + ".select();");

        return this;
    }

    /**
     * Selects points by index.<br/>
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} is called.
     */
    public Pie select(Number index) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".select(%s);", index));

        return this;
    }

    /**
     * Selects points by indexes.<br/>
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} is called.
     */
    public Pie select(Number[] indexes) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".select(%s);", Arrays.toString(indexes)));

        return this;
    }

    /**
     * Getter for the select marquee fill.
     */
    public void selectMarqueeFill() {
        APIlib.getInstance().addJSLine(jsBase + ".selectMarqueeFill();");
    }

    /**
     * Setter for fill settings using an array, an object or a string.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }

    /**
     * Setter for fill settings using an array, an object or a string.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }

    /**
     * Setter for fill settings using an array, an object or a string.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }

    /**
     * Fill color with opacity. Fill as a string or an object.
     */
    public Pie selectMarqueeFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, uk.easys.calculations.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(String[] keys, Number angle, uk.easys.calculations.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }

    /**
     * Linear gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }

    /**
     * Radial gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, uk.easys.calculations.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }

    /**
     * Radial gradient fill.
     * {docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public Pie selectMarqueeFill(String[] keys, Number cx, Number cy, uk.easys.calculations.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s, %s, %s, %s);", arrayToStringWrapQuotes(keys), cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }

    /**
     * Getter for the select marquee stroke.
     */
    public void selectMarqueeStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".selectMarqueeStroke();");
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(String color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(String color, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(String color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the select marquee stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie selectMarqueeStroke(String color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Getter for selected state settings.
     */
    public uk.easys.calculations.anychart.core.StateSettings selected() {
        return new uk.easys.calculations.anychart.core.StateSettings(jsBase + ".selected()");
    }

    /**
     * Setter for selected state settings.
     */
    public Pie selected(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selected(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Opens Facebook sharing dialog.
     */
    public void shareWithFacebook(String captionOrOptions, String link, String name, String description) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".shareWithFacebook(%s, %s, %s, %s);", wrapQuotes(captionOrOptions), wrapQuotes(link), wrapQuotes(name), wrapQuotes(description)));
    }

    /**
     * Opens LinkedIn sharing dialog.
     */
    public void shareWithLinkedIn(String captionOrOptions, String description) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".shareWithLinkedIn(%s, %s);", wrapQuotes(captionOrOptions), wrapQuotes(description)));
    }

    /**
     * Opens Pinterest sharing dialog.
     */
    public void shareWithPinterest(String linkOrOptions, String description) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".shareWithPinterest(%s, %s);", wrapQuotes(linkOrOptions), wrapQuotes(description)));
    }

    /**
     * Opens Twitter sharing dialog.
     */
    public void shareWithTwitter() {
        APIlib.getInstance().addJSLine(jsBase + ".shareWithTwitter();");
    }

    /**
     * Getter for the sorting setting.
     */
    public void sort() {
        APIlib.getInstance().addJSLine(jsBase + ".sort();");
    }

    /**
     * Setter for the sorting setting.<br/>
     * Ascending, Descending and No sorting is supported.
     */
    public Pie sort(uk.easys.calculations.anychart.enums.Sort value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".sort(%s);", (value != null) ? value.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the sorting setting.<br/>
     * Ascending, Descending and No sorting is supported.
     */
    public Pie sort(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".sort(%s);", wrapQuotes(value)));

        return this;
    }

    /**
     * Getter for the angle of the first slice.
     */
    public void startAngle() {
        APIlib.getInstance().addJSLine(jsBase + ".startAngle();");
    }

    /**
     * Setter for the angle of the first slice.
     */
    public Pie startAngle(String angle) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".startAngle(%s);", wrapQuotes(angle)));

        return this;
    }

    /**
     * Setter for the angle of the first slice.
     */
    public Pie startAngle(Number angle) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".startAngle(%s);", angle));

        return this;
    }

    /**
     * Starts select marquee drawing.
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.core.Chart#draw} is called.
     */
    public Pie startSelectMarquee(Boolean repeat) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".startSelectMarquee(%s);", repeat));

        return this;
    }

    /**
     * Getter for the pie slices stroke.
     */
    public void stroke() {
        APIlib.getInstance().addJSLine(jsBase + ".stroke();");
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.Stroke value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", (value != null) ? value.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(String value, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(String value, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(String value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }

    /**
     * Setter for the pie slices stroke.
     * {docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public Pie stroke(String value, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s, %s, %s, %s, %s);", wrapQuotes(value), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the pie slices stroke using an object.
     */
    public Pie stroke(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".stroke(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Getter for the chart title.
     */
    public uk.easys.calculations.anychart.core.ui.Title title() {
        return new uk.easys.calculations.anychart.core.ui.Title(jsBase + ".title()");
    }

    /**
     * Setter for the chart title.
     */
    public Pie title(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".title(%s);", settings));

        return this;
    }

    /**
     * Setter for the chart title.
     */
    public Pie title(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".title(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Return chart configuration as JSON object or string.
     */
    public void toJson(Boolean stringify) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toJson(%s);", stringify));
    }

    /**
     * Returns SVG string with paper size and landscape.
     */
    public void toSvg(String paperSize, Boolean landscape) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toSvg(%s, %s);", wrapQuotes(paperSize), landscape));
    }

    /**
     * Returns SVG string with with determined the width and height.
     */
    public void toSvg(Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toSvg(%s, %s);", width, height));
    }

    /**
     * Return chart configuration as XML string or XMLNode.
     */
    public void toXml(Boolean asXmlNode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toXml(%s);", asXmlNode));
    }

    /**
     * Getter for tooltip settings.
     */
    public uk.easys.calculations.anychart.core.ui.Tooltip tooltip() {
        return new uk.easys.calculations.anychart.core.ui.Tooltip(jsBase + ".tooltip()");
    }

    /**
     * Setter for tooltip settings.
     */
    public Pie tooltip(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", wrapQuotes(settings)));

        return this;
    }

    /**
     * Setter for tooltip settings.
     */
    public Pie tooltip(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", settings));

        return this;
    }

    /**
     * Getter for element top bound settings.
     */
    public void top() {
        APIlib.getInstance().addJSLine(jsBase + ".top();");
    }

    /**
     * Setter for element top bound settings.
     */
    public Pie top(Number top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", top));

        return this;
    }

    /**
     * Setter for element top bound settings.
     */
    public Pie top(String top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", wrapQuotes(top)));

        return this;
    }

    /**
     * Removes hover from the pie slice.
     */
    public Pie unhover() {
        APIlib.getInstance().addJSLine(jsBase + ".unhover();");

        return this;
    }

    public void setOnClickListener(uk.easys.calculations.anychart.chart.common.listener.ListenersInterface.OnClickListener listener) {
        StringBuilder js = new StringBuilder();

        js.append(jsBase).append(".listen('pointClick', function(e) {");

        if (listener.getFields() != null) {
            js.append("var result = ");
            for (String field : listener.getFields()) {
                js.append(String.format(Locale.US, "'%1$s' + ':' + e.point.get('%1$s') + ',' +", field));
            }
            js.setLength(js.length() - 8);
            js.append(";");

            js.append("android.onClick(result);");
        } else {
            js.append("android.onClick(null);");
        }
        js.append("});");

        uk.easys.calculations.anychart.chart.common.listener.ListenersInterface.getInstance().setOnClickListener(listener);

        APIlib.getInstance().addJSLine(js.toString());
    }

    public void setOnClickListener(uk.easys.calculations.anychart.chart.common.listener.ListenersInterface.OnClickListener listener, String type, String ePath) {
        StringBuilder js = new StringBuilder();

        js.append(jsBase).append(String.format(Locale.US, ".listen('%1$s', function(e) {", type));

        if (listener.getFields() != null) {
            ePath = (ePath != null) ? ePath + "." : "";
            js.append("var result = ");
            for (String field : listener.getFields()) {
                js.append(String.format(Locale.US, "'%1$s' + ':' + e.%2$s%1$s + ',' +", field, ePath));
            }
            js.setLength(js.length() - 8);
            js.append(";");

            js.append("android.onClick(result);");
        } else {
            js.append("android.onClick(null);");
        }
        js.append("});");

        uk.easys.calculations.anychart.chart.common.listener.ListenersInterface.getInstance().setOnClickListener(listener);

        APIlib.getInstance().addJSLine(js.toString());
    }

    /**
     * Removes an event listener which was added with listen() by the key returned by listen() or listenOnce().
     */
    public void unlistenByKey(String key) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".unlistenByKey(%s);", wrapQuotes(key)));
    }

    /**
     * Deselects all points.<br/>
     * <b>Note:</b> Works only after {@link uk.easys.calculations.anychart.charts.Pie#draw} is called.
     */
    public Pie unselect() {
        APIlib.getInstance().addJSLine(jsBase + ".unselect();");

        return this;
    }

    /**
     * Getter for element width settings.
     */
    public void width() {
        APIlib.getInstance().addJSLine(jsBase + ".width();");
    }

    /**
     * Setter for element width setting.
     */
    public Pie width(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", width));

        return this;
    }

    /**
     * Setter for element width setting.
     */
    public Pie width(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", wrapQuotes(width)));

        return this;
    }

    /**
     * Getter for the Z-index of the element.
     */
    public void zIndex() {
        APIlib.getInstance().addJSLine(jsBase + ".zIndex();");
    }

    /**
     * Setter for the Z-index of the element.
     */
    public Pie zIndex(Number zIndex) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".zIndex(%s);", zIndex));

        return this;
    }

    /**
     * Creates and returns the chart represented as an invisible HTML table.
     */
    public void toA11yTable(String title, Boolean asString) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toA11yTable(%s, %s);", wrapQuotes(title), asString));
    }

    /**
     * Creates and returns a chart as HTML table.
     */
    public void toHtmlTable(String title, Boolean asString) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".toHtmlTable(%s, %s);", wrapQuotes(title), asString));
    }

    /**
     * Getter for the parent bounds.<br>
     * Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public uk.easys.calculations.anychart.math.Rect parentBounds() {
        return new uk.easys.calculations.anychart.math.Rect(jsBase + ".parentBounds()");
    }

    /**
     * Setter for the parent bounds using single value.<br>
     * Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public Pie parentBounds(uk.easys.calculations.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }

    /**
     * Setter for the parent bounds using single value.<br>
     * Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public Pie parentBounds(String bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", wrapQuotes(bounds)));

        return this;
    }

    /**
     * Setter for the parent bounds using single value.<br>
     * Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public Pie parentBounds(Number bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", bounds));

        return this;
    }

    /**
     * Setter for the parent bounds using several values.<br>
     * Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public Pie parentBounds(Number left, Number top, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s, %s, %s, %s);", left, top, width, height));

        return this;
    }

    /**
     *
     */
    public uk.easys.calculations.anychart.data.View data(uk.easys.calculations.anychart.data.View data) {
        return new uk.easys.calculations.anychart.data.View(String.format(Locale.US, jsBase + ".data(%s)", (data != null) ? data.getJsBase() : null));
    }

    /**
     *
     */
    public uk.easys.calculations.anychart.data.View data(List<DataEntry> data, uk.easys.calculations.anychart.enums.TreeFillingMethod fillMethod) {
        return new uk.easys.calculations.anychart.data.View(String.format(Locale.US, jsBase + ".data(%s, %s)", arrayToString(data), (fillMethod != null) ? fillMethod.getJsBase() : null));
    }

    /**
     *
     */
    public uk.easys.calculations.anychart.data.View data(List<DataEntry> data, String fillMethod) {
        return new uk.easys.calculations.anychart.data.View(String.format(Locale.US, jsBase + ".data(%s, %s)", arrayToString(data), wrapQuotes(fillMethod)));
    }

    /**
     *
     */
    public Pie fill(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fill(%s);", wrapQuotes(value)));

        return this;
    }

}
package uk.easys.calculations.anychart.charts;

import uk.easys.calculations.anychart.APIlib;
import uk.easys.calculations.anychart.chart.common.dataentry.DataEntry;
import uk.easys.calculations.anychart.JsObject;
import uk.easys.calculations.anychart.core.SeparateChart;

import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import android.text.TextUtils;

// class
/**
 * Waterfall chart class.<br/>
To get the chart use these method:
 <ul>
     <li>{@link anychart#waterfall}</li>
 </ul>
 */
public class Waterfall extends SeparateChart {

    protected Waterfall() {

    }

    public static Waterfall instantiate() {
        return new Waterfall("new anychart.charts.waterfall()");
    }

    

    public Waterfall(String jsChart) {
        jsBase = "waterfall" + ++variableIndex;
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
    public uk.easys.calculations.anychart.charts.Waterfall a11y(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".a11y(%s);", settings));

        return this;
    }
    /**
     * Setter for the accessibility setting.
     */
    public uk.easys.calculations.anychart.charts.Waterfall a11y(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".a11y(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Adds series to the chart.
     */
    public void addSeries(uk.easys.calculations.anychart.data.View var_args) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".addSeries(%s);", (var_args != null) ? var_args.getJsBase() : null));
    }
    /**
     * Adds series to the chart.
     */
    public void addSeries(uk.easys.calculations.anychart.data.Set var_args) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".addSeries(%s);", (var_args != null) ? var_args.getJsBase() : null));
    }
    /**
     * Adds series to the chart.
     */
    public void addSeries(String[] var_args) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".addSeries(%s);", arrayToStringWrapQuotes(var_args)));
    }
    /**
     * Getter for the annotations.
     */
    public uk.easys.calculations.anychart.core.annotations.PlotController annotations() {
        return new uk.easys.calculations.anychart.core.annotations.PlotController(jsBase + ".annotations()");
    }
    /**
     * Setter for the annotations.
     */
    public uk.easys.calculations.anychart.charts.Waterfall annotations(String[] annotationsList) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".annotations(%s);", arrayToStringWrapQuotes(annotationsList)));

        return this;
    }
    /**
     * Getter for the autoRedraw flag. <br/>
Flag whether to automatically call chart.draw() on any changes or not.
     */
    public void autoRedraw() {
        APIlib.getInstance().addJSLine(jsBase + ".autoRedraw();");
    }
    /**
     * Setter for the autoRedraw flag.<br/>
Flag whether to automatically call chart.draw() on any changes or not.
     */
    public uk.easys.calculations.anychart.charts.Waterfall autoRedraw(Boolean enabled) {
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
    public uk.easys.calculations.anychart.charts.Waterfall background(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".background(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the chart baseline.
     */
    public void baseline() {
        APIlib.getInstance().addJSLine(jsBase + ".baseline();");
    }
    /**
     * Setter for the chart baseline.<br/>
The baseline is the line relative to which the series with the negative or positive value is drawn and painted over.
     */
    public uk.easys.calculations.anychart.charts.Waterfall baseline(Number value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".baseline(%s);", value));

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
    public uk.easys.calculations.anychart.charts.Waterfall bottom(Number bottom) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bottom(%s);", bottom));

        return this;
    }
    /**
     * Setter for element bottom bound settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bottom(String bottom) {
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
    public uk.easys.calculations.anychart.charts.Waterfall bounds(uk.easys.calculations.anychart.utils.RectObj bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(uk.easys.calculations.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for bounds of the element using one parameter.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(uk.easys.calculations.anychart.core.utils.Bounds bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(Number x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", x, wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, Number y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, Number y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, Number y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, Number y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), y, wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, String y, Number width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, String y, Number width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), width, wrapQuotes(height)));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, String y, String width, Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), height));

        return this;
    }
    /**
     * Setter for element bounds settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall bounds(String x, String y, String width, String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".bounds(%s, %s, %s, %s);", wrapQuotes(x), wrapQuotes(y), wrapQuotes(width), wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for the connector stroke.
     */
    public void connectorStroke() {
        APIlib.getInstance().addJSLine(jsBase + ".connectorStroke();");
    }
    /**
     * Setter for the connector stroke.
     */
    public uk.easys.calculations.anychart.charts.Waterfall connectorStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the connector stroke.
     */
    public uk.easys.calculations.anychart.charts.Waterfall connectorStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the connector stroke.
     */
    public uk.easys.calculations.anychart.charts.Waterfall connectorStroke(String color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".connectorStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for connector stroke using an object.
     */
    public uk.easys.calculations.anychart.charts.Waterfall connectorStroke(String settings) {
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
    public uk.easys.calculations.anychart.charts.Waterfall container(uk.easys.calculations.anychart.graphics.vector.Layer element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the element's container.
     */
    public uk.easys.calculations.anychart.charts.Waterfall container(uk.easys.calculations.anychart.graphics.vector.Stage element) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".container(%s);", (element != null) ? element.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the element's container.
     */
    public uk.easys.calculations.anychart.charts.Waterfall container(String element) {
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
    public uk.easys.calculations.anychart.charts.Waterfall contextMenu(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".contextMenu(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the context menu.
     */
    public uk.easys.calculations.anychart.charts.Waterfall contextMenu(Boolean settings) {
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
{docs:Quick_Start/Credits}Learn more about credits settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall credits(String value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".credits(%s);", wrapQuotes(value)));

        return this;
    }
    /**
     * Setter for the chart credits.
{docs:Quick_Start/Credits}Learn more about credits settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall credits(Boolean value) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".credits(%s);", value));

        return this;
    }
    /**
     * Getter for crosshair settings.
     */
    public uk.easys.calculations.anychart.core.ui.Crosshair crosshair() {
        return new uk.easys.calculations.anychart.core.ui.Crosshair(jsBase + ".crosshair()");
    }
    /**
     * Setter for crosshair settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall crosshair(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".crosshair(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for crosshair settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall crosshair(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".crosshair(%s);", settings));

        return this;
    }
    /**
     * 
     */
    public uk.easys.calculations.anychart.data.View data(List<DataEntry> data) {
        return new uk.easys.calculations.anychart.data.View(String.format(Locale.US, jsBase + ".data(%s)", arrayToString(data)));
    }
    /**
     * Getter for the data mode.
     */
    public void dataMode() {
        APIlib.getInstance().addJSLine(jsBase + ".dataMode();");
    }
    /**
     * Setter for the data mode.
     */
    public uk.easys.calculations.anychart.charts.Waterfall dataMode(uk.easys.calculations.anychart.enums.WaterfallDataMode mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".dataMode(%s);", (mode != null) ? mode.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the data mode.
     */
    public uk.easys.calculations.anychart.charts.Waterfall dataMode(String mode) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".dataMode(%s);", wrapQuotes(mode)));

        return this;
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
    public uk.easys.calculations.anychart.charts.Waterfall draw(Boolean async) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".draw(%s);", async));

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
    public uk.easys.calculations.anychart.charts.Waterfall exports(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".exports(%s);", wrapQuotes(settings)));

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
    public uk.easys.calculations.anychart.charts.Waterfall fullScreen(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".fullScreen(%s);", enabled));

        return this;
    }
    /**
     * Returns pixel bounds of the element due to parent bounds and self bounds settings.
     */
    public uk.easys.calculations.anychart.math.Rect getPixelBounds() {
        return new uk.easys.calculations.anychart.math.Rect(jsBase + ".getPixelBounds()");
    }
    /**
     * Getter for the data bounds of the chart.<br/>
<b>Note:</b> Works only after {@link anychart.charts.Waterfall#draw} is called.
     */
    public uk.easys.calculations.anychart.math.Rect getPlotBounds() {
        return new uk.easys.calculations.anychart.math.Rect(jsBase + ".getPlotBounds()");
    }
    /**
     * Gets the series by its id.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall getSeries(Number id) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".getSeries(%s)", id));
    }
    /**
     * Gets the series by its id.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall getSeries(String id) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".getSeries(%s)", wrapQuotes(id)));
    }
    /**
     * Getter for the series by its index.
     */
    public uk.easys.calculations.anychart.core.cartesian.series.Base getSeriesAt(Number index) {
        return new uk.easys.calculations.anychart.core.cartesian.series.Base(String.format(Locale.US, jsBase + ".getSeriesAt(%s)", index));
    }
    /**
     * Returns the number of series in a chart.
     */
    public void getSeriesCount() {
        APIlib.getInstance().addJSLine(jsBase + ".getSeriesCount();");
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
     * Returns the number of X-axes.
     */
    public void getXAxesCount() {
        APIlib.getInstance().addJSLine(jsBase + ".getXAxesCount();");
    }
    /**
     * Returns chart X scales.
     */
    public void getXScales() {
        APIlib.getInstance().addJSLine(jsBase + ".getXScales();");
    }
    /**
     * Returns the number of Y-axes.
     */
    public void getYAxesCount() {
        APIlib.getInstance().addJSLine(jsBase + ".getYAxesCount();");
    }
    /**
     * Returns chart Y scales.
     */
    public void getYScales() {
        APIlib.getInstance().addJSLine(jsBase + ".getYScales();");
    }
    /**
     * Converts the global coordinates to local coordinates.
<b>Note:</b> Works only after {@link anychart.core.Chart#draw} is called.
     */
    public void globalToLocal(Number xCoord, Number yCoord) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".globalToLocal(%s, %s);", xCoord, yCoord));
    }
    /**
     * Getter for the hatch fill palette settings.
     */
    public uk.easys.calculations.anychart.palettes.HatchFills hatchFillPalette() {
        return new uk.easys.calculations.anychart.palettes.HatchFills(jsBase + ".hatchFillPalette()");
    }
    /**
     * Setter for hatch fill palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall hatchFillPalette(uk.easys.calculations.anychart.graphics.vector.hatchfill.HatchFillType[] settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFillPalette(%s);", arrayToString(settings)));

        return this;
    }
    /**
     * Setter for hatch fill palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall hatchFillPalette(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".hatchFillPalette(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for hatch fill palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall hatchFillPalette(uk.easys.calculations.anychart.palettes.HatchFills settings) {
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
    public uk.easys.calculations.anychart.charts.Waterfall height(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", height));

        return this;
    }
    /**
     * Setter for element height setting.
     */
    public uk.easys.calculations.anychart.charts.Waterfall height(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".height(%s);", wrapQuotes(height)));

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
    public uk.easys.calculations.anychart.charts.Waterfall id(String id) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".id(%s);", wrapQuotes(id)));

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
    public uk.easys.calculations.anychart.charts.Waterfall interactivity(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".interactivity(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for interactivity settings for the chart.
     */
    public uk.easys.calculations.anychart.charts.Waterfall interactivity(uk.easys.calculations.anychart.enums.HoverMode settings) {
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
    public uk.easys.calculations.anychart.charts.Waterfall label(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s);", settings));

        return this;
    }
    /**
     * Setter for the chart label.
     */
    public uk.easys.calculations.anychart.charts.Waterfall label(String index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", wrapQuotes(index), settings));

        return this;
    }
    /**
     * Setter for the chart label.
     */
    public uk.easys.calculations.anychart.charts.Waterfall label(String index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", wrapQuotes(index), wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart label.
     */
    public uk.easys.calculations.anychart.charts.Waterfall label(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", index, settings));

        return this;
    }
    /**
     * Setter for the chart label.
     */
    public uk.easys.calculations.anychart.charts.Waterfall label(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".label(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for chart labels.
     */
    public uk.easys.calculations.anychart.core.ui.LabelsFactory labels() {
        return new uk.easys.calculations.anychart.core.ui.LabelsFactory(jsBase + ".labels()");
    }
    /**
     * Setter for chart labels.<br>
     */
    public uk.easys.calculations.anychart.charts.Waterfall labels(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".labels(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for chart labels.<br>
     */
    public uk.easys.calculations.anychart.charts.Waterfall labels(Boolean settings) {
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
    public uk.easys.calculations.anychart.charts.Waterfall left(Number left) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".left(%s);", left));

        return this;
    }
    /**
     * Setter for element left bound settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall left(String left) {
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
    public uk.easys.calculations.anychart.charts.Waterfall legend(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".legend(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for chart legend settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall legend(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".legend(%s);", settings));

        return this;
    }
    /**
     * Getter for the line marker.
     */
    public uk.easys.calculations.anychart.core.axismarkers.Line lineMarker(Number index) {
        return new uk.easys.calculations.anychart.core.axismarkers.Line(String.format(Locale.US, jsBase + ".lineMarker(%s)", index));
    }
    /**
     * Setter for the line marker settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall lineMarker(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the line marker settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall lineMarker(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s);", settings));

        return this;
    }
    /**
     * Setter for the line marker settings by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall lineMarker(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the line marker settings by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall lineMarker(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".lineMarker(%s, %s);", index, settings));

        return this;
    }
    /**
     * Converts the local coordinates to global coordinates.
<b>Note:</b> Works only after {@link anychart.core.Chart#draw} is called.
     */
    public void localToGlobal(Number xCoord, Number yCoord) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".localToGlobal(%s, %s);", xCoord, yCoord));
    }
    /**
     * Getter for the chart margin.<br/>
<img src='/si/8.4.0/anychart.core.Chart.prototype.margin.png' width='352' height='351'/>
     */
    public uk.easys.calculations.anychart.core.utils.Margin margin() {
        return new uk.easys.calculations.anychart.core.utils.Margin(jsBase + ".margin()");
    }
    /**
     * Setter for the chart margin in pixels using a single complex object.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number[] margin) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s);", Arrays.toString(margin)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using a single complex object.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String[] margin) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s);", arrayToStringWrapQuotes(margin)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using a single complex object.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String margin) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s);", wrapQuotes(margin)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(String value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart margin in pixels using several simple values.
     */
    public uk.easys.calculations.anychart.charts.Waterfall margin(Number value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".margin(%s, %s, %s, %s);", value1, value2, value3, value4));

        return this;
    }
    /**
     * Getter for the chart markers palette settings.
     */
    public uk.easys.calculations.anychart.palettes.Markers markerPalette() {
        return new uk.easys.calculations.anychart.palettes.Markers(jsBase + ".markerPalette()");
    }
    /**
     * Setter for the chart markers palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall markerPalette(uk.easys.calculations.anychart.palettes.Markers settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markerPalette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the chart markers palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall markerPalette(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markerPalette(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart markers palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall markerPalette(uk.easys.calculations.anychart.enums.MarkerType settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markerPalette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the chart markers palette settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall markerPalette(String[] settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".markerPalette(%s);", arrayToStringWrapQuotes(settings)));

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
    public uk.easys.calculations.anychart.charts.Waterfall maxHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", height));

        return this;
    }
    /**
     * Setter for the maximum height.
     */
    public uk.easys.calculations.anychart.charts.Waterfall maxHeight(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxHeight(%s);", wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for maximum labels.
     */
    public uk.easys.calculations.anychart.core.ui.LabelsFactory maxLabels() {
        return new uk.easys.calculations.anychart.core.ui.LabelsFactory(jsBase + ".maxLabels()");
    }
    /**
     * Setter for maximum labels.
     */
    public uk.easys.calculations.anychart.charts.Waterfall maxLabels(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxLabels(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for maximum labels.
     */
    public uk.easys.calculations.anychart.charts.Waterfall maxLabels(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxLabels(%s);", settings));

        return this;
    }
    /**
     * Getter for the maximum point width.
     */
    public void maxPointWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".maxPointWidth();");
    }
    /**
     * Setter for the maximum point width.
     */
    public uk.easys.calculations.anychart.charts.Waterfall maxPointWidth(Number settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxPointWidth(%s);", settings));

        return this;
    }
    /**
     * Setter for the maximum point width.
     */
    public uk.easys.calculations.anychart.charts.Waterfall maxPointWidth(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxPointWidth(%s);", wrapQuotes(settings)));

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
    public uk.easys.calculations.anychart.charts.Waterfall maxWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".maxWidth(%s);", width));

        return this;
    }
    /**
     * Setter for the maximum width.
     */
    public uk.easys.calculations.anychart.charts.Waterfall maxWidth(String width) {
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
    public uk.easys.calculations.anychart.charts.Waterfall minHeight(Number height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", height));

        return this;
    }
    /**
     * Setter for the minimum height.
     */
    public uk.easys.calculations.anychart.charts.Waterfall minHeight(String height) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minHeight(%s);", wrapQuotes(height)));

        return this;
    }
    /**
     * Getter for minimum labels.
     */
    public uk.easys.calculations.anychart.core.ui.LabelsFactory minLabels() {
        return new uk.easys.calculations.anychart.core.ui.LabelsFactory(jsBase + ".minLabels()");
    }
    /**
     * Setter for minimum labels.
     */
    public uk.easys.calculations.anychart.charts.Waterfall minLabels(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minLabels(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for minimum labels.
     */
    public uk.easys.calculations.anychart.charts.Waterfall minLabels(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minLabels(%s);", settings));

        return this;
    }
    /**
     * Getter for the minimum point length.
     */
    public void minPointLength() {
        APIlib.getInstance().addJSLine(jsBase + ".minPointLength();");
    }
    /**
     * Setter for the minimum point length.
     */
    public uk.easys.calculations.anychart.charts.Waterfall minPointLength(Number length) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minPointLength(%s);", length));

        return this;
    }
    /**
     * Setter for the minimum point length.
     */
    public uk.easys.calculations.anychart.charts.Waterfall minPointLength(String length) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minPointLength(%s);", wrapQuotes(length)));

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
    public uk.easys.calculations.anychart.charts.Waterfall minWidth(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".minWidth(%s);", width));

        return this;
    }
    /**
     * Setter for the minimum width.
     */
    public uk.easys.calculations.anychart.charts.Waterfall minWidth(String width) {
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
{docs:Working_with_Data/No_Data_Label} Learn more about "No data" feature {docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall noData(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".noData(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the chart padding.<br/>
<img src='/si/8.4.0/anychart.core.Chart.prototype.padding.png' width='352' height='351'/>
     */
    public uk.easys.calculations.anychart.core.utils.Padding padding() {
        return new uk.easys.calculations.anychart.core.utils.Padding(jsBase + ".padding()");
    }
    /**
     * Setter for the chart paddings in pixels using a single value.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number[] padding) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s);", Arrays.toString(padding)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using a single value.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String[] padding) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s);", arrayToStringWrapQuotes(padding)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using a single value.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String padding) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s);", wrapQuotes(padding)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), wrapQuotes(value2), value3, value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(String value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", wrapQuotes(value1), value2, value3, value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, String value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, String value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, String value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, String value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, wrapQuotes(value2), value3, value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, Number value2, String value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, Number value2, String value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, wrapQuotes(value3), value4));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, Number value2, Number value3, String value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, value3, wrapQuotes(value4)));

        return this;
    }
    /**
     * Setter for the chart paddings in pixels using several numbers.
     */
    public uk.easys.calculations.anychart.charts.Waterfall padding(Number value1, Number value2, Number value3, Number value4) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".padding(%s, %s, %s, %s);", value1, value2, value3, value4));

        return this;
    }
    /**
     * Getter for the chart colors palette.
     */
    public uk.easys.calculations.anychart.palettes.RangeColors palette() {
        return new uk.easys.calculations.anychart.palettes.RangeColors(jsBase + ".palette()");
    }
    /**
     * Setter for the chart colors palette.
     */
    public uk.easys.calculations.anychart.charts.Waterfall palette(uk.easys.calculations.anychart.palettes.RangeColors settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the chart colors palette.
     */
    public uk.easys.calculations.anychart.charts.Waterfall palette(uk.easys.calculations.anychart.palettes.DistinctColors settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the chart colors palette.
     */
    public uk.easys.calculations.anychart.charts.Waterfall palette(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart colors palette.
     */
    public uk.easys.calculations.anychart.charts.Waterfall palette(String[] settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".palette(%s);", arrayToStringWrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the point width settings.
     */
    public void pointWidth() {
        APIlib.getInstance().addJSLine(jsBase + ".pointWidth();");
    }
    /**
     * Setter for the point width settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall pointWidth(Number settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".pointWidth(%s);", settings));

        return this;
    }
    /**
     * Setter for the point width settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall pointWidth(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".pointWidth(%s);", wrapQuotes(settings)));

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
     * Getter for the range marker.
     */
    public uk.easys.calculations.anychart.core.axismarkers.Range rangeMarker(Number index) {
        return new uk.easys.calculations.anychart.core.axismarkers.Range(String.format(Locale.US, jsBase + ".rangeMarker(%s)", index));
    }
    /**
     * Setter for the range marker.
     */
    public uk.easys.calculations.anychart.charts.Waterfall rangeMarker(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the range marker.
     */
    public uk.easys.calculations.anychart.charts.Waterfall rangeMarker(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s);", settings));

        return this;
    }
    /**
     * Setter for the range marker by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall rangeMarker(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the range marker by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall rangeMarker(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".rangeMarker(%s, %s);", index, settings));

        return this;
    }
    /**
     * Removes all listeners from an object. You can also optionally remove listeners of some particular type.
     */
    public void removeAllListeners(String type) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeAllListeners(%s);", wrapQuotes(type)));
    }
    /**
     * Removes all series from chart.
     */
    public uk.easys.calculations.anychart.charts.Waterfall removeAllSeries() {
        APIlib.getInstance().addJSLine(jsBase + ".removeAllSeries();");

        return this;
    }
    /**
     * Removes one of series from chart by its id.
     */
    public uk.easys.calculations.anychart.charts.Waterfall removeSeries(Number id) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeSeries(%s);", id));

        return this;
    }
    /**
     * Removes one of series from chart by its id.
     */
    public uk.easys.calculations.anychart.charts.Waterfall removeSeries(String id) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeSeries(%s);", wrapQuotes(id)));

        return this;
    }
    /**
     * Removes one of series from chart by its index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall removeSeriesAt(Number index) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".removeSeriesAt(%s);", index));

        return this;
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
    public uk.easys.calculations.anychart.charts.Waterfall right(Number right) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".right(%s);", right));

        return this;
    }
    /**
     * Setter for element right bound setting.
     */
    public uk.easys.calculations.anychart.charts.Waterfall right(String right) {
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
     * Getter for the text marker.
     */
    public uk.easys.calculations.anychart.core.axismarkers.Text textMarker(Number index) {
        return new uk.easys.calculations.anychart.core.axismarkers.Text(String.format(Locale.US, jsBase + ".textMarker(%s)", index));
    }
    /**
     * Setter for the text marker.
     */
    public uk.easys.calculations.anychart.charts.Waterfall textMarker(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the text marker.
     */
    public uk.easys.calculations.anychart.charts.Waterfall textMarker(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s);", settings));

        return this;
    }
    /**
     * Setter for the text marker by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall textMarker(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the text marker by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall textMarker(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".textMarker(%s, %s);", index, settings));

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
    public uk.easys.calculations.anychart.charts.Waterfall title(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".title(%s);", settings));

        return this;
    }
    /**
     * Setter for the chart title.
     */
    public uk.easys.calculations.anychart.charts.Waterfall title(String settings) {
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
     * Getter for the chart tooltip.
     */
    public uk.easys.calculations.anychart.core.ui.Tooltip tooltip() {
        return new uk.easys.calculations.anychart.core.ui.Tooltip(jsBase + ".tooltip()");
    }
    /**
     * Setter for the chart tooltip.
     */
    public uk.easys.calculations.anychart.charts.Waterfall tooltip(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".tooltip(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart tooltip.
     */
    public uk.easys.calculations.anychart.charts.Waterfall tooltip(Boolean settings) {
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
    public uk.easys.calculations.anychart.charts.Waterfall top(Number top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", top));

        return this;
    }
    /**
     * Setter for element top bound settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall top(String top) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".top(%s);", wrapQuotes(top)));

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
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(uk.easys.calculations.anychart.data.View data, uk.easys.calculations.anychart.enums.TextParsingMode csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", (data != null) ? data.getJsBase() : null, (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(uk.easys.calculations.anychart.data.View data, String csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", (data != null) ? data.getJsBase() : null, wrapQuotes(csvSettings)));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(uk.easys.calculations.anychart.data.View data, uk.easys.calculations.anychart.data.TextParsingSettings csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", (data != null) ? data.getJsBase() : null, (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(uk.easys.calculations.anychart.data.Set data, uk.easys.calculations.anychart.enums.TextParsingMode csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", (data != null) ? data.getJsBase() : null, (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(uk.easys.calculations.anychart.data.Set data, String csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", (data != null) ? data.getJsBase() : null, wrapQuotes(csvSettings)));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(uk.easys.calculations.anychart.data.Set data, uk.easys.calculations.anychart.data.TextParsingSettings csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", (data != null) ? data.getJsBase() : null, (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(String[] data, uk.easys.calculations.anychart.enums.TextParsingMode csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", arrayToStringWrapQuotes(data), (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(String[] data, String csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", arrayToStringWrapQuotes(data), wrapQuotes(csvSettings)));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(String[] data, uk.easys.calculations.anychart.data.TextParsingSettings csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", arrayToStringWrapQuotes(data), (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(String data, uk.easys.calculations.anychart.enums.TextParsingMode csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", wrapQuotes(data), (csvSettings != null) ? csvSettings.getJsBase() : null));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(String data, String csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", wrapQuotes(data), wrapQuotes(csvSettings)));
    }
    /**
     * Adds Waterfall series.
     */
    public uk.easys.calculations.anychart.core.waterfall.series.Waterfall waterfall(String data, uk.easys.calculations.anychart.data.TextParsingSettings csvSettings) {
        return new uk.easys.calculations.anychart.core.waterfall.series.Waterfall(String.format(Locale.US, jsBase + ".waterfall(%s, %s)", wrapQuotes(data), (csvSettings != null) ? csvSettings.getJsBase() : null));
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
    public uk.easys.calculations.anychart.charts.Waterfall width(Number width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", width));

        return this;
    }
    /**
     * Setter for element width setting.
     */
    public uk.easys.calculations.anychart.charts.Waterfall width(String width) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".width(%s);", wrapQuotes(width)));

        return this;
    }
    /**
     * Getter for the chart X-axis.
     */
    public uk.easys.calculations.anychart.core.axes.Linear xAxis(Number index) {
        return new uk.easys.calculations.anychart.core.axes.Linear(String.format(Locale.US, jsBase + ".xAxis(%s)", index));
    }
    /**
     * Setter for the chart X-axis.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xAxis(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xAxis(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart X-axis.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xAxis(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xAxis(%s);", settings));

        return this;
    }
    /**
     * Setter for the chart X-axis by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xAxis(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xAxis(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart X-axis by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xAxis(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xAxis(%s, %s);", index, settings));

        return this;
    }
    /**
     * Getter for the chart grid by X-scale.
     */
    public uk.easys.calculations.anychart.core.grids.Linear xGrid(Number index) {
        return new uk.easys.calculations.anychart.core.grids.Linear(String.format(Locale.US, jsBase + ".xGrid(%s)", index));
    }
    /**
     * Setter for the chart grid by X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xGrid(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xGrid(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart grid by X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xGrid(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xGrid(%s);", settings));

        return this;
    }
    /**
     * Setter for chart grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xGrid(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xGrid(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for chart grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xGrid(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xGrid(%s, %s);", index, settings));

        return this;
    }
    /**
     * Getter for the chart minor grid by X-scale.
     */
    public uk.easys.calculations.anychart.core.grids.Linear xMinorGrid(Number index) {
        return new uk.easys.calculations.anychart.core.grids.Linear(String.format(Locale.US, jsBase + ".xMinorGrid(%s)", index));
    }
    /**
     * Setter for the chart minor grid by X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xMinorGrid(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xMinorGrid(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart minor grid by X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xMinorGrid(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xMinorGrid(%s);", settings));

        return this;
    }
    /**
     * Setter for the chart minor grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xMinorGrid(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xMinorGrid(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart minor grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xMinorGrid(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xMinorGrid(%s, %s);", index, settings));

        return this;
    }
    /**
     * Getter for the chart X-scale.
     */
    public uk.easys.calculations.anychart.scales.Ordinal xScale() {
        return new uk.easys.calculations.anychart.scales.Ordinal(jsBase + ".xScale()");
    }
    /**
     * Setter for the chart X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xScale(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xScale(uk.easys.calculations.anychart.enums.ScaleTypes settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the chart X-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xScale(uk.easys.calculations.anychart.scales.Base settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Getter for the scroller.
     */
    public uk.easys.calculations.anychart.core.ui.ChartScroller xScroller() {
        return new uk.easys.calculations.anychart.core.ui.ChartScroller(jsBase + ".xScroller()");
    }
    /**
     * Setter for the scroller.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xScroller(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScroller(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the scroller.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xScroller(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xScroller(%s);", settings));

        return this;
    }
    /**
     * Getter for the zoom settings.
     */
    public uk.easys.calculations.anychart.core.utils.OrdinalZoom xZoom() {
        return new uk.easys.calculations.anychart.core.utils.OrdinalZoom(jsBase + ".xZoom()");
    }
    /**
     * Setter for the zoom settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xZoom(Number settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xZoom(%s);", settings));

        return this;
    }
    /**
     * Setter for the zoom settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xZoom(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xZoom(%s);", settings));

        return this;
    }
    /**
     * Setter for the zoom settings.
     */
    public uk.easys.calculations.anychart.charts.Waterfall xZoom(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".xZoom(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Getter for the chart Y-axis.
     */
    public uk.easys.calculations.anychart.core.axes.Linear yAxis(Number index) {
        return new uk.easys.calculations.anychart.core.axes.Linear(String.format(Locale.US, jsBase + ".yAxis(%s)", index));
    }
    /**
     * Setter for the chart Y-axis.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yAxis(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yAxis(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart Y-axis.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yAxis(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yAxis(%s);", settings));

        return this;
    }
    /**
     * Setter for the chart Y-axis by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yAxis(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yAxis(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart Y-axis by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yAxis(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yAxis(%s, %s);", index, settings));

        return this;
    }
    /**
     * Getter for the chart grid by Y-scale.
     */
    public uk.easys.calculations.anychart.core.grids.Linear yGrid(Number index) {
        return new uk.easys.calculations.anychart.core.grids.Linear(String.format(Locale.US, jsBase + ".yGrid(%s)", index));
    }
    /**
     * Setter for the chart grid by Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yGrid(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yGrid(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart grid by Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yGrid(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yGrid(%s);", settings));

        return this;
    }
    /**
     * Setter for chart grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yGrid(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yGrid(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for chart grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yGrid(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yGrid(%s, %s);", index, settings));

        return this;
    }
    /**
     * Getter for the chart minor grid by Y-scale.
     */
    public uk.easys.calculations.anychart.core.grids.Linear yMinorGrid(Number index) {
        return new uk.easys.calculations.anychart.core.grids.Linear(String.format(Locale.US, jsBase + ".yMinorGrid(%s)", index));
    }
    /**
     * Setter for the chart minor grid by Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yMinorGrid(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yMinorGrid(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart minor grid by Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yMinorGrid(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yMinorGrid(%s);", settings));

        return this;
    }
    /**
     * Setter for the chart minor grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yMinorGrid(Number index, String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yMinorGrid(%s, %s);", index, wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart minor grid by index.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yMinorGrid(Number index, Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yMinorGrid(%s, %s);", index, settings));

        return this;
    }
    /**
     * Getter for the chart Y-scale.
     */
    public uk.easys.calculations.anychart.scales.Linear yScale() {
        return new uk.easys.calculations.anychart.scales.Linear(jsBase + ".yScale()");
    }
    /**
     * Setter for the chart Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yScale(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yScale(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for the chart Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yScale(uk.easys.calculations.anychart.enums.ScaleTypes settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yScale(%s);", (settings != null) ? settings.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the chart Y-scale.
     */
    public uk.easys.calculations.anychart.charts.Waterfall yScale(uk.easys.calculations.anychart.scales.Base settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".yScale(%s);", (settings != null) ? settings.getJsBase() : null));

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
    public uk.easys.calculations.anychart.charts.Waterfall zIndex(Number zIndex) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".zIndex(%s);", zIndex));

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
    public uk.easys.calculations.anychart.charts.Waterfall animation(Boolean settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".animation(%s);", settings));

        return this;
    }
    /**
     * Setter for animation settings by one value.
     */
    public uk.easys.calculations.anychart.charts.Waterfall animation(String settings) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".animation(%s);", wrapQuotes(settings)));

        return this;
    }
    /**
     * Setter for animation settings using several parameters.
     */
    public uk.easys.calculations.anychart.charts.Waterfall animation(Boolean enabled, Number duration) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".animation(%s, %s);", enabled, duration));

        return this;
    }
    /**
     * Stops current marquee action if any.
     */
    public uk.easys.calculations.anychart.charts.Waterfall cancelMarquee() {
        APIlib.getInstance().addJSLine(jsBase + ".cancelMarquee();");

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
    public uk.easys.calculations.anychart.charts.Waterfall enabled(Boolean enabled) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".enabled(%s);", enabled));

        return this;
    }
    /**
     * Getter for the selected points.
     */
    public void getSelectedPoints() {
        APIlib.getInstance().addJSLine(jsBase + ".getSelectedPoints();");
    }
    /**
     * Gets marquee process running value.
     */
    public void inMarquee() {
        APIlib.getInstance().addJSLine(jsBase + ".inMarquee();");
    }
    /**
     * Getter for the select marquee fill.
     */
    public void selectMarqueeFill() {
        APIlib.getInstance().addJSLine(jsBase + ".selectMarqueeFill();");
    }
    /**
     * Setter for fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.Fill color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s);", (color != null) ? color.getJsBase() : null));

        return this;
    }
    /**
     * Setter for fill settings using an array, an object or a string.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(String[] color) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s);", arrayToStringWrapQuotes(color)));

        return this;
    }
    /**
     * Fill color with opacity. Fill as a string or an object.
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(String color, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s);", wrapQuotes(color), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, uk.easys.calculations.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(String[] keys, Number angle, Boolean mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, mode, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(String[] keys, Number angle, uk.easys.calculations.anychart.graphics.vector.Rect mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, (mode != null) ? mode.getJsBase() : null, opacity));

        return this;
    }
    /**
     * Linear gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(String[] keys, Number angle, String mode, Number opacity) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s);", arrayToStringWrapQuotes(keys), angle, wrapQuotes(mode), opacity));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(uk.easys.calculations.anychart.graphics.vector.GradientKey keys, Number cx, Number cy, uk.easys.calculations.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeFill(%s, %s, %s, %s, %s, %s, %s);", (keys != null) ? keys.getJsBase() : null, cx, cy, (mode != null) ? mode.getJsBase() : null, opacity, fx, fy));

        return this;
    }
    /**
     * Radial gradient fill.
{docs:Graphics/Fill_Settings}Learn more about coloring.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeFill(String[] keys, Number cx, Number cy, uk.easys.calculations.anychart.graphics.math.Rect mode, Number opacity, Number fx, Number fy) {
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
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.Stroke color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(uk.easys.calculations.anychart.graphics.vector.ColoredFill color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", (color != null) ? color.getJsBase() : null, thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(String color, Number thickness, String dashpattern, String lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(String color, Number thickness, String dashpattern, String lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), wrapQuotes(lineJoin), (lineCap != null) ? lineCap.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(String color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, String lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, wrapQuotes(lineCap)));

        return this;
    }
    /**
     * Setter for the select marquee stroke.
{docs:Graphics/Stroke_Settings}Learn more about stroke settings.{docs}
     */
    public uk.easys.calculations.anychart.charts.Waterfall selectMarqueeStroke(String color, Number thickness, String dashpattern, uk.easys.calculations.anychart.graphics.vector.StrokeLineJoin lineJoin, uk.easys.calculations.anychart.graphics.vector.StrokeLineCap lineCap) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".selectMarqueeStroke(%s, %s, %s, %s, %s);", wrapQuotes(color), thickness, wrapQuotes(dashpattern), (lineJoin != null) ? lineJoin.getJsBase() : null, (lineCap != null) ? lineCap.getJsBase() : null));

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
     * Starts select marquee drawing.
<b>Note:</b> Works only after {@link anychart.core.Chart#draw} is called.
     */
    public uk.easys.calculations.anychart.charts.Waterfall startSelectMarquee(Boolean repeat) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".startSelectMarquee(%s);", repeat));

        return this;
    }
    /**
     * Getter for the parent bounds.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public uk.easys.calculations.anychart.math.Rect parentBounds() {
        return new uk.easys.calculations.anychart.math.Rect(jsBase + ".parentBounds()");
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public uk.easys.calculations.anychart.charts.Waterfall parentBounds(uk.easys.calculations.anychart.math.Rect bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", (bounds != null) ? bounds.getJsBase() : null));

        return this;
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public uk.easys.calculations.anychart.charts.Waterfall parentBounds(String bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", wrapQuotes(bounds)));

        return this;
    }
    /**
     * Setter for the parent bounds using single value.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public uk.easys.calculations.anychart.charts.Waterfall parentBounds(Number bounds) {
        APIlib.getInstance().addJSLine(String.format(Locale.US, jsBase + ".parentBounds(%s);", bounds));

        return this;
    }
    /**
     * Setter for the parent bounds using several values.<br>
Bounds that would be used in case of percent size calculations. Expects pixel values only.
     */
    public uk.easys.calculations.anychart.charts.Waterfall parentBounds(Number left, Number top, Number width, Number height) {
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
    public Object xScale(Class scaleClass) {
        Object instance = null;
        try {
            instance = scaleClass.getDeclaredConstructor(String.class).newInstance(jsBase + ".xScale()");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (java.lang.reflect.InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }
    /**
     * 
     */
    public Object yScale(Class scaleClass) {
        Object instance = null;
        try {
            instance = scaleClass.getDeclaredConstructor(String.class).newInstance(jsBase + ".yScale()");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (java.lang.reflect.InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
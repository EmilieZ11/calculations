package uk.easys.calculations.anychart.charts.resource;

import uk.easys.calculations.anychart.JsObject;

import uk.easys.calculations.anychart.core.gantt.timelineheader.LevelWrapper;
import uk.easys.calculations.anychart.enums.Interval;

import java.util.Locale;
import java.util.Arrays;

// typedef
/**
 * Type definition for zoom level.
 */
public class ZoomLevel extends JsObject  {

    
    public ZoomLevel(Number count, String id, uk.easys.calculations.anychart.core.gantt.timelineheader.LevelWrapper[] levels, Interval unit, Number unitPixSize) {
        js.append(String.format(Locale.US, "{count:%s, id: %s, levels: %s, unit: %s, unitPixSize: %s, } ", count, wrapQuotes(id), arrayToString(levels), (unit != null) ? unit.getJsBase() : null, unitPixSize));
    }
    public ZoomLevel(Number count, String id, uk.easys.calculations.anychart.core.gantt.timelineheader.LevelWrapper[] levels, String unit, Number unitPixSize) {
        js.append(String.format(Locale.US, "{count:%s, id: %s, levels: %s, unit: %s, unitPixSize: %s, } ", count, wrapQuotes(id), arrayToString(levels), wrapQuotes(unit), unitPixSize));
    }

    @Override
    public String getJsBase() {
        return js.toString();
    }

}
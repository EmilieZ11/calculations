package uk.easys.calculations.anychart.ui.rangeselector;

import uk.easys.calculations.anychart.JsObject;

import uk.easys.calculations.anychart.enums.StockRangeAnchor;
import uk.easys.calculations.anychart.enums.StockRangeType;
import uk.easys.calculations.anychart.enums.Interval;

import java.util.Locale;
import java.util.Arrays;

// typedef
/**
 * Type definition for range.
 */
public class Range extends JsObject  {

    
    public Range(StockRangeAnchor anchor, Number count, String endDate, String startDate, String text, StockRangeType type, Interval unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", (anchor != null) ? anchor.getJsBase() : null, count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), (type != null) ? type.getJsBase() : null, (unit != null) ? unit.getJsBase() : null));
    }
    public Range(StockRangeAnchor anchor, Number count, String endDate, String startDate, String text, StockRangeType type, String unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", (anchor != null) ? anchor.getJsBase() : null, count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), (type != null) ? type.getJsBase() : null, wrapQuotes(unit)));
    }
    public Range(StockRangeAnchor anchor, Number count, String endDate, String startDate, String text, String type, Interval unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", (anchor != null) ? anchor.getJsBase() : null, count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), wrapQuotes(type), (unit != null) ? unit.getJsBase() : null));
    }
    public Range(StockRangeAnchor anchor, Number count, String endDate, String startDate, String text, String type, String unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", (anchor != null) ? anchor.getJsBase() : null, count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), wrapQuotes(type), wrapQuotes(unit)));
    }
    public Range(String anchor, Number count, String endDate, String startDate, String text, StockRangeType type, Interval unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", wrapQuotes(anchor), count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), (type != null) ? type.getJsBase() : null, (unit != null) ? unit.getJsBase() : null));
    }
    public Range(String anchor, Number count, String endDate, String startDate, String text, StockRangeType type, String unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", wrapQuotes(anchor), count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), (type != null) ? type.getJsBase() : null, wrapQuotes(unit)));
    }
    public Range(String anchor, Number count, String endDate, String startDate, String text, String type, Interval unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", wrapQuotes(anchor), count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), wrapQuotes(type), (unit != null) ? unit.getJsBase() : null));
    }
    public Range(String anchor, Number count, String endDate, String startDate, String text, String type, String unit) {
        js.append(String.format(Locale.US, "{anchor:%s, count: %s, endDate: %s, startDate: %s, text: %s, type: %s, unit: %s, } ", wrapQuotes(anchor), count, wrapQuotes(endDate), wrapQuotes(startDate), wrapQuotes(text), wrapQuotes(type), wrapQuotes(unit)));
    }

    @Override
    public String getJsBase() {
        return js.toString();
    }

}
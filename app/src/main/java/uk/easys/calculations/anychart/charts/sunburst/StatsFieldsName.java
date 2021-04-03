package uk.easys.calculations.anychart.charts.sunburst;

import uk.easys.calculations.anychart.JsObject;


import java.util.Locale;
import java.util.Arrays;

// typedef
/**
 * Type definition for the statistics fields name.
 */
public class StatsFieldsName extends JsObject  {

    
    public StatsFieldsName(Number depth, String[] level) {
        js.append(String.format(Locale.US, "{depth:%s, level: %s, } ", depth, arrayToStringWrapQuotes(level)));
    }

    @Override
    public String getJsBase() {
        return js.toString();
    }

}
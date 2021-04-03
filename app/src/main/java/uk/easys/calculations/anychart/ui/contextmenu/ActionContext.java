package uk.easys.calculations.anychart.ui.contextmenu;

import uk.easys.calculations.anychart.JsObject;

import uk.easys.calculations.anychart.core.Chart;
import uk.easys.calculations.anychart.ui.contextmenu.Item;
import uk.easys.calculations.anychart.core.Point;
import uk.easys.calculations.anychart.core.VisualBase;

import java.util.Locale;
import java.util.Arrays;

// typedef
/**
 * Type definition for the action context.
 */
public class ActionContext extends JsObject  {

    
    public ActionContext(Chart chart, String event, Item item, uk.easys.calculations.anychart.core.Point[] selectedPoints, VisualBase target, String type) {
        js.append(String.format(Locale.US, "{chart:%s, event: %s, item: %s, selectedPoints: %s, target: %s, type: %s, } ", (chart != null) ? chart.getJsBase() : null, wrapQuotes(event), (item != null) ? item.getJsBase() : null, arrayToString(selectedPoints), (target != null) ? target.getJsBase() : null, wrapQuotes(type)));
    }

    @Override
    public String getJsBase() {
        return js.toString();
    }

}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.BoxDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Cartesian3d;
import com.anychart.core.axes.Linear;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.core.cartesian.series.Box;
import com.anychart.core.cartesian.series.Column3d;
import com.anychart.core.cartesian.series.RangeColumn;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LabelsOverlapMode;
import com.anychart.enums.Orientation;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.SolidFill;

import java.util.ArrayList;
import java.util.List;

public class AnyChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_chart);

        bar_chart();
        // range_chart();
        // threedeechart();
    }

    private void range_chart() {

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.cartesian();

        cartesian.title("Coastal Water Temperature \\nin London vs Edinburgh in 2015 (°C)");

        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomDataEntry2("Jan", 5.8, 7.9, 6.1, 8.9));
        data.add(new CustomDataEntry2("Feb", 4.6, 6.1, 5.5, 8.2));
        data.add(new CustomDataEntry2("Mar", 5.9, 8.1, 5.9, 8.1));
        data.add(new CustomDataEntry2("Apr", 7.8, 10.7, 7.1, 9.8));
        data.add(new CustomDataEntry2("May", 10.5, 13.7, 8.3, 10.7));
        data.add(new CustomDataEntry2("June", 13.8, 17, 10.7, 14.5));
        data.add(new CustomDataEntry2("July", 16.5, 18.5, 12.3, 16.7));
        data.add(new CustomDataEntry2("Aug", 17.8, 19, 14, 16.3));
        data.add(new CustomDataEntry2("Sep", 15.4, 17.8, 13.7, 15.3));
        data.add(new CustomDataEntry2("Oct", 12.7, 15.3, 12.3, 14.4));
        data.add(new CustomDataEntry2("Nov", 9.8, 13, 12.9, 10.7));
        data.add(new CustomDataEntry2("Dec", 9, 10.1, 8.2, 11.1));

        Set set = Set.instantiate();
        set.data(data);
        Mapping londonData = set.mapAs("{ x: 'x', high: 'londonHigh', low: 'londonLow' }");
        Mapping edinburgData = set.mapAs("{ x: 'x', high: 'edinburgHigh', low: 'edinburgLow' }");

        RangeColumn columnLondon = cartesian.rangeColumn(londonData);
        columnLondon.name("London");

        RangeColumn columnEdinburg = cartesian.rangeColumn(edinburgData);
        columnEdinburg.name("Edinburgh");

        cartesian.xAxis(true);
        cartesian.yAxis(true);

        cartesian.yScale()
                .minimum(4d)
                .maximum(20d);

        cartesian.legend(true);

        cartesian.yGrid(true)
                .yMinorGrid(true);

        cartesian.tooltip().titleFormat("{%SeriesName} ({%x})");

        anyChartView.setChart(cartesian);
    }

    private void bar_chart() {
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian boxChart = AnyChart.box();

        boxChart.title("Top 10 Jobs Salaries Grades Per Year Calisota, USA");

        boxChart.xAxis(0).staggerMode(true);

        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomBoxDataEntry("Registered Nurse", 20000, 26000, 27000, 32000, 38000, new Integer[]{50000, 52000}));
        data.add(new CustomBoxDataEntry("Dental Hygienist", 24000, 28000, 32000, 38000, 42000, new Integer[]{48000}));
        data.add(new CustomBoxDataEntry("Computer Systems Analyst", 40000, 49000, 62000, 73000, 88000, new Integer[]{32000, 29000, 106000}));
        data.add(new CustomBoxDataEntry("Physical Therapist", 52000, 59000, 65000, 74000, 83000, new Integer[]{91000}));
        data.add(new CustomBoxDataEntry("Software Developer", 45000, 54000, 66000, 81000, 97000, new Integer[]{120000}));
        data.add(new CustomBoxDataEntry("Information Security Analyst", 47000, 56000, 69000, 85000, 100000, new Integer[]{110000, 115000, 32000}));
        data.add(new CustomBoxDataEntry("Physician Assistant", 67000, 72000, 84000, 95000, 110000, new Integer[]{57000, 54000}));
        data.add(new CustomBoxDataEntry("Dentist", 75000, 99000, 123000, 160000, 210000, new Integer[]{220000, 70000}));
        data.add(new CustomBoxDataEntry("Physician", 58000, 96000, 130000, 170000, 200000, new Integer[]{42000, 210000, 215000}));

        Box box = boxChart.box(data);

        box.whiskerWidth("20%");

        anyChartView.setChart(boxChart);
    }

    private void threedeechart() {


        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian3d column3d = AnyChart.column3d();

        column3d.yScale().stackMode(ScaleStackMode.VALUE);

        column3d.animation(true);

        column3d.title("Types of Coffee");
        column3d.title().padding(0d, 0d, 15d, 0d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("Espresso", 1, null, null, null, null, null));
        seriesData.add(new CustomDataEntry("Doppio", 2, null, null, null, null, null));
        seriesData.add(new CustomDataEntry("Trippio", 3, null, null, null, null, null));
        seriesData.add(new CustomDataEntry("Americano", 1, 3, null, null, null, null));
        seriesData.add(new CustomDataEntry("Cappuchino", 1, null, 1, 2, null, null));
        seriesData.add(new CustomDataEntry("Macchiato", 2.5, null, null, 1, null, null));
        seriesData.add(new CustomDataEntry("Latte", 1, null, 2, 1, null, null));
        seriesData.add(new CustomDataEntry("Latte Macchiato", 1, null, 2, null, 1, null));
        seriesData.add(new CustomDataEntry("Vienna Coffee", 1, null, null, null, 2, null));
        seriesData.add(new CustomDataEntry("Mocco", 1, null, 1, null, 1, 1));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Data = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping series4Data = set.mapAs("{ x: 'x', value: 'value4' }");
        Mapping series5Data = set.mapAs("{ x: 'x', value: 'value5' }");
        Mapping series6Data = set.mapAs("{ x: 'x', value: 'value6' }");

        Column3d series1 = column3d.column(series1Data);
        series1.name("Espresso");
        series1.fill(new SolidFill("#3e2723", 1d));
        series1.stroke("1 #f7f3f3");
        series1.hovered().stroke("3 #f7f3f3");

        Column3d series2 = column3d.column(series2Data);
        series2.name("Water");
        series2.fill(new SolidFill("#64b5f6", 1d));
        series2.stroke("1 #f7f3f3");
        series2.hovered().stroke("3 #f7f3f3");

        Column3d series3 = column3d.column(series3Data);
        series3.name("Milk");
        series3.fill(new SolidFill("#fff3e0", 1d));
        series3.stroke("1 #f7f3f3");
        series3.hovered().stroke("3 #f7f3f3");

        Column3d series4 = column3d.column(series4Data);
        series4.name("Steamed milk");
        series4.fill(new SolidFill("#bcaaa4", 1d));
        series4.stroke("1 #f7f3f3");
        series4.hovered().stroke("3 #f7f3f3");

        Column3d series5 = column3d.column(series5Data);
        series5.name("Cream");
        series5.fill(new SolidFill("#e6c1b5", 1d));
        series5.stroke("1 #f7f3f3");
        series5.hovered().stroke("3 #f7f3f3");

        Column3d series6 = column3d.column(series6Data);
        series6.name("Chocolate");
        series6.fill(new SolidFill("#bf360c", 1d));
        series6.stroke("1 #f7f3f3");
        series6.hovered().stroke("3 #f7f3f3");

        column3d.legend().enabled(true);
        column3d.legend().fontSize(13d);
        column3d.legend().padding(0d, 0d, 20d, 0d);

        column3d.yScale().ticks("[0, 1, 2, 3, 4, 5]");
        column3d.xAxis(0).stroke("1 #a18b7e");
        column3d.xAxis(0).labels().fontSize("#a18b7e");
        column3d.yAxis(0).stroke("1 #a18b7e");
        column3d.yAxis(0).labels().fontColor("#a18b7e");
        column3d.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        column3d.yAxis(0).title().enabled(true);
        column3d.yAxis(0).title().text("Portions of Ingredients");
        column3d.yAxis(0).title().fontColor("#a18b7e");

        column3d.interactivity().hoverMode(HoverMode.BY_X);

        column3d.tooltip()
                .displayMode(TooltipDisplayMode.UNION)
                .format("{%Value} {%SeriesName}");

        column3d.yGrid(0).stroke("#a18b7e", 1d, null, null, null);
        column3d.xGrid(0).stroke("#a18b7e", 1d, null, null, null);

        anyChartView.setChart(column3d);
    }

    private class CustomDataEntry2 extends DataEntry {
        public CustomDataEntry2(String x, Number edinburgHigh, Number edinburgLow, Number londonHigh, Number londonLow) {
            setValue("x", x);
            setValue("edinburgHigh", edinburgHigh);
            setValue("edinburgLow", edinburgLow);
            setValue("londonHigh", londonHigh);
            setValue("londonLow", londonLow);
        }
    }

    private class CustomBoxDataEntry extends BoxDataEntry {
        CustomBoxDataEntry(String x, Integer low, Integer q1, Integer median, Integer q3, Integer high, Integer[] outliers) {
            super(x, low, q1, median, q3, high);
            setValue("outliers", outliers);
        }
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4, Number value5, Number value6) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
            setValue("value5", value5);
            setValue("value6", value6);
        }
    }
}
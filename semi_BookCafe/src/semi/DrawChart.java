package semi;

import java.util.Arrays;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class DrawChart implements ExampleChart<CategoryChart> {
  private List<String> xAxis;
  private List<Integer> yAxis_1;
  private List<Integer> yAxis_2;
  
  public DrawChart() {}
  public DrawChart(List<String> xAxis, List<Integer> yAxis_1, List<Integer> yAxis_2) {
	  this.xAxis = xAxis;
	  this.yAxis_1 = yAxis_1;
	  this.yAxis_2 = yAxis_2;
  }
 
  @Override
  public CategoryChart getChart() {
 
    // Create Chart
    CategoryChart chart = new CategoryChartBuilder().width(640).height(274).xAxisTitle("날짜").yAxisTitle("금액(원)").build();
 
    // Customize Chart
    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
    chart.getStyler().setHasAnnotations(false);
    chart.getStyler().setXAxisMaxLabelCount(12);
    chart.getStyler().setYAxisDecimalPattern("##,###,###"); // y축 데이터 format 설정
    
    // Series
    chart.addSeries("매출", this.xAxis, this.yAxis_1);
    chart.addSeries("순이익", this.xAxis, this.yAxis_2);
    
    return chart;
  }
  public CategoryChart getChart(List<Integer> yAxis) {
	  
	    // Create Chart
	    CategoryChart chart = new CategoryChartBuilder().width(640).height(274).xAxisTitle("날짜").yAxisTitle("인원 수(명)").build();
	 
	    // Customize Chart
	    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
	    chart.getStyler().setHasAnnotations(false);
	    chart.getStyler().setXAxisMaxLabelCount(12);
	    chart.getStyler().setYAxisDecimalPattern("##,###,###");
	 
	    // Series
	    chart.addSeries("이용자 수", this.xAxis, yAxis);
	    
	    return chart;
	  }
}
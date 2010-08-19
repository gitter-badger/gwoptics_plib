package org.gwoptics.testing;
/*
ScatterPlot

Example showing how to use blank2DCanvas to quickly create a simple scatter plot.
*/
import java.util.ArrayList;

import org.gwoptics.ValueType;
import org.gwoptics.graphics.GWColour;
import org.gwoptics.graphics.graph2D.Graph2D;
import org.gwoptics.graphics.graph2D.backgrounds.GridBackground;
import org.gwoptics.graphics.graph2D.traces.Blank2DTrace;
import org.gwoptics.graphics.graph2D.traces.ILine2DEquation;
import org.gwoptics.graphics.graph2D.traces.Line2DTrace;

import processing.core.PApplet;
import processing.core.PGraphics;


public class graphTest extends PApplet{
	class Point2D{
	  public float X,Y;
	  public Point2D(float x, float y){X=x;Y=y;}
	}
	
	public class eq implements ILine2DEquation{
	  public double computePoint(double x,int pos) {
	    return Math.pow(10,x);
	  }		
	}

	public class ArrayTrace extends Blank2DTrace{
	  private ArrayList<Point2D> _data;
	  private float pSize = 0.08f;
	  
	  public ArrayTrace(){
	    _data = new ArrayList<Point2D>();
	  }
	  
	  public void addPoint(float x, float y){_data.add(new Point2D(x,y));}
	
	  public void TraceDraw(PGraphics canvas) {
	    if(_data != null){            
	      for (int i = 0;i < _data.size()-1; i++) {
	        
	        Point2D p1,p2;
	        p1 = (Point2D)_data.get(i);
	        p2 = (Point2D)_data.get(i+1);
	        
	        canvas.pushStyle();
		    canvas.stroke(255,0,0);
		    canvas.line(p1.X,p1.Y,p2.X,p2.Y);      
		    canvas.popStyle();
	      }
	    }
	  }
	}
	
	ArrayTrace sTrace;
	Line2DTrace lTrace;
	Graph2D g;
		
	public void setup(){
	  size(900,600);
	  
	  sTrace  = new ArrayTrace();
	  lTrace = new Line2DTrace(new eq());
	  
	  g = new Graph2D(this, 700,500, true);
	  g.setAxisColour(220, 220, 220);
	  g.setFontColour(255, 255, 255);
	  		
	  g.position.y = 50;
	  g.position.x = 100;
	  		
	  g.setYAxisTickSpacing(1f);
	  g.setXAxisTickSpacing(1f);
	  
	  g.setXAxisMinorTicks(1);
	  g.setYAxisMinorTicks(1);
	  
	  g.setYAxisMin(1f);
	  g.setYAxisMax(100000f);
	  
	  g.getYAxis().setLogarithmicAxis(true);
	  
	  g.getYAxis().setTickLabelType(ValueType.EXPONENT);
	  g.getYAxis().setMinorTicks(4);
	  g.setXAxisMin(0f);
	  g.setXAxisMax(5f);
	  g.setXAxisLabelAccuracy(0);
	  
	  lTrace.setTraceColour(255, 0, 0);
	  
	  GridBackground gb = new GridBackground(new GWColour(100,100,100), new GWColour(15));
	  g.setBackground(gb);
	  g.addTrace(lTrace);	
	  
	  frameRate(10);
	}
		
	public void draw(){
	  background(0);
	  g.draw();
	}
}
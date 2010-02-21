import org.gwoptics.graphics.graph2D.Graph2D;
import org.gwoptics.graphics.graph2D.traces.Blank2DTrace;

class spiralTrace extends Blank2DTrace{
	
	public float xparam(float t){
		return t*0.1f*cos(t);
	}
	
	public float yparam(float t){
		return t*0.1f*sin(t);
	}
	
	public void TraceDraw(PGraphics parent) {
		parent.background(0);
		
		int res = 40;
		float dAngle = 2f*PI/res;
		
		for(int i=0;i<10*res;i++){
			parent.stroke(0,0,255,255);
			parent.line(xparam(dAngle*i), yparam(dAngle*i), xparam(dAngle*(i+1)), yparam(dAngle*(i+1)));
		}
	}
}

spiralTrace r;
Graph2D g;

void setup(){
	size(600,600);
	
	r  = new spiralTrace();
	 
	g = new Graph2D(this, 400,400, true);
	g.setAxisColour(220, 220, 220);
	g.setFontColour(100, 100, 100);
	
	g.position.y = 50;
	g.position.x = 100;
	g.setYAxisTickSpacing(2f);
	g.setXAxisTickSpacing(2f);

	g.setYAxisMin(-5f);
	g.setYAxisMax(5f);
	g.setXAxisMin(-5f);
	g.setXAxisMax(5f);
	
	g.addTrace(r);
}

void draw(){
	background(0);
	g.draw();
}

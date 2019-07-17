package unsw.graphics.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;

import unsw.graphics.CoordFrame2D;
import unsw.graphics.Point2DBuffer;
import unsw.graphics.Shader;
import unsw.graphics.geometry.Point2D;
import unsw.graphics.geometry.Polygon2D;

public class LineSceneObject extends PolygonalSceneObject{
	private Point2D start = new Point2D(0,0);
	private Point2D end = new Point2D(1,0);
	private Color myLineColor;
	public LineSceneObject(SceneObject parent, float x0, float y0, float x1, float y1, Color lineColor){
		super(parent,drawLine(x0,y0,x1,y1),null,lineColor);
		myLineColor = lineColor;
	}
	public LineSceneObject(SceneObject parent, Color lineColor){
		this(parent,0,0,1,0,lineColor);
	}
	
	
	private static Polygon2D drawLine(float x0, float y0, float x1, float y1){
		Point2D s = new Point2D(x0,y0);
		Point2D e = new Point2D(x1,y1);
		List<Point2D> points = new ArrayList<Point2D>();
		points.add(s);
		points.add(e);
		return  new Polygon2D(points);
		
	}

}

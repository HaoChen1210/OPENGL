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

public class CircularSceneObject extends PolygonalSceneObject{
	private static final int VERTICES = 32;
	private float r;
	private Point2D centre = new Point2D(0,0);
	private Color myFillColor;
	private Color myLineColor;
	private static Polygon2D myPolygon;
	public CircularSceneObject(SceneObject parent,float r, Color fillColor, Color lineColor) {
		super(parent, drawCircle(r), fillColor, lineColor);
		myFillColor= fillColor;
		myLineColor = lineColor;
		r = 1;
		System.out.println("1");
		// TODO Auto-generated constructor stub
	}
	public CircularSceneObject(SceneObject parent, Color fillColor, Color lineColor) {
		this(parent,1,fillColor,lineColor);
	}

	public float getR() {
		return r;
	}
	public void setR(float r) {
		this.r = r;
	}
	public Point2D getC() {
		return centre;
	}
	public void setC(Point2D c) {
		this.centre = c;
	}
	public Color getMyFillColor() {
		return myFillColor;
	}
	public void setMyFillColor(Color myFillColor) {
		this.myFillColor = myFillColor;
	}
	public Color getMyLineColor() {
		return myLineColor;
	}
	public void setMyLineColor(Color myLineColor) {
		this.myLineColor = myLineColor;
	}

	
	
	private static Polygon2D drawCircle(float r){
		//System.out.println("tt");
		List<Point2D> points = new ArrayList<Point2D>();
		for (int i = 0; i < 360; i = (i+(360/32))) {
            float a = (float) Math.toRadians(i);
            float x = r * (float) Math.cos(a);
            float y = r * (float) Math.sin(a); // Off center
            Point2D p = new Point2D(x, y);
            points.add(p);
        }
		System.out.println(points.get(2).getX()+","+points.get(2).getY());
		return new Polygon2D(points);
	}

	/*
	public void drawSelf(GL3 gl, CoordFrame2D frame) {
		Point2D c = centre;
		double angle = 0;
		//System.out.println("ttttttttt");
		System.out.println("sssss");
		List<Point2D> points = new ArrayList<Point2D>();
		for (int i = 0; i < VERTICES; i++) {
            float a = (float) (i * Math.PI * 2 / VERTICES); // java.util.Math uses radians!!!
            float x = r * (float) Math.cos(a);
            float y = r * ((float) Math.sin(a) + 1); // Off center
            Point2D p = new Point2D(x, y);
            points.add(p);
        }
		Point2DBuffer buffer = new Point2DBuffer(points);
        int[] names = new int[1];
        gl.glGenBuffers(1, names, 0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, names[0]);
        gl.glBufferData(GL.GL_ARRAY_BUFFER, points.size() * 2 * Float.BYTES,
                buffer.getBuffer(), GL.GL_STATIC_DRAW);
        gl.glVertexAttribPointer(Shader.POSITION, 2, GL.GL_FLOAT, false, 0, 0);
        Shader.setModelMatrix(gl, frame.getMatrix());
        gl.glDrawArrays(GL.GL_LINE_LOOP, 0, points.size());
        gl.glDeleteBuffers(1, names, 0);
        
		
	}*/

}

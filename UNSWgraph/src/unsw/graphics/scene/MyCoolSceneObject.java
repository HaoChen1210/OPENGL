package unsw.graphics.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import unsw.graphics.geometry.Point2D;
import unsw.graphics.geometry.Polygon2D;

/**
 * A cool scene object
 *
 */
public class MyCoolSceneObject extends SceneObject {
	static Color cFillCol1 = new Color(1, 0.5f, 0.5f);
	static Color cFillCol2 = new Color(1, 0.8f, 0.5f);
	static Color cFillCol3 = new Color(1, 0.2f, 0.6f);
	static Color cFillCol4 = new Color(1, 0.3f, 0.7f);
	static Color cFillCol5 = new Color(1, 0.1f, 0.9f);
	static Color cFillCol6 = new Color(1, 0.1f, 0.5f);
	static Color cFillCol7 = new Color(1, 0.3f, 0.4f);
	static Color cFillCol8 = new Color(1, 0.8f, 0.2f);
	Polygon2D poly1 = new Polygon2D(0,0, 1,1, 0,1);
	Polygon2D poly2 = new Polygon2D(0,0, 1,1, 1,0);
	Polygon2D poly3 = new Polygon2D(0,0, 1,0, 1,-1);
	Polygon2D poly4 = new Polygon2D(0,0, 1,-1, 0,-1);
	Polygon2D poly5 = new Polygon2D(0,0, 0,-1, -1,-1);
	Polygon2D poly6 = new Polygon2D(0,0, -1,-1, -1,0);
	Polygon2D poly7 = new Polygon2D(0,0, -1,0, -1,1);
	Polygon2D poly8 = new Polygon2D(0,0, -1,1, 0,1);
	public MyCoolSceneObject(SceneObject parent) {
		super(parent);
		
		PolygonalSceneObject p1 =  new PolygonalSceneObject(this, poly1, cFillCol1, Color.WHITE);
		CircularSceneObject c1 = new CircularSceneObject(p1, 0.25f, Color.YELLOW, Color.WHITE); 
		PolygonalSceneObject p2 =  new PolygonalSceneObject(this, poly2, cFillCol2, Color.WHITE);
		PolygonalSceneObject p3 =  new PolygonalSceneObject(this, poly3, cFillCol3, Color.WHITE);
		PolygonalSceneObject p4 =  new PolygonalSceneObject(this, poly4, cFillCol4, Color.WHITE);
		PolygonalSceneObject p5 =  new PolygonalSceneObject(this, poly5, cFillCol5, Color.WHITE);
		PolygonalSceneObject p6 =  new PolygonalSceneObject(this, poly6, cFillCol6, Color.WHITE);
		PolygonalSceneObject p7 =  new PolygonalSceneObject(this, poly7, cFillCol7, Color.WHITE);
		PolygonalSceneObject p8 =  new PolygonalSceneObject(this, poly8, cFillCol8, Color.WHITE);
		CircularSceneObject c2 = new CircularSceneObject(p8, 0.25f, Color.RED, Color.WHITE); 
		//CircularSceneObject c1 = new CircularSceneObject(p1, 0.25f, Color.YELLOW, Color.WHITE);    
		// TODO: Write your scene object
	}



}

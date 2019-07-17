package unsw.graphics.scene;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL3;

import unsw.graphics.CoordFrame2D;
import unsw.graphics.Matrix3;
import unsw.graphics.Vector3;
import unsw.graphics.geometry.Point2D;

/**
 * A SceneObject is an object that can move around in the world.
 * 
 * SceneObjects form a scene tree.
 * 
 * Each SceneObject is offset from its parent by a translation, a rotation and a
 * scale factor.
 *
 * TODO: The methods you need to complete are at the bottom of the class
 *
 * @author malcolmr
 * @author Robert Clifton-Everest
 */
public class SceneObject {

	// the links in the scene tree
	private SceneObject myParent;
	private List<SceneObject> myChildren;

	// the local transformation
	private Point2D myTranslation;
	private float myRotation; // normalised to the range [-180..180)
	private float myScale;

	// Is this part of the tree showing?
	private boolean amShowing;

	/**
	 * Special constructor for creating the root node. Do not use otherwise.
	 */
	public SceneObject() {
		myParent = null;
		myChildren = new ArrayList<SceneObject>();

		myRotation = 0;
		myScale = 1;
		myTranslation = new Point2D(0, 0);

		amShowing = true;
	}

	/**
	 * Public constructor for creating SceneObjects, connected to a parent.
	 * 
	 * New objects are created at the same location, orientation and scale as
	 * the parent.
	 *
	 * @param parent
	 */
	public SceneObject(SceneObject parent) {
		myParent = parent;
		myChildren = new ArrayList<SceneObject>();

		parent.myChildren.add(this);

		myRotation = 0;
		myScale = 1;
		myTranslation = new Point2D(0, 0);

		// initially showing
		amShowing = true;
	}

	/**
	 * Remove an object and all its children from the scene tree.
	 */
	public void destroy() {
		List<SceneObject> childrenList = new ArrayList<SceneObject>(myChildren);
		for (SceneObject child : childrenList) {
			child.destroy();
		}
		if (myParent != null)
			myParent.myChildren.remove(this);
	}

	/**
	 * Get the parent of this scene object
	 * 
	 * @return
	 */
	public SceneObject getParent() {
		return myParent;
	}

	/**
	 * Get the children of this object
	 * 
	 * @return
	 */
	public List<SceneObject> getChildren() {
		return myChildren;
	}

	/**
	 * Get the local rotation (in degrees)
	 * 
	 * @return
	 */
	public float getRotation() {
		return myRotation;
	}

	/**
	 * Set the local rotation (in degrees)
	 * 
	 * @return
	 */
	public void setRotation(float rotation) {
		myRotation = MathUtil.normaliseAngle(rotation);
	}

	/**
	 * Rotate the object by the given angle (in degrees)
	 * 
	 * @param angle
	 */
	public void rotate(float angle) {
		myRotation += angle;
		myRotation = MathUtil.normaliseAngle(myRotation);
	}

	/**
	 * Get the local scale
	 * 
	 * @return
	 */
	public float getScale() {
		return myScale;
	}

	/**
	 * Set the local scale
	 * 
	 * @param scale
	 */
	public void setScale(float scale) {
		myScale = scale;
	}

	/**
	 * Multiply the scale of the object by the given factor
	 * 
	 * @param factor
	 */
	public void scale(float factor) {
		myScale *= factor;
	}

	/**
	 * Get the local position of the object
	 * 
	 * @return
	 */
	public Point2D getPosition() {
		return myTranslation;
	}

	/**
	 * Set the local position of the object
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		setPosition(new Point2D(x, y));
	}

	/**
	 * Set the local position of the object
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(Point2D p) {
		myTranslation = p;
	}

	/**
	 * Move the object by the specified offset in local coordinates
	 * 
	 * @param dx
	 * @param dy
	 */
	public void translate(float dx, float dy) {
		myTranslation = myTranslation.translate(dx, dy);
	}

	/**
	 * Test if the object is visible
	 * 
	 * @return
	 */
	public boolean isShowing() {
		return amShowing;
	}

	/**
	 * Set the showing flag to make the object visible (true) or invisible
	 * (false). This flag should also apply to all descendents of this object.
	 * 
	 * @param showing
	 */
	public void show(boolean showing) {
		amShowing = showing;
	}

	/**
	 * Update the object and all it's children. This method is called once per
	 * frame.
	 * 
	 * @param dt
	 *            The amount of time since the last update (in seconds)
	 */
	public void update(float dt) {
		updateSelf(dt);

		// Make a copy of all the children to avoid concurrently modification
		// issues if new objects
		// are added to the scene during the update.
		List<SceneObject> children = new ArrayList<SceneObject>(myChildren);
		for (SceneObject so : children) {
			so.update(dt);
		}
	}

	/**
	 * Update the object itself. Does nothing in the default case. Subclasses
	 * can override this for animation or interactivity.
	 * 
	 * @param dt
	 */
	public void updateSelf(float dt) {
		// Do nothing by default
	}

	/**
	 * Draw the object (but not any descendants)
	 * 
	 * This does nothing in the base SceneObject class. Override this in
	 * subclasses.
	 * 
	 * @param gl
	 */
	public void drawSelf(GL3 gl, CoordFrame2D frame) {
		// Do nothing by default
	}

	// ===========================================
	// COMPLETE THE METHODS BELOW
	// ===========================================

	/**
	 * Draw the object and all of its descendants recursively.
	 * 
	 * TODO: Complete this method
	 * 
	 * @param gl
	 */
	public void draw(GL3 gl, CoordFrame2D frame) {

		// don't draw if it is not showing
		if (!amShowing) {
			return;
		}
		// Point2D myPosition = this.getPosition();

		// frame.getMatrix();
		// frame = frame.translate(myTranslation);
		// frame.rotate(myRotation);
		// frame.scale(myScale, myScale);

		CoordFrame2D newFrame =frame.translate(myTranslation).rotate(myRotation).scale(myScale, myScale);
		//newFrame.identity();
		drawSelf(gl, newFrame);
		for (SceneObject children : myChildren) {
			children.draw(gl, newFrame);
		}

		// frame.getMatrix();

		// TODO: Compute the coordinate frame for this object
		// draw the object (Call drawSelf() to draw the object itself)
		// and all its children recursively

	}

	public Matrix3 trsProduct() {
		Matrix3 trans = Matrix3.translation(myTranslation);
		Matrix3 rotate = Matrix3.rotation(myRotation);
		Matrix3 scale = Matrix3.scale(myScale, myScale);
		Matrix3 transTimesRotate = trans.multiply(rotate);
		Matrix3 product = transTimesRotate.multiply(scale);
		return product;
	}

	public Matrix3 inverseTrsProduct() {
		Point2D newTrans = new Point2D(-myTranslation.getX(), -myTranslation.getY());
		Matrix3 trans = Matrix3.translation(newTrans);
		Matrix3 rotate = Matrix3.rotation(-myRotation);
		Matrix3 scale = Matrix3.scale(1 / myScale, 1 / myScale);
		Matrix3 sTimesR = scale.multiply(rotate);
		Matrix3 product = sTimesR.multiply(trans);
		return product;
	}

	/**
	 * Compute the object's position in world coordinates
	 * 
	 * @return a point in world coordinats
	 */
	public Point2D getGlobalPosition() {

		// Point2D point = new Point2D(0, 0);
		Matrix3 m = trsProduct();

		if (myParent != null) {
			
			m = myParent.trsProduct().multiply(m);
		}
		// point.translate(dx, dy)

		// myParent.getGlobalPosition();
		float[] pointOut = m.getValues();
		// System.out.println(pointOut[7]);
		// System.out.println(pointOut[6]);
		Point2D point = new Point2D(pointOut[6], pointOut[7]);
		// point.translate(pointOut[6], pointOut[7]);
		// point.getX();
		// System.out.println("tt");
		// System.out.println(point.getX());
		// TODO: Complete this

		return point;
	}

	/**
	 * Compute the object's rotation in the global coordinate frame
	 * 
	 * @return the global rotation of the object (in degrees) and normalized to
	 *         the range (-180, 180) degrees.
	 */
	public float getGlobalRotation() {
		Matrix3 m = trsProduct();
		if (myParent != null) {
			m = myParent.trsProduct().multiply(m);
		}
		float[] pointOut = m.getValues();
		float anlge = (float) Math.toDegrees(Math.atan2(pointOut[1], pointOut[0]));
		float out = MathUtil.normaliseAngle(anlge);
		return out;
		// TODO: Complete this
		// return 0;
	}

	/**
	 * Compute the object's scale in global terms
	 * 
	 * @return the global scale of the object
	 */
	public float getGlobalScale() {
		Matrix3 m = trsProduct();
		if (myParent != null) {
			m = myParent.trsProduct().multiply(m);
		}
		float[] mValue = m.getValues();
		double scaleSqure = (double) (Math.pow(mValue[0], 2) + Math.pow(mValue[1], 2) + Math.pow(mValue[2], 2));
		float scale = (float) Math.sqrt(scaleSqure);
		// System.out.println(scale);
		return scale;
		// TODO: Complete this
		// return 1;
	}

	/**
	 * Change the parent of a scene object.
	 * 
	 * @param parent
	 */
	public void setParent(SceneObject parent) {
		// TODO: add code so that the object does not change its global
		// position, rotation or scale
		// when it is reparented. You may need to add code before and/or after
		// the fragment of code that has been provided - depending on your
		// approach
		Point2D gp = getGlobalPosition();
		float gr = getGlobalRotation();
		float gs = getGlobalScale();

		Point2D gpParent = parent.getGlobalPosition();
		float grParent = parent.getGlobalRotation();
		float gsParent = parent.getGlobalScale();

		Matrix3 inverseMatrix = parent.inverseTrsProduct();

		float px = gp.getX();
		float py = gp.getY();
		Vector3 v = new Vector3(px,py,1);
		Vector3 finalV = inverseMatrix.multiply(v);
		
		setPosition(finalV.asPoint2D().getX(),finalV.asPoint2D().getY());
		
		//System.out.println(finalV.asPoint2D().getX()+","+finalV.asPoint2D().getY());
		// Point2D gpPoint = new Point2D(, );
		myRotation = MathUtil.normaliseAngle(gr - grParent);
		/*
		if(myRotation>180){
			myRotation = 180 -myRotation;
		}
		*/
		//System.out.println(myRotation);
		myScale = gs / gsParent;

		myParent.myChildren.remove(this);
		myParent = parent;
		myParent.myChildren.add(this);

	}

}

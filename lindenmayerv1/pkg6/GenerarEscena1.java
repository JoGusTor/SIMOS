package lindenmayerv1.pkg6;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class GenerarEscena1 extends Applet implements KeyListener, MouseMotionListener {

    private static final long serialVersionUID = -5353540872979294434L;
    
    
    private static final double MOVESPEED = 0.05;

    private GraphicsConfiguration config = null;
    private Canvas3D canvas = null;
    private SimpleUniverse universe = null;
    private BranchGroup root = null;
    private TransformGroup camera = null;

    private int lastX = 0;

    //Needed for changing the camera position
    private Transform3D transform;
    private Vector3d vector;

    public GenerarEscena1(TransformGroup[] tg) throws HeadlessException {
        root = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        Color3f light1Color = new Color3f(0.2f, 0.2f, 0.2f);
        AmbientLight light1 = new AmbientLight(light1Color);
        light1.setInfluencingBounds(bounds);
        
        
        for (int i = 0; i < tg.length; i++) {
            root.addChild(tg[i]);
        }
        root.addChild(light1);
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());

        //Set up the canvas
        this.config = SimpleUniverse.getPreferredConfiguration();
        this.canvas = new Canvas3D(config);

        this.canvas.addKeyListener(this);

        this.universe = new SimpleUniverse(canvas);
        
        this.add("Center", this.canvas);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        
        this.camera = this.universe.getViewingPlatform().getViewPlatformTransform();
        OrbitBehavior orbiter = new OrbitBehavior(canvas);
        orbiter.setSchedulingBounds( bounds );
        this.universe.getViewingPlatform().setViewPlatformBehavior(orbiter);

        this.universe.addBranchGraph(root);
        universe.getViewingPlatform().setNominalViewingTransform();
       

    }

    @Override
    public void keyTyped(KeyEvent e) throws UnsupportedOperationException{
        if (this.transform == null) {
            //Creating the object we need
            this.transform = new Transform3D();
            this.vector = new Vector3d();

            //Making it the same as used
            camera.getTransform(transform);
            transform.get(vector);
        }
        
        switch (e.getKeyChar()) {
            case 'a':
                //moves camera to the left
                vector.x -= GenerarEscena1.MOVESPEED;
                break;
            case 'd':
                //moves camera to the right
                vector.x += GenerarEscena1.MOVESPEED;
                break;
            case 'w':
                //moves camera  up
                vector.y += GenerarEscena1.MOVESPEED;
                break;
            case 's':
                //moves camera down
                vector.y -= GenerarEscena1.MOVESPEED;
                break;
            case 'q':
                //moves camera  up
                vector.z += GenerarEscena1.MOVESPEED;
                break;
            case 'e':
                //moves camera down
                vector.z -= GenerarEscena1.MOVESPEED;
                break;
            
        }

        //Setting the object back
        transform.set(vector);
        camera.setTransform(transform);
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       if (this.transform == null) {
            //Creating the object we need
            this.transform = new Transform3D();
            this.vector = new Vector3d();

            //Making it the same as used
            camera.getTransform(transform);
            transform.get(vector);
        }
    }

}

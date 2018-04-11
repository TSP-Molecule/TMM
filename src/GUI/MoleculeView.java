package GUI;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import structures.Atom;
import structures.Bond;
import structures.Molecule;
import structures.enums.Elem;
import structures.enums.Geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static structures.enums.Geometry.*;
//http://netzwerg.ch/blog/2015/03/22/javafx-3d-line/


public class MoleculeView extends Group {

    private final double ATOM_RADIUS = 50;
    private final double BOND_RADIUS = 10;
    private final double BOND_LENGTH = 200;

    public MoleculeView(Molecule molecule) {
       drawPic(molecule);
    }





    // start From online

    private void drawBond(Point3D origin, Point3D target) {

        // Begin external code
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Cylinder bond = new Cylinder(10, height);
        bond.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
       // End external code

        PhongMaterial bondMaterial = new PhongMaterial();
        bondMaterial.setSpecularColor(Color.GRAY);
        bondMaterial.setDiffuseColor(Color.GRAY);
        bond.setMaterial(bondMaterial);
        getChildren().add(bond);
    }


    public Group pleaseWork(Molecule mole) {
        Group group = new Group();

//        Cylinder can = new Cylinder(50, 200);
//        can.getTransforms().add(new Translate(0, 0, 0));
//        can.setMaterial(new PhongMaterial(Color.PAPAYAWHIP));
//        getChildren().add(can);

        double rad = 200;
        double thetaZY = 0;//s
        double thetaXY = 0;//t
        double angY = 60;
        double angZ = 120;

        double x = 0;
        double y = 0;
        double z = 0;
        Point3D origin = new Point3D(0, 0, 0);
        drawAtom(new Atom(Elem.Carbon), x, y, z, group);
        for (int i = 0; i < 3; i++) {
            x = rad + rad * Math.cos(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            y = rad * Math.sin(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            z = rad * Math.cos(Math.toRadians(thetaXY));
            System.out.printf("bee:  (%f, %f, %f) \n", x, y, z);
            thetaZY += angY;
            thetaXY += angZ;
            Point3D curr = new Point3D(x, y, z);
            drawAtom(new Atom(Elem.Hydrogen), x, y, z, group);
//            if (i > 0) {
                drawBond(origin, curr);
               // getChildren().add(sil);

               // System.out.println(sil.getHeight() + ", " + sil.getRadius() + ", " + sil.getTranslateX() + ", " + sil.getTranslateY() + ", " + sil.getTranslateZ());
//            }
        }

        Sphere sphere = new Sphere(600);
        sphere.setMaterial(new PhongMaterial(Color.BLUE));
        group.getChildren().add(sphere);
        return group;
    }


    class Atomus {
        double x;
        double y;
        double z;

        Point3D loc;
        Atomus par;
        ArrayList<Atom> kids = new ArrayList<>();
        ArrayList<Atom> sibs = new ArrayList<>();
        Atom atom;

        public Atomus(Atom atom, double x, double y, double z, Atomus par) {
            this.atom = atom;
            this.x = x;
            this.y = y;
            this.z = z;
            this.par = par;
            fillKids();
            setCoordinates(x, y, z);
        }

        public void setCoordinates(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            loc = new Point3D(x, y, z);
        }

        public void fillKids() {
            if (par == null) {
                kids = atom.getAttachedAtoms();
            } else {
                for (Atom a: atom.getAttachedAtoms()) {
                    if (!par.atom.equals(a)) {
                        kids.add(a);
                    }
                }
            }
        }

    }


    public void drawPic(Molecule mole) {
        Atom cen = mole.getCenter();
        Atomus adam = new Atomus(cen, 0, 0, 0, null);

        Stack<Atomus> stack = new Stack<>();
        HashMap<Atomus, Boolean> drawn = new HashMap<>();
        HashMap<Atomus, Boolean> visited = new HashMap<>();

        stack.push(adam);

        while(!stack.isEmpty()) {
            Atomus node = stack.pop();
            if (drawn.get(node) == null) {
                drawAtom(node.atom, node.x, node.y, node.z);
            }
            if (visited.get(node) != null) {
                continue;
            }
            Geometry geo = node.atom.getGeometry();
            if (geo == null) {
                continue;
            }
            switch (geo) {
                case Tetrahedral:
                    tetra(node, stack, drawn);
                    break;
                case TrigonalPlanar:
                    trig(node, stack, drawn);
                    break;
                case Bent:
                    bent(node, stack, drawn);
                    break;
                default:
                    System.out.println("hi im a " + geo);

            }
        }


    }


    /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    private void tetra(Atomus node, Stack<Atomus> stack, HashMap<Atomus, Boolean> drawn) {
        Group group = new Group();
        double rad = 200;
        double thetaZY = 0;//s
        double thetaXY = 0;//t
        double angY = 60;
        double angZ = 120;

        boolean big = false;
        int draw = 1;

        if (node.par != null) {
            Point3D deltaPar = node.par.loc.subtract(node.loc);
            if (deltaPar.getZ() > 0) {
                draw = -1;
            }
//            thetaZY = node.par.loc.angle(node.loc);
            System.out.println("parent: " + deltaPar);
//            yAxis = node.par.loc;

        }
        int j = 0;

        Point3D origin = node.loc;// new Point3D(0, 0, 0);
        if (node.par == null) {
            j = 1;
            Atom a = node.kids.get(0);
            double x = node.x - rad;
            double y = node.y;
            double z = node.z;
            drawAtom(node.kids.get(0), node.x - rad, node.y, node.z);
            Point3D curr = new Point3D(x, y, z);
            Atomus atomus = new Atomus(a, x, y, z, node);
           // drawAtom(atom, x, y, z);
            drawn.put(atomus, true);
            stack.push(atomus);
           // getChildren().add(createConnection(origin, curr));
            drawBond(origin, curr);

        }



        double x = 0;
        double y = 0;
        double z = 0;


       // drawAtom(new Atom(Elem.Carbon), x, y, z);
        for (int i = j; i < node.kids.size(); i++) {
            Atom atom = node.kids.get(i);
            x = rad + rad * Math.cos(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            y = -rad * Math.sin(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            z = draw * rad * Math.cos(Math.toRadians(thetaXY));
            x = node.x + rad * Math.cos(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            y = node.y + rad * Math.sin(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            z = node.z + draw * rad * Math.cos(Math.toRadians(thetaXY));
            System.out.printf("bee:  (%f, %f, %f) \n", x, y, z);
            thetaZY += angY;
            thetaXY += angZ;
            Point3D curr = new Point3D(x, y, z);
            Atomus atomus = new Atomus(atom, x, y, z, node);
            drawAtom(atom, x, y, z);
            drawn.put(atomus, true);
            stack.push(atomus);
          //  getChildren().add(createConnection(origin, curr));
            drawBond(origin, curr);

        }
       // getChildren().add(group);
    }

    private void terrat(Atomus node, Stack<Atomus> stack, HashMap<Atomus, Boolean> drawn) {
        Group group = tetra2(new Atomus(new Atom(Elem.Carbon),0, 0, 0, node.par), stack, drawn);
        group.getTransforms().add(new Translate(node.x, node.y, node.z));
        getChildren().add(group);
    }

    private Group tetra2(Atomus node, Stack<Atomus> stack, HashMap<Atomus, Boolean> drawn) {
        Group group = new Group();
        double rad = 200;
        double thetaZY = 0;//s
        double thetaXY = 0;//t
        double angY = 60;
        double angZ = 120;

        if (node.par != null) {
            //thetaZY = node.par.loc.angle(node.loc);
            //System.out.println("parent");
//            yAxis = node.par.loc;

        }



        double x = 0;
        double y = 0;
        double z = 0;

        Point3D origin = node.loc;// new Point3D(0, 0, 0);
        // drawAtom(new Atom(Elem.Carbon), x, y, z);
        for (int i = 0; i < node.kids.size(); i++) {
            Atom atom = node.kids.get(i);
            x = rad + rad * Math.cos(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            y = rad * Math.sin(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            z = rad * Math.cos(Math.toRadians(thetaXY));
            System.out.printf("bee:  (%f, %f, %f) \n", x, y, z);
            thetaZY += angY;
            thetaXY += angZ;
            Point3D curr = new Point3D(x, y, z);
            Atomus atomus = new Atomus(atom, x, y, z, node);
            drawAtom(atom, x, y, z, group);
            drawn.put(atomus, true);
            stack.push(atomus);
           // group.getChildren().add(createConnection(origin, curr));
            drawBond(origin, curr);
        }
        return group;
        // getChildren().add(group);
    }

    private void trig(Atomus node, Stack<Atomus> stack, HashMap<Atomus, Boolean> drawn) {
        double rad = 200;
        double thetaZY = 0;//s
        double thetaXY = 0;//t
        double angY = 0;
        double angZ = 120;

        double x = 0;
        double y = 0;
        double z = 0;
        Point3D origin = node.loc;// new Point3D(0, 0, 0);
        // drawAtom(new Atom(Elem.Carbon), x, y, z);
        for (int i = 0; i < node.kids.size(); i++) {
            Atom atom = node.kids.get(i);
            x = rad * Math.cos(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            y = rad * Math.sin(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            z = rad * Math.cos(Math.toRadians(thetaXY));
            // System.out.printf("bee:  (%f, %f, %f) \n", x, y, z);
            thetaZY += angY;
            thetaXY += angZ;
            Point3D curr = new Point3D(x, y, z);
            Atomus atomus = new Atomus(atom, x, y, z, node);
            drawAtom(atom, x, y, z);
            drawn.put(atomus, true);
            stack.push(atomus);
           // getChildren().add(createConnection(origin, curr));
            drawBond(origin, curr);
        }
    }


    private void bent(Atomus node, Stack<Atomus> stack, HashMap<Atomus, Boolean> drawn) {
        double rad = 200;
        double thetaZY = 0;//s
        double thetaXY = 0;//t
        double angY = 0;
        double angZ = 120;

        double x = 0;
        double y = 0;
        double z = 0;
        Point3D origin = node.loc;// new Point3D(0, 0, 0);
        int draw = 1;
        if (node.par != null) {
            Point3D deltaPar = node.par.loc.subtract(node.loc);
            if (deltaPar.getZ() > 0) {
                draw = -1;
            }
//            thetaZY = node.par.loc.angle(node.loc);
            System.out.println("parent: " + deltaPar);
//            yAxis = node.par.loc;

        }
        // drawAtom(new Atom(Elem.Carbon), x, y, z);
        for (int i = 0; i < node.kids.size(); i++) {
            Atom atom = node.kids.get(i);
            x = rad * Math.cos(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            y = rad * Math.sin(Math.toRadians(thetaZY)) * Math.sin(Math.toRadians(thetaXY));
            z = draw * rad * Math.cos(Math.toRadians(thetaXY));
            // System.out.printf("bee:  (%f, %f, %f) \n", x, y, z);
            thetaZY += angY;
            thetaXY += angZ;
            Point3D curr = new Point3D(x, y, z);
            Atomus atomus = new Atomus(atom, x, y, z, node);
            drawAtom(atom, x, y, z);
            drawn.put(atomus, true);
            stack.push(atomus);
           // getChildren().add(createConnection(origin, curr));
            drawBond(origin, curr);
        }
    }

    private double toDegrees(double inRadians) {
        return inRadians * 180 / Math.PI;
    }

    private double toRadians(double inDegrees) {
        return inDegrees * Math.PI / 180;
    }

    private void drawBond(Bond b, double x, double y, double z, double theta, Group g) {
        Color color = Color.LIGHTGREY;
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color);

        Cylinder cylinder = new Cylinder(BOND_RADIUS, BOND_LENGTH);
        cylinder.setMaterial(material);

        cylinder.setTranslateX(x);
        cylinder.setTranslateY(y);
        cylinder.setTranslateZ(z);

//        cylinder.getTransforms().add(new Rotate(theta, x, y, z, Rotate.Z_AXIS ));
        cylinder.setRotationAxis(Rotate.Z_AXIS);
        cylinder.setRotate(theta);

        getChildren().add(cylinder);
    }



    /**
     * Draws an atom with the proper color and location.
     *
     * @param atom
     * @param x
     * @param y
     * @param z
     */
    private void drawAtom(Atom atom, double x, double y, double z, Group g) {
        Color color = atom.getElement().getColor();
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color);

        Sphere sphere = new Sphere(ATOM_RADIUS);
        sphere.setMaterial(material);

        sphere.setTranslateX(x);
        sphere.setTranslateY(y);
        sphere.setTranslateZ(z);

        getChildren().add(sphere);
    }

    private void drawAtom(Atom atom, double x, double y, double z) {
        Color color = atom.getElement().getColor();
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color);

        Sphere sphere = new Sphere(ATOM_RADIUS);
        sphere.setMaterial(material);

        sphere.setTranslateX(x);
        sphere.setTranslateY(y);
        sphere.setTranslateZ(z);

        getChildren().add(sphere);
    }
}

package structures.enums;

import structures.Atom;
import structures.Molecule;

/**
 * Different Geometries that a molecule could have, based on steric number and number of lone pairs.
 *
 * @author Emily Anible
 * CS3141, Spring 2018, Team ATOM
 */
public enum Geometry {
    //Name (# Atoms Bonded to A, # Lone Pairs around A

    //Steric: 2
    Linear(2, 0, "Linear", 180),

    //Steric: 3
    TrigonalPlanar(3, 0, "Trigonal Planar", 120),
    VShape(2, 1, "V-Shape", 120), //Less than

    //Steric: 4
    Tetrahedral(4, 0, "Tetrahedral", 109),
    TrigonalPyramidal(3, 1, "Trigonal Pyramidal", 109), //Less than
    Bent(2, 2, "Bent", 109), //Much less than

    //Steric: 5
    TrigonalBipyriamidal(5, 0, "Trigonal Bipyramidal", 120), //90 between axis
    Seesaw(4, 1, "See-saw", 120), //less than 120, less than 90 between axis
    TShaped(3, 2, "T-Shaped", 90), //less than 90
    Linear5(2, 3, "Linear (5)", 180),

    //Steric: 6
    Octahedral(6, 0, "Octahedral", 90),
    SquarePyramidal(5, 1, "Square Pyramidal", 90), //less than 90
    SquarePlanar(4, 2, "Square Planar", 90),
    TShape6(3, 3, "T-Shaped (6)", 90), //less than 90
    Linear6(2, 4, "Linear (6)", 180);

    /**
     * Number of atoms bonded to the target atom
     */
    private final int bondedAtoms;
    /**
     * Number of lone pairs associated with the target atom
     */
    private final int lonePairs;
    /**
     * Bond angle associated with the molecular geometry
     */
    private final double bondAngle;
    /**
     * Text-based name of the molecular geometry. Specifiers for geometries with identical names.
     */
    private final String name;

    Geometry(int bondedAtoms, int lonePairs, String name, double bondAngle) {
        this.bondedAtoms = bondedAtoms;
        this.lonePairs = lonePairs;
        this.name = name;
        this.bondAngle = bondAngle;
    }

    /**
     * @param bondedAtoms number of atoms bonded to target atom
     * @param lonePairs   number of lone pairs on target atom
     * @return Geometry associated with target atom
     */
    public static Geometry get(int bondedAtoms, int lonePairs) {
        for (Geometry g : Geometry.values()) {
            if (g.getBondedAtoms() == bondedAtoms && g.getLonePairs() == lonePairs) {
                return g;
            }
        }
        return null;
    }

    /**
     * @return bondedAtoms
     */
    public int getBondedAtoms() {
        return bondedAtoms;
    }

    /**
     * @return lonePairs
     */
    public int getLonePairs() {
        return lonePairs;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Calculates the molecular geometry of the center atom of a molecule
     *
     * @param molecule Molecule for which to calculate Molecular Geometry
     */
    public static void calculateGeometry(Molecule molecule) {
        if (molecule.getAtoms().size() == 2) {
            for (Atom a : molecule.getAtoms()) {
                a.setGeometry(Linear);
            }
            return;
        }
        for (Atom a : molecule.getAtoms()) {
            int lonePairs = a.getLonePairs();
            int attached = a.getAttachedBonds().size();

            a.setGeometry(Geometry.get(attached, lonePairs));
            System.out.println(a + " has geometry " + a.getGeometry());
        }

    }

    public double getBondAngle() {
        return bondAngle;
    }
}

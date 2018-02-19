package structures;

/**
 * Element -
 *      @author Emily Anible
 *      Element Object. Each instance of the object contains information about the element,
 *      and can be copied to create duplicate "atoms".
 *      The information about real elements is stored and translated elsewhere,
 *      this object only holds the information about each one used.
 */
public class Element {


    /**
     *  Element Attributes
     *      Each element has various attributes about it.
     *      The object will store a copy of this information accessible
     *      through the getters.
     *
     *      This information should NEVER change during runtime!
     */
    private final int       atomicNumber;
    private final String    symbol;
    private final String    name;
    private final double    atomicMass;
    private final int       atomicRadius;
    private final double    density;
    private final int       cpkColor;
    //TODO: electronegativity

    public Element(int      atomicNumber,
                   String   symbol,
                   String   name,
                   double   atomicMass,
                   int      atomicRadius,
                   double   density,
                   int      cpkColor) {

        this.atomicNumber   = atomicNumber;
        this.symbol         = symbol;
        this.name           = name;
        this.atomicMass     = atomicMass;
        this.atomicRadius   = atomicRadius;
        this.density        = density;
        this.cpkColor       = cpkColor;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getAtomicMass() {
        return atomicMass;
    }

    public int getAtomicRadius() {
        return atomicRadius;
    }

    public double getDensity() {
        return density;
    }

    public int getCpkColor() {
        return cpkColor;
    }

}

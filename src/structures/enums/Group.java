package structures.enums;

/**
 * Group enum -- gives the group number, name, and available valence electron counts of elements with the
 * associated group.
 *
 * @author Emily Anible
 * CS3141, Spring 2018, Team ATOM
 */
public enum Group {
    Group1(1, "IA", 1),
    Group2(2, "IIA", 2),

    Group3(3, "IIIB", 0),
    Group4(4, "IVB", 0),
    Group5(5, "VB", 0),
    Group6(6, "VIB", 0),
    Group7(7, "VIIB", 0),
    Group8(8, "VIIIB", 0),
    Group9(9, "VIIIB", 0),
    Group10(10, "VIIIB", 0),
    Group11(11, "IB", 0),
    Group12(12, "IIB", 0),

    Group13(13, "IIIA", 3),
    Group14(14, "IVA", 4),
    Group15(15, "VA", 5),
    Group16(16, "VIA", 6),
    Group17(17, "VIIA", 7),
    Group18(18, "VIIIA", 8),
    GroupA(19, "Actinides", 0),
    GroupL(20, "Lanthanides", 0);

    /**
     * Group number according to new IUPAC naming scheme
     */
    private final int num;
    /**
     * Group name in text (CAS naming scheme)
     */
    private final String group;
    /**
     * Number of valence electrons in outer shells (only applies to CAS-A groups)
     */
    private final int valenceElectrons;

    Group(int num, String group, int valenceElectrons) {
        this.num = num;
        this.group = group;
        this.valenceElectrons = valenceElectrons;
    }

    /**
     * @param group The group name
     * @return Group associated with the group name
     */
    public static Group getByName(String group) {
        for (Group g : Group.values()) {
            if (g.toString().equals(group)) return g;
        }
        return null;
    }

    /**
     * @param num Group Number
     * @return Group associated with the group number
     */
    public static Group getByNum(int num) {
        for (Group g : Group.values()) {
            if (g.getInt() == num) return g;
        }
        return null;
    }

    /**
     * @return group number
     */
    public int getInt() {
        return num;
    }

    /**
     * @return group name
     */
    public String getGroup() {
        return group;
    }

    /**
     * @return number of free valence electrons
     */
    public int getValenceE() {
        return valenceElectrons;
    }
}

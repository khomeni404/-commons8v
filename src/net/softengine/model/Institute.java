package net.softengine.model;

import java.io.Serializable;
import java.util.*;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 16-Jan-18 at 12:13 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 16-Jan-18
 * Version : 1.0
 */

public enum Institute implements Serializable {
    BD0010001("BD0010001", "Mercantile Bank Limited", "61, Dilkusha C/A, Dhaka"),
    BD0010002("BD0010002", "Data Soft", "23/6, Shamoly, Dhaka, Bangladesh");


    public final String CODE;
    public final String NAME;
    public final String ADDRESS;

    Institute(final String CODE, final String NAME, final String ADDRESS) {
        this.CODE = CODE;
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
    }

    private static final Map<String, Institute> Institute_MAP = new HashMap<String, Institute>();

    static {
        for (Institute pops : Institute.values()) {
            Institute_MAP.put(pops.CODE, pops);
        }
    }

    public static Institute get(final String CODE) {
        return Institute_MAP.get(CODE);
    }


    public static void main(String[] args) {

//        List<Institute> getList = getList("01");
//        System.out.println(getList.size());
    }

    @SuppressWarnings("unchecked")
    public static List<Institute> getList() {
        return new ArrayList(EnumSet.allOf(Institute.class));
    }

    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (Institute i : getList()) {
            map.put(i.CODE, i.NAME);
        }
        return map;
    }


    @Override
    public String toString() {
        return this.CODE + " : " + this.NAME;
    }

}

package net.softengine.model;

import java.io.Serializable;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 07-Jan-18 at 4:13 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 07-Jan-18
 * Version : 1.0
 */

public abstract class GenericModel implements Serializable {
    /**
     * Method returns basic information of Object holds.
     * @return String
     */
    public abstract String info();
}

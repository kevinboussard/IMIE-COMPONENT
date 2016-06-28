package com.imie.component.tool;

import com.imie.component.entity.Order;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 28/06/16.
 */
@XmlRootElement
@XmlSeeAlso({Order.class})
public class EntityList<T> {

    private List<T> listOfEntityObjects;

    public EntityList() {
        listOfEntityObjects = new ArrayList<T>();
    }

    public EntityList(List<T> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<T> getItems() {
        return listOfEntityObjects;
    }
}
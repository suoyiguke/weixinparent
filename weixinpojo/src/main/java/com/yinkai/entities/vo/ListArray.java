package com.yinkai.entities.vo;

import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

public class ListArray implements Serializable{
    private List<Footprint> footprints=new ArrayList<Footprint>();

    public List<Footprint> getFootprints() {
        return footprints;
    }

    public void setFootprints(List<Footprint> footprints) {
        this.footprints = footprints;
    }
}

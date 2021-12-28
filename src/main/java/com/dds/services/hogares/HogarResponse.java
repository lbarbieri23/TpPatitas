package com.dds.services.hogares;

import com.dds.services.hogares.model.Hogar;

import java.util.List;

public class HogarResponse {

    private Integer total;
    private Integer offset;
    private List<Hogar> hogares;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<Hogar> getHogares() {
        return hogares;
    }

    public void setHogares(List<Hogar> hogares) {
        this.hogares = hogares;
    }
}

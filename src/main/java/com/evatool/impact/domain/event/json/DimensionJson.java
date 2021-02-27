package com.evatool.impact.domain.event.json;

import com.evatool.impact.domain.entity.Dimension;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class DimensionJson {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (DimensionJson) o;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.type, that.type)
                && Objects.equals(this.description, that.description);
    }

    public boolean equals(Dimension that) {
        return Objects.equals(this.id, that.getId().toString())
                && Objects.equals(this.name, that.getName())
                && Objects.equals(this.type, that.getType().toString())
                && Objects.equals(this.description, that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.type, this.description);
    }
}

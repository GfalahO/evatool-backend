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
        DimensionJson that = (DimensionJson) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description);
    }

    public boolean equals(Dimension that) {
        return Objects.equals(id, that.getId().toString()) && Objects.equals(name, that.getName()) && Objects.equals(type, that.getType().toString()) && Objects.equals(description, that.getDescription());
    }
}

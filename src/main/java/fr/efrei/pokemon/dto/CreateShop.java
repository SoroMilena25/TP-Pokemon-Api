package fr.efrei.pokemon.dto;

import java.util.List;

public class CreateShop {

    private String name;

    private List<String> objects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        this.objects = objects;
    }
}

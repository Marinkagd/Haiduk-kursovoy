package db.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "furniturecategory")
public class FurnitureCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "furniturecategory", cascade = CascadeType.ALL)
    private List<Furniture> furnitureList;

    public FurnitureCategory() {
    }

    public FurnitureCategory(String name) {
        this.name = name;
        this.furnitureList = null;
    }


    public void convertToArrayList() {
        furnitureList = new ArrayList<>(furnitureList);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FurnitureCategory [id=" + id + ", name=" + name + "]";
    }
}

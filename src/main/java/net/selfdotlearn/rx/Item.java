package net.selfdotlearn.rx;

public class Item {
    private String id;

    private String created;

    public String getId()
    {
        return id;
    }

    public String getCreated()
    {
        return created;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}

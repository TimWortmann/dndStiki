package de.dnd.stiki.adapters.dndClass.counter;

public class CounterDto {

    private Long id;

    private String name;

    private String reset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }
}

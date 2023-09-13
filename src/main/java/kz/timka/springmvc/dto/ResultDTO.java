package kz.timka.springmvc.dto;

public class ResultDTO {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ResultDTO(int value) {
        this.value = value;
    }

    public ResultDTO() {
    }
}

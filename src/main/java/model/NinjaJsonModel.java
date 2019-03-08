package model;

import lombok.Getter;
import lombok.Setter;

public class NinjaJsonModel {

    @Getter
    @Setter
    private String[] result;

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private int total;
}

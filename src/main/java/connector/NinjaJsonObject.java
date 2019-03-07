package connector;

import lombok.Getter;
import lombok.Setter;

public class NinjaJsonObject {

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

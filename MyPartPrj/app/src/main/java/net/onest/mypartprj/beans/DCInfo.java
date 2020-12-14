package net.onest.mypartprj.beans;

import java.util.List;

public class DCInfo {
    private List<DefineChoice> defineChoices;

    public List<DefineChoice> getDefineChoices() {
        return defineChoices;
    }

    public void setDefineChoices(List<DefineChoice> defineChoices) {
        this.defineChoices = defineChoices;
    }

    @Override
    public String toString() {
        return "DCInfo{" +
                "defineChoices=" + defineChoices +
                '}';
    }
}

package wrappers;

public class EmbroideryFilterWrapper extends ProductFilterWrapper {

    private int minStitches;

    private int maxStitches;

    private int minColors;

    private int maxColors;

    private int minSquareMillimeters;

    private int maxSquareMillimeters;

    public int getMinStitches() {
        return minStitches;
    }

    public void setMinStitches(int minStitches) {
        this.minStitches = minStitches;
    }

    public int getMaxStitches() {
        return maxStitches;
    }

    public void setMaxStitches(int maxStitches) {
        this.maxStitches = maxStitches;
    }

    public int getMinColors() {
        return minColors;
    }

    public void setMinColors(int minColors) {
        this.minColors = minColors;
    }

    public int getMaxColors() {
        return maxColors;
    }

    public void setMaxColors(int maxColors) {
        this.maxColors = maxColors;
    }

    public int getMinSquareMillimeters() {
        return minSquareMillimeters;
    }

    public void setMinSquareMillimeters(int minSquareMillimeters) {
        this.minSquareMillimeters = minSquareMillimeters;
    }

    public int getMaxSquareMillimeters() {
        return maxSquareMillimeters;
    }

    public void setMaxSquareMillimeters(int maxSquareMillimeters) {
        this.maxSquareMillimeters = maxSquareMillimeters;
    }

}

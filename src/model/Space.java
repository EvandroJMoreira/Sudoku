package model;

public class Space {
    private Integer actual;
    private final int expected;
    private final boolean fixed;
    private final int row;
    private final int col;

    // Construtor atualizado que requer todas as coordenadas
    public Space(final int row, final int col, final int expected, final boolean fixed) {
        this.row = row;
        this.col = col;
        this.expected = expected;
        this.fixed = fixed;
        if (fixed) {
            actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(final Integer actual) {
        if (!fixed) {
            this.actual = actual;
        }
    }

    public void clearSpace() {
        setActual(null);
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
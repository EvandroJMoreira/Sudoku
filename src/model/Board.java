package model;

import java.util.List;
import java.util.Collection;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {
    private final List<List<Space>> spaces;  // spaces.get(row).get(col)

    public Board(List<List<Space>> spaces) {
       this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatus() {
        if (spaces.stream().flatMap(Collection::stream)
                 .noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))) {
            return GameStatusEnum.NON_STARTED;
        }
        return spaces.stream().flatMap(Collection::stream)
                   .anyMatch(s -> isNull(s.getActual())) ? 
                   GameStatusEnum.INCOMPLETE : GameStatusEnum.COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == GameStatusEnum.NON_STARTED) {
            return false;
        }
        return spaces.stream().flatMap(Collection::stream)
                   .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(int col, int row, int value) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            return false;
        }
        
        Space space = spaces.get(row).get(col);
            if (space.isFixed()) {
                return false;
            }
            space.setActual(value);
            return true;
     } 

    public boolean clearValue(int col, int row) {
    	Space space = spaces.get(row).get(col); 
        if (space.isFixed()) {
            return false;
        }
        
        space.clearSpace();
        return true;
    }

    public void reset() {
        spaces.forEach(row -> row.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished() {
        return !hasErrors() && getStatus().equals(GameStatusEnum.COMPLETE);
    }
}
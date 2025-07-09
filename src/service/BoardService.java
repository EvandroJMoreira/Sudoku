package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Board;
import model.GameStatusEnum;
import model.Space;

public class BoardService {
    private final static int BOARD_LIMIT = 9;
    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        if (gameConfig == null) {
            throw new IllegalArgumentException("Game config cannot be null");
        }
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces() {
        return board.getSpaces();
    }

    public void reset() {
        board.reset();
    }

    public boolean hasErrors() {
        return board.hasErrors();
    }

    public GameStatusEnum getStatus() {
        return board.getStatus();
    }

    public boolean gameIsFinished() {
        return board.gameIsFinished();
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int row = 0; row < BOARD_LIMIT; row++) {
            List<Space> rowSpaces = new ArrayList<>();
            for (int col = 0; col < BOARD_LIMIT; col++) {
                String key = row + "," + col;
                String config = gameConfig.getOrDefault(key, "0,false");
                String[] parts = config.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid config format for position " + key);
                }
                int expected = Integer.parseInt(parts[0]);
                boolean fixed = Boolean.parseBoolean(parts[1]);
                rowSpaces.add(new Space(row, col, expected, fixed));
            }
            spaces.add(rowSpaces);
        }
        return spaces;
    }
}
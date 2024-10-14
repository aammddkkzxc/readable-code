package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.config.GameConfig;
import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

public class MineSweeper implements GameRunnable, GameInitializable {

    private final GameBoard gameBoard;
    private final InputHandler consoleInputHandler;
    private final OutputHandler consoleOutputHandler;

    public MineSweeper(GameConfig gameConfig) {
        gameBoard = new GameBoard(gameConfig.getGameLevel());
        consoleInputHandler = gameConfig.getInputHandler();
        consoleOutputHandler = gameConfig.getOutputHandler();
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        consoleOutputHandler.showGameStartComments();

        while (gameBoard.isInProgress()) {
            try {
                consoleOutputHandler.showBoard(gameBoard);

                CellPosition cellPosition = getCellInputFromUser();
                UserAction userAction = getUserActionInputFromUser();
                actOnCell(cellPosition, userAction);
            } catch (GameException e) {
                consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printSimpleMessage();
            }
        }

        consoleOutputHandler.showBoard(gameBoard);

        if (gameBoard.isWinStatus()) {
            consoleOutputHandler.printGameWinningComment();
        }

        if (gameBoard.isLoseStatus()) {
            consoleOutputHandler.printGameLosingComment();
        }
    }

    private void actOnCell(CellPosition cellPosition, UserAction userAction) {

        if (doesUserChooseToPlantFlag(userAction)) {
            gameBoard.flagAt(cellPosition);
            return;
        }

        if (doesUserChooseToOpenCell(userAction)) {
            gameBoard.openAt(cellPosition);
        }

        throw new GameException("잘못된 번호를 선택하셨습니다.");
    }

    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;
    }

    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }

    private UserAction getUserActionInputFromUser() {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserActionFromUser();
    }

    private CellPosition getCellInputFromUser() {
        consoleOutputHandler.printCommentForSelectingCell();
        CellPosition cellPosition = consoleInputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다");
        }
        return cellPosition;
    }

}

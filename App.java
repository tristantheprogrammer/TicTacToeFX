import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    private static final int SIZE = 5;
    private Button[][] buttons = new Button[SIZE][SIZE];
    private char currentPlayer = 'X';

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createAndInitializeGrid();

        Scene scene = new Scene(gridPane, 300, 300);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createAndInitializeGrid() {
        GridPane gridPane = new GridPane();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Button button = createButton();
                buttons[row][col] = button;

                final int finalRow = row;
                final int finalCol = col;

                button.setOnAction(e -> handleButtonClick(finalRow, finalCol));

                gridPane.add(button, col, row);
            }
        }

        return gridPane;
    }

    private Button createButton() {
        Button button = new Button();
        button.setMinSize(60, 60);
        return button;
    }

    private void handleButtonClick(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            if (checkForWinner(row, col)) {
                showWinner(currentPlayer);
                resetGame();
            } else if (isBoardFull()) {
                showDraw();
                resetGame();
            } else {
                switchPlayer();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkForWinner(int row, int col) {
        return checkRow(row) || checkColumn(col) || checkDiagonals(row, col);
    }

    private boolean checkRow(int row) {
        for (int col = 0; col < SIZE - 4; col++) {
            if (buttons[row][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row][col + 1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row][col + 2].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row][col + 3].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row][col + 4].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(int col) {
        for (int row = 0; row < SIZE - 4; row++) {
            if (buttons[row][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row + 1][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row + 2][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row + 3][col].getText().equals(String.valueOf(currentPlayer))
                    && buttons[row + 4][col].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(int row, int col) {
        return checkMainDiagonal(row, col) || checkAntiDiagonal(row, col);
    }

    private boolean checkMainDiagonal(int row, int col) {
        for (int i = 0; i < 5; i++) {
            int r = row - i;
            int c = col - i;
            if (r >= 0 && r + 4 < SIZE && c >= 0 && c + 4 < SIZE) {
                if (buttons[r][c].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r + 1][c + 1].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r + 2][c + 2].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r + 3][c + 3].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r + 4][c + 4].getText().equals(String.valueOf(currentPlayer))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkAntiDiagonal(int row, int col) {
        for (int i = 0; i < 5; i++) {
            int r = row + i;
            int c = col - i;
            if (r - 4 >= 0 && r < SIZE && c >= 0 && c + 4 < SIZE) {
                if (buttons[r][c].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r - 1][c + 1].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r - 2][c + 2].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r - 3][c + 3].getText().equals(String.valueOf(currentPlayer))
                        && buttons[r - 4][c + 4].getText().equals(String.valueOf(currentPlayer))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner(char player) {
        System.out.println("Player " + player + " wins!");
    }

    private void showDraw() {
        System.out.println("It's a draw!");
    }

    private void resetGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }
}

import java.util.Scanner;

    public class SudokuSolver {
    
        public static void main(String[] args) {
            int[][] board = readBoard();
            
            System.out.println("Sudoku puzzle to solve:");
            printBoard(board);
            
            if (solveSudoku(board)) {
                System.out.println("\nSudoku solved:");
                printBoard(board);
            } else {
                System.out.println("\nNo solution exists.");
            }
        }
    
        // Method to read Sudoku board from user input
        public static int[][] readBoard() {
            Scanner scanner = new Scanner(System.in);
            int[][] board = new int[9][9];
            
            System.out.println("Enter the Sudoku puzzle (use 0 to represent empty cells):");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            
            return board;
        }
    
        // Method to solve Sudoku using backtracking
        public static boolean solveSudoku(int[][] board) {
            int row = -1;
            int col = -1;
            boolean isEmpty = true;
    
            // Find the first empty cell
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        row = i;
                        col = j;
                        isEmpty = false;
                        break;
                    }
                }
                if (!isEmpty) {
                    break;
                }
            }
    
            // If no empty cell found, the puzzle is solved
            if (isEmpty) {
                return true;
            }
    
            // Try placing numbers from 1 to 9
            for (int num = 1; num <= 9; num++) {
                if (isSafe(board, row, col, num)) {
                    board[row][col] = num;
    
                    // Recursively solve the rest of the board
                    if (solveSudoku(board)) {
                        return true;
                    } else {
                        // If placing num doesn't lead to a solution, backtrack
                        board[row][col] = 0;
                    }
                }
            }
    
            // Trigger backtracking
            return false;
        }
    
        // Method to check if a number can be placed at board[row][col]
        private static boolean isSafe(int[][] board, int row, int col, int num) {
            // Check row and column constraints
            for (int i = 0; i < board.length; i++) {
                if (board[row][i] == num || board[i][col] == num) {
                    return false;
                }
            }
    
            // Check 3x3 subgrid constraints
            int startRow = row - row % 3;
            int startCol = col - col % 3;
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    if (board[i][j] == num) {
                        return false;
                    }
                }
            }
    
            return true;
        }
    
        // Method to print the Sudoku board
        public static void printBoard(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    


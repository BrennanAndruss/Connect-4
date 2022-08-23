import java.util.Arrays;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    // Declare and initialize board
    String[][] board = {{"⚪", "⚪", "⚪", "⚪", "⚪", "⚪","⚪"},
                        {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪","⚪"},
                        {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪","⚪"},
                        {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪","⚪"},
                        {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪","⚪"},
                        {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪","⚪"}};
    System.out.println("Board: ");
    drawBoard(board);
    System.out.println();

    // Determine player and computer symbols
    String player1 = "🔴";
    String player2 = "🟡";

    // Variable to store inputted row
    int col;
    int row;

    while(true)
    {
      // Player 1's turn
      System.out.println("Player 1's turn: ");
      col = selection(board);
      row = fillBoard(board, col, player1);
      drawBoard(board);
      System.out.println();

      // Check for win
      if(checkWin(board, player1, row, col))
      {
        System.out.print("Player 1 wins!");
        break;
      }

      // Player 2's turn
      System.out.println("Player 2's turn: ");
      col = selection(board);
      row = fillBoard(board, col, player2);
      drawBoard(board);
      System.out.println();
      
      // Check for win
      if(checkWin(board, player2, row, col))
      {
        System.out.print("Player 2 wins!");
        break;
      }
    }
  }

  // Method to print the board
  public static void drawBoard(String[][] board)
  {
    for(int i = 0; i < board.length; i++)
    {
      System.out.println(Arrays.toString(board[i]));
    }
  }

  // Method for player selection
  public static int selection(String[][] board)
  {
    Scanner num = new Scanner(System.in);
    int col = 0;

    while(true)
    {
      // Get user input for selection
      System.out.print("Choose a column (1 - 7): ");
      col = num.nextInt() - 1;

      // Check if selection is on the board
      if(col < 0 || col > 6)
      {
        System.out.println("Invalid selection.");
      }

      // Check if the column is filled
      else if(board[0][col] != "⚪")
      {
        System.out.println("Column has been filled. Choose another column.");
      }

      else
      {
        return col;
      }
    }
  }

  // Method to fill the board at the selected column
  public static int fillBoard(String[][] board, int col, String player)
  {
    int row = 0;
    for(int i = board.length-1; i >= 0; i--)
    {
      if(board[i][col] == "⚪")
      {
        board[i][col] = player;
        row = i;
        break;
      }
    }
    return row;
  }

  // Method to check for win
  public static boolean checkWin(String[][] board, String player, int row, int col)
  {
    // Variable to keep track of matches in a row
    int count = 0;
    
    // Horizontal check
    for(int i = 0; i < board[row].length; i++)
    {
      if(board[row][i] == player)
      {
        count++;
      }
      else
      {
        count = 0;
      }
      if(count == 4)
      {
        return true;
      }
    }
    count = 0;
    
    // Vertical check
    for(int i = 0; i < board.length; i++)
    {
      if(board[5-i][col] == player)
      {
        count++;
      }
      else
      {
        count = 0;
      }
      if(count == 4)
      {
        return true;
      }
    }
    count = 0;

    // Diagonal check (\)
    for(int i = row, j = col; i < board.length && j < board[row].length; i++, j++)
    {
      if(board[i][j] == player)
      {
        count++;
      }
      else
      {
        break;
      }
    }
    for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--)
    {
      if(board[i][j] == player)
      {
        count++;
      }
      else
      {
        break;
      }
    }
    if(count >= 4)
    {
      return true;
    }
    count = 0;

    // Diagonal check (/)
    for(int i = row, j = col; i >= 0 && j < board[row].length; i--, j++)
    {
      if(board[i][j] == player)
      {
        count++;
      }
      else
      {
        break;
      }
    }
    for(int i = row+1, j = col-1; i < board.length && j >= 0; i++, j--)
    {
      if(board[i][j] == player)
      {
        count++;
      }
      else
      {
        break;
      }
    }
    if(count >= 4)
    {
      return true;
    }

    return false;
  }
}
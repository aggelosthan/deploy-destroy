import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] blackArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] whiteArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] board = new int[16];
        String[] boardWord = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        deployment(whiteArray, blackArray, board, boardWord);
        winner(board, boardWord);
    }

    // A function that has plenty of prints, a welcome message.
    public static void gameIntro() {
        System.out.println("\n**************  Welcome to Deploy and Destroy   **************\n");
        System.out.println(ANSI_GREEN_BG + ANSI_BLACK + "DEPLOYMENT PHASE:" + ANSI_RESET);
        System.out.println("1. Every player ( Black & White ) has and army of 8 soldiers.");
        System.out.println("2. There are 16 rounds, in every round you deploy a soldier in the main board. ");
        System.out.println("3. Each player has 8 soldiers, [1-8], the less the weaker, the most the stronger. ");
        System.out.println("4. Every soldier has it's unique position in the board.\n");

        System.out.println(ANSI_RED_BG + ANSI_BLACK + "DESTRUCTION PHASE:" + ANSI_RESET);
        System.out.println("1. After the Deployment Phase, and after everything is set up, Destruction Phase is next.");
        System.out.println("2. One player at a time, choose to destroy an enemy soldier in the board.");
        System.out.println("3. To successfully destroy a soldier, your soldiers needs to be larger than the enemy soldier, so you have to place your soldiers as clever as possible.");
        System.out.println("4. Winner is the one who destroys 3 soldiers of the enemy team.\n");
        System.out.println(ANSI_WHITE_BG + ANSI_BLACK + "Let the game begin!" + ANSI_RESET);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
    }

    // A function that flips the coin with some game logic inside.
    public static String coinFlip() {
        Scanner scan = new Scanner(System.in);
        String userChoice;
        do {
            userChoice = scan.nextLine();

            if (userChoice.equals("flip") || userChoice.equals("f") || userChoice.equals("F")) {
                int coinFlip = (int) (Math.random() * 2);
                switch (coinFlip) {
                    case 0:
                        return "White";
                    case 1:
                        return "Black";
                    default:
                }
            } else if (userChoice.equals("no") || userChoice.equals("n")) {
                System.out.println("Next time. Bye!");
                System.exit(0);
            } else {
                System.out.print("Invalid command, try again: ");
            }

        } while (true);
    }

    // A more simple flip coin FUNCTION.
    public static String anotherCoin() {
        int coinFlip = (int) (Math.random() * 2);
        switch (coinFlip) {
            case 0:
                return "White";
            case 1:
                return "Black";
            default:
        }
        return "";
    }


    // A function that checks that the NUMBER that the player gave, is valid and after a cool message.
    public static int playerNumValidity(int[] playerBoard) {
        Scanner scan = new Scanner(System.in);
        int userInput;
        do {
            numberAvailabilty(playerBoard);
            System.out.print("\nPick a soldier number.");
            System.out.print("\nGive a number between [1-8]: ");
            userInput = scan.nextInt();
            if (userInput > 0 && userInput < 9) {
                if (playerBoard[userInput - 1] != 0) {
                    playerBoard[userInput - 1] = 0;
                    System.out.print("\n");
                    System.out.println((ANSI_GREEN_BG + ANSI_BLACK + "Soldier PICKED successfully." + ANSI_RESET));
                    return userInput;
                } else {
                    System.out.print("\n");
                    System.out.println((ANSI_YELLOW_BG + ANSI_BLACK + "Soldier not available, it is already placed." + ANSI_RESET));
                }
            } else {
                System.out.print("\n");
                System.out.println((ANSI_RED_BG + ANSI_BLACK + "Number out of limit." + ANSI_RESET));
            }
        } while (!zeroValue(playerBoard));
        return -1;
    }

    // A function that checks that the BOARD NUMBER that the player gave, is valid and after a cool message.
    public static int boardNumValidity(int[] board, String word, String color, String[] boardWord, int selectedSoldier) {
        Scanner scan = new Scanner(System.in);
        int userInput;
        while (!notZeroValue(board)) {
            displayNumBord(boardWord, board);
            System.out.println("\nChoose a position on the board to deploy your soldier. ");
            System.out.print("Give a number between [1-16]: ");
            userInput = scan.nextInt();

            if (userInput >= 1 && userInput <= 16) {
                if (board[userInput - 1] == 0) {
                    board[userInput - 1] = selectedSoldier;
                    boardWord[userInput - 1] = color + word + ANSI_RESET;
                    System.out.print("\n");
                    System.out.println(ANSI_GREEN_BG + ANSI_BLACK + "Soldier PLACED successfully on the board" + ANSI_RESET);
                    break;
                } else {
                    System.out.print("\n");
                    System.out.println(ANSI_YELLOW_BG + ANSI_BLACK + "Position on the board is not available" + ANSI_RESET);
                }
            } else {
                System.out.print("\n");
                System.out.println(ANSI_RED_BG + ANSI_BLACK + "Number out of limits." + ANSI_RESET);
            }
        }
        return -1;
    }

    // A function that prints the soldier availability.
    public static void numberAvailabilty(int[] board) {
        System.out.println("\nYour available soldiers are: ");
        System.out.print("[");
        for (int soldier : board) {
            System.out.print(" " + soldier);
        }
        System.out.print(" ]\n");
    }

    // A function that prints the main board, including the number with the color of the player.
    public static void displayNumBord(String[] wordBoard, int[] board) {
        System.out.println("\n");
        for (int i = 1; i <= wordBoard.length; i++) {
            System.out.print(" [" + board[i - 1] + " " + wordBoard[i - 1] + "] ");
        }
    }

    // A function that is being used in "playerNumValidity", this function checks if all the values in the array are 0.
    public static boolean zeroValue(int[] array) {
        for (int value : array) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    // A function that is being used in "boardNumValidity", this function checks if all the values in the array are not 0.
    public static boolean notZeroValue(int[] array) {
        for (int value : array) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    // A function used for the deployment phase of the game.
    public static void deployment(int[] whiteBoard, int[] blackBoard, int[] board, String[] boardWord) {
        gameIntro();
        System.out.print("Are you ready to play? If yes, type 'flip' or 'f' to flip the coin else type 'no' or 'n' to exit the game: ");
        String turn = coinFlip();
        int selectedSoldier;
        System.out.println("\n" + ANSI_BLACK_BG + ANSI_GREEN + "DEPLOYMENT PHASE:" + ANSI_RESET);
        while (!notZeroValue(board)) {
            if (turn.equals("White")) {
                System.out.println("\n");
                System.out.println(ANSI_WHITE_BG + ANSI_BLACK + "White you play." + ANSI_RESET);
                selectedSoldier = playerNumValidity(whiteBoard);
                boardNumValidity(board, "W", ANSI_WHITE_BG, boardWord, selectedSoldier);

                turn = "Black";
            } else {
                System.out.println("\n");
                System.out.println(ANSI_BLACK_BG + ANSI_WHITE + "Black you play." + ANSI_RESET);
                selectedSoldier = playerNumValidity(blackBoard);
                boardNumValidity(board, "B", ANSI_BLACK_BG, boardWord, selectedSoldier);
                turn = "White";
            }
        }
    }


    // A function that has the game logic and winner.
    public static void winner(int[] numBoard, String[] wordBoard) {
        System.out.println("\n");
        System.out.println(ANSI_BLACK_BG + ANSI_RED + "DESTRUCTION PHASE:" + ANSI_RESET);
        Scanner scan = new Scanner(System.in);
        int userNum;
        String blackColor = "\u001B[40mB\u001B[0m";
        String whiteColor = "\u001B[47mW\u001B[0m";
        int whiteScore = 0;
        int blackScore = 0;
        boolean gameEnds = false;
        String turn = anotherCoin();
        String winner = "None";

        while (!gameEnds) {
            if (whiteScore < 3 && blackScore < 3) {
                if (turn.equals("White")) {
                    System.out.println("\n");
                    System.out.println(ANSI_WHITE_BG + ANSI_BLACK + "White you play." + ANSI_RESET);
                    displayNumBord(wordBoard, numBoard);
                    System.out.print("\nChoose a black position to destroy: ");
                    userNum = scan.nextInt();
                    boolean whiteScoreReturner = destruction(userNum, numBoard, wordBoard, blackColor);
                    if (whiteScoreReturner) {
                        whiteScore++;
                        winner = ANSI_WHITE_BG + ANSI_BLACK + "White" + ANSI_RESET;
                    }
                    turn = "Black";
                } else {
                    System.out.println("\n");
                    System.out.println(ANSI_BLACK_BG + ANSI_WHITE + "Black you play." + ANSI_RESET);
                    displayNumBord(wordBoard, numBoard);
                    System.out.print("\nChoose a white position to destroy: ");
                    userNum = scan.nextInt();
                    boolean blackScoreReturner = destruction(userNum, numBoard, wordBoard, whiteColor);
                    if (blackScoreReturner) {
                        blackScore++;
                        winner = ANSI_BLACK_BG + ANSI_WHITE + "Black" + ANSI_RESET;
                    }
                    turn = "White";
                }
            } else {
                gameEnds = true;
            }
        }
        System.out.println("\nWinner is " + winner);
    }


    // A function used for the destruction phase of the game.
    public static boolean destruction(int userNum, int[] numBoard, String[] wordBoard, String color) {
        if (userNum < 1 || userNum > numBoard.length) {
            System.out.println(ANSI_RED_BG + ANSI_BLACK + "Invalid position." + ANSI_RESET);
            return false;
        }

        int prevNum = (userNum > 1) ? numBoard[userNum - 2] : 0;
        int nextIndex = userNum - 1;

        if (nextIndex < numBoard.length) {
            int nextNum = numBoard[nextIndex];
            int sum = prevNum + nextNum;

            if (numBoard[userNum - 1] < sum && wordBoard[userNum - 1].equals(color)) {
                System.out.println(ANSI_GREEN_BG + ANSI_BLACK + "Successfully destroyed" + ANSI_RESET);
                numBoard[userNum - 1] = 0;
                return true;
            } else {
                System.out.println(ANSI_YELLOW_BG + ANSI_BLACK + "It cannot be destroyed." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED_BG + ANSI_BLACK + "Invalid position." + ANSI_RESET);
        }
        return false;
    }


    // Declaring ANSI COLORS.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK_BG = "\u001B[40m";
    public static final String ANSI_WHITE_BG = "\u001B[47m";
    public static final String ANSI_YELLOW_BG = "\u001B[43m";
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
}
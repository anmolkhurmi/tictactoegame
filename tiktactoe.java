import java.util.*;
    
public class tiktactoe {
    public static void main(String[] args) {
        System.out.println("Welcome to tictactoe game!!!");   
        System.out.println("write your position number (1-9) :  ");
        char[][] boardesign = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
        printboard(boardesign);
        boolean gamecondition = true;
        
        List<Integer> one2nine = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> xmoves = new ArrayList<>();
        List<Integer> omoves = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while (gamecondition) {
            int position = scan.nextInt();
            if(one2nine.contains(position)){
                piecemove(boardesign, position, 'x');
                xmoves.add(position);
                one2nine.remove(Integer.valueOf(position));
                
                if (!ongoing(xmoves, omoves, one2nine)) {
                    gamecondition = false;
                    System.out.println("Game Over");
                    break;
                }
                
                if(one2nine.size() > 0) {
                    int omove = randomfromavailable(one2nine, omoves);
                    piecemove(boardesign, omove, 'O');
                    omoves.add(omove);
                }

                if (!ongoing(xmoves, omoves, one2nine)) {
                    gamecondition = false;
                    System.out.println("Game Over");
                }
            } else {
                System.out.println("Position already taken or out of (1-9)");
            }
            printboard(boardesign);
        }
    }
    /////////////////

    
    public static int randomfromavailable(List<Integer> one2nine, List<Integer> omoves){
        Random random = new Random();
        
        int randomindex = random.nextInt(one2nine.size());
        int randomdigit = one2nine.get(randomindex);
        
        one2nine.remove(Integer.valueOf(randomdigit));
        omoves.add(randomdigit);
        return randomdigit;
        
    }
    public static boolean checkwinner(List<Integer> moves) {
        List<List<Integer>> winningCombinations = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9),
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(3, 6, 9),
            Arrays.asList(1, 5, 9),
            Arrays.asList(3, 5, 7)
        );

        for (List<Integer> combo : winningCombinations) {
            if (moves.containsAll(combo)) {
                return true;
            }
        }
        return false;
    }
    public static boolean ongoing(List<Integer> xmoves, List<Integer> omoves, List<Integer> one2nine) {
        if (checkwinner(xmoves)) {
            System.out.println("Player X wins!");
            return false;
        } else if (checkwinner(omoves)) {
            System.out.println("Player O wins!");
            return false;
        } else if (one2nine.isEmpty()) {
            System.out.println("It's a tie!");
            return false;
        }
        return true;
    }
    public static void printboard(char[][] boardesign){
        for(char[] row : boardesign){
            for(char column : row ){
                System.out.print(column);
            }
            System.out.println();
        }
    }
    public static void piecemove (char[][] boardesign , int position , char user){
        switch (position) {
            case 1:
                boardesign[0][0] = user;
                break;
            case 2:
                boardesign[0][2] = user;
                break;
            case 3:
                boardesign[0][4] = user;
                break;
            case 4:
                boardesign[2][0] = user;
                break;
            case 5:
                boardesign[2][2] = user;
                break;
            case 6:
                boardesign[2][4] = user;
                break;
            case 7:
                boardesign[4][0] = user;
                break;
            case 8:
                boardesign[4][2] = user;
                break;
            case 9:
                boardesign[4][4] = user;
                break;
        }
        printboard(boardesign);
    }
}
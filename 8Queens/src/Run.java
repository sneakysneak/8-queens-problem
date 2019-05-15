public class Run {
    public static void main(String[] args) {
        EightQueenProblem eightQueenProblem = new EightQueenProblem();

        eightQueenProblem.setBoard();
        eightQueenProblem.firstQueen();
        System.out.println("\nUser input first queen only: ");
        eightQueenProblem.printBoard();
        eightQueenProblem.solveQueens();
    }

//        public static void main(String args[]){
//            String number = "a1h8";
//            int []arr = new int[number.length()];
//            System.out.println("THe asscii value of each character is: ");
//            for(int i=0;i<arr.length;i++){
//                arr[i] = number.charAt(i); // assign the integer value of character i.e ascii
//                System.out.print(" "+arr[i]);
//            }
//        }
}

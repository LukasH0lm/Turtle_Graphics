public class Turtle {

    int[] position = {0,0};
    enum direction {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }

    direction currentDirection;
    

    char[][] board = new char[20][20];
    boolean isPenDown = false;
    boolean isExiting = false;
    boolean isShowingStep = false;

    public Turtle(){
        this.position[0] = 0;
        this.position[1] = 0;
        this.currentDirection = direction.RIGHT;

    }

    public void run(int[] instructions){

        step(instructions);
        board[position[1]][position[0]] = 'o' ;


    }


    public void move(int[] spaces){
        if (spaces[0] != 0){
            int spacesLeft = spaces[0];

            System.out.println("beginning movement");
            System.out.println("moves left: " + spacesLeft);
            System.out.println("direction : " + this.currentDirection);

            try{
                while(spacesLeft > 0){
                    draw();
                    if (currentDirection == direction.UP){
                        position[1] -= 1;
                    }

                    if (currentDirection == direction.RIGHT){
                        position[0] += 1;
                    }

                    if (currentDirection == direction.DOWN){
                        position[1] += 1;
                    }

                    if (currentDirection == direction.LEFT){
                        position[0] -= 1;
                    }

                    draw();
                    spacesLeft--;
                    System.out.println("current position: x: " + position[0] +" y: " + position[1]);
                    System.out.println("moves left after move: " + spacesLeft);



                    if (isShowingStep){

                        draw();
                    }
            }
        }   catch (IndexOutOfBoundsException e){

            System.out.println("ERROR: move amount too large");
            System.out.println("Reseting Turtle position to (0,0)");


            }
        }
    }

    public void draw(){

        if (isPenDown) {
            board[position[1]][position[0]] = 'x';
        }

    }

    public void step(int[] instructions) {

        if(!isExiting){

            for (int i = 0; i < instructions.length; i++){
                System.out.println("step: " + i);
                System.out.println("current instruction: " + instructions[i]);
                System.out.println("instruction lenght: " + instructions.length);
                if (instructions[i] == 5 && i + 1 < instructions.length){
                    doAction(instructions[i],instructions[i+1]);
                    i++;
                }else {
                    doAction(instructions[i]);
                }
            }
        }
    }


    public void displayBoard(){
        for (char[] column:
                board) {
            System.out.println();
            for (char tile:
                    column) {
                System.out.print(tile);

            }

        }
    }


    public void doAction(int instruction, int... argument){
        switch (instruction){
            case 1 -> isPenDown = false;
            case 2 -> {isPenDown = true; draw();}
            case 3 -> turnRight();
            case 4 -> turnLeft();
            case 5 -> move(argument);
            case 6 -> displayBoard();
            case 9 -> isExiting = true;
        }
        if (isPenDown){
            draw();
        }
    }





    public void turnLeft(){

        if (currentDirection == direction.UP){
            currentDirection = direction.LEFT;
        }
        else if (currentDirection == direction.RIGHT){
            currentDirection = direction.UP;
        }
        else if (currentDirection == direction.DOWN){
            currentDirection = direction.RIGHT;
        }
        else if (currentDirection == direction.LEFT){
            currentDirection = direction.DOWN;
        }

    }

    public void turnRight(){

        if (currentDirection == direction.UP){
            currentDirection = direction.RIGHT;
        }
        else if (currentDirection == direction.RIGHT){
            currentDirection = direction.DOWN;
        }
        else if (currentDirection == direction.DOWN){
            currentDirection = direction.LEFT;
        }
        else if (currentDirection == direction.LEFT){
            currentDirection = direction.UP;
        }

    }



}

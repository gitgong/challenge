package dev.blake.portfolio.logic;

/**
    instructions in form:
     1a) starting location x,y direction = "1 2 N"
     1b) movement left/right/move =  "LMLMLMLMM";
    2a) starting location x,y direction = "3 3 E"
    2b) movement left/right/move =  "MMRMMRMRRM";
 
    test includes blocked move over boundary)
    3a) starting location x,y direction = "3 3 E"
    3b) movement left/right/move =  "MMRMMRMRRMM"
 */
public class RoverController {


    int x;
    int y;
    char direction;

    //maximum size of grid area in square
    int maxX = 5;
    int maxY = 5;


    public static void main (String args[]){

        RoverController rover1 = new RoverController();
        RoverController rover2 = new RoverController();
        RoverController rover3 = new RoverController();

        String instructionsRover1Line1PosDir = "1 2 N";
        String instructionsRover1Line2Mov = "LMLMLMLMM";

        String instructionsRover2Line1PosDir = "3 3 E";
        String instructionsRover2Line2Mov = "MMRMMRMRRM";

        //moves off board to right (x)
        String instructionsRover3Line1PosDir = "3 3 E";
        String instructionsRover3Line2Mov = "MMRMMRMRRMLMRM";

        //setup rover1 position
        rover1.setStartLocation(instructionsRover1Line1PosDir);
        System.out.println("rover 1 starting pos: " + rover1.x + " " + rover1.y + " " + rover1.direction);
        //run command
        rover1.processInstructions(instructionsRover1Line2Mov);
        System.out.println("Rover 1 final: " + rover1.x + " " + rover1.y + " " + rover1.direction);

        //setup rover2 position
        rover2.setStartLocation(instructionsRover2Line1PosDir);
        System.out.println("rover 2 starting pos: " + rover2.x + " " + rover2.y + " " + rover2.direction);
        //run command
        rover2.processInstructions(instructionsRover2Line2Mov);
        System.out.println("rover 2 final pos: " + rover2.x + " " + rover2.y + " " + rover2.direction);

        //setup rover3 position
        rover3.setStartLocation(instructionsRover3Line1PosDir);
        System.out.println("rover 3 starting pos: " + rover3.x + " " + rover3.y + " " + rover3.direction);
        //run command
        rover3.processInstructions(instructionsRover3Line2Mov);
        System.out.println("rover 3 final pos: " + rover3.x + " " + rover3.y + " " + rover3.direction);

        //test
        boolean pass = false;
        if(rover1.x==1 && rover1.y==3 && rover1.direction=='N'){
            pass=true;
        }
        System.out.println("Rover 1 Unit test pass=" + pass);
        pass = false;
        if(rover2.x==5 && rover2.y==1 && rover2.direction=='E'){
            pass=true;
        }
        System.out.println("Rover 2 Unit test pass=" + pass);
        pass = false;
        if(rover3.x==5 && rover3.y==2 && rover3.direction=='E'){
            pass=true;
        }
        System.out.println("Rover 3 Unit test pass=" + pass);

    }



    void setStartLocation(String inst){
        x = Character.getNumericValue(inst.charAt(0));
        y = Character.getNumericValue(inst.charAt(2));
        direction = inst.charAt(4);

    }


    void processInstructions(String inst){
        for(int i = 0; i < inst.length(); i++){
            switch (inst.charAt(i)) {
                case 'M':
                    //move forward one gridpoint
                        moveForward();
                        break;
                case 'R':
                    //spin right
                    setDirection(0);

                    break;
                case 'L':
                    //spin left
                    setDirection(1);
                    break;
            }
        }
    }

    void setDirection(int dir){
        if(dir==0){
            //check direction and set
            switch (direction) {
                case 'N':
                    direction = 'E';
                    break;
                case 'E':
                    direction = 'S';
                    break;
                case 'S':
                    direction = 'W';
                    break;
                case 'W':
                    direction = 'N';
                    break;
            }
        }
        else{
            //check direction and set
            switch (direction) {
                case 'N':
                    direction = 'W';
                    break;
                case 'E':
                    direction = 'N';
                    break;
                case 'S':
                    direction = 'E';
                    break;
                case 'W':
                    direction = 'S';
                    break;
            }
        }
    }

    void moveForward  () {
        switch (direction) {
            case 'N':
                if(y+1>maxY){
                    System.out.println("Maximum gridsize is : " + maxY + " height, cannot move to "+(y+1));
                    break;
                }
                y++;
                break;
            case 'E':
                if(x+1>maxX){
                    System.out.println("Maximum gridsize is : " + maxX + " wide, cannot move to "+(x+1));
                    break;
                }
                x++;
                break;
            case 'S':
                if(y-1<0){
                    System.out.println("Error: Y axis cannot move below 0");
                    break;
                }
                y--;
                break;
            case 'W':
                if(x-1<0){
                    System.out.println("Error: X axis cannot move below 0");
                    break;
                }
                x--;
                break;
        }
    }




}


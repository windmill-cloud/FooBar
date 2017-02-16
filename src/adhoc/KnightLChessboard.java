package adhoc;

/**
 * Created by xuanwang on 2/10/17.
 */
public class KnightLChessboard {
    /*
    Hey guys, here's how I did this: The idea is to start from the (n-1,n-1) piece and going back to (0,0). The algorithm looks like this:
            for (i,j) in range(1,n) * range(1,n) #moving possibilities for the knight
while we didn't reach (0,0) or (no new squares are reachables):

    look at squares that we could reach from the last discovered ones

    substract those that we already discovered in previous steps (which means less moves)

    store these new squares in a set, named new set

    if (0,0) is in the new set, then print the number of iterations over the while loop

    else print(-1)*/



    public static void printMatrix(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){

            }
        }
    }

    public static void main(String[] args){

    }
}

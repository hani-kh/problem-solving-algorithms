package examples.NQueens;

import core.State;

public class NQueensBoard implements State {

    int [] board;
    int size;
    public NQueensBoard(int[] a) {
        board=new int[a.length];
        for(int i=0;i<board.length;i++)
            this.board[i]=a[i];
        this.size=board.length;

    }

    @Override
    public boolean equals(Object s) {
        NQueensBoard casted = (NQueensBoard)s;
            //define equal condition
            for(int i=0;i<size;i++)
                if(this.board[i]!=casted.board[i])
                    return false;
            return true;
    }

    @Override
    public boolean isGoal() {
        return getCoupleAttacks() == 0;
    }

    public String toString() {
        String toPrint="";
        for(int j=0;j<size;j++)
        {
            for(int i=0;i<size;i++)
            {
                if(this.board[i]==j)
                    toPrint+=" Q ";
                else
                    toPrint+=" X ";

            }
            toPrint+="\n";
        }
        return toPrint;
    }

    public int getSize() {
        return size;
    }
    public int getCoupleAttacks()
    {
        int numberOfAttacks=0;
        for(int i=0;i<size-1;i++)
        {
            for(int j=i+1;j<size;j++)
            {
                if(board[i]==board[j]) numberOfAttacks++;//the Queen in the same row
                else
                if(Math.abs(i-j)==Math.abs(board[i]-board[j]))//diagonal attack
                    numberOfAttacks++;
            }
        }
        //  System.out.println("Number of attacks"+numberOfAttacks);
        return numberOfAttacks;
    }

}

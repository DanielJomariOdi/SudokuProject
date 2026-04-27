package Sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Sudokuset
{


    private Random r = new Random();
    
    private final ArrayList<ArrayList<Integer>> Puzzleset;
    
    public Sudokuset()
    {
        Puzzleset = setpiece();
    }
    
    public void printset()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                System.out.print(" " + Puzzleset.get(i).get(j));
            }
            System.out.println("");
        }
    }
    
    private ArrayList<ArrayList<Integer>>setpiece()
    {
        ArrayList<ArrayList<Integer>> PuzzleSet = GenerateSet();
            while(checkpuzzle(PuzzleSet))
                PuzzleSet = GenerateSet();
            
            return PuzzleSet;
    }

    public ArrayList<ArrayList<Integer>> GenerateSet()
    {
        ArrayList<ArrayList<Integer>> Rows = new ArrayList<>();
        ArrayList<ArrayList<Integer>> Columns = new ArrayList<>();
        ArrayList<ArrayList<Integer>> Boxes = new ArrayList<>();
        int hardreset = 0;
        for (int i = 0; i < 9; i++)
        {
            Rows.add(new ArrayList<>());
            Columns.add(new ArrayList<>());
            Boxes.add(new ArrayList<>());
            
            for (int j = 1; j < 10; j++)
                Boxes.get(i).add(j);
        }
        
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                hardreset++;
                if(hardreset==200)
                    return null;
                
                int temp, whichbox, rephrase;
                whichbox = ((i/3)*3)+(j/3);
                
                try
                {
                    temp = r.nextInt(0, Boxes.get(whichbox).size());
                    rephrase = 0;
                    while(Rows.get(i).contains(Boxes.get(whichbox).get(temp))  ||   Columns.get(j).contains(Boxes.get(whichbox).get(temp)))
                    {
                        temp = r.nextInt(0, Boxes.get(whichbox).size());
                        if(rephrase==20)
                        {
                            for (int k = j-1; k > -1; k--)
                            {
                                Boxes.get(((i/3)*3)+((k)/3)).add(Rows.get(i).get(k));
                                Rows.get(i).removeLast();
                                Columns.get(k).removeLast();
                            }
                            j=-1;
                            break;
                        }
                        rephrase++;
                    }
                }
                catch(Exception e)
                {
                    return null;
                }
                
                if(rephrase!=20)
                {
                    Rows.get(i).add(Boxes.get(whichbox).get(temp));
                    Columns.get(j).add(Boxes.get(whichbox).get(temp));
                    Boxes.get(whichbox).remove(temp);
                }
                
            }
        }
        return Rows;
    }
    
    public boolean checkpuzzle(ArrayList<ArrayList<Integer>> PuzzleSet)
    {
        if(PuzzleSet==null)
            return true;
        
        for (int i = 0; i < PuzzleSet.size(); i++)
        {
            if(PuzzleSet.get(i).size()!=9)
                return true;
        }
        return false;
    }
    
    public String setname(ArrayList<ArrayList<Integer>> PuzzleSet)
    {
        int temp = 0;
        for (int i = 0; i < PuzzleSet.size(); i++)
        {
            for (int j = 0; j < PuzzleSet.get(i).size(); j++)
            {
                if(j%2==1)
                    temp += PuzzleSet.get(i).get(j);
                else
                    temp = temp*PuzzleSet.get(i).get(j);
            }
        }
        
        String Tname = String.valueOf(Math.abs(temp));
        String name = "";
        
        for (int i = 0; i <Tname.length()-1; i++)
        {
            if((Integer.parseInt(Tname.substring(i, i+2))>47 && Integer.parseInt(Tname.substring(i, i+2))<58) || (Integer.parseInt(Tname.substring(i, i+2))>64 && Integer.parseInt(Tname.substring(i, i+2))<91))
                name += (char)Integer.parseInt(Tname.substring(i, i+2));
        }
        
        if(name.length()==0)
            return Tname;
        return name;
    }
        
}

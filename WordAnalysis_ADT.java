import java.io.*;
import java.util.Scanner;

public class WordAnalysis_ADT {
    int n,m,k;
    LinkedList<WordInformation> [] arrayOfDifferentLengths ;
    WordInformation [] sortedArray;
    
    public WordAnalysis_ADT(String f )
    {
       k = Calcuate_K(f);
       arrayOfDifferentLengths =  new LinkedList [k+1];
       sortedArray = new WordInformation [200];
       readFileAndAnalyse(f);
    }

    private int Calcuate_K(String f)
    {
        int max = 0;
        String word;
        try{
            Scanner input = new Scanner(new File(f));
            while (input.hasNext()){ 
                word = input.next();
                word=word.replaceAll("[, . ; ? ! / \" ]","");
                if (max < word.trim().length())
                    max = word.trim().length();
            }
            input.close();
        }catch (IOException ex){
            System.out.println("error in calcuating the longest word in the file");
            }
            return max;
    }

    private void readFileAndAnalyse (String f)
    {
            try {

                for ( int i = 0 ; i< arrayOfDifferentLengths.length ; i ++)
                    arrayOfDifferentLengths[i] = new LinkedList<WordInformation> ();
                
                Scanner input = new Scanner(new File(f));

                int lineNo = 0;
                n =0;
                m =0;
                String line = input.nextLine();
                String AllWordInLine[] = null;
            
                
                while (!(line==null)) 
                {
                    lineNo ++;
                    int position =1;
                    AllWordInLine = line.split("\\s");  
                    for (int i = 0; i < AllWordInLine.length; i++) 
                    {
                        String word=AllWordInLine[i].replaceAll("[, . ; ? ! / \"]","");
                        word = word.trim();
                         if (word.equalsIgnoreCase(""))
                             continue;
                         
                            n++;
                            boolean FoundWord = false;
                            //Start reading
                         
                            if (arrayOfDifferentLengths[word.length()].empty())
                                arrayOfDifferentLengths[word.length()].insert(new WordInformation (word , lineNo, position));
                            else
                            {
                                arrayOfDifferentLengths[word.length()].findFirst();
                                while (! arrayOfDifferentLengths[word.length()].last() && ! FoundWord)
                                {
                                    if (arrayOfDifferentLengths[word.length()].retrieve().word.equalsIgnoreCase(word))
                                    {
                                        arrayOfDifferentLengths[word.length()].retrieve().Add(lineNo, position);
                                        FoundWord = true;
                                    }
                                    else
                                        arrayOfDifferentLengths[word.length()].findNext();
                                }
                                if ( ! FoundWord)//check the last node
                                {
                                    if (arrayOfDifferentLengths[word.length()].retrieve().word.equalsIgnoreCase(word))
                                    {
                                        arrayOfDifferentLengths[word.length()].retrieve().Add(lineNo, position);
                                        FoundWord = true;
                                    }
                                }
                                
                                if (!FoundWord )
                                    arrayOfDifferentLengths[word.length()].insert(new WordInformation (word , lineNo, position));
                                    
                                
                                    
                            }
                            //sorted Array
                            if (!FoundWord )
                            {
                                sortedArray[m] = new WordInformation (word , lineNo, position);
                                m++;
                            }
                            else
                            {
                                for ( int j = 0 ; j < m ; j++)
                                    if (sortedArray[j] != null && sortedArray[j].word.equalsIgnoreCase(word))
                                        sortedArray[j].size = arrayOfDifferentLengths[word.length()].retrieve().size  ;
                            }
                        
                         position ++ ;
                    }
                    if(input.hasNextLine())
                        line = input.nextLine();
                    else
                        break;        
                }
                input.close();
                mergesort(0, m-1 );
                
                } catch (IOException ex) {
            System.out.println("error in read method");
        }
        
    }
    
    //*************************************************************************************************
    // operation 1
    
    public int documentLength  ()
    { 
        return n;
    }
    
    //*************************************************************************************************
    // operation 2
    public int uniqueWords  ()
    {
        return m;
    }
    
    //*************************************************************************************************
    // operation 3
    public int totalWord (String w)
    {
        int count = 0 ;
        if ( arrayOfDifferentLengths[w.length()].getsize() > 0)
        {   
            arrayOfDifferentLengths[w.length()].findFirst();
            while (! arrayOfDifferentLengths[w.length()].last())
            {
                if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
                    count = arrayOfDifferentLengths[w.length()].retrieve().size;
                arrayOfDifferentLengths[w.length()].findNext();
            }
            if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
                count = arrayOfDifferentLengths[w.length()].retrieve().size;
        }
        return count;
        
    }
    
    //*************************************************************************************************
    // operation 4
    public int totalWordsForLength (int l)
    {
        return arrayOfDifferentLengths[l].getsize();
    }
    
    //*************************************************************************************************
    // operation 5
     public void displayUniqueWords ()
    {
        for ( int i = 0 ; i <m ;  i++)
            System.out.println(" ( " + sortedArray[i].word + " , " + sortedArray[i].size + " ), ");
    }    
    
    //*************************************************************************************************
    // operation 6
    public  LinkedList<WordOccurrence> occurrences (String w){
        
        LinkedList<WordOccurrence> tmp = null;
        
        if ( arrayOfDifferentLengths[w.length()].getsize() > 0){   
            arrayOfDifferentLengths[w.length()].findFirst();
            while (! arrayOfDifferentLengths[w.length()].last()){
                if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
                    tmp = arrayOfDifferentLengths[w.length()].retrieve().occList;
                arrayOfDifferentLengths[w.length()].findNext();
            }
            if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
                tmp = arrayOfDifferentLengths[w.length()].retrieve().occList;
        }
        
        return tmp;
    }

    //*************************************************************************************************
    // operation 7
   public boolean checkAdjacent  (String word1, String word2){
        //word 1 and word 2 are the same 
        if (word1.equalsIgnoreCase(word2)){
            LinkedList<WordOccurrence> wordOccList = occurrences(word1);     
            if (wordOccList != null ){
                if (wordOccList.getsize() > 1){
                    wordOccList.findFirst();
                    WordOccurrence pos1 = wordOccList.retrieve();
                    for ( int i = 1; i < wordOccList.getsize() ; i++){
                        wordOccList.findNext();
                        WordOccurrence pos2 = wordOccList.retrieve();
                        if ( pos1.lineNo == pos2.lineNo && ((pos2.position - pos1.position) ==1 || (pos2.position - pos1.position) ==-1) )
                            return true;
                        pos1=pos2;   
                        }
                   }
               }
                System.out.print("sorry these two word isn't in the file ");
                return false;
        }
       
       
        // the words aren't the same 
           if (( arrayOfDifferentLengths[word1.length()].getsize() ==0) || (arrayOfDifferentLengths[word2.length()].getsize() == 0))
            return false;

        LinkedList<WordOccurrence> W1OccList = occurrences (word1);
        LinkedList<WordOccurrence> W2OccList = occurrences (word2);
        
        if ( W1OccList != null && W2OccList != null )
        {
            W1OccList.findFirst();
            W2OccList.findFirst();
            
            while ( ! W1OccList.last() && !W2OccList.last())
            {
                    int line1 = W1OccList.retrieve().lineNo;
                    int line2 = W2OccList.retrieve().lineNo;
                    if ( line1 == line2 )
                    {
                        int w_no1 = W1OccList.retrieve().position;
                        int w_no2 =W2OccList.retrieve().position;
                        if ((w_no2 - w_no1) == 1 || (w_no2 - w_no1)==-1)
                            return true;
                        else if (  (w_no2 - w_no1) > 1)
                            W1OccList.findNext();
                        else
                            W2OccList.findNext();
                    }
                    else if ( line1 > line2)
                    {
                        W2OccList.findNext();
                    }
                    else
                    {
                        W1OccList.findNext();
                     }
            }
            
            while ( W1OccList.last() && !W2OccList.last())
            {
                    int line1 = W1OccList.retrieve().lineNo;
                    int line2 =W2OccList.retrieve().lineNo;
                    if ( line1 == line2 )
                    {
                        int w_no1 = W1OccList.retrieve().position;
                        int w_no2 =W2OccList.retrieve().position;
                        if ((w_no2 - w_no1) == 1 || (w_no2 - w_no1) == -1)
                            return true;
                    }
                    W2OccList.findNext();
            }
                    
            while ( !W1OccList.last() && W2OccList.last())
            {
                    int line1 = W1OccList.retrieve().lineNo;
                    int line2 =W2OccList.retrieve().lineNo;
                    if ( line1 == line2 )
                    {
                        int w_no1 = W1OccList.retrieve().position;
                        int w_no2 =W2OccList.retrieve().position;
                        if ((w_no2 - w_no1) == 1 || (w_no2 - w_no1) == -1)
                            return true;
                    }
                    W1OccList.findNext();
            }

            if ( W1OccList.last() && W2OccList.last())
            {
                int line1 = W1OccList.retrieve().lineNo;
                int line2 =W2OccList.retrieve().lineNo;
                if ( line1 == line2 )
                {
                    int w_no1 = W1OccList.retrieve().position;
                    int w_no2 =W2OccList.retrieve().position;
                    if ((w_no2 - w_no1) == 1 || (w_no2 - w_no1) == -1)
                        return true;
                }
            }
        }   
        return false;
     
    }    

   
   public void mergesort ( int First , int Last ) 
    {
        if ( First >= Last )
            return;
        int middle = ( First + Last ) / 2;
        mergesort (First , middle ) ; 
        mergesort (middle + 1 , Last ) ;   
        merge (First , middle , Last ) ;            
    }

    private void merge ( int First , int middle , int Last ) 
    {
        WordInformation [] Array = new WordInformation [ Last - First + 1];
        int i = First , j = middle + 1 , k = 0;
    
        while ( i <= middle && j <= Last )
        {
            if ( sortedArray[ i ].size >= sortedArray [ j ].size)
                Array [ k ++] = sortedArray[ i ++];
            else
                Array [ k ++] = sortedArray[ j ++];
        }
        
        if ( i > middle )
            while ( j <= Last )
                Array [ k ++] = sortedArray[ j ++];
        else
            while ( i <= middle )
                Array [ k ++] = sortedArray[ i ++];
        
        for ( k = 0; k < Array . length ; k ++)
            sortedArray[ k + First ] = Array [ k ];
    }

}
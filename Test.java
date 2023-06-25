import java.util.*;
public class Test {
    
    public static void main(String[] args) {
        Scanner key = new Scanner (System.in);
        System.out.println("Enter the file name:");
        String FileName = key.next();
        
        WordAnalysis_ADT  ADT = new WordAnalysis_ADT(FileName.concat(".txt"));
        //1)
        System.out.println("The output of operation (1) would be " + ADT.documentLength());
        
        //2)
        System.out.println("The output of operation (2) would be "+ ADT.uniqueWords());
        
        //3)
        System.out.println("Enter the word you want to check it's occurance:");
        String Word = key.next();
        System.out.println("The output of operation (3) for the word \'" +Word+ "\' would be " + ADT.totalWord(Word));
        
        //4)
        System.out.println("Enter the Length you want to check:");
        int Length = key.nextInt();
        System.out.println("The output of operation (4) for word length "+Length+" would be " + ADT.totalWordsForLength(Length));

        //5)        
        System.out.println("The output of operation (5) would be ");
        ADT.displayUniqueWords();
        
        //6)
        System.out.println("Enter the word you want to see it's location in the file");
        Word = key.next();
        System.out.print("The output of operation (6) for the word "+Word+" would be " );
        LinkedList <WordOccurrence> WordLocList = ADT.occurrences(Word);
        if (WordLocList != null)
        {
            WordLocList.findFirst();
            while ( ! WordLocList.last())
            {
                System.out.print(WordLocList.retrieve().toString() + "  ");
                WordLocList.findNext();
            }
            System.out.println(WordLocList.retrieve().toString() + "  ");
        }
        else 
            System.out.println("Sorry the word isn't in the file");
        
        
        //7)
        System.out.println("Enter two word to check it's adjcent ");
        String Word1 = key.next();
        String Word2 =key.next();
        System.out.println("The output of operation (7) for the two words \'" + Word1+"\' and \'" +Word2+ "\' would be " + ADT.checkAdjacent(Word1, Word2));        

    }
    
}
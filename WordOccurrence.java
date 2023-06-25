
public class WordOccurrence {
    public int lineNo;
    public int position;

    public WordOccurrence() {
        this.lineNo = 0;
        this.position = 0;
    }

    public WordOccurrence(int lineNo, int position) {
        this.lineNo = lineNo;
        this.position = position;
    }
    @Override
    public String toString() {
            return " (" + lineNo + " ," + position + ") ";
    }

}
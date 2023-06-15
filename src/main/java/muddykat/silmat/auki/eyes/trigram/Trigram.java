package muddykat.silmat.auki.eyes.trigram;

public class Trigram {

    private char a, b, c;

    public Trigram(int a, int b, int c){
        this.a = (char) a;
        this.b = (char) b;
        this.c = (char) c;
    }

    public Trigram(char a, char b, char c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Trigram(String trigram) {
        this(trigram.charAt(0),trigram.charAt(1),trigram.charAt(2));
    }

    public String getTrigramValue(TrigramReadMode mode){
        return mode.calculateValue(a,b,c);
    }

    public String getTrigramString(TrigramReadMode mode){
        return mode.printData(a,b,c);
    }
}

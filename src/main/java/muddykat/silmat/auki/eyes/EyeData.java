package muddykat.silmat.auki.eyes;

import muddykat.silmat.auki.ApplicationControl;
import muddykat.silmat.auki.eyes.trigram.Trigram;
import muddykat.silmat.auki.eyes.trigram.TrigramReadMode;
import muddykat.silmat.auki.scenes.TrigramView;

import java.util.ArrayList;

public class EyeData {

    private String message;
    private ArrayList<ArrayList<Character>> eyeLineData = new ArrayList<>();
    private ArrayList<ArrayList<Trigram>> trigramLineData = new ArrayList<>();


    public EyeData(String name, String... eyeLines){
        this.message = name;
        for (String line : eyeLines) {
            ArrayList<Character> characters = new ArrayList<>();
            for (Character digit : line.toCharArray()) {
                characters.add(digit);
            }
            eyeLineData.add(characters);
        }
        calculateTrigramsFromRaw();
    }

    private void calculateTrigramsFromRaw(){
        ArrayList<String> rawTrigramData = new ArrayList<>();
        for(int i = 0; i < eyeLineData.size(); i+=2){
            ArrayList<Character> lineA = eyeLineData.get(i);
            ArrayList<Character> lineB = eyeLineData.get(i + 1);
            // Print characters from lineA and lineB in pairs
            int lineAIndex = 0;
            int lineBIndex = 0;
            StringBuilder newData = new StringBuilder();
            while (lineAIndex < lineA.size() || lineBIndex < lineB.size()) {
                if (lineAIndex < lineA.size()) {
                    newData.append(lineA.get(lineAIndex));
                    lineAIndex++;
                }

                if (lineBIndex < lineB.size()) {
                    newData.append(lineB.get(lineBIndex));
                    lineBIndex++;
                }
            }
            String rawTrigramLineData = newData.toString().replaceAll("(.{3})", "$1,").replaceAll(",$", "\n");
            rawTrigramData.add(rawTrigramLineData);
        }

        for(String trigramLine : rawTrigramData){
            String[] trigrams = trigramLine.replaceAll("\n", "").split(",");
            ArrayList<Trigram> rawLineData = new ArrayList<>();
            for (int i = 0; i < trigrams.length; i++) {
                Trigram data = new Trigram(i % 2 == 0 ? trigrams[i] : reverseString(trigrams[i]));
                rawLineData.add(data);
            }
            trigramLineData.add(rawLineData);
        }
    }

    public String getRawEyeData() {
        return convertToString(eyeLineData).replaceAll("(.{1})", "$1,").replaceAll(",$","");
    }

    public String getRawTrigramData() {
        StringBuilder builder = new StringBuilder();
        for (ArrayList<Trigram> line : trigramLineData) {
            line.forEach((t -> {builder.append(t.getTrigramString(TrigramView.selectedMode) + ",");}));
            builder.append("\n");
        }

        return builder.substring(0, builder.length()-2);
    }

    public String getTrigramValues() {
        StringBuilder builder = new StringBuilder();
        for (ArrayList<Trigram> line : trigramLineData) {
            line.forEach((t -> {builder.append(t.getTrigramValue(TrigramView.selectedMode) + ",");}));
            builder.append("\n");
        }

        return builder.substring(0, builder.length()-2);
    }

    private String convertToString(ArrayList<ArrayList<Character>> list) {
        StringBuilder sb = new StringBuilder();

        for (ArrayList<Character> innerList : list) {
            for (Character c : innerList) {
                sb.append(c);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String reverseString(String value){
        if (value == null || value.isEmpty()) {
            return value;
        }

        char[] characters = value.toCharArray();
        int left = 0;
        int right = characters.length - 1;

        while (left < right) {
            // Swap characters at left and right indices
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;

            // Move the indices inward
            left++;
            right--;
        }

        return new String(characters);
    }
    
    public static String cycleString(String input, int cycles) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int length = input.length();
        cycles = cycles % length; // handle cases where cycles is greater than the length of the string

        if (cycles < 0) {
            cycles = length + cycles; // handle negative cycles
        }

        // Perform the cycle operation
        String rotated = input.substring(length - cycles) + input.substring(0, length - cycles);

        return rotated;
    }

    public String getName() {
        return message;
    }
}

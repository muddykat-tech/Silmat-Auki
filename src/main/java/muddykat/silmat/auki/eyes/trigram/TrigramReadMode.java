package muddykat.silmat.auki.eyes.trigram;

public enum TrigramReadMode {
    abc, acb, bac, bca, cab, cba;

    public String printData(char a, char b, char c) {
        return this.name().replace('a', a).replace('b', b).replace('c', c);
    }

    public String calculateValue(char a, char b, char c) {
        String data = printData(a,b,c);
        return Integer.toString(Integer.parseInt(data, 5), 10);
    }

    // Converts the input back into a read-mode of ABC
    public String convertToRaw(char a1, char a2, char a3) {
        char[] chars = new char[3];
        chars[name().indexOf('a')] = a1; // 2 // index 1
        chars[name().indexOf('b')] = a2; // 3 // index 3
        chars[name().indexOf('c')] = a3; // 1 // index 2

        return new String(chars);
    }
}

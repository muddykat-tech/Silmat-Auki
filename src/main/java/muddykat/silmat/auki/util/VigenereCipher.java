package muddykat.silmat.auki.util;

public class VigenereCipher {
    public static String encrypt(String message, String sKey){
        char msg[] = message.toCharArray();
        char key[] = sKey.toCharArray();

        int msgLen = msg.length, i, j;
        char newKey[] = new char[msgLen];
        char encryptedMsg[] = new char[msgLen];

        //generate new key in cyclic manner equal to the length of original message
        for(i = 0, j = 0; i < msgLen; ++i, ++j){
            if(j == key.length)
                j = 0;
            newKey[i] = key[j];
        }

        //encryption
        for(i = 0; i < msgLen; ++i)
            encryptedMsg[i] = (char)(((msg[i] + newKey[i]) % 26) + 'A');

        return String.valueOf(encryptedMsg);
    }

    public static String decrypt(String message, String sKey){
        char msg[] = message.toCharArray();
        char key[] = sKey.toCharArray();

        int msgLen = msg.length, i, j;
        char newKey[] = new char[msgLen];
        char decryptedMsg[] = new char[msgLen];

        //generate new key in cyclic manner equal to the length of original message
        for(i = 0, j = 0; i < msgLen; ++i, ++j){
            if(j == key.length)
                j = 0;
            newKey[i] = key[j];
        }

        for(i = 0; i < msgLen; ++i)
            decryptedMsg[i] = (char)((((msg[i] - newKey[i]) + 26) % 26) + 'A');

        return String.valueOf(decryptedMsg);
    }

}

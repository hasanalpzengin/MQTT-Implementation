
package message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Encoder {
    
    private static String stringToHexString(String s) throws UnsupportedEncodingException{
        String in = String.format("%040x", new BigInteger(1, s.getBytes("UTF-8")));
        String[] bytes = in.split("(?<=\\G..)");
        StringBuilder sBuilder = new StringBuilder();
        
        for(int i=0; i<bytes.length; i++){
            if(!bytes[i].contentEquals("00")){
                sBuilder.append(bytes[i]);
            }
        }
        return sBuilder.toString();
    }
    
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < data.length; i++) {
            int index = i * 2;
            int value = Integer.parseInt(s.substring(index, index + 2), 16);
            data[i] = (byte) value;
        }
        return data;
    }
    
    public static byte[] encode(String s) throws UnsupportedEncodingException{
        return s.getBytes(Charset.forName("UTF-8"));
    }
    
    
}

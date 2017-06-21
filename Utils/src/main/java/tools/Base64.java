package tools;


public class Base64
{

    /**
     * decode a Base 64 encoded String.
     * <p>
     * <h4>String to byte conversion</h4>
     * This method uses a naive String to byte interpretation, it simply gets
     * each char of the String and calls it a byte.
     * </p>
     * <p>
     * Since we should be dealing with Base64 encoded Strings that is a
     * reasonable assumption.
     * </p>
     * <p>
     * <h4>End of data</h4>
     * We dont try to stop the converion when we find the"="end of data padding
     * char. We simply add zero bytes to the unencode buffer.
     * </p>
     */
    public static String decode(String encoded)
    {

        StringBuffer sb = new StringBuffer();
        int maxturns;
        // work out how long to loop for.
        if (encoded.length() % 3 == 0)
            maxturns = encoded.length();
        else
            maxturns = encoded.length() + (3 - (encoded.length() % 3));
        // tells us whether to include the char in the unencode
        boolean skip;
        // the unencode buffer
        byte[] unenc = new byte[4];
        byte b;
        for (int i = 0, j = 0; i < maxturns; i++)
        {
            skip = false;
            // get the byte to convert or 0
            if (i < encoded.length())
                b = (byte) encoded.charAt(i);
            else
                b = 0;
            // test and convert first capital letters, lowercase, digits then +
            // and /
            if (b >= 65 && b < 91)
                unenc[j] = (byte) (b - 65);
            else
                if (b >= 97 && b < 123)
                    unenc[j] = (byte) (b - 71);
                else
                    if (b >= 48 && b < 58)
                        unenc[j] = (byte) (b + 4);
                    else
                        if (b == '+')
                            unenc[j] = 62;
                        else
                            if (b == '/')
                                unenc[j] = 63;
                            else
                                if (b == '=')
                                    unenc[j] = 0;
                                else
                                {

                                    char c = (char) b;
                                    if (c == '\n' || c == '\r' || c == ' ' || c == '\t')
                                        skip = true;
                                    else
                                        // could throw an exception here? its
                                        // input we dont
                                        // understand.
                                        ;
                                }
            // once the array has boiled convert the bytes back into chars
            if (!skip && ++j == 4)
            {
                // shift the 6 bit bytes into a single 4 octet word
                int res = (unenc[0] << 18) + (unenc[1] << 12) + (unenc[2] << 6) + unenc[3];
                byte c;
                int k = 16;
                // shift each octet down to read it as char and add to
                // StringBuffer
                while (k >= 0)
                {
                    c = (byte) (res >> k);
                    if (c > 0)
                        sb.append((char) c);
                    k -= 8;
                }
                // reset j and the unencode buffer
                j = 0;
                unenc[0] = 0;
                unenc[1] = 0;
                unenc[2] = 0;
                unenc[3] = 0;
            }
        }
        return sb.toString();
    }

    /**
     * encode plaintext data to a base 64 string
     * 
     * @param plain
     *            the text to convert. If plain is longer than 76 characters
     *            this method returns null (see RFC2045).
     * @return the encoded text (or null if string was longer than 76 chars).
     */
    public static String encode(String plain)
    {
        if (plain.length() > 76)
            return null;

        StringBuffer sb = new StringBuffer();
        // the encode buffer
        byte[] enc = new byte[3];
        boolean end = false;
        for (int i = 0, j = 0; !end; i++)
        {
            // char _ch = plain.charAt(i);
            if (i == plain.length() - 1)
                end = true;
            enc[j++] = (byte) plain.charAt(i);
            if (j == 3 || end)
            {
                int res;
                // this is a bit inefficient at the end point
                // worth it for the small decrease in code size?
                res = (enc[0] << 16) + (enc[1] << 8) + enc[2];
                int b;
                int lowestbit = 18 - (j * 6);
                for (int toshift = 18; toshift >= lowestbit; toshift -= 6)
                {
                    b = res >>> toshift;
                    b &= 63;
                    if (b >= 0 && b < 26)
                        sb.append((char) (b + 65));
                    if (b >= 26 && b < 52)
                        sb.append((char) (b + 71));
                    if (b >= 52 && b < 62)
                        sb.append((char) (b - 4));
                    if (b == 62)
                        sb.append("+");
                    if (b == 63)
                        sb.append("/");
                    if (sb.length() % 76 == 0)
                        sb.append("\n");
                }
                // now set the end chars to be pad character if there
                // was less than integral input (ie: less than 24 bits)
                if (end)
                {
                    if (j == 1)
                        sb.append("==");
                    if (j == 2)
                        sb.append("=");
                }
                enc[0] = 0;
                enc[1] = 0;
                enc[2] = 0;
                j = 0;
            }
        }
        return sb.toString();
    }
}

import parcs.*;

import java.util.ArrayList;
import java.util.List;

public class KMP implements AM {


    public Result search(String text, String pattern) {
        Result result = new Result();

        if (pattern == null || pattern.length() == 0)
        {
            System.out.println("Pattern occurs with zero shift");
            return result;
        }

        if (text == null || pattern.length() > text.length())
        {
            System.out.println("Pattern not found");
            return result;
        }

        char[] chars = pattern.toCharArray();

        int[] next = new int[pattern.length() + 1];
        for (int i = 1; i < pattern.length(); i++)
        {
            int j = next[i + 1];

            while (j > 0 && chars[j] != chars[i])
                j = next[j];

            if (j > 0 || chars[j] == chars[i])
                next[i + 1] = j + 1;
        }

        for (int i = 0, j = 0; i < text.length(); i++)
        {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j))
            {
                if (++j == pattern.length())
                {
                    System.out.println("Pattern occurs with shift " +
                            (i - j + 1));
                    result.addIndex(i - j + 1);
                }
            }
            else if (j > 0)
            {
                j = next[j];
                i--;
            }
        }

        return result;
    }

    public void run(AMInfo info) {
        Input input = (Input) info.parent.readObject();
        String text = input.getText();
        String pattern = input.getPattern();

        //System.out.println("Input : text = " + text + ", pattern = " + pattern);

        info.parent.write(search(text, pattern));
    }
}

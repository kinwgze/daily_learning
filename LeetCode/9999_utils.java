package leetcode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kinwgze
 */
public class ForGitHub {
    /**
     * 中文匹配正则表达式
     */
    static final String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static void main(String[] args) {

        String s = "0058.Length of Last Word 最后一个单词的长度";
        String res;

        // md标题处理及输出

        res = s.replaceAll(REGEX_CHINESE, "").replace(" ", "_")
                .replace(".", "_") + ".md";
        System.out.println("\n" + "Markdown文件标题：" + res.replace("_.", ".") + "\n");

        // 时间及commit信息处理及输出
        Date now = new Date();
        SimpleDateFormat  time = new SimpleDateFormat("yyyy/MM/dd");
        String titleTime = time.format(now);
        String[] strs = s.split(" ");
        String[] strs2 = s.split("\\.");
        System.out.println("commit信息：" + titleTime + " " + strs2[0] + "." + strs[strs.length - 1] + "\n ");

    }
}

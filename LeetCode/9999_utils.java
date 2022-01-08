package leetcode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ForGitHub {
    public static void main(String[] args) {

        String s = null;
        String res;

        // 接受输入
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            s = sc.nextLine();
        }

        // md标题处理及输出
        res = s.replace(" ", "_");
        res = res.replace(".", "_");
        res = res +".md";
        System.out.println("\n" + "Markdown文件标题：" + res + "\n");

        // 时间及commit信息处理及输出
        Date now = new Date();
        SimpleDateFormat  time = new SimpleDateFormat("yyyy/MM/dd");
        String titleTime = time.format(now);
        String[] strs2 = s.split("\\.");
        System.out.println("commit信息：" + titleTime + " " + strs2[0] + "." + strs[strs.length - 1] + "\n ");

    }
}

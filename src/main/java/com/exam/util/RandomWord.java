package com.exam.util;

/**
 * 1.2、随机生成1到40位的随机字符串，
 * 要求如下：
 * 由a-zA-Z字母组成，
 * 只有第一位必须为大写其他均小写。
 * 举例：Lucy、Fei、A、Pjskdfiesdfjksd； A-Z 65-90 a-z 97-122
 *
 * @return String
 */
public class RandomWord {

    public static StringBuffer randomWord() {
        StringBuffer word = new StringBuffer();

        //大于等于 0.0 且小于 1.0 的伪随机 double 值， A-Z
        int FistWord = (65 + (int) (26 * Math.random()));
        word.append((char) FistWord);

        //位数 0-39 小写
        int number = (int) (40 * Math.random());
        for (int a = 0; a < number; a++) {
            //append a-z 0-39随机
            word.append((char) (97 + (int) (26 * Math.random())));
        }
        return word;
    }
}

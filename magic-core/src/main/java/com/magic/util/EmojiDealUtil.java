package com.magic.util;

import com.vdurmont.emoji.EmojiParser;

import java.util.List;

/**
 * emoji表情处理工具类
 */
public class EmojiDealUtil extends EmojiParser {
    /**
     * 获取非表情字符串
     * @param input
     * @return
     */
    public static String getNonEmojiString(String input) {
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        List<UnicodeCandidate> replacements = getUnicodeCandidates(input);
        for (UnicodeCandidate candidate : replacements) {
            sb.append(input.substring(prev, candidate.getEmojiStartIndex()));
            prev = candidate.getFitzpatrickEndIndex();
        }
        return sb.append(input.substring(prev)).toString();
    }

    /**
     * 获取表情字符串
     * @param input
     * @return
     */
    public static String getEmojiUnicodeString(String input){
        EmojiTransformer  transformer = new EmojiTransformer() {
            public String transform(UnicodeCandidate unicodeCandidate) {
                return unicodeCandidate.getEmoji().getHtmlHexadecimal();
            }
        };
        StringBuilder sb = new StringBuilder();
        List<UnicodeCandidate> replacements = getUnicodeCandidates(input);
        for (UnicodeCandidate candidate : replacements) {
            sb.append(transformer.transform(candidate));
        }
        return  parseToUnicode(sb.toString());
    }

    public static String getUnicode(String source){
        String returnUniCode=null;
        String uniCodeTemp=null;
        for(int i=0;i<source.length();i++){
            uniCodeTemp = "\\u"+Integer.toHexString((int)source.charAt(i));
            returnUniCode=returnUniCode==null?uniCodeTemp:returnUniCode+uniCodeTemp;
        }
        return returnUniCode;
    }
//
//    /**
//     * 判断是否包含emoji表情
//     * @param source 原字符串
//     * @return 是否包含emoji表情
//     */
//    public static boolean checkContainEmoji(String source) {
//        if(source!=null && source.length()>0){
//            return	source.matches("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]");
//        }else{
//            return false;
//        }
//    }
//
//    /**
//     * 将字符串中的emoji表情顾虑掉
//     * @param source 原字符串
//     * @param slipStr emoji表情替换成的字符串
//     * @return 过滤后的字符串
//     */
//    public static String filterEmoji(String source,String slipStr) {
//        if(source!=null && source.length()>0){
//            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
//        }else{
//            return source;
//        }
//    }
}

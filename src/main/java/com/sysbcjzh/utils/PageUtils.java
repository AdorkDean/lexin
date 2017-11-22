package com.sysbcjzh.utils;

public class PageUtils {
    public static String ajaxPage(int total, int page, int limit, String JsMethod) {
        StringBuffer sb = new StringBuffer();
        int count = total / limit;
        if (total % limit > 0) {
            count++;
        }
        if (page == 1)
            sb.append("<span class=\"disabled\">首页</span><span class=\"disabled\">上一页</span>");
        else {
            sb.append("<a href=\"javascript:;\" onclick=\"" + JsMethod + "(1)\">首页</a><a href=\"javascript:;\" onclick=\"" + JsMethod + "(" + (page - 1) + ")\">上一页</a>");
        }
        int sta = page - 3 <= 0 ? 1 : page - 3;
        int end = page + 3 >= count ? count : page + 3;
        for (int i = sta; i <= end; i++) {
            if (i == page)
                sb.append("<span class=\"current\">" + i + "</span>");
            else {
                sb.append("<a href=\"javascript:;\" onclick=\"" + JsMethod + "(" + i + ")\">" + i + "</a>");
            }
        }
        if (page >= count)
            sb.append("<span class=\"disabled\">下一页</span><span class=\"disabled\">尾页</span>");
        else {
            sb.append("<a href=\"javascript:;\" onclick=\"" + JsMethod + "(" + (page + 1) + ")\">下一页</a><a href=\"javascript:;\" onclick=\"" + JsMethod + "(" + count + ")\">尾页</a>");
        }
        sb.append(" 页次：<span style=\"color:red\">" + page + "</span>/" + count + " 记录：" + total + "条 ");
        return sb.toString();
    }

    public static String Page163(int total, int page, int limit, String Url) {
        StringBuffer sb = new StringBuffer();
        int count = total / limit;
        if (total % limit > 0) {
            count++;
        }
        if (count > 1) {
            sb.append("<div class=\"splitPages2\">");
            if (page == 1)
                sb.append("<span class=\"jumptoTip\">首页 </span><span class=\"prevPage\">上一页<i></i></span>");
            else {
                sb.append("<a href=\"" + Url + "p=1\">首页</a><a href=\"" + Url + "p=" + (page - 1) + "\" class=\"prevPage\">上一页<i></i></a>");
            }
            int sta = page - 3 <= 0 ? 1 : page - 3;
            int end = page + 3 >= count ? count : page + 3;
            for (int i = sta; i <= end; i++) {
                if (i == page)
                    sb.append("<span>" + i + "</span>");
                else {
                    sb.append("<a href=\"" + Url + "p=" + i + "\">" + i + "</a>");
                }
            }
            if (page >= count)
                sb.append("<span class=\"nextPage\">下一页<i></i></span><span class=\"jumptoTip\">尾页</span>");
            else {
                sb.append("<a href=\"" + Url + "p=" + (page + 1) + "\" class=\"nextPage\">下一页<i></i></a><a href=\"" + Url + "p=" + count + "\">尾页</a>");
            }
            sb.append("<span class=\"jumptoTip\">&nbsp;&nbsp;页次：<c style=\"color:red\">" + page + "</c>/" + count + "&nbsp;共：" + total + "条</span></div>");
        }
        return sb.toString();
    }

    public static String ajaxPage163(int total, int page, int limit, String JsMethod) {
        int count = total / limit;
        if (total % limit > 0) {
            count++;
        }
        String result = "";
        if (count > 1) {
            StringBuffer sb = new StringBuffer();
            sb.append("<div class=\"splitPages2\">");
            if (page == 1)
                sb.append("<span class=\"jumptoTip\">首页 </span><span class=\"prevPage\">上一页<i></i></span>");
            else {
                sb.append("<a href=\"javascript:;\" onclick=\"" + JsMethod + "(1)\">首页</a><a href=\"javascript:;\" class=\"prevPage\" onclick=\"" + JsMethod + "(" + (page - 1) + ")\">上一页<i></i></a>");
            }
            int sta = page - 3 <= 0 ? 1 : page - 3;
            int end = page + 3 >= count ? count : page + 3;
            for (int i = sta; i <= end; i++) {
                if (i == page)
                    sb.append("<span>" + i + "</span>");
                else {
                    sb.append("<a href=\"javascript:;\" onclick=\"" + JsMethod + "(" + i + ")\">" + i + "</a>");
                }
            }
            if (page >= count)
                sb.append("<span class=\"nextPage\">下一页<i></i></span><span class=\"jumptoTip\">尾页</span>");
            else {
                sb.append("<a href=\"javascript:;\" class=\"nextPage\" onclick=\"" + JsMethod + "(" + (page + 1) + ")\">下一页<i></i></a><a href=\"javascript:;\" onclick=\"" + JsMethod + "(" + count + ")\">尾页</a>");
            }
            sb.append("<span class=\"jumptoTip\">&nbsp;&nbsp;页次：<c style=\"color:red\">" + page + "</c>/" + count + "&nbsp;共：" + total + "条</span></div>");
            result = sb.toString();
        }
        return result;
    }

    public static String Page(int total, int page, int limit, String Url) {
        StringBuffer sb = new StringBuffer();
        int count = total / limit;
        if (total % limit > 0) {
            count++;
        }
        if (count > 1) {
            if (page == 1)
                sb.append("<span class=\"disabled\">首页</span><span class=\"disabled\">上一页</span>");
            else {
                sb.append("<a href=\"" + Url + "p=1\">首页</a><a href=\"" + Url + "p=" + (page - 1) + "\">上一页</a>");
            }
            int sta = page - 3 <= 0 ? 1 : page - 3;
            int end = page + 3 >= count ? count : page + 3;
            for (int i = sta; i <= end; i++) {
                if (i == page)
                    sb.append("<span class=\"current\">" + i + "</span>");
                else {
                    sb.append("<a href=\"" + Url + "p=" + i + "\">" + i + "</a>");
                }
            }
            if (page >= count)
                sb.append("<span class=\"disabled\">下一页</span><span class=\"disabled\">尾页</span>");
            else {
                sb.append("<a href=\"" + Url + "p=" + (page + 1) + "\">下一页</a><a href=\"" + Url + "p=" + count + "\">尾页</a>");
            }
            sb.append(" 页次：<span style=\"color:red\">" + page + "</span>/" + count + " 记录：" + total + "条 ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ajaxPage = ajaxPage(100, 3, 10, "js");
        System.out.println(ajaxPage);
    }
}
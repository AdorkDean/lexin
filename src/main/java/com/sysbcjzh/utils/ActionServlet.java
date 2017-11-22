//    ActionServlet.java

package com.sysbcjzh.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.sysbcjzh.utils:
//			XSSRequestWrapper

public final class ActionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final HashMap methods = new HashMap();
    private static final HashMap actions = new HashMap();

    public ActionServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        try {
            inits(req.getRequestURI(), "Get", new XSSRequestWrapper(req), resp);
        } catch (Exception exception) {
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        try {
            inits(req.getRequestURI(), "Post", new XSSRequestWrapper(req), resp);
        } catch (Exception exception) {
        }
    }

    protected Object _LoadAction(String act_name)
            throws InstantiationException, IllegalAccessException {
        Object action = actions.get(act_name);
        if (action == null)
            try {
                int indexOf = act_name.lastIndexOf("/");
                String pack = "/";
                String name = act_name;
                if (indexOf > 0) {
                    pack = act_name.substring(0, indexOf);
                    name = act_name.substring(indexOf + 1);
                }
                action = Class.forName((new StringBuilder(String.valueOf(getInitParameter(pack)))).append(".").append(name).toString()).newInstance();
                if (!actions.containsKey(act_name))
                    synchronized (actions) {
                        actions.put(act_name, action);
                    }
            } catch (Exception exception) {
            }
        return action;
    }

    private Method _GetActionMethod(Object action, String method) {
        String key = (new StringBuilder(String.valueOf(action.getClass().getName().replace("com.caipiao.servlet.", "")))).append('.').append(method).toString();
        Method m = (Method) methods.get(key);
        if (m == null) {
            Method amethod[];
            int j = (amethod = action.getClass().getMethods()).length;
            for (int i = 0; i < j; i++) {
                Method m1 = amethod[i];
                if (m1.getModifiers() == 1 && m1.getName().equals(method)) {
                    synchronized (methods) {
                        methods.put(key, m1);
                    }
                    m = m1;
                }
            }

        }
        return m;
    }

    protected void inits(String url, String type, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String method = null;
        Matcher ma = Pattern.compile("[^/].+.jzh").matcher(url);
        if (ma.find())
            method = ma.group().replace(".jzh", "");
        if (method != null) {
            String split[] = method.split("!");
            if (split.length == 2)
                method = (new StringBuilder(String.valueOf(split[1]))).append(type).toString();
            else
                method = (new StringBuilder("do")).append(type).toString();
            Object loadAction = _LoadAction(split[0]);
            Method getActionMethod = _GetActionMethod(loadAction, method);
            getActionMethod.invoke(loadAction, new Object[]{
                    req, resp
            });
        } else {
            resp.sendRedirect(getInitParameter("404"));
        }
    }

    public void init()
            throws ServletException {
    }

    public void destroy() {
        if (actions != null)
            try {
                Method dm = actions.getClass().getMethod("destroy", new Class[0]);
                if (dm != null)
                    dm.invoke(actions, new Object[0]);
            } catch (Exception exception) {
            }
        super.destroy();
    }

}

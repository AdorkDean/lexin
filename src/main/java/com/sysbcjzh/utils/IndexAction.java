//    IndexAction.java

package com.sysbcjzh.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class IndexAction extends HttpServlet {

    public IndexAction() {
    }

    public abstract void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException;

    public abstract void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException;

    public void destroy() {
        super.destroy();
    }
}

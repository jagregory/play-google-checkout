package models;

import play.mvc.Http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class PlayHttpServletResponse implements HttpServletResponse {
    public PlayHttpServletResponse(Http.Response response) {

    }

    public void addCookie(Cookie cookie) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean containsHeader(String s) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String encodeURL(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String encodeRedirectURL(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String encodeUrl(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String encodeRedirectUrl(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void sendError(int i, String s) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void sendError(int i) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void sendRedirect(String s) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setDateHeader(String s, long l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addDateHeader(String s, long l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setHeader(String s, String s1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addHeader(String s, String s1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setIntHeader(String s, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addIntHeader(String s, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setStatus(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setStatus(int i, String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getCharacterEncoding() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getContentType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PrintWriter getWriter() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setCharacterEncoding(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setContentLength(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setContentType(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBufferSize(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getBufferSize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void flushBuffer() throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void resetBuffer() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isCommitted() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void reset() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLocale(Locale locale) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Locale getLocale() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

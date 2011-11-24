/**
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Felipe Oliveira (http://mashup.fm)
 *
 */
package models;

import play.mvc.Http;
import play.mvc.Http.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * The Class PlayHttpServletRequest.
 */
public class PlayHttpServletRequest implements HttpServletRequest {

	/** The req. */
	private Request req;

	/**
	 * Instantiates a new play http servlet request.
	 *
	 * @param request
	 *            the request
	 */
	public PlayHttpServletRequest(Request request) {
		req = request;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		return null; // throw new RuntimeException("Method not implemented!");
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	public Enumeration getAttributeNames() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String env)
			throws UnsupportedEncodingException {
		// return this.req.contentType ; // throw new
		// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		// return this.req.con; // throw new
		// RuntimeException("Method not implemented!");
		return -1;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	public String getContentType() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	public String getParameter(String name) {
		return req.params.get(name);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	public Enumeration getParameterNames() {
		return null; // throw new RuntimeException("Method not implemented!");
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String name) {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	public Map getParameterMap() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	public String getScheme() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	public String getServerName() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return req.port; // throw new
							// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String name, Object o) {
		req.params.put(name, String.valueOf(o)); // throw new
													// RuntimeException("Method not implemented!");
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String name) {
		req.params.remove(name); // throw new
									// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	public Enumeration getLocales() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		return req.secure;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	public RequestDispatcher getRequestDispatcher(String path) {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 */
	public String getRealPath(String path) {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getRemotePort()
	 */
	public int getRemotePort() {
		return req.port; // throw new
							// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	public String getLocalName() {
		return req.domain;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getLocalAddr()
	 */
	public String getLocalAddr() {
		return req.host;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletRequest#getLocalPort()
	 */
	public int getLocalPort() {
		return req.port;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return null;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String name) {
		return -1; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	public String getHeader(String name) {
        Http.Header header = req.headers.get(name.toLowerCase());
        
        if (header == null) {
            return null;
        }
        
        return header.value();
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	public Enumeration getHeaders(String name) {
		return null;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	public Enumeration getHeaderNames() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	public int getIntHeader(String name) {
		return req.params.get(name, Integer.class); // throw new
													// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		return req.method;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		return req.path; // throw new
							// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return req.path; // throw new
							// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return req.querystring;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return req.user; // throw new
							// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String role) {
		return false; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return req.url;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return new StringBuffer(req.url);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return req.path; // throw new
							// RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean create) {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public HttpSession getSession() {
		return null; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return false; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return false; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return false; // throw new RuntimeException("Method not implemented!");

	}

	/**
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return false; // throw new RuntimeException("Method not implemented!");

	}

}
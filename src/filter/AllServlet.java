package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class AllServlet
 */
@WebFilter("/*")
public class AllServlet implements Filter {

    public AllServlet() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		String encode ="UTF-8";
		request.setCharacterEncoding(encode);
        //System.out.println(System.getProperty("file.encoding"));その時環境の文字コードを取得
        //↑init()直後の処理
		chain.doFilter(request, response);
		//↓destroy()直前の処理
		response.setCharacterEncoding(encode);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

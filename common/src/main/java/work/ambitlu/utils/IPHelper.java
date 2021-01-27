package work.ambitlu.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * IP帮助工具
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:31
 */
public class IPHelper {
	private static final String UNKNOWN = "unknown";

	/**
	 * 得到用户的真实地址,如果有多个就取第一个
	 *
	 * @return
	 */
	public static String getIpAddr() {
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		if (request == null) {
			return null;
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] ips = ip.split(",");
		return ips[0].trim();
	}


}
package io.jonccrawley.resolver.exception;

public class HTTPStatusException extends Exception {

	public enum HTTPStatusCode {
		OK(200, "OK"),
		NO_CONTENT(204, "No Content"),
		FOUND(302, "Found"),
		SEE_OTHER(303, "See Other"),
		BAD_REQUEST(400, "Bad Request"),
		UNAUTHORIZED(401, "Unauthorized"),
		FORBIDDEN(403, "Forbidden"),
		NOT_FOUND(404, "Not Found"),
		NOT_ACCEPTABLE(406, "Not Acceptable"),
		REQUEST_TIMEOUT(408, "Request Timeout"),
		GONE(410, "Gone"),
		PRECONDITION_FAILED(412, "Precondition Failed"),
		REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
		EXPECTATION_FAILED(417, "Expectation Failed"),
		AUTHENTICATION_TIMEOUT(419, "Authentication Timeout"),
		PRECONDITION_REQUIRED(428, "Precondition Required"),
		REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
		INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
		NOT_IMPLEMENTED(501, "Not Implemented"),
		SERVICE_UNAVAILABLE(503, "Service Unavailable"),
		GATEWAY_TIMEOUT(504, "Gateway Timeout"),
		NOT_EXTENDED(510, "Not Extended");

		private int code;
		private String reason;

		HTTPStatusCode(int code, String reason) {

			this.code = code;
			this.reason = reason;
		}

		public int getCode() {

			return code;
		}

		public String getReason() {

			return reason;
		}
	}

	public HTTPStatusCode getHTTPStatusCode() {

		return httpsStatusCode;
	}

	private final HTTPStatusCode httpsStatusCode;

	public HTTPStatusException(HTTPStatusCode httpsStatusCode) {

		this.httpsStatusCode = httpsStatusCode;
	}

	public HTTPStatusException(HTTPStatusCode httpsStatusCode, String message) {

		super(message);
		this.httpsStatusCode = httpsStatusCode;
	}

	public HTTPStatusException(HTTPStatusCode httpsStatusCode, Throwable throwable) {

		super(throwable);
		this.httpsStatusCode = httpsStatusCode;
	}

	public int getCode() {

		return httpsStatusCode.getCode();
	}

	public String getReason() {

		return httpsStatusCode.getReason();
	}
}
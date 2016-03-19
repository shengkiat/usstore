package sg.edu.nus.iss.uss.exception;

import sg.edu.nus.iss.uss.exception.ErrorConstants.UssCode;

public class UssException extends Exception {

	private static final long serialVersionUID = -3360812732250813553L;
	
	public UssException(UssCode code, Exception e) {
		super(e);
		this.code = code;
	}

	public UssException(UssCode code, String message) {
		super(message);
		this.code = code;
	}

	private UssCode code;

	public UssCode getCode() {
		return code;
	}

	public void setCode(UssCode code) {
		this.code = code;
	}

}
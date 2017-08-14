package com.alacriti.elm.constants;

public final class Constants {

	public static String PATH_KEY_STORE;
	public static String PATH_KEY_STORE_CREDENTIALS;
	public static String PATH_FREEMARKER_TEMPLATE;
	public static String PATH_CONFIG_FILE;
	public static final String FREEMARKER_TEMPLATE_INIT_PARAM_NAME = "template-path";
	public static final String CONFIG_FILE_INIT_PARAM_NAME = "config-file-path";
	public static final String KEYSTORE_PATH = "keystore_path";
	/*
	 * Time mentioned in hh:mm:ss format.
	 * 1:00 refers to hour.
	 */
	public static final String TOKEN_EXPIRY_DURATION_IN_SEC = "1:00";	
	public static final String REJECT_FORM_TEMPLATE = "rejectForm.ftl";
	public static final String NA_STRING = "N/A";
	public static final String FORWARD_SLASH = "/";
	public static final String EMPTY_STRING = "";
	public static final String UNDERSCORE = "_";
	public static final String HYPHEN = "-";
	public static final String DOT = ".";
	public static final String PIPE = "|";
	public static final String COLON = ":";
	public static final String SEMICOLON = ";";
	public static final String COMMA = ",";
	public static final String NEW_LINE = "\n";
	public static final String QUESTION_MARK = "?";
	public static final String IS_EQUAL_TO = "=";
	public static final double DEFAULT_DOUBLE = 0;
	public static final int DEFAULT_INT = 0;
	public static final long DEFAULT_LONG = 0;
	public static final short DEFAULT_SHORT = 0;
	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";

	public final static String SOURCE_TYPE_WITH_TOKEN = "token";
	public final static String SOURCE_TYPE_WITH_OUT_TOKEN = "direct";

	public final static String TXN_TYPE_WITH_BANK = "bank";
	public final static String TXN_TYPE_WITH_CARD = "card";

	public final static String CHARGE_STATUS_VERIFIED = "verified";
	public final static String CHARGE_STATUS_CONFIRMED = "confirmed";

	public final static String TXN_STATUS_INITIATED = "initiated";
	public final static String TXN_STATUS_PROCESSED = "processed";
	public final static String TXN_STATUS_FAILED = "failed";
	public final static String TXN_STATUS_SCHEDULED = "scheduled";
	public final static String TXN_STATUS_PURGED = "purged";
	public final static String TXN_STATUS_CANCELLED = "cancelled";

	public final static int HTTP_STATUS_CODE_OK = 200;
	public final static int HTTP_STATUS_CODE_BAD_REQUEST = 400;
	public final static int HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;

	public final static String BOOT_CNFG="BOOT_CNFG";
	public final static String LOG4J_CNFG="LOG4J_CNFG";
	public final static String REST_CNFG="REST_CNFG";
	public final static String ACCOUNT_TYPE_CARD = "card";
	public final static String ACCOUNT_TYPE_BANK = "bank";

}

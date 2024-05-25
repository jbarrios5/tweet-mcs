package py.com.mcs.tweet.bean.interceptor;

public class TraceContextHolder {
    private static String logId;

    public static String getLogId() {
        return logId;
    }

    public static void setLogId(String traceId) {
        TraceContextHolder.logId = traceId;
    }
}
package common.constant;
// Author: anhnv

public class FPConstant {

    public static class AccountRole {
        public static final String ACCOUNT_ROLE_STAFF = "STAFF";
        public static final String ACCOUNT_ROLE_CUSTOMER = "CUSTOMER";
        public static final String ACCOUNT_ROLE_ADMIN = "ADMIN";
    }

    public static class Url {
        public static final String BASE_WEB_URL = "http://localhost:8080/football_pitch/";
        public static final String BASE_URL_API_ACCOUNT = "http://localhost:8080/football_pitch/api/accounts/";
        public static final String BASE_URL_API_PITCH = "http://localhost:8080/football_pitch/api/pitchs/";
        public static final String BASE_URL_API_TYPE = "http://localhost:8080/football_pitch/api/types/";
        public static final String BASE_URL_API_SERVICE = "http://localhost:8080/football_pitch/api/services/";
        public static final String BASE_URL_API_BOOKING_BILL = "http://localhost:8080/football_pitch/api/booking_bills/";
        public static final String BASE_URL_API_SERVICE_BILL = "http://localhost:8080/football_pitch/api/service_bills/";
    }

    public static class BookingStatus {
        public static final String BOOKED = "BOOKED";
        public static final String CHECKED_IN = "CHECKED_IN";
        public static final String CHECKED_OUT = "CHECKED_OUT";
        public static final String CANCELLED = "CANCELLED";

    }

    public static class TypeId {
        public static final String TYPE_5 = "1";
        public static final String TYPE_7 = "2";
        public static final String TYPE_11 = "3";
    }

}
